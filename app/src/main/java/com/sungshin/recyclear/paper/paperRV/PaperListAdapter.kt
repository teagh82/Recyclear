package com.sungshin.recyclear.paper.paperRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ItemPaperListBinding

class PaperListAdapter : RecyclerView.Adapter<PaperListAdapter.PaperListViewHolder>() {

    val paperList = mutableListOf<PaperListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaperListViewHolder {
        val binding = ItemPaperListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PaperListViewHolder(binding)
    }

    override fun getItemCount(): Int = paperList.size

    override fun onBindViewHolder(holder: PaperListViewHolder, position: Int) {
        holder.onBind(paperList[position])
    }

    class PaperListViewHolder(
        private val binding: ItemPaperListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(paperListInfo: PaperListInfo) {
            Glide.with(itemView)
                .load(paperListInfo.detect_image)
                .into(binding.imageviewPaperItem)

            binding.textviewPaperDate.text = paperListInfo.detect_date
            binding.textviewPaperPercent.text = paperListInfo.detect_percent
        }
    }

}