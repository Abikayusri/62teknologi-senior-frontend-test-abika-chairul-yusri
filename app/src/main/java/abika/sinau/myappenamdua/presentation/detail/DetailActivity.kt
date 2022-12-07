package abika.sinau.myappenamdua.presentation.detail

import abika.sinau.core.utils.base.BaseViewModelActivity
import abika.sinau.core.utils.toastShort
import abika.sinau.myappenamdua.databinding.ActivityDetailBinding
import android.content.Context
import android.content.Intent
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
        toastShort("BusinessId: $businessId")
    }

    override fun setupObservers(lifecycleOwner: LifecycleOwner, viewModel: DetailViewModel) {

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