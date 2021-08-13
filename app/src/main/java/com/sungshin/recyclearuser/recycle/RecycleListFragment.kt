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
                    image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    content = "비닐은 뽁뽁이 등이 있다..",
                    title = "비닐은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    content = "비닐은 뽁뽁이 등이 있다..",
                    title = "비닐은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    content = "비닐은 뽁뽁이 등이 있다..",
                    title = "비닐은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    content = "비닐은 뽁뽁이 등이 있다..",
                    title = "비닐은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    content = "비닐은 뽁뽁이 등이 있다..",
                    title = "비닐은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    content = "비닐은 뽁뽁이 등이 있다..",
                    title = "비닐은 어떻게?"
                ),
                RecycleListInfo(
                    image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    content = "비닐은 뽁뽁이 등이 있다..",
                    title = "비닐은 어떻게?"
                ),



            )
        )
        // 데이터 변경되었으니 업데이트해라
        recycleListAdapter.notifyDataSetChanged()
    }
}