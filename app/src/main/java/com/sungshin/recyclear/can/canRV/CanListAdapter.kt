package com.sungshin.recyclear.can.canRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ItemCanListBinding

class CanListAdapter  : RecyclerView.Adapter<CanListAdapter.CanListViewHolder>() {

    val canList = mutableListOf<CanListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanListViewHolder {
        val binding = ItemCanListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CanListViewHolder(binding)
    }

    override fun getItemCount(): Int = canList.size

    override fun onBindViewHolder(holder: CanListViewHolder, position: Int) {
        holder.onBind(canList[position])
    }

    class CanListViewHolder(
        private val binding: ItemCanListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(canListInfo: CanListInfo) {
            Glide.with(itemView)
                .load(canListInfo.detect_image)
                .into(binding.imageviewCanItem)

            binding.textviewCanDate.text = canListInfo.detect_date
            binding.textviewCanPercent.text = canListInfo.detect_percent
        }
    }

}