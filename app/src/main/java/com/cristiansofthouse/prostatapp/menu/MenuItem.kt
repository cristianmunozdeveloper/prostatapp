package com.cristiansofthouse.prostatapp.menu

import android.view.View
import com.cristiansofthouse.prostatapp.R
import com.cristiansofthouse.prostatapp.databinding.ViewMenuItemBinding
import com.xwray.groupie.viewbinding.BindableItem

class MenuItem(
    private val index: Int,
    private val name: String,
    private val navigator: (Int) -> Unit
) : BindableItem<ViewMenuItemBinding>() {

    override fun bind(viewBinding: ViewMenuItemBinding, position: Int) {
        viewBinding.menuItemName.text = name
        viewBinding.root.setOnClickListener { navigator.invoke(index) }
    }

    override fun getLayout(): Int = R.layout.view_menu_item

    override fun initializeViewBinding(view: View): ViewMenuItemBinding =
        ViewMenuItemBinding.bind(view)
}
