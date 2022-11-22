package com.effectivemobiletest.ui.pages.detailsProductPage

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.domain.annotations.MainDispatcher
import com.effectivemobile.test.databinding.DetailProductLayoutBinding
import com.effectivemobiletest.decorations.marginDecorations.CenterZoomLinearLayoutManager
import com.effectivemobiletest.epoxy.adapters.PhotosAdapter
import com.effectivemobiletest.epoxy.contollers.ProductCharacteristicsController
import com.effectivemobiletest.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class DetailsProductFragment : BaseFragment<DetailProductLayoutBinding, DetailProductViewModel>() {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val binding: DetailProductLayoutBinding by viewBinding(CreateMethod.INFLATE)
    override val viewModel: DetailProductViewModel by viewModels()

    private val controllerProductCharacteristics by lazy { ProductCharacteristicsController() }
    private val photosAdapter = PhotosAdapter()

    override fun setUpViewsBinding() {
        binding.include.isFavorite.setOnClickListener{
            binding.include.isFavorite.isSelected = !it.isSelected
        }
        binding.include.colorAndCapacityEV.setController(controllerProductCharacteristics)
        binding.photosProductCarousel.adapter = photosAdapter
        binding.photosProductCarousel.layoutManager = CenterZoomLinearLayoutManager(requireContext())
        binding.photosProductCarousel.apply {
            set3DItem(false)
            setAlpha(false)
            setInfinite(true)
            setIntervalRatio(0.95f)
        }
    }

    override fun observeData() {
        lifecycle.addObserver(viewModel)
        viewModel.productDetailsData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { productDetailsData->
                lifecycleScope.launch {
                    setPageData(productDetailsData)
                }
            }
        }
    }
    private suspend fun setPageData(productDetailsData:DetailsPageData):Unit = withContext(mainDispatcher){
        controllerProductCharacteristics.setData(productDetailsData.colorAndCapacityData)
        photosAdapter.setData(productDetailsData.photos)
        binding.include.rating.rating = productDetailsData.rating
        binding.include.ramField.text = productDetailsData.ssd
        binding.include.cpuField.text = productDetailsData.CPU
        binding.include.titleProductField.text = productDetailsData.title
        binding.include.cameraField.text = productDetailsData.camera
        binding.include.fieldAmount.text = productDetailsData.price
        binding.include.isFavorite.isSelected = productDetailsData.isFavorites
        binding.include.sdField.text = productDetailsData.sd
        binding.photosProductCarousel.visibility = View.VISIBLE
        binding.include.root.visibility = View.VISIBLE
    }
}