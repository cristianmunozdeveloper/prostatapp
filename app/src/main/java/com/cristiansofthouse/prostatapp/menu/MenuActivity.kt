package com.cristiansofthouse.prostatapp.menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cristiansofthouse.navigation.Navigation
import com.cristiansofthouse.prostatapp.R
import com.cristiansofthouse.prostatapp.databinding.ActivityMenuBinding
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    @Inject
    lateinit var navigation: Navigation

    private val groupAdapter = GroupieAdapter().apply { spanCount = 2 }

    private val binding: ActivityMenuBinding by lazy {
        ActivityMenuBinding.inflate(layoutInflater)
    }

    private lateinit var menuItems: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecycler()
        addMenuItems()
    }

    private fun navigate(index: Int) {
        Toast.makeText(this, "$index", Toast.LENGTH_SHORT).show()
    }

    private fun addMenuItems() {
        menuItems = resources.getStringArray(R.array.menu_items)
        menuItems.forEachIndexed { index, name ->
            groupAdapter.add(MenuItem(index, name, ::navigate))
        }
    }

    private fun setupRecycler() {
        binding.menuItemsContainer.apply {
            layoutManager =
                GridLayoutManager(this@MenuActivity, groupAdapter.spanCount)
            adapter = groupAdapter
        }
    }
}