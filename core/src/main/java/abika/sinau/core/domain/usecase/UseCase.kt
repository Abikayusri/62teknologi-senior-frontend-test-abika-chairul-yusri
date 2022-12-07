package abika.sinau.core.domain.usecase

import abika.sinau.core.data.source.Resource
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
interface UseCase {
    suspend fun getBusinessSearch(location: String): Resource<BusinessSearchResponse>
    suspend fun getBusinessDetail(businessId: String): Resource<BusinessDetailDomain>
    suspend fun getBusinessReview(businessId: String): Resource<BusinessReviewDomain>
}