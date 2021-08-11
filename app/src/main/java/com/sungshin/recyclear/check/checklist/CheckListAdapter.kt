package com.sungshin.recyclear.check.checklist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.check.CheckDetailActivity
import com.sungshin.recyclear.databinding.ItemCheckListBinding

class CheckListAdapter : RecyclerView.Adapter<CheckListAdapter.CheckListViewHolder>() {

    val checkList = mutableListOf<CheckListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListViewHolder {
        val binding = ItemCheckListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CheckListViewHolder(binding)
    }

    override fun getItemCount(): Int = checkList.size

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int) {
        holder.onBind(checkList[position])
    }

    class CheckListViewHolder(
        private val binding: ItemCheckListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mLastClickTime : Long = 0

        fun onBind(checkListInfo: CheckListInfo) {
            Glide.with(itemView)
                .load(checkListInfo.detect_image)
                .into(binding.imageviewCheckItem)

            binding.textviewCheckDate.text = checkListInfo.detect_date
            binding.textviewCheckPercent.text = checkListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, CheckDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",checkListInfo.detect_date)
                    intent.putExtra("detect_percent",checkListInfo.detect_percent)
                    intent.putExtra("detect_image",checkListInfo.detect_image)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}