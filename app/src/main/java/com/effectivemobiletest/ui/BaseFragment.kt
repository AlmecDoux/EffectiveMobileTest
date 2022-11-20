package com.effectivemobiletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.effectivemobiletest.events.NavigationActions

abstract class BaseFragment<VBinding : ViewBinding, ViewModel:BaseViewModel>: Fragment(){

    protected abstract val viewModel: ViewModel
    protected abstract val binding: VBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationObserver()
        setUpViewsBinding()
        observeData()
    }

    open fun setUpViewsBinding(){}

    open fun observeData(){}


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
