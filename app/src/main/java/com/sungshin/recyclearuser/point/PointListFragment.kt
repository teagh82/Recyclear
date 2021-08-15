package com.sungshin.recyclearuser.point

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sungshin.recyclearuser.databinding.FragmentPointListBinding
import com.sungshin.recyclearuser.point.pointlist.PointListAdapter
import com.sungshin.recyclearuser.point.pointlist.PointListInfo
import com.sungshin.recyclearuser.utils.FirebaseUtil
import com.sungshin.recyclearuser.utils.MyPref


class PointListFragment : Fragment() {
    private var _binding: FragmentPointListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val pointListAdapter: PointListAdapter by lazy{ PointListAdapter() }

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

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

        loadDatas()
    }

    private fun loadDatas() {
        val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]
        Log.d("FIREBASE", saveIDdata)
        Log.d("FIREBASE", "Start Loading")

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (classSnapshot in dataSnapshot.child("unchecked").children) {
                    classSnapshot.key?.let { Log.d("FIREBASE", it) }
                    for (imageSnapshot in classSnapshot.children) {
                        imageSnapshot.key?.let { Log.d("FIREBASE", it) }
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile =
                                imageSnapshot.child("pred").getValue(String::class.java)
                            val label = classSnapshot.key

                            if (date != null && imageFile != null && label != null) {
                                datas.apply {
                                    add(
                                        PointListInfo(
                                            detect_image = imageFile,
                                            detect_label = label,
                                            detect_date = date
                                        )
                                    )
                                }

                                pointListAdapter.pointList.addAll(
                                    datas
                                )

                                // 데이터 변경되었으니 업데이트해라
                                pointListAdapter.notifyDataSetChanged()

                                Log.d("FIREBASE", "date: $date / img: $imageFile / label: $label")
                            }
                        }

                        else {
                            Log.d("FIREBASE", "not hasChildren")
                        }
                    }
                }

                Log.d("FIREBASE", "Load Done")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", error.message)
            }
        }

        val classRef = database.reference.child("User").child(saveIDdata)
        classRef.addValueEventListener(valueEventListener)
    }
}