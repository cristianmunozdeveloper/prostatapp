package com.cristiansofthouse.information

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.cristiansofthouse.information.EpsAdapter.EpsHolder
import com.cristiansofthouse.information.databinding.EpsItemViewBinding

class EpsAdapter(val makeCall: MakeCallListener) : RecyclerView.Adapter<EpsHolder>() {

    var eps = mutableListOf<Eps>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class EpsHolder(view: View) : ViewHolder(view) {
        private val binding = EpsItemViewBinding.bind(view)

        fun bind(eps: Eps, makeCall: MakeCallListener) {
            binding.root.layoutParams =
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            binding.title.text = eps.title
            binding.phone.text = eps.phoneNumber
            binding.makeCall.setOnClickListener {
                makeCall.makeCall(eps.phoneNumber)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpsHolder {
        return EpsHolder(LayoutInflater.from(parent.context).inflate(R.layout.eps_item_view, null))
    }

    override fun getItemCount(): Int = eps.size

    override fun onBindViewHolder(holder: EpsHolder, position: Int) {
        holder.bind(eps[position], makeCall)
    }
}
