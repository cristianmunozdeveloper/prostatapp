package com.cristiansofthouse.prostatapp.information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cristiansofthouse.prostatapp.R
import com.cristiansofthouse.prostatapp.databinding.ActivityInformationBinding

const val SELECTED_INDEX = "SELECTED_INDEX"

class InformationActivity : AppCompatActivity() {

    private val binding: ActivityInformationBinding by lazy {
        ActivityInformationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        when (intent.extras?.getInt(SELECTED_INDEX)) {
            2 -> loadEducationResources()
            3 -> loadAdvisoryResources()
        }
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
    }

    private fun loadAdvisoryResources() {
        val drawable = resources.getDrawable(R.drawable.ic_advisory)
        val text = resources.getString(R.string.advisory)
        binding.imageViewTitle.setImageDrawable(drawable)
        binding.textviewInformation.text = text
    }

    private fun loadEducationResources() {
        val drawable = resources.getDrawable(R.drawable.ic_education)
        val text = resources.getString(R.string.education)
        binding.imageViewTitle.setImageDrawable(drawable)
        binding.textviewInformation.text = text
    }
}
