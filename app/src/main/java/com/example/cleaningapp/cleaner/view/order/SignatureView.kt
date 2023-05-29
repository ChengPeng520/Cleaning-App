package com.example.cleaningapp.cleaner.view.order

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class SignatureView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val path = Path()
    private val paint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 10f
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }
//
//        へ　　　　　／|
//    　 /＼7　　　 ∠＿/
//    　 /　│　　 ／　／            ℙ𝕀𝕂𝔸 ~ ℙ𝕀𝕂𝔸 ~ ℙ𝕀𝕂𝔸 ~
//    　│　Z ＿,＜　／　　 /`ヽ
//    　│　　　　　ヽ　　 /　　〉        ℙ𝕀𝕂𝔸 ~ ℙ𝕀𝕂𝔸 ~
//      Y　　　　　 `　 /　　/
//    　ｲ ●　､　 ●   〈　　/               ℙ𝕀𝕂𝔸 ~
//    　()　 へ　　　　|　＼〈
//    　　>ｰ ､_　 ィ　 │ ／／
//    　 / へ　　 /　ﾉ＜| ＼＼
//    　 ヽ_ﾉ　　(_／　 │／／
//    　　7　　　　　　　|／
//    　　＞―r￣￣`ｰ―＿
//

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> startDrawing(x, y)
            MotionEvent.ACTION_MOVE -> continueDrawing(x, y)
            else -> return false
        }
        return true
    }

    fun clean() {
        path.reset()
        invalidate()
    }

    fun startDrawing(x: Float, y: Float) {
        path.moveTo(x, y)
        invalidate()
    }

    fun continueDrawing(x: Float, y: Float) {
        path.lineTo(x, y)
        invalidate()
    }

}
