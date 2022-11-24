package com.effectivemobiletest.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.effectivemobiletest.events.NavigationActions
import javax.inject.Inject

abstract class BaseFragment<VBinding : ViewBinding, ViewModel:BaseViewModel>: Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: ViewModel
    protected abstract val binding: VBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireContext().injectFragment()
        createViewModel()
        setNavigationObserver()
        setUpViewsBinding()
        viewModel.observeData()
    }
    abstract fun Context.injectFragment()
    abstract fun createViewModel()
    open fun setUpViewsBinding(){}

    open fun ViewModel.observeData(){}


    private fun setNavigationObserver() = this.viewModel.navigateEvent.observe(viewLifecycleOwner){ errorEvent->
        errorEvent.getContentIfNotHandled()?.let { navigateEvent ->
            when (navigateEvent) {
                is NavigationActions.NavigationDirections -> findNavController().navigate(
                    directions = navigateEvent.direction
                )
                NavigationActions.NavigationBack -> findNavController().navigateUp()
            }
        }
    }
}
