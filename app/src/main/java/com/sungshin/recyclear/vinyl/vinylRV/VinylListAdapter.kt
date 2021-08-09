package com.sungshin.recyclear.vinyl.vinylRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ItemVinylListBinding

class VinylListAdapter : RecyclerView.Adapter<VinylListAdapter.VinylListViewHolder>() {

    val vinylList = mutableListOf<VinylListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VinylListViewHolder {
        val binding = ItemVinylListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VinylListViewHolder(binding)
    }

    override fun getItemCount(): Int = vinylList.size

    override fun onBindViewHolder(holder: VinylListViewHolder, position: Int) {
        holder.onBind(vinylList[position])
    }

    class VinylListViewHolder(
        private val binding: ItemVinylListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(vinylListInfo: VinylListInfo) {
            Glide.with(itemView)
                .load(vinylListInfo.detect_image)
                .into(binding.imageviewVinylItem)

            binding.textviewVinylDate.text = vinylListInfo.detect_date
            binding.textviewVinylPercent.text = vinylListInfo.detect_percent
        }
    }

}