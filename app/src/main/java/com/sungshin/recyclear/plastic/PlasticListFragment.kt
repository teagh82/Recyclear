package com.sungshin.recyclear.plastic

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
import com.sungshin.recyclear.can.canlist.CanListInfo
import com.sungshin.recyclear.databinding.FragmentPlasticListBinding
import com.sungshin.recyclear.plastic.plasticlist.PlasticListAdapter
import com.sungshin.recyclear.plastic.plasticlist.PlasticListInfo
import com.sungshin.recyclear.utils.FirebaseUtil

class PlasticListFragment : Fragment() {
    private var _binding: FragmentPlasticListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val plasticListAdapter: PlasticListAdapter by lazy{ PlasticListAdapter() }

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<PlasticListInfo>()

    var hasPet: Boolean = false
    var hasPet2: Boolean = false
    var hasPet3: Boolean = false
    var hasPet4: Boolean = false
    var hasPet5: Boolean = false
    var hasPet6: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlasticListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewPlasticImages.adapter = plasticListAdapter

        loadDatas()
    }

    private fun loadDatas(){
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    if (userSnapshot.child("unchecked").hasChild("pet")) {
                        for (imageSnapshot in userSnapshot.child("unchecked").child("pet").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("image").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPet = true

                                    datas.apply {
                                        add(
                                            PlasticListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred,
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPet = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("unchecked").hasChild("pen")) {
                        for (imageSnapshot in userSnapshot.child("unchecked").child("pen").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPet2 = true
                                    datas.apply {
                                        add(
                                            PlasticListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred,
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPet2 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("unchecked").hasChild("mouse")) {
                        for (imageSnapshot in userSnapshot.child("unchecked").child("mouse").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("image").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPet5 = true

                                    datas.apply {
                                        add(
                                            PlasticListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred,
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPet5 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("checked").hasChild("pet")) {
                        for (imageSnapshot in userSnapshot.child("checked").child("pet").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPet3 = true

                                    datas.apply {
                                        add(
                                            PlasticListInfo(
                                                detect_image = date,
                                                detect_percent = imageFile,
                                                detect_date = pred
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPet3 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("checked").hasChild("pen")) {
                        for (imageSnapshot in userSnapshot.child("checked").child("pen").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPet4 = true

                                    datas.apply {
                                        add(
                                            PlasticListInfo(
                                                detect_image = date,
                                                detect_percent = imageFile,
                                                detect_date = pred
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPet4 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("checked").hasChild("mouse")) {
                        for (imageSnapshot in userSnapshot.child("checked").child("mouse").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasPet6 = true

                                    datas.apply {
                                        add(
                                            PlasticListInfo(
                                                detect_image = date,
                                                detect_percent = imageFile,
                                                detect_date = pred
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasPet6 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }
                }

                plasticListAdapter.plasticList.addAll(
                    datas
                )

                // 데이터 변경되었으니 업데이트해라
                plasticListAdapter.notifyDataSetChanged()

                if(hasPet || hasPet2 || hasPet3 || hasPet4 || hasPet5 || hasPet6){
                    binding.constraintlayoutPlasticRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutPlasticEmpty.visibility = View.GONE
                }
                else if(!hasPet && !hasPet2 && !hasPet3 && !hasPet4 && !hasPet5 && !hasPet6){
                    binding.constraintlayoutPlasticRecycler.visibility = View.GONE
                    binding.constraintlayoutPlasticEmpty.visibility = View.VISIBLE
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