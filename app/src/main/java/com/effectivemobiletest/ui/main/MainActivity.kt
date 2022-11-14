package com.effectivemobiletest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.ActivityMainBinding
import com.effectivemobiletest.App
import com.effectivemobiletest.App.Companion.outLogs
import com.effectivemobiletest.ui.pages.tabs.TabsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by viewBinding(CreateMethod.INFLATE)

    private var navController:NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.startDestination.observe(this){
            it.getContentIfNotHandled()?.let { startDestination->
                findNavController(binding.fragmentContainer.id).findDestination(startDestination)?.let {
                    outLogs(it.displayName)
                }
                outLogs(startDestination.toString())
                val navController = getRootNavController()
                val graph = navController.navInflater.inflate(getMainNavigationGraphId())
                graph.setStartDestination(startDestination)
                navController.graph = graph
                onNavControllerActivated(navController)
                App.outLogs("${navController.graph.displayName}")
                supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, true)
                setContentView(binding.root)
            }
        }
    }

    private fun getRootNavController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        return navHost.navController
    }

    private fun getMainNavigationGraphId(): Int = R.navigation.nav_graph

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            if (f is TabsFragment || f is NavHostFragment) return
            onNavControllerActivated(f.findNavController())
        }
    }
    private fun onNavControllerActivated(navController: NavController) {
        if (this.navController == navController) return
        this.navController = navController
    }
}