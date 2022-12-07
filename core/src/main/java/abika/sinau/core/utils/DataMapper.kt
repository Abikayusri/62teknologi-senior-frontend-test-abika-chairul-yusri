package abika.sinau.core.utils

import abika.sinau.core.data.source.Resource
import abika.sinau.core.data.source.remote.response.BusinessDetailResponse
import abika.sinau.core.data.source.remote.response.BusinessReviewResponse
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
object DataMapper {

    fun mapBusinessDetailResponseToDomain(input: Resource<BusinessDetailResponse>): Resource<BusinessDetailDomain> {
        val data = input.data
        val result = BusinessDetailDomain(

            id = data?.id,
            name = data?.name,
            imageUrl = data?.imageUrl,
            city = data?.location?.city,
            price = data?.price,
            rating = data?.rating
        )

        return Resource.Success(result)
    }

    fun mapBusinessReviewResponseToDomain(input: Resource<BusinessReviewResponse>): Resource<BusinessReviewDomain> {
        val data = input.data
        val review = arrayListOf<BusinessReviewDomain.Review>()
        review.addAll(review)

        val result = BusinessReviewDomain(
            total = data?.total,
            reviews = review
        )

        return Resource.Success(result)
    }
}