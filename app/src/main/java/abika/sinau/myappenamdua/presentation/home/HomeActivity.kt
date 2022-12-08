package abika.sinau.myappenamdua.presentation.home

import abika.sinau.core.data.source.Resource
import abika.sinau.core.domain.model.BusinessSearchDomain
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.toastShort
import abika.sinau.core.utils.visible
import abika.sinau.myappenamdua.R
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
                    toastShort(getString(R.string.label_data_not_found))
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
            inclLimitPageFilter.tvTitle.text = getString(R.string.label_limit_data)
            inclLimitPageFilter.root.setOnClickListener {
                FilterResultDialog(
                    filterItem = listOf(
                        getString(R.string.label_5_data),
                        getString(R.string.label_10_data),
                        getString(R.string.label_20_data),
                        getString(R.string.label_50_data),
                        getString(R.string.label_all_data)
                    ),
                    filterType = FilterResultDialog.LIMIT_PAGE,
                    listener = {
                        actionSearch(
                            location = viewModel.location,
                            price = viewModel.priceFilter,
                            limit = viewModel.limitFilter
                        )
                    }
                ).show(supportFragmentManager, FilterResultDialog::class.java.canonicalName)
            }

            inclPriceFilter.tvTitle.text = getString(R.string.label_price)
            inclPriceFilter.root.setOnClickListener {
                FilterResultDialog(
                    filterItem = listOf(
                        getString(R.string.label_price_1),
                        getString(R.string.label_price_2),
                        getString(R.string.label_price_3),
                        getString(R.string.label_price_4)
                    ),
                    filterType = FilterResultDialog.PRICE,
                    listener = {
                        actionSearch(
                            location = viewModel.location,
                            price = viewModel.priceFilter,
                            limit = viewModel.limitFilter
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
        limit: String? = null
    ) {
        binding.cgInitialState.gone()

        val limitPageData = when (limit) {
            getString(R.string.label_5_data) -> 5
            getString(R.string.label_10_data) -> 10
            getString(R.string.label_20_data) -> 20
            getString(R.string.label_50_data) -> 50
            else -> null
        }

        val priceData = ArrayList<Int>()
        if (!price.isNullOrEmpty()) {
            price.forEach {
                val priceResult = when (it) {
                    getString(R.string.label_price_1) -> 1
                    getString(R.string.label_price_2) -> 2
                    getString(R.string.label_price_3) -> 3
                    getString(R.string.label_price_4) -> 4
                    else -> 0
                }
                priceData.add(priceResult)
            }
        }

        val priceDataResult =
            if (priceData.isEmpty()) null else priceData.joinToString(", ") ?: null

        viewModel.searchBusiness(
            location,
            priceDataResult,
            limitPageData
        )
    }
}