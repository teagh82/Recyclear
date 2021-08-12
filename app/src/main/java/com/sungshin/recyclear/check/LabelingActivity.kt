package com.sungshin.recyclear.check

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sungshin.recyclear.R
import com.sungshin.recyclear.check.spinner.SpinnerAdapterLabel
import com.sungshin.recyclear.check.spinner.SpinnerModel
import com.sungshin.recyclear.databinding.ActivityLabelingBinding

class LabelingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabelingBinding

    private lateinit var spinnerAdapterLabel: SpinnerAdapterLabel
    private val listOfLabels = ArrayList<SpinnerModel>()

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
            finish()
            Log.d("button", "LABELING END")
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
                    if(position != 0)
                        Toast.makeText(this@LabelingActivity, "Selected: ${label.name}", Toast.LENGTH_SHORT).show()

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
}