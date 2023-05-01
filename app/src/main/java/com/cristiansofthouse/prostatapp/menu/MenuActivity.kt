package com.cristiansofthouse.prostatapp.menu

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.cristiansofthouse.common.ResultDialog
import com.cristiansofthouse.navigation.Destination
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
        imcListener()
        binding.imageviewAboutUs.setOnClickListener { aboutUs() }
    }

    private fun imcListener() {
        val text = getString(R.string.calcula_tu_indice_de_masa_corporal_imc)
        binding.textviewImc.text =
            SpannableString(text).apply { setSpan(UnderlineSpan(), 0, text.length, 0) }
        binding.textviewImc.setOnClickListener { goToImc() }
        binding.imageviewImc.setOnClickListener { goToImc() }
    }

    private fun goToImc() {
        startActivity(
            navigation.goTo(
                this,
                Destination.Imc
            )
        )
    }

    private fun navigate(destination: Destination) {
        startActivity(navigation.goTo(this, destination))
    }

    private fun aboutUs() {
        ResultDialog.newInstance(getString(R.string.about_us))
            .show(supportFragmentManager, this.javaClass.name)
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
