package com.sungshin.recyclearuser.my.glass.glasslist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ItemGlassListBinding
import com.sungshin.recyclearuser.my.glass.GlassDetailActivity

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
        private var mLastClickTime : Long = 0

        fun onBind(glassListInfo: GlassListInfo) {
            Glide.with(itemView)
                .load(glassListInfo.detect_image)
                .into(binding.imageviewGlassItem)

            binding.textviewGlassDate.text = glassListInfo.detect_date
            binding.textviewGlassPercent.text = glassListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, GlassDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",glassListInfo.detect_date)
                    intent.putExtra("detect_percent",glassListInfo.detect_percent)
                    intent.putExtra("detect_image",glassListInfo.detect_image)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}