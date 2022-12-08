package abika.sinau.myappenamdua.presentation.detail.adapter

import abika.sinau.core.domain.model.BusinessReviewDomain
import abika.sinau.core.utils.loadImage
import abika.sinau.myappenamdua.R
import abika.sinau.myappenamdua.databinding.ItemReviewBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @author by Abika Chairul Yusri on 12/9/2022
 */

class ReviewAdapter() :
    RecyclerView.Adapter<ReviewAdapter.ReviewAdapterViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<BusinessReviewDomain.Review>() {
        override fun areItemsTheSame(
            oldItem: BusinessReviewDomain.Review,
            newItem: BusinessReviewDomain.Review
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BusinessReviewDomain.Review,
            newItem: BusinessReviewDomain.Review
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: ArrayList<BusinessReviewDomain.Review>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ReviewAdapterViewHolder(
            ItemReviewBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewAdapterViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data, position) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ReviewAdapterViewHolder(
        private val binding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusinessReviewDomain.Review, position: Int) {
            binding.apply {
                ivImage.loadImage(
                    data.imageUrl.toString(),
                    R.drawable.ic_broken_image_grey_24,
                    R.drawable.ic_broken_image_grey_24
                )
                tvUserName.text = data.name
                tvRating.text = data.rating.toString()
                tvDescription.text = data.text
            }
        }
    }
}