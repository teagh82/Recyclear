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
                    news_title = "송가인-서경덕, '독립유공자 후손 주거환경 개선' 캠페인",
                    news_date = "2021.08.13",
                    news_link = "https://www.yna.co.kr/view/AKR20210813014200371?input=1195m"
                ),
                NewsListInfo(
                    news_title = "'환경 재앙' 그리스 산불…총리 “기후 위기 대응하겠다“",
                    news_date = "2021.08.13",
                    news_link = "https://www.news1.kr/articles/?4403007"
                ),
                NewsListInfo(
                    news_title = "국민 97.8% “플라스틱 폐기물 환경 오염 심각”",
                    news_date = "2021.08.11",
                    news_link = "https://www.seoul.co.kr/news/newsView.php?id=20210811500061&wlog_tag3=naver"
                ),
                NewsListInfo(
                    news_title = "자연·수행환경 파괴 장안사 인근 폐기물 매립장 반대",
                    news_date = "2021.08.13",
                    news_link = "https://www.pressian.com/pages/articles/2021081317003498717?utm_source=naver&utm_medium=search#0DKU"
                ),
                NewsListInfo(
                    news_title = "포항시, 환경정보 제공 강화해 시민들 불안감 해소",
                    news_date = "2021.08.13",
                    news_link = "http://www.ecolaw.co.kr/news/articleView.html?idxno=93345"
                ),
                NewsListInfo(
                    news_title = "대구환경청, 포항제철소에서 자원순환 홍보 전시회 17일까지 개최",
                    news_date = "2021.08.13",
                    news_link = "https://www.news1.kr/articles/?4403326"
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
                    news_title = "인공지능팩토리, AI로 지구환경 문제 해결 해커톤 주관",
                    news_date = "2021.08.13",
                    news_link = "https://zdnet.co.kr/view/?no=20210813090918"
                ),
                NewsListInfo(
                    news_title = "환경부 '금강 자연성 회복 사업' 시민 설명회…내년 본격 추진",
                    news_date = "2021.08.12",
                    news_link = "https://newsis.com/view/?id=NISX20210812_0001546480&cID=10201&pID=10200"
                )
            )
        )
        // 데이터 변경되었으니 업데이트해라
        newsListAdapter.notifyDataSetChanged()
    }
}
