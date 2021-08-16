package com.sungshin.recyclear.check.checklist

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungshin.recyclear.check.CheckDetailActivity
import com.sungshin.recyclear.databinding.ItemCheckListBinding
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.experimental.or


class CheckListAdapter : RecyclerView.Adapter<CheckListAdapter.CheckListViewHolder>() {

    val checkList = mutableListOf<CheckListInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListViewHolder {
        val binding = ItemCheckListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CheckListViewHolder(binding)
    }

    override fun getItemCount(): Int = checkList.size

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int) {
        holder.onBind(checkList[position])
    }

    class CheckListViewHolder(
        private val binding: ItemCheckListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private var mLastClickTime : Long = 0

        fun onBind(checkListInfo: CheckListInfo) {
            Glide.with(itemView)
                .load(checkListInfo.detect_image)
                .into(binding.imageviewCheckItem)

//            val byteArrayOutputStream = ByteArrayOutputStream()
//            var imageBytes: ByteArray = byteArrayOutputStream.toByteArray()
//            imageBytes = Base64.decode(checkListInfo.detect_image, Base64.DEFAULT)
//            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//            binding.imageviewCheckItem.setImageBitmap(decodedImage)

//            Log.d("firebase", checkListInfo.detect_image.toString())
//            val b: ByteArray = binaryStringToByteArray(checkListInfo.detect_image)!!
//            Log.d("firebase", b.toString())
//            val reviewImage: Drawable = Drawable.createFromStream(ByteArrayInputStream(b), "reviewImage")
//            Log.d("firebase", reviewImage.toString())
//            binding.imageviewCheckItem. setImageDrawable(reviewImage)

            binding.textviewCheckDate.text = checkListInfo.detect_date
            binding.textviewCheckPercent.text = checkListInfo.detect_percent

            // item click event
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, CheckDetailActivity::class.java)

                if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                    intent.putExtra("detect_date",checkListInfo.detect_date)
                    intent.putExtra("detect_percent",checkListInfo.detect_percent)
                    intent.putExtra("detect_image",checkListInfo.detect_image)
                    intent.putExtra("detect_image_name",checkListInfo.detect_image_name)
                    intent.putExtra("detect_class",checkListInfo.detect_class)

                    ContextCompat.startActivity(itemView.context, intent, null)
                }
            }
        }
    }

}

//// 스트링을 바이너리 바이트 배열로
//fun binaryStringToByteArray(s: String): ByteArray? {
//    val count = s.length / 8
//    val b = ByteArray(count)
//    for (i in 1 until count) {
//        val t = s.substring((i - 1) * 8, i * 8)
//        b[i - 1] = binaryStringToByte(t)
//    }
//    return b
//}
//
//// 스트링을 바이너리 바이트로
//fun binaryStringToByte(s: String): Byte {
//    var ret: Byte = 0
//    var total: Byte = 0
//    for (i in 0..7) {
//        ret = if (s[7 - i] == '1') (1 shl i).toByte() else 0
//        total = (ret or total) as Byte
//    }
//    return total
//}