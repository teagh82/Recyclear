package com.sungshin.recyclearuser.my.plastic.plasticlist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ItemPlasticListBinding
import com.sungshin.recyclearuser.my.plastic.PlasticDetailActivity

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
        private var mLastClickTime : Long = 0

        fun onBind(plasticListInfo: PlasticListInfo) {
            Glide.with(itemView)
                .load(plasticListInfo.detect_image)
                .into(binding.imageviewPlasticItem)

            binding.textviewPlasticDate.text = plasticListInfo.detect_date
            binding.textviewPlasticPercent.text = plasticListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, PlasticDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",plasticListInfo.detect_date)
                    intent.putExtra("detect_percent",plasticListInfo.detect_percent)
                    intent.putExtra("detect_image",plasticListInfo.detect_image)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}