package abika.sinau.core.domain.model


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */

data class BusinessSearchDomain(
    val businesses: ArrayList<Businesse>? = null,
    val total: Int? = null
) {
    data class Businesse(
        val id: String?,
        val name: String?,
        val imageUrl: String?,
        val city: String?,
        val price: String?,
        val rating: Double?,
        val phone: String? = null,
        val location: Location? = null,
    ) {
        data class Location(
            val city: String? = null,
        )
    }
}