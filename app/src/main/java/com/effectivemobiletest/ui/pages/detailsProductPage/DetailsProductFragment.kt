package com.effectivemobiletest.ui.pages.detailsProductPage

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.databinding.DetailProductLayoutBinding
import com.effectivemobiletest.epoxy.contollers.ProductCharacteristicsController
import com.effectivemobiletest.epoxy.models.mapperClasses.Capacity
import com.effectivemobiletest.epoxy.models.mapperClasses.ColorsOfPoint
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxyProductCapacityItem
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxyProductColorsItem
import com.effectivemobiletest.ui.BaseFragment

class DetailsProductFragment(): BaseFragment<DetailProductLayoutBinding, DetailProductViewModel>() {

    override val binding: DetailProductLayoutBinding by viewBinding(CreateMethod.INFLATE)
    override val viewModel: DetailProductViewModel by viewModels()

    private val controller by lazy { ProductCharacteristicsController() }

    override fun setUpViewsBinding() {
        binding.include.colorAndCapacityEV.setController(controller)
    }

    override fun observeData() {
        binding.include.rating.rating = 4.5f
        controller.setData(listOf(
            EpoxyProductColorsItem(listOf(
                ColorsOfPoint(color = "#772D03", isSelected = true),
                ColorsOfPoint(color = "#010035", isSelected = false))
        ),
        EpoxyProductCapacityItem(listOf(
            Capacity("128", isSelected = true),
            Capacity("256", isSelected = false),
        ))))
    }
}