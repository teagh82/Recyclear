package com.sungshin.recyclear.check.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

var myShapes : MutableList<MyShape> = ArrayList()
var yoloX: Double = 0.0
var yoloY: Double = 0.0
var yoloW: Double = 0.0
var yoloH: Double = 0.0


class CustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    var startX = -1
    var startY = -1
    var stopX = -1
    var stopY = -1

    @SuppressLint("WrongCall")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> { //touch 시작, 화면에 손가락 올림.
                startX = event.x.toInt()
                startY = event.y.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                // 화면에서 이동할 때, 화면에서 손가락을 띄였을 때.
                stopX = event.x.toInt()
                stopY = event.y.toInt()
                this.invalidate() // 명령 완료, 그리기 호출.
            }

            // move랑 up을 나눠서 처리하는 이유는, 도형의 잔상이 남지 않도록 하기 위해서.

            MotionEvent.ACTION_UP -> {
                val shape = MyShape() // 도형 데이터 1건을 저장시킬 객체 생성
                shape.startX = startX
                shape.startY = startY
                shape.stopX = stopX
                shape.stopY = stopY
//                shape.color = myShapes.size
                myShapes.add(shape) // ArrayList에 저장. 도형 누적.

                this.invalidate()
            }
        }

        var imageW = 998.0
        var imageH = 998.0

        yoloX = (startX + imageW/2) / imageW
        yoloY = (startY + imageH/2) / imageH
        yoloW = (stopX - startX) / imageW
        yoloH = (stopY - startY) / imageH

        Log.d("labeling pos", "({$startX}, {$startY}), ({$stopX}, {$stopY})")
        Log.d("labeling pos", "($yoloX $yoloY $yoloW $yoloH)")

        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint() // paint라는 객체를 생성하고
        paint.style = Paint.Style.STROKE // 채워지지 않는 도형 형성
        // paint.strokeWidth = size.toFloat()

        for(i in myShapes.indices){
            val shape2 = myShapes[i]
            paint.setStrokeWidth(3.toFloat())
            // 각 도형별(1번째) 사이즈 가져와서 펜 설정.

//            if(shape2.color == 0){
//                paint.color = Color.RED
//            }else if(shape2.color == 1){
//                paint.color = Color.BLUE
//            } else{
//                paint.color = Color.GREEN
//            }
            paint.color = Color.BLUE

            canvas?.drawRect(shape2.startX.toFloat(), shape2.startY.toFloat(), shape2.stopX.toFloat(), shape2.stopY.toFloat(), paint)

        }
        paint.color = Color.BLUE
        canvas?.drawRect(startX.toFloat(), startY.toFloat(), stopX.toFloat(), stopY.toFloat(), paint)

    }


}