package com.effectivemobiletest.ui.pages.detailsProductPage

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.domain.annotations.MainDispatcher
import com.effectivemobile.test.databinding.DetailProductLayoutBinding
import com.effectivemobiletest.adapters.ProductFeatureAdapter
import com.effectivemobiletest.adapters.minorAdapters.ProductPhotosAdapter
import com.effectivemobiletest.appComponent
import com.effectivemobiletest.decorations.layoutManagers.CenterZoomLinearLayoutManager
import com.effectivemobiletest.ui.BaseFragment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsProductFragment : BaseFragment<DetailProductLayoutBinding, DetailProductViewModel>() {

    @Inject
    @MainDispatcher
    lateinit var mainDispatcher: CoroutineDispatcher

    override val binding: DetailProductLayoutBinding by viewBinding(CreateMethod.INFLATE)

    override fun Context.injectFragment() {
        appComponent.inject(this@DetailsProductFragment)
    }

    override fun createViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailProductViewModel::class.java]
    }

    override fun setUpViewsBinding() {
        binding.include.isFavorite.setOnClickListener{
            binding.include.isFavorite.isSelected = !it.isSelected
        }
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.marketBtn.setOnClickListener {
            findNavController().navigate(DetailsProductFragmentDirections.actionDetailsProductFragmentToCartPageFragment())
        }
    }
    override fun DetailProductViewModel.observeData() {
        viewModel.productDetailsData.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { productDetailsData->
                lifecycleScope.launch {
                    setPageData(productDetailsData)
                }
            }
        }
        viewModel.getProductData()
    }

    private suspend fun setPageData(productDetailsData:DetailsPageData):Unit = withContext(mainDispatcher){
        binding.include.colorAndCapacityRecycler.adapter = ProductFeatureAdapter().apply {
            items = productDetailsData.colorAndCapacityData
        }
        val photosAdapter = ProductPhotosAdapter()
        binding.photosProductCarousel.adapter = photosAdapter
        binding.photosProductCarousel.layoutManager = CenterZoomLinearLayoutManager(requireContext())
        binding.photosProductCarousel.apply {
            set3DItem(false)
            setAlpha(false)
            setInfinite(true)
            setIntervalRatio(0.95f)
        }
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