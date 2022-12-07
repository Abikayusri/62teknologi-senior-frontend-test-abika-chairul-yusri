package abika.sinau.myappenamdua.presentation.home

import abika.sinau.core.data.source.Resource
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
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
    private val _resultBusinessSearch = MutableLiveData<Resource<BusinessSearchResponse>>()
    val resultBusinessSearch: LiveData<Resource<BusinessSearchResponse>> get() = _resultBusinessSearch

    fun searchBusiness(location: String) {
        viewModelScope.launch {
            _resultBusinessSearch.postValue(Resource.Loading())
            try {
                val result = usecase.getBusinessSearch(location)
                _resultBusinessSearch.postValue(result)
            } catch (error: Exception) {
                _resultBusinessSearch.postValue(Resource.Error(error.message.toString()))
            }
        }
    }
}