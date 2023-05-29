package com.example.cleaningapp.share

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable

import android.util.AttributeSet
import android.widget.ImageView

@SuppressLint("AppCompatCustomView")
class CircleImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {
    private val paint: Paint = Paint()

    /**
     * 繪製圓形圖片
     */
    override fun onDraw(canvas: Canvas?) {
        val drawable = drawable
        if(drawable != null) {
            val bitmap = (drawable as BitmapDrawable).bitmap
            val b = getCircleBitmap(bitmap, 14)
            val rectSrc = Rect(0, 0, b.width, b. height)
            val rectDest = Rect(0, 0, width, height)
            paint.reset()
            canvas?.drawBitmap(b, rectSrc, rectDest, paint)
        } else super.onDraw(canvas)
    }

    private fun getCircleBitmap(bitmap: Bitmap, pixels: Int): Bitmap {
        val output = Bitmap.createBitmap(
            bitmap.width,
            bitmap.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        val color = -0xbdbdbe
        val rect = Rect(0, 0, bitmap.width, bitmap.height)
        paint.isAntiAlias = true
        canvas.drawARGB(0, 0, 0, 0)
        paint.color = color
        val x = bitmap.width
        canvas.drawCircle((x / 2).toFloat(), (x / 2).toFloat(), (x / 2).toFloat(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rect, rect, paint)
        return output
    }
}