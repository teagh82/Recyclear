package com.sungshin.recyclearuser.point

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sungshin.recyclearuser.databinding.FragmentPointListBinding
import com.sungshin.recyclearuser.point.pointlist.PointListAdapter
import com.sungshin.recyclearuser.point.pointlist.PointListInfo


class PointListFragment : Fragment() {
    private var _binding: FragmentPointListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val pointListAdapter: PointListAdapter by lazy{ PointListAdapter() }

    var datas= mutableListOf<PointListInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPointListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewPointImages.adapter = pointListAdapter

        // 서버 연결 x
        pointListAdapter.pointList.addAll(
            listOf<PointListInfo>(
                PointListInfo(
                    detect_image = "https://image.msscdn.net/images/goods_img/20200210/1298440/1298440_1_500.jpg",
                    detect_date = "2021-08-14",
                    detect_label = "vinyl"
                ),
                PointListInfo(
                    detect_image = "https://image.msscdn.net/images/goods_img/20200210/1298440/1298440_1_500.jpg",
                    detect_date = "2021-08-14",
                    detect_label = "vinyl"
                ),
            )
        )
        // 데이터 변경되었으니 업데이트해라
        pointListAdapter.notifyDataSetChanged()
    }
}