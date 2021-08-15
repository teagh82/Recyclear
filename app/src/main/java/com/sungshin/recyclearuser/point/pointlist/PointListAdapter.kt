package com.sungshin.recyclearuser.point.pointlist

import android.content.Intent
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ItemPointListBinding

class PointListAdapter : RecyclerView.Adapter<PointListAdapter.PointListViewHolder>() {

    val pointList = mutableListOf<PointListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointListViewHolder {
        val binding = ItemPointListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PointListViewHolder(binding)
    }

    override fun getItemCount(): Int = pointList.size

    override fun onBindViewHolder(holder: PointListViewHolder, position: Int) {
        holder.onBind(pointList[position])
    }

    class PointListViewHolder(
        private val binding: ItemPointListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mLastClickTime : Long = 0

        fun onBind(pointListInfo: PointListInfo) {
            Glide.with(itemView)
                .load(pointListInfo.detect_image)
                .into(binding.imageviewPointItem)

            binding.textviewPointDate.text = pointListInfo.detect_date
            binding.textviewPointLabel.text = pointListInfo.detect_label
        }
    }

}