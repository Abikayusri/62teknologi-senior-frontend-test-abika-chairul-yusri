package abika.sinau.core.domain.usecase

import abika.sinau.core.data.source.Resource
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain
import abika.sinau.core.domain.model.BusinessSearchDomain
import abika.sinau.core.domain.repository.Repository


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
class UseCaseImpl(
    private val repository: Repository
) : UseCase {
    override suspend fun getBusinessSearch(
        location: String?,
        price: ArrayList<String>?,
        limit: Int?
    ): Resource<BusinessSearchDomain> {
        return repository.getBusinessSearch(
            location,
            price,
            limit
        )
    }

    override suspend fun getBusinessDetail(businessId: String): Resource<BusinessDetailDomain> {
        return repository.getBusinessDetail(businessId)
    }

    override suspend fun getBusinessReview(businessId: String): Resource<BusinessReviewDomain> {
        return repository.getBusinessReview(businessId)
    }
}