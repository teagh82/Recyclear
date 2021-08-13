package com.sungshin.recyclearuser.recycle.recyclelist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ItemRecycleListBinding

class RecycleListAdapter  : RecyclerView.Adapter<RecycleListAdapter.RecycleListViewHolder>() {

    val recycleList = mutableListOf<RecycleListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleListViewHolder {
        val binding = ItemRecycleListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecycleListViewHolder(binding)
    }

    override fun getItemCount(): Int = recycleList.size

    override fun onBindViewHolder(holder: RecycleListViewHolder, position: Int) {
        holder.onBind(recycleList[position])
    }

    class RecycleListViewHolder(
        private val binding: ItemRecycleListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mLastClickTime : Long = 0

        fun onBind(recycleListInfo: RecycleListInfo) {
            Glide.with(itemView)
                .load(recycleListInfo.image)
                .into(binding.imageviewRecycle)

            binding.textviewRecycleTitle.text = recycleListInfo.title
            binding.textviewRecycleContent.text = recycleListInfo.content
        }
    }

}