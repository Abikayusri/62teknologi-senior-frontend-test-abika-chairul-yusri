package abika.sinau.myappenamdua.presentation.home.filter

import abika.sinau.core.utils.gone
import abika.sinau.myappenamdua.R
import abika.sinau.myappenamdua.databinding.ItemCheckboxBinding
import abika.sinau.myappenamdua.databinding.ItemRadioButtonBinding
import abika.sinau.myappenamdua.databinding.LayoutFilterItemBinding
import abika.sinau.myappenamdua.databinding.LayoutLimitResultDialogBinding
import abika.sinau.myappenamdua.presentation.home.HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * @author by Abika Chairul Yusri on 12/8/2022
 */
class FilterResultDialog(
    private val filterItem: List<String>? = null,
    private val filterType: String,
    val listener: () -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: LayoutLimitResultDialogBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private val viewmodel: HomeViewModel by activityViewModels()
    private var tempFilter = mutableListOf<String>()
    private val filterSetIds = mutableListOf<Int>()
    private val filterSelectedTemp = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutLimitResultDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            ivClose.setOnClickListener {
                dismiss()
            }

            tvReset.setOnClickListener {
                filterSelectedTemp.clear()

                when (filterType) {
                    LIMIT_PAGE -> {
                        viewmodel.limitFilter = requireContext().getString(R.string.label_all_data)
                    }
                    PRICE -> {
                        viewmodel.priceFilter.clear()
                    }
                }
                listener.invoke()
                dismiss()
            }

            tvTitle.text = if (filterType == LIMIT_PAGE) requireContext().getString(R.string.label_total_data) else PRICE

            btnTerapkan.setOnClickListener {
                when (filterType) {
                    LIMIT_PAGE -> {
                        viewmodel.limitFilter = tempFilter.firstOrNull()
                    }
                    PRICE -> {
                        viewmodel.priceFilter.clear()
                        viewmodel.priceFilter.addAll(tempFilter)
                    }
                }
                listener.invoke()
                dismiss()
            }
        }

        bottomSheetDialog = dialog as BottomSheetDialog
        bottomSheetDialog.setContentView(binding.root)

        setupProcess()
    }

    private fun setupProcess() {
        when (filterType) {
            LIMIT_PAGE -> {
                tempFilter.add(viewmodel.limitFilter.toString())
                setupRadioButton()
            }
            PRICE -> {
                tempFilter.addAll(viewmodel.priceFilter)
                setupCheckBox()
            }
        }
    }

    // region radio button

    private fun setupRadioButton() {
        filterItem.let {
            val filterSetBinding = createFilterRadioButton()
            createFilterRadioItem(filterSetBinding, filterItem)
            binding.llFilterSelection.addView(filterSetBinding.root)
        }
    }

    private fun createFilterRadioItem(
        filterSetBinding: LayoutFilterItemBinding,
        filterItem: List<String>?
    ) {
        filterItem?.forEach {
            val radioButtonBinding = ItemRadioButtonBinding.inflate(layoutInflater)
            radioButtonBinding.root.id = View.generateViewId()
            radioButtonBinding.root.text = it

            val layoutParams = RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT
            )

            radioButtonBinding.root.layoutParams = layoutParams

            if (viewmodel.limitFilter?.isNotEmpty() == true) {
                radioButtonBinding.root.isChecked = viewmodel.limitFilter == it
            }

            radioButtonBinding.root.setOnClickListener {
                tempFilter.clear()
                tempFilter.add(radioButtonBinding.root.text.toString())
            }

            filterSetBinding.rgFilter.addView(radioButtonBinding.root)
        }
    }

    private fun createFilterRadioButton(): LayoutFilterItemBinding {
        val filterRadioButtonBinding = LayoutFilterItemBinding.inflate(layoutInflater)
        View.generateViewId().let { id ->
            filterSetIds.add(id)
            filterRadioButtonBinding.root.id = id
        }

        val layoutParams = LinearLayout.LayoutParams(
            RadioGroup.LayoutParams.MATCH_PARENT,
            RadioGroup.LayoutParams.WRAP_CONTENT
        )

        filterRadioButtonBinding.root.layoutParams = layoutParams
        return filterRadioButtonBinding
    }

    // endregion

    // region checkbox

    private fun setupCheckBox() {
        if (filterItem?.isNotEmpty() == true) {
            val filterCheckBoxBinding = createCheckBox(filterItem)
            binding.llFilterSelection.addView(filterCheckBoxBinding.root)
        }
    }

    private fun createCheckBox(filterItem: List<String>): LayoutFilterItemBinding {
        val filterCheckBox = LayoutFilterItemBinding.inflate(layoutInflater)
        View.generateViewId().let { id ->
            filterSetIds.add(id)
            filterCheckBox.root.id = id
        }

        filterCheckBox.rgFilter.gone()

        filterItem.forEach {
            val checkBoxBinding = ItemCheckboxBinding.inflate(layoutInflater)
            checkBoxBinding.root.id = View.generateViewId()
            checkBoxBinding.root.text = it
            checkBoxBinding.root.isChecked = viewmodel.priceFilter.contains(it)

            filterCheckBox.llFilter.addView(checkBoxBinding.root)

            val checkedBox = checkBoxBinding.cbType.findViewById<CheckBox>(checkBoxBinding.root.id)
            checkedBox.setOnClickListener {
                if (checkedBox.isChecked) {
                    tempFilter.add(checkedBox.text.toString())
                } else {
                    tempFilter.remove(checkedBox.text.toString())
                }
            }
        }
        return filterCheckBox
    }

    // endregion

    companion object {
        const val LIMIT_PAGE = "Menampilkan"
        const val PRICE = "Harga"
    }
}