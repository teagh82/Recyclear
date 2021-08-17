package com.sungshin.recyclear.glass

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
import androidx.appcompat.app.AppCompatDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.FragmentGlassListBinding
import com.sungshin.recyclear.glass.glasslist.GlassListAdapter
import com.sungshin.recyclear.glass.glasslist.GlassListInfo
import com.sungshin.recyclear.metal.metallist.MetalListInfo
import com.sungshin.recyclear.utils.FirebaseUtil

class GlassListFragment : Fragment() {
    private var _binding: FragmentGlassListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val glassListAdapter: GlassListAdapter by lazy{GlassListAdapter()}

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<GlassListInfo>()

    var hasGlass: Boolean = false
    var hasGlass2: Boolean = false

    private lateinit var progressDialog: AppCompatDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGlassListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewGlassImages.adapter = glassListAdapter

        loadDatas()
    }

    private fun loadDatas(){
        progressON()
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    if (userSnapshot.child("unchecked").hasChild("glass")) {
                        for (imageSnapshot in userSnapshot.child("unchecked").child("glass").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("image").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasGlass = true

                                    datas.apply {
                                        add(
                                            GlassListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred.substring(2,4) + "%",
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasGlass = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }

                    if (userSnapshot.child("checked").hasChild("glass")) {
                        for (imageSnapshot in userSnapshot.child("checked").child("glass").children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile = imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    hasGlass2 = true

                                    datas.apply {
                                        add(
                                            GlassListInfo(
                                                detect_image = imageFile,
                                                detect_percent = pred.substring(2,4) + "%",
                                                detect_date = date
                                            )
                                        )
                                    }

                                    Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")
                                }
                                else{
                                    hasGlass2 = false
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }
                }

                glassListAdapter.glassList.addAll(
                    datas
                )

                glassListAdapter.notifyDataSetChanged()

                if(hasGlass || hasGlass2){
                    binding.constraintlayoutGlassRecycler.visibility = View.VISIBLE
                    binding.constraintlayoutGlassEmpty.visibility = View.GONE
                }
                else if(!hasGlass && !hasGlass2){
                    binding.constraintlayoutGlassRecycler.visibility = View.GONE
                    binding.constraintlayoutGlassEmpty.visibility = View.VISIBLE
                }
                progressOFF()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", error.message)
            }
        }

        val classRef = database.reference.child("User")
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