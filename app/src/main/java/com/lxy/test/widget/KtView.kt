package com.lxy.test.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class KtView : View {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributes: AttributeSet?) : this(context, null, 0)
    constructor(context: Context, attributes: AttributeSet?, int: Int) : super(context, attributes, int) {
        init(attributes)
    }

   private fun init(attributes: AttributeSet?) {

        if (attributes != null) {
            println("att=======" + attributes)
        }


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(Color.parseColor("#30ff0000"))
    }

}