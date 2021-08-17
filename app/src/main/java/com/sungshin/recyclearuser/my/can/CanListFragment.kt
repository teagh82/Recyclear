package com.sungshin.recyclearuser.my.can

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
import com.sungshin.recyclearuser.my.can.canlist.CanListAdapter
import com.sungshin.recyclearuser.my.can.canlist.CanListInfo
import com.sungshin.recyclearuser.databinding.FragmentCanListBinding
import com.sungshin.recyclearuser.utils.FirebaseUtil
import com.sungshin.recyclearuser.utils.MyPref

class CanListFragment : Fragment() {
    private var _binding: FragmentCanListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val canListAdapter: CanListAdapter by lazy{ CanListAdapter() }

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<CanListInfo>()

    var hasCan: Boolean = false

    private lateinit var progressDialog: AppCompatDialog

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

    private fun loadDatas() {
        progressON()
        val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child("checked").hasChild("can")) {
                    for (imageSnapshot in dataSnapshot.child("checked")
                        .child("can").children) {
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile =
                                imageSnapshot.child("image").getValue(String::class.java)
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

                canListAdapter.canList.addAll(
                    datas
                )

                // 데이터 변경되었으니 업데이트해라
                canListAdapter.notifyDataSetChanged()

                if(hasCan){
                    binding.constraintlayoutCanRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutCanEmpty.visibility = View.GONE
                }
                else {
                    binding.constraintlayoutCanRecycler.visibility = View.GONE
                    binding.constraintlayoutCanEmpty.visibility = View.VISIBLE
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