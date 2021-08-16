package com.sungshin.recyclear.check

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sungshin.recyclear.check.checklist.CheckListAdapter
import com.sungshin.recyclear.check.checklist.CheckListInfo
import com.sungshin.recyclear.databinding.FragmentCheckListBinding
import com.sungshin.recyclear.utils.FirebaseUtil

class CheckListFragment : Fragment() {
    private var _binding: FragmentCheckListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val checkListAdapter: CheckListAdapter by lazy{ CheckListAdapter() }

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<CheckListInfo>()

    var hasCheck: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewCheckImages.adapter = checkListAdapter

        loadDatas()
    }

    // 서버 연결
    private fun loadDatas(){
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (classSnapshot in dataSnapshot.children) {
                    for (imageSnapshot in classSnapshot.children) {
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile =
                                imageSnapshot.child("origin").getValue(String::class.java)
                            val imageFileName = imageSnapshot.key.toString()
                            val pred = imageSnapshot.child("pred").getValue(String::class.java)
                            val className = classSnapshot.key.toString()

                            if (date != null && imageFile != null && pred != null) {
                                hasCheck = true

                                datas.apply {
                                    add(
                                        CheckListInfo(
                                            detect_image = imageFile,
                                            detect_image_name = imageFileName,
                                            detect_percent = pred,
                                            detect_date = date,
                                            detect_class = className
                                        )
                                    )
                                }

                                Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                            }
                            else{
                                hasCheck = false
                            }
                        }

                        else {
                            Log.d("FIREBASE", "not hasChildren")
                        }
                    }
                }

                checkListAdapter.checkList.addAll(
                    datas
                )

                checkListAdapter.notifyDataSetChanged()

                if(hasCheck){
                    binding.constraintlayoutCheckRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutCheckEmpty.visibility = View.GONE
                }
                else if(!hasCheck){
                    binding.constraintlayoutCheckRecycler.visibility = View.GONE
                    binding.constraintlayoutCheckEmpty.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", error.message)
            }
        }

        val classRef = database.reference.child("Admin")
        classRef.addValueEventListener(valueEventListener)
    }
}