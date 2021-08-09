package com.sungshin.recyclear.glass.glassRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ItemGlassListBinding

class GlassListAdapter : RecyclerView.Adapter<GlassListAdapter.GlassListViewHolder>() {

    val glassList = mutableListOf<GlassListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassListViewHolder {
        val binding = ItemGlassListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GlassListViewHolder(binding)
    }

    override fun getItemCount(): Int = glassList.size

    override fun onBindViewHolder(holder: GlassListViewHolder, position: Int) {
        holder.onBind(glassList[position])
    }

    class GlassListViewHolder(
        private val binding: ItemGlassListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(glassListInfo: GlassListInfo) {
            Glide.with(itemView)
                .load(glassListInfo.detect_image)
                .into(binding.imageviewGlassItem)

            binding.textviewGlassDate.text = glassListInfo.detect_date
            binding.textviewGlassPercent.text = glassListInfo.detect_percent
        }
    }

}