package com.sungshin.recyclear.metal.metalRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ItemMetalListBinding

class MetalListAdapter : RecyclerView.Adapter<MetalListAdapter.MetalListViewHolder>() {

    val metalList = mutableListOf<MetalListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetalListViewHolder {
        val binding = ItemMetalListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MetalListViewHolder(binding)
    }

    override fun getItemCount(): Int = metalList.size

    override fun onBindViewHolder(holder: MetalListViewHolder, position: Int) {
        holder.onBind(metalList[position])
    }

    class MetalListViewHolder(
        private val binding: ItemMetalListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(metalListInfo: MetalListInfo) {
            Glide.with(itemView)
                .load(metalListInfo.detect_image)
                .into(binding.imageviewMetalItem)

            binding.textviewMetalDate.text = metalListInfo.detect_date
            binding.textviewMetalPercent.text = metalListInfo.detect_percent
        }
    }

}