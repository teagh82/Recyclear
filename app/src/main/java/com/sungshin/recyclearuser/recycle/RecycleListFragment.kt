package com.sungshin.recyclearuser.recycle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sungshin.recyclearuser.databinding.FragmentRecycleListBinding
import com.sungshin.recyclearuser.recycle.recyclelist.RecycleListAdapter
import com.sungshin.recyclearuser.recycle.recyclelist.RecycleListInfo

class RecycleListFragment : Fragment() {
    private var _binding: FragmentRecycleListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val recycleListAdapter: RecycleListAdapter by lazy{ RecycleListAdapter() }

    var datas= mutableListOf<RecycleListInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecycleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewRecycle.adapter = recycleListAdapter

        // 서버 연결 x
        recycleListAdapter.recycleList.addAll(
            listOf<RecycleListInfo>(
                RecycleListInfo(
                    image = "https://image.msscdn.net/images/goods_img/20200210/1298440/1298440_1_500.jpg",
                    content = "비닐은 색상이나 재활용 마크에 관계없이 깨끗이 씻어 투명비닐봉투에 담아 배출해야한다.",
                    title = "비닐은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://jmagazine.joins.com/_data/photo/2020/06/3698936108_Sn9C7dOw_1.jpg",
                    content = "플라스틱류는 용기 안에 들어있는 내용물을 깨끗이 비운 후, 부착되어 있는 상표를 제거하고 뚜껑 등 다른 재질로 되어있는 부분도 분리해야한다.\n가능한 압착하여 버린다.",
                    title = "플라스틱 분리 배출 방법!"
                ),
                RecycleListInfo(
                    image = "https://m.economictimes.com/thumb/msid-61941670,width-1200,height-900,resizemode-4,imgsize-25928/better-valuations-of-paper-companies-just-a-matter-of-time.jpg",
                    content = "비닐 코팅된 표지나 제본 스프링 등은 분리하여 배출해야한다.\n종이컵은 내용물을 비운 뒤 배출해야 한다.",
                    title = "종이 분리수거 📄"
                ),
                RecycleListInfo(
                    image = "https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/pd/19/1/6/8/0/7/4/TpQIr/425168074_B.jpg",
                    content = "내용물을 비운 뒤 배출해야 한다. 뚜껑은 제거해야 한다.",
                    title = "캔은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://gainglobal.kr/data/item/1529372894/thumb-6riw_400x400.jpg",
                    content = "뚜껑을 제거 후 내용물을 비우고 배출해야 한다. 깨진 유리는 소량이라면 신문지에 싸서 종량제 봉투에 버린다. 양이 많은 경우 특수규격마대를 구매하여 배출한다.",
                    title = "유리병 잘 버리기"
                ),
                RecycleListInfo(
                    image = "https://pbs.twimg.com/profile_images/1081070799358849024/4g67jhaR.jpg",
                    content = "주요 거점(동주민센터, 주택가 골목 등)에 설치된 전용 수거함에 배출한다.",
                    title = "건전지를 버리는 방법"
                ),
                RecycleListInfo(
                    image = "",
                    content = "",
                    title = ""
                ),
            )
        )
        // 데이터 변경되었으니 업데이트해라
        recycleListAdapter.notifyDataSetChanged()
    }
}