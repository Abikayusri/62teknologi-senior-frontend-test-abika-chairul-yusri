package abika.sinau.core.data.source.remote.network

import abika.sinau.core.data.source.remote.response.BusinessDetailResponse
import abika.sinau.core.data.source.remote.response.BusinessReviewResponse
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
interface ApiService {

    @GET("businesses/search")
    suspend fun getBusinessSearchPaging(
        @Query("location") location: String?,
        @Query("price") price: String?,
        @Query("limit") limit: Int?,
    ): Response<BusinessSearchResponse>

    @GET("businesses/{businessesId}")
    suspend fun getBusinessDetail(
        @Path("businessesId") businessesId: String
    ): Response<BusinessDetailResponse>

    @GET("businesses/{businessesId}/reviews")
    suspend fun getBusinessReview(
        @Path("businessesId") businessesId: String
    ): Response<BusinessReviewResponse>
}