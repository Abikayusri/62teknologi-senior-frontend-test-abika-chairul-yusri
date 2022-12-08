package abika.sinau.myappenamdua.presentation.home

import abika.sinau.core.data.source.Resource
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import abika.sinau.core.domain.model.BusinessSearchDomain
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.toastShort
import abika.sinau.core.utils.visible
import abika.sinau.myappenamdua.databinding.ActivityHomeBinding
import abika.sinau.myappenamdua.presentation.detail.DetailActivity
import abika.sinau.myappenamdua.presentation.home.adapter.SearchAdapter
import abika.sinau.myappenamdua.presentation.home.filter.FilterResultDialog
import android.view.LayoutInflater
import android.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseViewModelActivity<HomeViewModel, ActivityHomeBinding>() {
    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override val viewModelClass: Class<HomeViewModel>
        get() = HomeViewModel::class.java

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: HomeViewModel) {
        viewModel.resultBusinessSearch.observe(lifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    val data = response.data
                    binding.apply {
                        setupFilter()
                        if (data?.total == 0 || data?.businesses.isNullOrEmpty()) {
                            cgEmptyState.visible()
                            cgFoundedSearch.gone()
                        } else {
                            cgEmptyState.gone()
                            cgFoundedSearch.visible()
                            setupRecycler(data)
                        }
                        pbLoading.gone()
                    }
                }
                is Resource.Error -> {
                    binding.apply {
                        cgEmptyState.visible()
                        cgFoundedSearch.gone()
                        pbLoading.gone()
                    }
                    toastShort("Data Tidak Ditemukan!")
                }
                is Resource.Loading -> {
                    binding.apply {
                        pbLoading.visible()
                        cgFoundedSearch.gone()
                    }
                }
            }
        }
    }

    private fun setupFilter() {
        binding.apply {
            inclLimitPageFilter.tvTitle.text = "Limit Data"
            inclLimitPageFilter.root.setOnClickListener {
                FilterResultDialog(
                    filterItem = listOf("10 Data", "20 Data", "50 Data", "100 Data", "Semua Data"),
                    filterType = FilterResultDialog.LIMIT_PAGE,
                    listener = {
                        toastShort("Datanya: ${viewModel.limitFilter}")
                        actionSearch(
                            location = viewModel.location,
                            price = viewModel.priceFilter,
                            limit = viewModel.limitFilter?.toInt()
                        )
                    }
                ).show(supportFragmentManager, FilterResultDialog::class.java.canonicalName)
            }

            inclPriceFilter.tvTitle.text = "Harga"
            inclPriceFilter.root.setOnClickListener {
                FilterResultDialog(
                    filterItem = listOf("$", "$$", "$$$", "$$$$"),
                    filterType = FilterResultDialog.PRICE,
                    listener = {
                        toastShort("Datanya: ${viewModel.priceFilter}")
                        actionSearch(
                            location = viewModel.location,
                            price = viewModel.priceFilter,
                            limit = viewModel.limitFilter?.toInt()
                        )
                    }
                ).show(supportFragmentManager, FilterResultDialog::class.java.canonicalName)
            }
        }
    }

    private fun setupRecycler(data: BusinessSearchDomain?) {
        binding.apply {
            val adapter = SearchAdapter(object : SearchAdapter.OnClickListener {
                override fun onClickItem(data: BusinessSearchDomain.Businesse) {
                    toastShort("Menekan: ${data.name}")
                    DetailActivity.navigateToDetail(this@HomeActivity, data.id.toString())
                }
            })

            adapter.submitData(data?.businesses)
            rvBusinessSearch.adapter = adapter
        }
    }

    override fun setupViews() {
        binding.apply {
            svBusiness.requestFocus()
            svBusiness.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.location = query
                    actionSearch(query)
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean {
                    val queryLength = query?.length ?: 0
                    if (queryLength == 0) initShowFirstState()
                    return true
                }
            })
        }
    }

    private fun initShowFirstState() {
        binding.apply {
            cgInitialState.visible()
            cgEmptyState.gone()
            cgFoundedSearch.gone()
        }
    }

    private fun actionSearch(
        location: String? = null,
        price: ArrayList<String>? = null,
        limit: Int? = null
    ) {
        binding.cgInitialState.gone()

        viewModel.searchBusiness(
            location,
            price,
            limit
        )
    }
}