package abika.sinau.core.data.source.remote

import abika.sinau.core.data.source.remote.network.ApiService
import abika.sinau.core.data.source.remote.response.BusinessDetailResponse
import abika.sinau.core.data.source.remote.response.BusinessReviewResponse
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
class DataSourceImpl(
    private val apiService: ApiService
) : DataSource {
    override suspend fun getBusinessSearch(
        location: String?,
        price: ArrayList<String>?,
        limit: Int?
    ): Response<BusinessSearchResponse> {
        return apiService.getBusinessSearchPaging(
            location,
            price,
            limit
        )
    }

//    override suspend fun getBusinessSearch(location: String): Response<BusinessSearchResponse> {
//        return apiService.getBusinessSearchPaging(location)
//    }

//    override suspend fun getBusinessSearch(request: BusinessSearchRequest): Response<BusinessSearchResponse> {
//        return apiService.getBusinessSearchPaging(request)
//    }

    override suspend fun getBusinessDetail(businessId: String): Response<BusinessDetailResponse> {
        return apiService.getBusinessDetail(businessId)
    }

    override suspend fun getBusinessReview(businessId: String): Response<BusinessReviewResponse> {
        return apiService.getBusinessReview(businessId)
    }
}