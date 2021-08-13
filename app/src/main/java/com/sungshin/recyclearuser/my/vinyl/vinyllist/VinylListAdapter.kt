package com.sungshin.recyclearuser.my.vinyl.vinyllist

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclearuser.databinding.ItemVinylListBinding
import com.sungshin.recyclearuser.my.vinyl.VinylDetailActivity

class VinylListAdapter : RecyclerView.Adapter<VinylListAdapter.VinylListViewHolder>() {

    val vinylList = mutableListOf<VinylListInfo>()

//    interface OnItemClickListener{
//        fun onItemClick(v : View, data : VinylListInfo)
//    }
//
//    private var listener : OnItemClickListener? = null
//
//    fun setOnItemClickListener (listener : OnItemClickListener){
//        this.listener = listener
//    }

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
//        holder.onBind(vinylList[position], listener)
        holder.onBind(vinylList[position])
    }

    class VinylListViewHolder(
        private val binding: ItemVinylListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mLastClickTime : Long = 0

        fun onBind(
            vinylListInfo: VinylListInfo
//            listener: VinylListAdapter.OnItemClickListener?
        ) {
            Glide.with(itemView)
                .load(vinylListInfo.detect_image)
                .into(binding.imageviewVinylItem)

            binding.textviewVinylDate.text = vinylListInfo.detect_date
            binding.textviewVinylPercent.text = vinylListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, VinylDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",vinylListInfo.detect_date)
                    intent.putExtra("detect_percent",vinylListInfo.detect_percent)
                    intent.putExtra("detect_image",vinylListInfo.detect_image)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}