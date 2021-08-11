package com.sungshin.recyclear.can.canlist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.can.CanDetailActivity
import com.sungshin.recyclear.databinding.ItemCanListBinding

class CanListAdapter  : RecyclerView.Adapter<CanListAdapter.CanListViewHolder>() {

    val canList = mutableListOf<CanListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CanListViewHolder {
        val binding = ItemCanListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CanListViewHolder(binding)
    }

    override fun getItemCount(): Int = canList.size

    override fun onBindViewHolder(holder: CanListViewHolder, position: Int) {
        holder.onBind(canList[position])
    }

    class CanListViewHolder(
        private val binding: ItemCanListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mLastClickTime : Long = 0

        fun onBind(canListInfo: CanListInfo) {
            Glide.with(itemView)
                .load(canListInfo.detect_image)
                .into(binding.imageviewCanItem)

            binding.textviewCanDate.text = canListInfo.detect_date
            binding.textviewCanPercent.text = canListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, CanDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",canListInfo.detect_date)
                    intent.putExtra("detect_percent",canListInfo.detect_percent)
                    intent.putExtra("detect_image",canListInfo.detect_image)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}