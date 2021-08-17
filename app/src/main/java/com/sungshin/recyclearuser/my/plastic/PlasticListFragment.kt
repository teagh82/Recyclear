package com.sungshin.recyclearuser.my.plastic

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sungshin.recyclearuser.R
import com.sungshin.recyclearuser.databinding.FragmentPlasticListBinding
import com.sungshin.recyclearuser.my.plastic.plasticlist.PlasticListAdapter
import com.sungshin.recyclearuser.my.plastic.plasticlist.PlasticListInfo
import com.sungshin.recyclearuser.utils.FirebaseUtil
import com.sungshin.recyclearuser.utils.MyPref

class PlasticListFragment : Fragment() {
    private var _binding: FragmentPlasticListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val plasticListAdapter: PlasticListAdapter by lazy{ PlasticListAdapter() }

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<PlasticListInfo>()

    private lateinit var progressDialog: AppCompatDialog

    var hasPet: Boolean = false
    var hasPet2: Boolean = false
    var hasPet3: Boolean = false

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

    private fun loadDatas() {
        progressON()

        val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child("checked").hasChild("pet")) {
                    for (imageSnapshot in dataSnapshot.child("checked")
                        .child("pet").children) {
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile =
                                imageSnapshot.child("image").getValue(String::class.java)
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

                if (dataSnapshot.child("checked").hasChild("pen")) {
                    for (imageSnapshot in dataSnapshot.child("checked")
                        .child("pen").children) {
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile = imageSnapshot.child("image").getValue(String::class.java)
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

                if (dataSnapshot.child("checked").hasChild("mouse")) {
                    for (imageSnapshot in dataSnapshot.child("checked")
                        .child("mouse").children) {
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile = imageSnapshot.child("image").getValue(String::class.java)
                            val pred = imageSnapshot.child("pred").getValue(String::class.java)

                            if (date != null && imageFile != null && pred != null) {
                                hasPet3 = true
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
                                hasPet3 = false
                            }
                        }

                        else {
                            Log.d("FIREBASE", "not hasChildren")
                        }
                    }
                }

                plasticListAdapter.plasticList.addAll(
                    datas
                )

                // 데이터 변경되었으니 업데이트해라
                plasticListAdapter.notifyDataSetChanged()

                if(hasPet || hasPet2 || hasPet3){
                    binding.constraintlayoutPlasticRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutPlasticEmpty.visibility = View.GONE
                }
                else if(!hasPet && !hasPet2 && !hasPet3){
                    binding.constraintlayoutPlasticRecycler.visibility = View.GONE
                    binding.constraintlayoutPlasticEmpty.visibility = View.VISIBLE
                }
                progressOFF()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", error.message)
            }
        }

        val classRef = database.reference.child("User").child(saveIDdata)
        classRef.addValueEventListener(valueEventListener)
    }

    // loading indicator
    fun progressON(){
        progressDialog = AppCompatDialog(this.context)
        progressDialog.setCancelable(false)
        progressDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setContentView(R.layout.loading)
        progressDialog.show()
        var img_loading_framge = progressDialog.findViewById<ImageView>(R.id.iv_frame_loading)
        var frameAnimation = img_loading_framge?.getBackground() as AnimationDrawable
        img_loading_framge?.post(object : Runnable{
            override fun run() {
                frameAnimation.start()
            }

        })
    }
    fun progressOFF(){
        if(progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss()
        }
    }
}