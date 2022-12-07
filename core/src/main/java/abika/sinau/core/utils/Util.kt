package abika.sinau.core.utils

import abika.sinau.core.data.source.Resource
import android.app.Activity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Response


/**
 * @author by Abika Chairul Yusri on 12/7/2022
 */

fun <RP : Any?> responseToResources(response: Response<RP>?): Resource<RP> {
    if (response?.isSuccessful == true) {
        response.body()?.let { result ->
            return Resource.Success(result)
        }
    }

    return Resource.Error(response?.message().toString())
}

fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.loadImage(url: String) {
    Glide
        .with(this)
        .load(url)
        .into(this)
}

fun ImageView.loadImage(url: String, placeholder: Int, error: Int) {
    Glide
        .with(this)
        .load(url)
        .error(error)
        .placeholder(placeholder)
        .into(this)
}

fun EditText.getTextString(): String {
    return text.toString()
}