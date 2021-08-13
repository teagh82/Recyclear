package com.sungshin.recyclearuser.news.newslist

import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sungshin.recyclearuser.databinding.ItemNewsListBinding

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder>() {

    val newsList = mutableListOf<NewsListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val binding = ItemNewsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        Log.d("newss", "check")
        return NewsListViewHolder(binding)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        holder.onBind(newsList[position])
    }

    class NewsListViewHolder(
        private val binding: ItemNewsListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mLastClickTime : Long = 0

        fun onBind(newsListInfo: NewsListInfo) {
            binding.textviewNewsTitle.text = newsListInfo.news_title
            binding.textviewNewsDate.text = newsListInfo.news_date

            // item click event
            itemView.setOnClickListener {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsListInfo.news_link))

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}