package abika.sinau.myappenamdua.presentation.home

import abika.sinau.core.data.source.Resource
import abika.sinau.core.domain.model.BusinessSearchDomain
import abika.sinau.core.domain.usecase.UseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author by Abika Chairul Yusri on 12/8/2022
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val usecase: UseCase
) : ViewModel() {
    private val _resultBusinessSearch = MutableLiveData<Resource<BusinessSearchDomain>>()
    val resultBusinessSearch: LiveData<Resource<BusinessSearchDomain>> get() = _resultBusinessSearch

    var limitFilter: String? = null
    var location: String? = null
    var priceFilter: ArrayList<String> = arrayListOf()

    fun searchBusiness(
        location: String?,
        price: ArrayList<String>? = null,
        limit: Int? = null
    ) {
        viewModelScope.launch {
            _resultBusinessSearch.postValue(Resource.Loading())
            try {
                val result = usecase.getBusinessSearch(
                    location,
                    price,
                    limit
                )
                _resultBusinessSearch.postValue(result)
            } catch (error: Exception) {
                _resultBusinessSearch.postValue(Resource.Error(error.message.toString()))
            }
        }
    }
}