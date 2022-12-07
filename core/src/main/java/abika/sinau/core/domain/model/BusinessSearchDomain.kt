package abika.sinau.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */

@Parcelize
data class BusinessSearchDomain(
    val id: Int?,
    val name: String?,
    val imageUrl: String?,
    val city: String?,
    val price: String?,
    val rating: Double?,
): Parcelable
