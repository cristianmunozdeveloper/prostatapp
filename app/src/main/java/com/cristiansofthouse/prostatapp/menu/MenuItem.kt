package com.cristiansofthouse.prostatapp.menu

import android.view.View
import com.cristiansofthouse.navigation.Destination
import com.cristiansofthouse.navigation.Destination.AboutMe
import com.cristiansofthouse.navigation.Destination.AnyDoubt
import com.cristiansofthouse.navigation.Destination.HowToTakeCareOfMe
import com.cristiansofthouse.navigation.Destination.MythsAboutMe
import com.cristiansofthouse.navigation.Destination.WhatToDoIfIGetSick
import com.cristiansofthouse.navigation.Destination.WhyImSick
import com.cristiansofthouse.prostatapp.R
import com.cristiansofthouse.prostatapp.databinding.ViewMenuItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class MenuItem(
    private val index: Int,
    private val name: String,
    private val navigator: (Destination) -> Unit
) : BindableItem<ViewMenuItemBinding>() {

    override fun bind(viewBinding: ViewMenuItemBinding, position: Int) {
        viewBinding.menuItemName.text = name
        viewBinding.root.setOnClickListener { navigator.invoke(goTo(index)) }
    }

    private fun goTo(index: Int): Destination {
        return when (index) {
            0 -> AboutMe
            1 -> HowToTakeCareOfMe
            2 -> WhyImSick
            3 -> MythsAboutMe
            4 -> WhatToDoIfIGetSick
            else -> AnyDoubt
        }
    }

    override fun getLayout(): Int = R.layout.view_menu_item

    override fun initializeViewBinding(view: View): ViewMenuItemBinding =
        ViewMenuItemBinding.bind(view)
}
