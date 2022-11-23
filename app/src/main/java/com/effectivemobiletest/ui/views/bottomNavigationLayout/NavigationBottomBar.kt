package com.effectivemobiletest.ui.views.bottomNavigationLayout

import android.content.Context
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.children
import androidx.core.view.iterator
import androidx.navigation.NavController
import androidx.navigation.ui.onNavDestinationSelected
import com.effectivemobile.test.R
import com.effectivemobile.test.databinding.BottomNavigationLayoutBinding
import com.effectivemobiletest.App.Companion.outLogs

class NavigationBottomBar(context: Context, private val attributeSet: AttributeSet):
    LinearLayoutCompat(context, attributeSet) {
    private var binding: BottomNavigationLayoutBinding
    private var navController: NavController? = null
    init {
        binding = BottomNavigationLayoutBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)
        setLayout()
    }
    private fun setLayout() {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.NavigationBottomBar)
        val menuResource = typedArray.getResourceId(R.styleable.NavigationBottomBar_menuLayout, -1)
        val menu: Menu = PopupMenu(context, this).menu
        MenuInflater(context).inflate(menuResource, menu)
        typedArray.recycle()
        generateLayouts(menu = menu)
    }

    fun setupWithNavController(navController: NavController){
        this.navController = navController
        this.navController?.addOnDestinationChangedListener { _, destination, _ ->
            val graph = destination.parent
            graph?.let {
                setSelected(destination = it.id)
            }
        }
    }
    private fun setSelected(destination: Int){
        for (bottomItem in binding.navigationLayout.iterator()){
            if (bottomItem is BottomNavigationItem) {
                bottomItem.isSelected = destination == bottomItem.getMenuDestination()
            }
        }
    }

    fun setBadge(value:Int){
        for (bottomItem in binding.navigationLayout.iterator()){
            if (bottomItem is BottomNavigationItem) {
                if(bottomItem.getMenuDestination() == R.id.cartPageFragment){
                    bottomItem.setBadge(value)
                }
            }
        }
    }
    private fun generateLayouts(menu: Menu){
        val countItem = menu.size()
        binding.navigationLayout.weightSum = countItem.toFloat()
        for (item in menu.children){
            binding.navigationLayout.addView(getImageView(menuItem = item))
        }
    }

    private fun getImageView(menuItem: MenuItem):BottomNavigationItem{
        val image = BottomNavigationItem(context = context, attributeSet = attributeSet)
        image.setNavigationMenuItem(navigationItem = menuItem)

        image.setOnClickListener{
            navController?.let{ image.navigationItem?.onNavDestinationSelected(navController!!)}
        }
        return image
    }

}