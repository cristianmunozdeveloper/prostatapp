package com.cristiansofthouse.prostatapp.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cristiansofthouse.navigation.Navigation
import com.cristiansofthouse.prostatapp.R
import com.cristiansofthouse.prostatapp.databinding.ActivityMenuBinding
import com.cristiansofthouse.prostatapp.information.InformationActivity
import com.cristiansofthouse.prostatapp.information.SELECTED_INDEX
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
        profileListener()
    }

    private fun profileListener() {
        binding.imageviewProfile.setOnClickListener { startActivity(navigation.goToProfile(this)) }
    }

    private fun navigate(index: Int) {
        val intent = when (index) {
            0 -> navigation.goToProstatest(this)
            1 -> navigation.goToTabuTest(this)
            2, 3 -> goToInformation(index)
            else -> Intent()
        }
        startActivity(intent)
    }

    private fun goToInformation(index: Int): Intent {
        return Intent(this, InformationActivity::class.java).apply {
            putExtra(SELECTED_INDEX, index)
        }
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
