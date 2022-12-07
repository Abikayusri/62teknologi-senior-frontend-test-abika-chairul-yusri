package abika.sinau.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class BusinessDetailResponse(
    @SerializedName("alias")
    val alias: String? = null,
    @SerializedName("categories")
    val categories: List<Category?>? = null,
    @SerializedName("coordinates")
    val coordinates: Coordinates? = null,
    @SerializedName("display_phone")
    val displayPhone: String? = null,
    @SerializedName("hours")
    val hours: List<Hour?>? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("image_url")
    val imageUrl: String? = null,
    @SerializedName("is_claimed")
    val isClaimed: Boolean? = null,
    @SerializedName("is_closed")
    val isClosed: Boolean? = null,
    @SerializedName("location")
    val location: Location? = null,
    @SerializedName("messaging")
    val messaging: Messaging? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("photos")
    val photos: List<String?>? = null,
    @SerializedName("price")
    val price: String? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("review_count")
    val reviewCount: Int? = null,
    @SerializedName("transactions")
    val transactions: List<Any?>? = null,
    @SerializedName("url")
    val url: String? = null
) {
    data class Category(
        @SerializedName("alias")
        val alias: String? = null,
        @SerializedName("title")
        val title: String? = null
    )

    data class Coordinates(
        @SerializedName("latitude")
        val latitude: Double? = null,
        @SerializedName("longitude")
        val longitude: Double? = null
    )

    data class Hour(
        @SerializedName("hours_type")
        val hoursType: String? = null,
        @SerializedName("is_open_now")
        val isOpenNow: Boolean? = null,
        @SerializedName("open")
        val `open`: List<Open?>? = null
    ) {
        data class Open(
            @SerializedName("day")
            val day: Int? = null,
            @SerializedName("end")
            val end: String? = null,
            @SerializedName("is_overnight")
            val isOvernight: Boolean? = null,
            @SerializedName("start")
            val start: String? = null
        )
    }

    data class Location(
        @SerializedName("address1")
        val address1: String? = null,
        @SerializedName("address2")
        val address2: String? = null,
        @SerializedName("address3")
        val address3: String? = null,
        @SerializedName("city")
        val city: String? = null,
        @SerializedName("country")
        val country: String? = null,
        @SerializedName("cross_streets")
        val crossStreets: String? = null,
        @SerializedName("display_address")
        val displayAddress: List<String?>? = null,
        @SerializedName("state")
        val state: String? = null,
        @SerializedName("zip_code")
        val zipCode: String? = null
    )

    data class Messaging(
        @SerializedName("url")
        val url: String? = null,
        @SerializedName("use_case_text")
        val useCaseText: String? = null
    )
}