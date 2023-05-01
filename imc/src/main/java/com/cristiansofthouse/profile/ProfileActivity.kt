package com.cristiansofthouse.profile

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cristiansofthouse.common.ResultDialog
import com.cristiansofthouse.profile.databinding.ActivityProfileBinding
import com.cristiansofthouse.profile.model.ProfileActions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModels()

    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
        binding.btnImc.setOnClickListener {
            viewModel.operationImc(
                binding.editWeight.text.toString(),
                binding.editSize.text.toString()
            )
        }
        setObserver()
    }

    private fun setObserver() {
        viewModel.action.observe(this, ::handleActions)
    }

    private fun handleActions(profileActions: ProfileActions) = when (profileActions) {
        is ProfileActions.ShowError -> Toast.makeText(
            this,
            profileActions.message,
            Toast.LENGTH_SHORT
        ).show()
        is ProfileActions.ShowResult -> resultImc(profileActions.result)
    }

    private fun resultImc(result: String) {
        ResultDialog.newInstance(result, binding.age.text.toString().toInt())
            .show(supportFragmentManager, this.javaClass.name)
    }

    private fun save() {
        Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show()
    }
}
