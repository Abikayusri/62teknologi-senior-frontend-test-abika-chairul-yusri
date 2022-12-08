package abika.sinau.core.utils

import abika.sinau.core.data.source.Resource
import abika.sinau.core.data.source.remote.response.BusinessDetailResponse
import abika.sinau.core.data.source.remote.response.BusinessReviewResponse
import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain
import abika.sinau.core.domain.model.BusinessSearchDomain


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */
object DataMapper {

    fun mapBusinessSearchResponseToDomain(input: Resource<BusinessSearchResponse>): Resource<BusinessSearchDomain> {
        val data = input.data
        val business = ArrayList<BusinessSearchDomain.Businesse>()
        data?.businesses?.map {
            val businessess = BusinessSearchDomain.Businesse(
                id = it?.id,
                name = it?.name,
                imageUrl = it?.imageUrl,
                city = it?.location?.city,
                price = it?.price,
                rating = it?.rating,
                phone = it?.phone,
            )
            business.add(businessess)
        }

        val result = BusinessSearchDomain(
            businesses = business,
            total = data?.total
        )

        return Resource.Success(result)
    }

    fun mapBusinessDetailResponseToDomain(input: Resource<BusinessDetailResponse>): Resource<BusinessDetailDomain> {
        val data = input.data
        val result = BusinessDetailDomain(
            id = data?.id,
            name = data?.name,
            imageUrl = data?.imageUrl,
            price = data?.price,
            rating = data?.rating,
            latitude = data?.coordinates?.latitude,
            longitude = data?.coordinates?.longitude,
            displayPhone = data?.displayPhone,
            photos = data?.photos,
        )

        return Resource.Success(result)
    }

    fun mapBusinessReviewResponseToDomain(input: Resource<BusinessReviewResponse>): Resource<BusinessReviewDomain> {
        val data = input.data
        val review = ArrayList<BusinessReviewDomain.Review>()
        data?.reviews?.map {
            val reviews = BusinessReviewDomain.Review(
                id = it?.id,
                rating = it?.rating,
                text = it?.text,
                timeCreated = it?.timeCreated,
                userId = it?.user?.id,
                imageUrl = it?.user?.imageUrl,
                name = it?.user?.name
            )
            review.add(reviews)
        }

        val result = BusinessReviewDomain(
            total = data?.total,
            reviews = review
        )
        return Resource.Success(result)
    }
}