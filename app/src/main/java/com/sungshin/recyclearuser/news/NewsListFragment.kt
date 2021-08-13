package com.sungshin.recyclearuser.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sungshin.recyclearuser.databinding.FragmentNewsListBinding
import com.sungshin.recyclearuser.news.newslist.NewsListAdapter
import com.sungshin.recyclearuser.news.newslist.NewsListInfo

class NewsListFragment : Fragment() {
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val newsListAdapter: NewsListAdapter by lazy{ NewsListAdapter() }

    var datas= mutableListOf<NewsListInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewNews.adapter = newsListAdapter
        // 서버 연결 x
        newsListAdapter.newsList.addAll(
            listOf<NewsListInfo>(
                NewsListInfo(
                    news_title = "고양시, 섬유 폐기물을 친환경 자원으로 바꾼다",
                    news_date = "2021.08.10",
                    news_link = "http://www.ecolaw.co.kr/news/articleView.html?idxno=93345"
                ),
                NewsListInfo(
                    news_title = "자연·수행환경 파괴 장안사 인근 폐기물 매립장 반대",
                    news_date = "2021.08.13",
                    news_link = "https://www.beopbo.com/news/articleView.html?idxno=301995"
                ),
                NewsListInfo(
                    news_title = "고양시, 섬유 폐기물을 친환경 자원으로 바꾼다",
                    news_date = "2021.08.10",
                    news_link = "http://www.ecolaw.co.kr/news/articleView.html?idxno=93345"
                ),
                NewsListInfo(
                    news_title = "자연·수행환경 파괴 장안사 인근 폐기물 매립장 반대",
                    news_date = "2021.08.13",
                    news_link = "https://www.beopbo.com/news/articleView.html?idxno=301995"
                ),
                NewsListInfo(
                    news_title = "고양시, 섬유 폐기물을 친환경 자원으로 바꾼다",
                    news_date = "2021.08.10",
                    news_link = "http://www.ecolaw.co.kr/news/articleView.html?idxno=93345"
                ),
                NewsListInfo(
                    news_title = "자연·수행환경 파괴 장안사 인근 폐기물 매립장 반대",
                    news_date = "2021.08.13",
                    news_link = "https://www.beopbo.com/news/articleView.html?idxno=301995"
                ),
                NewsListInfo(
                    news_title = "고양시, 섬유 폐기물을 친환경 자원으로 바꾼다",
                    news_date = "2021.08.10",
                    news_link = "http://www.ecolaw.co.kr/news/articleView.html?idxno=93345"
                ),
                NewsListInfo(
                    news_title = "자연·수행환경 파괴 장안사 인근 폐기물 매립장 반대",
                    news_date = "2021.08.13",
                    news_link = "https://www.beopbo.com/news/articleView.html?idxno=301995"
                )
            )
        )
        // 데이터 변경되었으니 업데이트해라
        newsListAdapter.notifyDataSetChanged()
    }
}
