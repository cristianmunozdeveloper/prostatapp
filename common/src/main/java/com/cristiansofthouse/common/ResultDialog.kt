package com.cristiansofthouse.common

import android.content.Context
import android.icu.lang.UProperty.AGE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.cristiansofthouse.common.databinding.FragmentResultDialogBinding

class ResultDialog : DialogFragment() {

    companion object {
        const val DIALOG_MESSAGE = "dialog_message"
        const val AGE = "age"
        fun newInstance(text: String, age: Int? = null): ResultDialog =
            ResultDialog().apply {
                arguments = Bundle().apply {
                    putString(DIALOG_MESSAGE, text)
                    age?.let {
                        putInt(AGE, age)
                    }
                }
            }
    }

    private var _binding: FragmentResultDialogBinding? = null

    private val binding get() = _binding!!

    private var listener: ResultDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ResultDialogListener) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val age = arguments?.getInt(AGE)
        val messageExtra = arguments?.getString(DIALOG_MESSAGE)
        var imcResult: String? = null
        age?.takeIf { it != 0 }?.let {
            imcResult = String.format(getString(R.string.imc), messageExtra)
            val imcTable =
                if (age >= 60) R.drawable.table_imc_adulto_mayor else R.drawable.tabla_imc
            binding.imcTable.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    imcTable,
                    null
                )
            )
        } ?: kotlin.run { binding.imcTable.isVisible = false }

        binding.textViewMessage.text = imcResult ?: messageExtra

        binding.btnOk.setOnClickListener {
            dismiss()
            listener?.callback()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface ResultDialogListener {
    fun callback()
}
