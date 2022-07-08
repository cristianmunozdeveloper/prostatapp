package com.cristiansofthouse.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cristiansofthouse.profile.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.tvImc.visibility = View.INVISIBLE
        binding.tvImcResult.visibility = View.INVISIBLE
        binding.btnSave.setOnClickListener { save() }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
        binding.btnImc.setOnClickListener { calculateImc() }
    }

    private fun calculateImc() {
        binding.tvImc.visibility = View.VISIBLE
        binding.tvImcResult.visibility = View.VISIBLE
        val weight = binding.editWeight.text.toString().toFloat()
        val size = binding.editSize.text.toString().toFloat() / 100
        val result = weight / (size * size)
        binding.tvImcResult.text = result.toString()
    }

    private fun save() {
        Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show()
    }
}
