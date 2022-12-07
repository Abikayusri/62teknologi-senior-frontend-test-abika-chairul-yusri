package abika.sinau.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class BusinessReviewResponse(
    @SerializedName("possible_languages")
    val possibleLanguages: List<String?>? = null,
    @SerializedName("reviews")
    val reviews: List<Review?>? = null,
    @SerializedName("total")
    val total: Int? = null
) {
    data class Review(
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("rating")
        val rating: Int? = null,
        @SerializedName("text")
        val text: String? = null,
        @SerializedName("time_created")
        val timeCreated: String? = null,
        @SerializedName("url")
        val url: String? = null,
        @SerializedName("user")
        val user: User? = null
    ) {
        data class User(
            @SerializedName("id")
            val id: String? = null,
            @SerializedName("image_url")
            val imageUrl: String? = null,
            @SerializedName("name")
            val name: String? = null,
            @SerializedName("profile_url")
            val profileUrl: String? = null
        )
    }
}