package abika.sinau.myappenamdua.presentation.detail

import abika.sinau.core.data.source.Resource
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain
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
class DetailViewModel @Inject constructor(
    private val usecase: UseCase
) : ViewModel() {
    private val _resultBusinessDetail = MutableLiveData<Resource<BusinessDetailDomain>>()
    val resultBusinessDetail: LiveData<Resource<BusinessDetailDomain>> get() = _resultBusinessDetail

    fun getBusinessDetail(businessId: String) {
        viewModelScope.launch {
            _resultBusinessDetail.postValue(Resource.Loading())
            try {
                val result = usecase.getBusinessDetail(businessId)
                _resultBusinessDetail.postValue(result)
            } catch (error: Exception) {
                _resultBusinessDetail.postValue(Resource.Error(error.message.toString()))
            }
        }
    }

    private val _resultBusinessReview = MutableLiveData<Resource<BusinessReviewDomain>>()
    val resultBusinessReview: LiveData<Resource<BusinessReviewDomain>> get() = _resultBusinessReview

    fun getBusinessReview(businessId: String) {
        viewModelScope.launch {
            _resultBusinessReview.postValue(Resource.Loading())
            try {
                val result = usecase.getBusinessReview(businessId)
                _resultBusinessReview.postValue(result)
            } catch (error: Exception) {
                _resultBusinessReview.postValue(Resource.Error(error.message.toString()))
            }
        }
    }

}