package com.sungshin.recyclear.paper

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
import com.sungshin.recyclear.databinding.FragmentPaperListBinding
import com.sungshin.recyclear.paper.paperlist.PaperListAdapter
import com.sungshin.recyclear.paper.paperlist.PaperListInfo
import com.sungshin.recyclear.utils.FirebaseUtil

class PaperListFragment : Fragment() {
    private var _binding: FragmentPaperListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val paperListAdapter: PaperListAdapter by lazy{ PaperListAdapter() }

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<PaperListInfo>()

    var hasPaper: Boolean = false
    var hasPaper2: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaperListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewPaperImages.adapter = paperListAdapter

        loadDatas()
    }

    private fun loadDatas(){
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    if (userSnapshot.child("unchecked").hasChild("paper box")) {
                        for (imageSnapshot in userSnapshot.child("unchecked").child("paper box").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("image").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPaper = true

                                    datas.apply {
                                        add(
                                            PaperListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred,
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPaper = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("checked").hasChild("paper box")) {
                        for (imageSnapshot in userSnapshot.child("checked").child("paper box").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPaper2 = true

                                    datas.apply {
                                        add(
                                            PaperListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred,
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPaper2 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }
                }

                paperListAdapter.paperList.addAll(
                    datas
                )

                paperListAdapter.notifyDataSetChanged()

                if(hasPaper || hasPaper2){
                    binding.constraintlayoutPaperRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutPaperEmpty.visibility = View.GONE
                }
                else if(!hasPaper && !hasPaper2){
                    binding.constraintlayoutPaperRecycler.visibility = View.GONE
                    binding.constraintlayoutPaperEmpty.visibility = View.VISIBLE
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