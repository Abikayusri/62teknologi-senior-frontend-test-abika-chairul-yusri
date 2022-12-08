package abika.sinau.myappenamdua.presentation.detail

import abika.sinau.core.data.source.Resource
import abika.sinau.core.domain.model.BusinessDetailDomain
import abika.sinau.core.domain.model.BusinessReviewDomain
import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.gone
import abika.sinau.core.utils.loadImage
import abika.sinau.core.utils.toastShort
import abika.sinau.core.utils.visible
import abika.sinau.myappenamdua.R
import abika.sinau.myappenamdua.databinding.ActivityDetailBinding
import abika.sinau.myappenamdua.presentation.detail.adapter.ImageSliderAdapter
import abika.sinau.myappenamdua.presentation.detail.adapter.ReviewAdapter
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseViewModelActivity<DetailViewModel, ActivityDetailBinding>() {
    override fun inflateLayout(layoutInflater: LayoutInflater): ActivityDetailBinding {
        return ActivityDetailBinding.inflate(layoutInflater)
    }

    override val viewModelClass: Class<DetailViewModel>
        get() = DetailViewModel::class.java

    private var businessId: String? = null

    override fun setupViews() {
        businessId = intent?.getStringExtra("BUSINESS_ID")
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: DetailViewModel) {
        viewModel.apply {
            getBusinessDetail(businessId.toString())
            getBusinessReview(businessId.toString())

            resultBusinessDetail.observe(lifecycleOwner) { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.apply {
                            pbLoading.gone()
                            cgDetailBusiness.visible()
                            setupDetailBusiness(response.data)
                        }
                    }
                    is Resource.Error -> {
                        binding.apply {
                            pbLoading.gone()
                            cgDetailBusiness.gone()
                        }
                    }
                    is Resource.Loading -> {
                        binding.apply {
                            pbLoading.visible()
                            cgDetailBusiness.visible()
                        }
                    }
                }
            }

            resultBusinessReview.observe(lifecycleOwner) { response ->
                when (response) {
                    is Resource.Success -> {
                        binding.cgReview.visible()
                        setupReviewList(response.data)
                    }
                    is Resource.Error -> {
                        binding.cgReview.gone()
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    private fun setupDetailBusiness(data: BusinessDetailDomain?) {
        binding.apply {
            val imageSliderAdapter = ImageSliderAdapter(data?.photos)
            vpImageSlider.adapter = imageSliderAdapter
            ciImageSlideIndicator.setViewPager(vpImageSlider)

            ivImage.loadImage(
                data?.imageUrl.toString(),
                R.drawable.ic_broken_image_grey_24,
                R.drawable.ic_broken_image_grey_24
            )

            tvName.text = data?.name
            tvPrice.text = data?.price
            tvPhone.text = data?.displayPhone

            clMap.setOnClickListener {
                openGoogleMaps(
                    data?.latitude,
                    data?.longitude,
                    data?.name,
                )
            }

            tvTapToMap.setOnClickListener {
                openGoogleMaps(
                    data?.latitude,
                    data?.longitude,
                    data?.name,
                )
            }
        }
    }

    private fun openGoogleMaps(
        latitude: Double?,
        longitude: Double?,
        name: String?,
    ) {
        val gmmIntentUri =
            Uri.parse("http://maps.google.com/maps?q=$latitude,$longitude($name)")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    private fun setupReviewList(data: BusinessReviewDomain?) {
        binding.apply {
            val adapter = ReviewAdapter()
            adapter.submitData(data?.reviews)

            rvReview.adapter = adapter
        }
    }

    companion object {
        fun navigateToDetail(context: Context, businessId: String) {
            Intent(context, DetailActivity::class.java).apply {
                putExtra("BUSINESS_ID", businessId)
                context.startActivity(this)
            }
        }
    }
}