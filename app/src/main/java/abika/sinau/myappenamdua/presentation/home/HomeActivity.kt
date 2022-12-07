package abika.sinau.myappenamdua.presentation.home

import abika.sinau.core.data.source.Resource
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.toastShort
import abika.sinau.myappenamdua.databinding.ActivityHomeBinding
import abika.sinau.myappenamdua.presentation.detail.DetailActivity
import abika.sinau.myappenamdua.presentation.home.adapter.SearchAdapter
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
            when(response) {
                is Resource.Success -> {
                    setupRecycler(response.data)
                }
                is Resource.Error -> {
                    toastShort("Data Tidak Ditemukan!")
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    private fun setupRecycler(data: BusinessSearchResponse?) {
        binding.apply {
            val adapter = SearchAdapter(object : SearchAdapter.OnClickListener {
                override fun onClickItem(data: BusinessSearchResponse.Businesse) {
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
                    actionSearch(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }
    }

    private fun actionSearch(query: String?) {
        viewModel.searchBusiness(query.toString())
    }
}