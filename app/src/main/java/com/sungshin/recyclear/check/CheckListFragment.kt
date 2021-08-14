package com.sungshin.recyclear.check

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
import com.sungshin.recyclear.check.checklist.CheckListAdapter
import com.sungshin.recyclear.check.checklist.CheckListInfo
import com.sungshin.recyclear.databinding.FragmentCheckListBinding
import com.sungshin.recyclear.utils.FirebaseUtil

class CheckListFragment : Fragment() {
    private var _binding: FragmentCheckListBinding? = null
    private val binding get() =_binding ?: error("View 를 참조하기 위해 binding 이 초기화 되지 않았습니다.")
    private val checkListAdapter: CheckListAdapter by lazy{ CheckListAdapter() }

    var date_list = ArrayList<String>()
    var img_list = ArrayList<String>()
    var pred_list = ArrayList<String>()
    val firebaseDB = FirebaseUtil()
    val database = firebaseDB.database

    var datas= mutableListOf<CheckListInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerviewCheckImages.adapter = checkListAdapter

        loadDatas()
    }

    // 서버 연결
    private fun loadDatas(){
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (userSnapshot in dataSnapshot.children) {
                    for (classSnapshot in userSnapshot.child("checked").children) {
                        for (imageSnapshot in classSnapshot.children) {
                            if (imageSnapshot.hasChildren()) {
                                val date = imageSnapshot.child("date").getValue(String::class.java)
                                val imageFile =
                                    imageSnapshot.child("imageFile").getValue(String::class.java)
                                val pred = imageSnapshot.child("pred").getValue(String::class.java)

                                if (date != null && imageFile != null && pred != null) {
                                    date_list.add(date)
                                    img_list.add(imageFile)
                                    pred_list.add(pred)
                                }

                                Log.d("FIREBASE", "date: $date / img: $imageFile / pred: $pred")

                                // adapter에 데이터 추가
                                var detectDate: String
                                var detectImage: String
                                var detectPercent: String

                                for (i in 0 until date_list.size) {
                                    detectDate = "20" + date_list[i].substring(
                                        0,
                                        2
                                    ) + "-" + date_list[i].substring(
                                        2, 4
                                    ) + "-" + date_list[i].substring(4, 6)
                                    detectImage = img_list[i]
                                    detectPercent = pred_list[i]


                                    Log.d(
                                        "FIREBASE",
                                        "date: $detectDate / img: $detectImage / pred: $detectPercent"
                                    )

                                    datas.apply {
                                        add(
                                            CheckListInfo(
                                                detect_image = detectImage,
                                                detect_percent = detectPercent,
                                                detect_date = detectDate
                                            )
                                        )
                                    }

                                    checkListAdapter.checkList.addAll(
                                        datas
                                    )

                                    // 데이터 변경되었으니 업데이트해라
                                    checkListAdapter.notifyDataSetChanged()
                                }
                            }

                            else {
                                Log.d("FIREBASE", "not hasChildren")
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", error.message)
            }
        }

        val classRef = database.reference.child("User")
        classRef.addValueEventListener(valueEventListener)
    }


    // 서버 연결 x
    private fun loadDummy(){
        // 서버 연결 x
        checkListAdapter.checkList.addAll(
            listOf<CheckListInfo>(
                CheckListInfo(
                    detect_image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    detect_date = "2021.08.10",
                    detect_percent = "70%"
                ),
                CheckListInfo(
                    detect_image = "https://cdn.incheontoday.com/news/photo/201805/110606_100698_3317.jpg",
                    detect_date = "2021.08.10",
                    detect_percent = "70%"
                ),
                CheckListInfo(
                    detect_image = "data:image/jpeg;base64,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",
                    detect_date = "2021.08.10",
                    detect_percent = "72%"
                ),
                CheckListInfo(
                    detect_image = "data:image/jpeg;base64,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",
                    detect_date = "2021.08.10",
                    detect_percent = "68%"
                ),
                CheckListInfo(
                    detect_image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    detect_date = "2021.08.10",
                    detect_percent = "70%"
                ),
                CheckListInfo(
                    detect_image = "https://cdn.incheontoday.com/news/photo/201805/110606_100698_3317.jpg",
                    detect_date = "2021.08.10",
                    detect_percent = "70%"
                ),
                CheckListInfo(
                    detect_image = "data:image/jpeg;base64,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",
                    detect_date = "2021.08.10",
                    detect_percent = "88%"
                ),
                CheckListInfo(
                    detect_image = "https://images.squarespace-cdn.com/content/v1/5c8d13427a1fbd35297a9ed1/1584916870771-Y1V8HXYAZAEV75ZYK4DH/reusable+bag+example.png?format=1000w",
                    detect_date = "2021.08.10",
                    detect_percent = "70%"
                ),
                CheckListInfo(
                    detect_image = "https://cdn.incheontoday.com/news/photo/201805/110606_100698_3317.jpg",
                    detect_date = "2021.08.10",
                    detect_percent = "70%"
                ),
                CheckListInfo(
                    detect_image = "",
                    detect_date = "",
                    detect_percent = ""
                ),
                CheckListInfo(
                    detect_image = "",
                    detect_date = "",
                    detect_percent = ""
                ),
                CheckListInfo(
                    detect_image = "",
                    detect_date = "",
                    detect_percent = ""
                )
            )
        )
        // 데이터 변경되었으니 업데이트해라
        checkListAdapter.notifyDataSetChanged()
    }
}