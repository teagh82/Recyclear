package com.sungshin.recyclear.check

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.sungshin.recyclear.R
import com.sungshin.recyclear.databinding.ActivityLabelingBinding

class LabelingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLabelingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabelingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // spinner
        val itemList = listOf("can", "paper", "vinyl", "plastic", "metal", "glass", "클래스를 선택해주세요")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemList)
        binding.spinnerLabeling.adapter = adapter
        //스피너 초기값을 마지막 아이템으로 설정
        binding.spinnerLabeling.setSelection(adapter.count)

        //droplist를 spinner와 간격을 두고 나오게 해줍니다.)
////아이템 크기가 45dp 이므로 45dp 간격을 주었습니다.
////이때 dp 를 px 로 변환해 주는 작업이 필요합니다.
//        spinner.dropDownVerticalOffset = dipToPixels(45f).toInt()
//
////스피너 선택시 나오는 화면 입니다.
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//
//                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
//                when (position) {
//                    0 -> {
//
//                    }
//                    1 -> {
//
//                    }
//                    //...
//                    else -> {
//
//                    }
//                }
//            }

        binding.spinnerLabeling.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(position != 0) Toast.makeText(this@LabelingActivity, itemList[position]+" 선택", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("선택해주세요")
            }
        }
    }
}