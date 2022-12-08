package abika.sinau.core.domain.model


data class BusinessReviewDomain(
    val reviews: ArrayList<Review>? = null,
    val total: Int? = null
) {
    data class Review(
        val id: String? = null,
        val rating: Int? = null,
        val text: String? = null,
        val timeCreated: String? = null,
        val user: User? = null,
        val userId: String? = null,
        val imageUrl: String? = null,
        val name: String? = null,
    ) {
        data class User(
            val id: String? = null,
            val imageUrl: String? = null,
            val name: String? = null,
        )
    }
}