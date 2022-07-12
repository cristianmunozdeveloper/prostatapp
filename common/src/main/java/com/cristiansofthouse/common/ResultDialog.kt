package com.cristiansofthouse.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.cristiansofthouse.common.databinding.FragmentResultDialogBinding

class ResultDialog : DialogFragment() {

    companion object {
        const val DIALOG_MESSAGE = "dialog_message"
        fun newInstance(message: String): ResultDialog =
            ResultDialog().apply {
                arguments = Bundle().apply {
                    putString(DIALOG_MESSAGE, message)
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
        binding.textViewMessage.text = arguments?.getString(DIALOG_MESSAGE)
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
