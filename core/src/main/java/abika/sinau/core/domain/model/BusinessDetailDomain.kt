package abika.sinau.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */

@Parcelize
data class BusinessDetailDomain(
    val id: String? = null,
    val name: String? = null,
    val imageUrl: String? = null,
    val isClosed: Boolean? = null,
    val coordinate: List<Coordinates>? = null,
    val rating: Double? = null,
    val location: Location? = null,
    val city: String? = null,
    val photos: List<String>? = null,
    val price: String? = null,
) : Parcelable {
    @Parcelize
    data class Coordinates(
        val latitude: Double? = null,
        val longitude: Double? = null
    ) : Parcelable

    @Parcelize
    data class Location(
        val address1: String? = null,
        val address2: String? = null,
        val address3: String? = null,
        val city: String? = null,
        val country: String? = null,
        val crossStreets: String? = null,
        val state: String? = null,
        val zipCode: String? = null
    ) : Parcelable
}
