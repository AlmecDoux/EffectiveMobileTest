package com.effectivemobiletest.ui.pages.detailsProductPage

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.PagerSnapHelper
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.domain.annotations.MainDispatcher
import com.effectivemobile.test.databinding.DetailProductLayoutBinding
import com.effectivemobiletest.decorations.marginDecorations.CenterZoomLinearLayoutManager
import com.effectivemobiletest.epoxy.contollers.ProductCharacteristicsController
import com.effectivemobiletest.epoxy.contollers.ProductPhotosController
import com.effectivemobiletest.epoxy.models.mapperClasses.Capacity
import com.effectivemobiletest.epoxy.models.mapperClasses.ColorsOfPoint
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxyProductCapacityItem
import com.effectivemobiletest.epoxy.models.mapperClasses.EpoxyProductColorsItem
import com.effectivemobiletest.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailsProductFragment: BaseFragment<DetailProductLayoutBinding, DetailProductViewModel>() {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val binding: DetailProductLayoutBinding by viewBinding(CreateMethod.INFLATE)
    override val viewModel: DetailProductViewModel by viewModels()

    private val controllerProductCharacteristics by lazy { ProductCharacteristicsController() }
    private val controllerProductPhotos by lazy { ProductPhotosController() }
    override fun setUpViewsBinding() {
        binding.include.colorAndCapacityEV.setController(controllerProductCharacteristics)
        val snap = PagerSnapHelper()
        binding.photosProductCarousel.layoutManager = CenterZoomLinearLayoutManager(requireContext())
        snap.attachToRecyclerView(binding.photosProductCarousel)
        binding.photosProductCarousel.setController(controllerProductPhotos)
    }

    override fun observeData() {
        lifecycle.addObserver(viewModel)
        binding.include.rating.rating = 4.5f
        controllerProductCharacteristics.setData(listOf(
            EpoxyProductColorsItem(listOf(
                ColorsOfPoint(color = "#772D03", isSelected = true),
                ColorsOfPoint(color = "#010035", isSelected = false))
        ),
        EpoxyProductCapacityItem(listOf(
            Capacity("128", isSelected = true),
            Capacity("256", isSelected = false),
        ))))
        viewModel.photosData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { photosData->
                lifecycleScope.launch(mainDispatcher){
                    controllerProductPhotos.setData(photosData)
                }

            }
        }
    }
}