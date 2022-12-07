package abika.sinau.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class BusinessSearchResponse(
    @SerializedName("businesses")
    val businesses: ArrayList<Businesse?>? = null,
    @SerializedName("region")
    val region: Region? = null,
    @SerializedName("total")
    val total: Int? = null
) {
    data class Businesse(
        @SerializedName("alias")
        val alias: String? = null,
        @SerializedName("categories")
        val categories: List<Category?>? = null,
        @SerializedName("coordinates")
        val coordinates: Coordinates? = null,
        @SerializedName("display_phone")
        val displayPhone: String? = null,
        @SerializedName("distance")
        val distance: Double? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("image_url")
        val imageUrl: String? = null,
        @SerializedName("is_closed")
        val isClosed: Boolean? = null,
        @SerializedName("location")
        val location: Location? = null,
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("phone")
        val phone: String? = null,
        @SerializedName("price")
        val price: String? = null,
        @SerializedName("rating")
        val rating: Double? = null,
        @SerializedName("review_count")
        val reviewCount: Int? = null,
        @SerializedName("transactions")
        val transactions: List<String?>? = null,
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
            @SerializedName("display_address")
            val displayAddress: List<String?>? = null,
            @SerializedName("state")
            val state: String? = null,
            @SerializedName("zip_code")
            val zipCode: String? = null
        )
    }

    data class Region(
        @SerializedName("center")
        val center: Center? = null
    ) {
        data class Center(
            @SerializedName("latitude")
            val latitude: Double? = null,
            @SerializedName("longitude")
            val longitude: Double? = null
        )
    }
}