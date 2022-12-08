package abika.sinau.myappenamdua.presentation.detail.adapter

import abika.sinau.core.utils.loadImage
import abika.sinau.myappenamdua.R
import abika.sinau.myappenamdua.databinding.ItemCarouselBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 * @author by Abika Chairul Yusri on 12/9/2022
 */
class ImageSliderAdapter(
    private val list: List<String?>?,
) : RecyclerView.Adapter<ImageSliderAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String?) {
            binding.apply {
                ivImageSlider.loadImage(
                    item.toString(),
                    R.drawable.ic_broken_image_grey_24,
                    R.drawable.ic_broken_image_grey_24
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            ItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val data = list?.get(position)
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        val size: Int = if (list?.size!! > 5) {
            5
        } else {
            list.size
        }

        return size
    }
}