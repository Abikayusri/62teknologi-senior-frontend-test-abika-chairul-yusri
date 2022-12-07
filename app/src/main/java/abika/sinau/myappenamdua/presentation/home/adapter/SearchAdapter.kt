package abika.sinau.myappenamdua.presentation.home.adapter

import abika.sinau.core.data.source.remote.response.BusinessSearchResponse
import abika.sinau.core.utils.loadImage
import abika.sinau.myappenamdua.databinding.ItemBusinessSearchBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


/**
 * @author by Abika Chairul Yusri on 12/8/2022
 */
class SearchAdapter(private val onItemClick: OnClickListener) :
    RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<BusinessSearchResponse.Businesse>() {
        override fun areItemsTheSame(
            oldItem: BusinessSearchResponse.Businesse,
            newItem: BusinessSearchResponse.Businesse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BusinessSearchResponse.Businesse,
            newItem: BusinessSearchResponse.Businesse
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: ArrayList<BusinessSearchResponse.Businesse?>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchAdapterViewHolder(
            ItemBusinessSearchBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data, position) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class SearchAdapterViewHolder(
        private val binding: ItemBusinessSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BusinessSearchResponse.Businesse, position: Int) {
            binding.apply {
                ivImage.loadImage(data.imageUrl.toString())
                tvBusinessName.text = data.name
                tvCity.text = data.location?.city
                tvPrice.text = data.price
                tvRating.text = data.rating.toString()
                tvPhone.text = data.phone

                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(data: BusinessSearchResponse.Businesse)
    }
}