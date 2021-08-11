package com.sungshin.recyclear.metal.metallist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.databinding.ItemMetalListBinding
import com.sungshin.recyclear.metal.MetalDetailActivity

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
        private var mLastClickTime : Long = 0
        
        fun onBind(metalListInfo: MetalListInfo) {
            Glide.with(itemView)
                .load(metalListInfo.detect_image)
                .into(binding.imageviewMetalItem)

            binding.textviewMetalDate.text = metalListInfo.detect_date
            binding.textviewMetalPercent.text = metalListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, MetalDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",metalListInfo.detect_date)
                    intent.putExtra("detect_percent",metalListInfo.detect_percent)
                    intent.putExtra("detect_image",metalListInfo.detect_image)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}