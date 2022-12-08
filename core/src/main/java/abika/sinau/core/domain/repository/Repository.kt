package abika.sinau.core.domain.repository

import abika.sinau.core.data.source.Resource
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain
import abika.sinau.core.domain.model.BusinessSearchDomain


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
interface Repository {
    suspend fun getBusinessSearch(
        location: String?,
        price: String?,
        limit: Int?
    ): Resource<BusinessSearchDomain>

    suspend fun getBusinessDetail(businessId: String): Resource<BusinessDetailDomain>
    suspend fun getBusinessReview(businessId: String): Resource<BusinessReviewDomain>
}