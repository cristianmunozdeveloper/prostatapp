package com.cristiansofthouse.information

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristiansofthouse.information.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity(), MakeCallListener {

    private val binding: ActivityInformationBinding by lazy {
        ActivityInformationBinding.inflate(layoutInflater)
    }

    private val epsAdapter = EpsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.imageviewBackButton.setOnClickListener { onBackPressed() }
        binding.epsList.adapter = epsAdapter
        binding.epsList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        fillList()
    }

    override fun makeCall(phoneNumber: String) {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CALL_PHONE),
                1
            )
        } else {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))
            ContextCompat.startActivity(binding.root.context, intent, null)
        }
    }

    private fun fillList() {
        val epsList = mutableListOf<Eps>()
        val titles = resources.getStringArray(R.array.lista_de_eps)
        val phoneNumbers = resources.getStringArray(R.array.lista_de_telefonos)
        titles.forEachIndexed { index, title ->
            epsList.add(Eps(title, phoneNumbers[index]))
        }
        epsAdapter.eps = epsList
    }
}

interface MakeCallListener {
    fun makeCall(phoneNumber: String)
}
