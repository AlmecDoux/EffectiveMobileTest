package com.effectivemobiletest.ui.views.bottomNavigationLayout

import android.content.Context
import android.util.AttributeSet
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.marginStart
import androidx.navigation.NavController
import androidx.navigation.ui.onNavDestinationSelected
import com.effectivemobile.test.R

class NavigationBottomBar(context: Context, private val attributeSet: AttributeSet):
    LinearLayoutCompat(context, attributeSet) {
    private val navigationItemsContainer:LinearLayoutCompat? = null
    private var navController: NavController? = null
    init {
        setLayout()
    }
    private fun setLayout() {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.NavigationBottomBar)
        val menuResource = typedArray.getResourceId(R.styleable.NavigationBottomBar_menuLayout, -1)
        val menu: Menu = PopupMenu(context, this).menu
        this.background = ContextCompat.getDrawable(context, R.drawable.dark_rounded_panel)
        MenuInflater(context).inflate(menuResource, menu)
        typedArray.recycle()
        val layoutParams = LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(10, 10, 10, 10)
        this.layoutParams = layoutParams
        this.gravity = Gravity.CENTER
        this.orientation = HORIZONTAL
        this.clipToPadding = true
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
        for (bottomItem in this.children.iterator()){
            if(bottomItem is LinearLayoutCompat){
                val item = bottomItem.getChildAt(0)
                if(item is BottomNavigationItem){
                    item.isSelected = destination == item.getMenuDestination()
                }
            }
        }
    }

    private fun generateLayouts(menu: Menu){
        val countItem = menu.size()
        this.weightSum = countItem.toFloat() + 2
        val textView = inflate(context, R.layout.bottom_bar_text_layout, null) as AppCompatTextView
        val layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT)
        layoutParams.weight = 1.5f
        layoutParams.gravity = Gravity.CENTER
        textView.layoutParams = layoutParams
        this.addView(textView)
        for (item in menu.children){
            this.addView(getImageView(menuItem = item))
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