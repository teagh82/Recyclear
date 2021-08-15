package com.sungshin.recyclear.check

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sungshin.recyclear.R
import com.sungshin.recyclear.check.canvas.*
import com.sungshin.recyclear.check.spinner.SpinnerAdapterLabel
import com.sungshin.recyclear.check.spinner.SpinnerModel
import com.sungshin.recyclear.databinding.ActivityLabelingBinding
import com.sungshin.recyclear.metal.MetalActivity
import com.sungshin.recyclear.utils.FirebaseUtil
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LabelingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabelingBinding

    private lateinit var spinnerAdapterLabel: SpinnerAdapterLabel
    private val listOfLabels = ArrayList<SpinnerModel>()

    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var labelName: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabelingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(labelImg)
            .into(binding.imageviewLabelingBack)

        setSpinner()
        setupSpinnerHandler()
        onClickBtns()
    }

    private fun onClickBtns() {
        binding.buttonLabelingEnd.setOnClickListener {
            var yoloLabel = "$labelName $yoloX $yoloY $yoloW $yoloH"
            var yoloImg = labelImg

            sendData(yoloLabel, yoloImg)
            eraseData(labelImgName)

            Log.d("LABELING", "LABELING END")
            Toast.makeText(this, "재학습 데이터를 서버에 전송했습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CheckActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLabelingErase.setOnClickListener {
            myShapes.clear()

            Log.d("LABELING", "LABELING ERASE")
        }
    }

    private fun setSpinner(){
        val labels = resources.getStringArray(R.array.labeling_class)

        for (i in labels.indices) {
            val label = SpinnerModel(R.drawable.icon_spinner, labels[i])
            listOfLabels.add(label)
        }
        spinnerAdapterLabel = SpinnerAdapterLabel(this, R.layout.item_spinner, listOfLabels)
        binding.spinnerLabeling.adapter = spinnerAdapterLabel
    }

    private fun setupSpinnerHandler() {
        binding.spinnerLabeling.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val label = binding.spinnerLabeling.getItemAtPosition(position) as SpinnerModel
                if (!label.name.equals("클래스 선택")) {
                    if(position != 0) {
                        Toast.makeText(
                            this@LabelingActivity,
                            "Selected: ${label.name}",
                            Toast.LENGTH_SHORT
                        ).show()

                        labelName = position - 1
                    }
                    // 맨 위부터 position 0번부터 순서대로 동작
                    when (position) {
                        0 -> {
                        }
                        1 -> {

                        }
                        2 -> {

                        }
                        3 -> {

                        }
                        4 -> {

                        }
                        5 -> {

                        }
                        6 -> {

                        }
                        else -> {

                        }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

    private fun sendData(yoloLabel:String, yoloImg:String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val img_name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
            Log.d("LABELING", img_name)

            val yoloRefImg =
                database.getReference("Yolo").child(img_name).child("origin")
            yoloRefImg.setValue(yoloImg)

            val yoloRefLabel =
                database.getReference("Yolo").child(img_name).child("label")
            yoloRefLabel.setValue(yoloLabel)
        }

        else {
            Log.d("LABELING", "SDK Ver Error, Cannot Send YOLO Data")
        }
    }

    private fun eraseData(yoloImgName:String) {
        database.getReference("Admin").child(className).child(labelImgName).removeValue()
            .addOnSuccessListener {
                Log.d("LABELING", "eraseData success")
            }
            .addOnFailureListener {
                Log.d("LABELING", "eraseData failed")
            }
    }
}