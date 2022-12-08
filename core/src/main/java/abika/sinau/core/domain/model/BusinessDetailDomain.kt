package abika.sinau.core.domain.model


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */

data class BusinessDetailDomain(
    val id: String? = null,
    val name: String? = null,
    val imageUrl: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val rating: Double? = null,
    val photos: List<String?>? = null,
    val price: String? = null,
    val displayPhone: String? = null,
)
