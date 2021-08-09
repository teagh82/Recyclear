package com.sungshin.recyclear.plastic.plasticRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ItemPlasticListBinding

class PlasticListAdapter : RecyclerView.Adapter<PlasticListAdapter.PlasticListViewHolder>() {

    val plasticList = mutableListOf<PlasticListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlasticListViewHolder {
        val binding = ItemPlasticListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlasticListViewHolder(binding)
    }

    override fun getItemCount(): Int = plasticList.size

    override fun onBindViewHolder(holder: PlasticListViewHolder, position: Int) {
        holder.onBind(plasticList[position])
    }

    class PlasticListViewHolder(
        private val binding: ItemPlasticListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(plasticListInfo: PlasticListInfo) {
            Glide.with(itemView)
                .load(plasticListInfo.detect_image)
                .into(binding.imageviewPlasticItem)

            binding.textviewPlasticDate.text = plasticListInfo.detect_date
            binding.textviewPlasticPercent.text = plasticListInfo.detect_percent
        }
    }

}