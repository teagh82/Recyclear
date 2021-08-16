package com.sungshin.recyclear.can

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sungshin.recyclear.can.canlist.CanListAdapter
import com.sungshin.recyclear.can.canlist.CanListInfo
import com.sungshin.recyclear.databinding.FragmentCanListBinding
import com.sungshin.recyclear.utils.FirebaseUtil

class CanListFragment : Fragment() {
    private var _binding: FragmentCanListBinding? = null
    private val binding get() = _binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val canListAdapter: CanListAdapter by lazy { CanListAdapter() }

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas = mutableListOf<CanListInfo>()

    var hasCan: Boolean = false
    var hasCan2: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCanListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewCanImages.adapter = canListAdapter

        loadDatas()
    }

    // 서버 연결
    private fun loadDatas(){
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    userSnapshot.key?.let { Log.d("FIREBASE", it) }
                    if (userSnapshot.child("unchecked").hasChild("can")) {
                        for (imageSnapshot in userSnapshot.child("unchecked").child("can").children) {
                            if (imageSnapshot.hasChildren()) {
                                imageSnapshot.key?.let { Log.d("FIREBASE", it) }
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("image").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasCan = true

                                    datas.apply {
                                        add(
                                            CanListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred,
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "unchecked")
                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasCan = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("checked").hasChild("can")) {
                        for (imageSnapshot in userSnapshot.child("checked").child("can").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasCan2 = true

                                    datas.apply {
                                        add(
                                            CanListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred,
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                    Log.d("FIREBASE", "checked")
                                }
                                else{
                                    hasCan2 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }
                }

                canListAdapter.canList.addAll(
                    datas
                )

                canListAdapter.notifyDataSetChanged()

                if(hasCan || hasCan2){
                    binding.constraintlayoutCanRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutCanEmpty.visibility = View.GONE
                }
                else if(!hasCan && !hasCan2){
                    binding.constraintlayoutCanRecycler.visibility = View.GONE
                    binding.constraintlayoutCanEmpty.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", error.message)
            }
        }

        val classRef = database.reference.child("User")
        classRef.addValueEventListener(valueEventListener)
    }
}