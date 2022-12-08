package abika.sinau.core.data.source.remote

import abika.sinau.core.data.source.remote.response.BusinessDetailResponse
import abika.sinau.core.data.source.remote.response.BusinessReviewResponse
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
interface DataSource {
    //    suspend fun getBusinessSearch(location: String): Response<BusinessSearchResponse>
//    suspend fun getBusinessSearch(request: BusinessSearchRequest): Response<BusinessSearchResponse>
    suspend fun getBusinessSearch(
        location: String?,
        price: ArrayList<String>?,
        limit: Int?
    ): Response<BusinessSearchResponse>

    suspend fun getBusinessDetail(businessId: String): Response<BusinessDetailResponse>
    suspend fun getBusinessReview(businessId: String): Response<BusinessReviewResponse>
}