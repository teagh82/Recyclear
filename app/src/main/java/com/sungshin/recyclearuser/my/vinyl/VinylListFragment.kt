package com.sungshin.recyclearuser.my.vinyl

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
import com.sungshin.recyclearuser.databinding.FragmentVinylListBinding
import com.sungshin.recyclearuser.my.vinyl.vinyllist.VinylListAdapter
import com.sungshin.recyclearuser.my.vinyl.vinyllist.VinylListInfo
import com.sungshin.recyclearuser.utils.FirebaseUtil
import com.sungshin.recyclearuser.utils.MyPref

class VinylListFragment : Fragment() {

    private var _binding: FragmentVinylListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val vinylListAdapter: VinylListAdapter by lazy{VinylListAdapter()}

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<VinylListInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVinylListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewVinylImages.adapter = vinylListAdapter

        loadDatas()
    }

    private fun loadDatas() {
        val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child("checked").hasChild("vinyl")) {
                    for (imageSnapshot in dataSnapshot.child("checked")
                        .child("vinyl").children) {
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile = imageSnapshot.child("image").getValue(String::class.java)
                            val pred = imageSnapshot.child("pred").getValue(String::class.java)

                            if (date != null && imageFile != null && pred != null) {
                                datas.apply {
                                    add(
                                        VinylListInfo(
                                            detect_image = imageFile,
                                            detect_percent = pred,
                                            detect_date = date
                                        )
                                    )
                                }

                                Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                            }
                        }

                        else {
                            Log.d("FIREBASE", "not hasChildren")
                        }
                    }
                }
                vinylListAdapter.vinylList.addAll(
                    datas
                )

                // 데이터 변경되었으니 업데이트해라
                vinylListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", error.message)
            }
        }

        val classRef = database.reference.child("User").child(saveIDdata)
        classRef.addValueEventListener(valueEventListener)
    }
}