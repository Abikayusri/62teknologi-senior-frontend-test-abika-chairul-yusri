package abika.sinau.core.data.repository

import abika.sinau.core.data.source.Resource
import abika.sinau.core.data.source.remote.DataSource
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain
import abika.sinau.core.domain.repository.Repository
import abika.sinau.core.utils.DataMapper.mapBusinessDetailResponseToDomain
import abika.sinau.core.utils.DataMapper.mapBusinessReviewResponseToDomain
import abika.sinau.core.utils.responseToResources


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
class RepositoryImpl(
    private val dataSource: DataSource
) : Repository {
    override suspend fun getBusinessSearch(location: String): Resource<BusinessSearchResponse> {
        return responseToResources(dataSource.getBusinessSearch(location))
    }

    override suspend fun getBusinessDetail(businessId: String): Resource<BusinessDetailDomain> {
        val result = responseToResources(dataSource.getBusinessDetail(businessId))
        return mapBusinessDetailResponseToDomain(result)
    }

    override suspend fun getBusinessReview(businessId: String): Resource<BusinessReviewDomain> {
        val result = responseToResources(dataSource.getBusinessReview(businessId))

        return mapBusinessReviewResponseToDomain(result)
    }
}