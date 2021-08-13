package com.sungshin.recyclearuser.my.paper.paperlist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ItemPaperListBinding
import com.sungshin.recyclearuser.my.paper.PaperDetailActivity

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
        private var mLastClickTime : Long = 0

        fun onBind(paperListInfo: PaperListInfo) {
            Glide.with(itemView)
                .load(paperListInfo.detect_image)
                .into(binding.imageviewPaperItem)

            binding.textviewPaperDate.text = paperListInfo.detect_date
            binding.textviewPaperPercent.text = paperListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, PaperDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",paperListInfo.detect_date)
                    intent.putExtra("detect_percent",paperListInfo.detect_percent)
                    intent.putExtra("detect_image",paperListInfo.detect_image)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}