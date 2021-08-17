package com.sungshin.recyclearuser.point

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

    var hasPoint: Boolean = false

    private lateinit var progressDialog: AppCompatDialog

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
        progressON()

        val saveIDdata = MyPref.prefs.getString("id", " ").split(".com")[0]
        Log.d("FIREBASE", saveIDdata)
        Log.d("FIREBASE", "Start Loading")

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (classSnapshot in dataSnapshot.child("unchecked").children) {
                    for (imageSnapshot in classSnapshot.children) {
                        if (imageSnapshot.hasChildren()) {
                            val date = imageSnapshot.child("date").getValue(String::class.java)
                            val imageFile =
                                imageSnapshot.child("image").getValue(String::class.java)
                            val imageFileName = imageSnapshot.key.toString()
                            val label = classSnapshot.key
                            val className = classSnapshot.key.toString()

                            if (date != null && imageFile != null && label != null) {
                                hasPoint = true

                                datas.apply {
                                    add(
                                        PointListInfo(
                                            detect_image = imageFile,
                                            detect_image_name = imageFileName,
                                            detect_label = label,
                                            detect_date = date,
                                            detect_class = className
                                        )
                                    )
                                }

                                Log.d("FIREBASE", "date: $date / img: $imageFile / label: $label")
                            }
                            else{
                                hasPoint = false
                            }
                        }

                        else {
                            Log.d("FIREBASE", "not hasChildren")
                        }
                    }
                }

                pointListAdapter.pointList.addAll(
                    datas
                )

                // 데이터 변경되었으니 업데이트해라
                pointListAdapter.notifyDataSetChanged()

                if(hasPoint){
                    binding.constraintlayoutPointRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutPointEmpty.visibility = View.GONE
                }
                else if(!hasPoint){
                    binding.constraintlayoutPointRecycler.visibility = View.GONE
                    binding.constraintlayoutPointEmpty.visibility = View.VISIBLE
                }

                progressOFF()

                Log.d("FIREBASE", "Load Done")
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