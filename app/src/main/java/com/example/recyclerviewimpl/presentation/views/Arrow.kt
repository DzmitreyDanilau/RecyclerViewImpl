package com.example.recyclerviewimpl.presentation.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.recyclerviewimpl.R
import kotlin.math.roundToInt

/**
requestLayout() - if view size changed and system should redraw
invalidate() - say that view should be redraw view
onSizeChanged()
onMeasure() - call when system must know size of the view
setMeasuresDemensions() - by this method we set new size
 */
const val ARROW_HEIGHT_DEF = 100F

class Arrow(context: Context, attrs: AttributeSet) : View(context, attrs) {
    // Set up paints for canvas drawing.
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowWidth = 4f
    private var arrowHeight = 0f

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.Arrow, 0, 0)
        arrowPaint.color = typedArray.getColor(
            R.styleable.Arrow_color_positive,
            ContextCompat.getColor(context, R.color.arrowColor_positive)
        )
        arrowPaint.strokeWidth = arrowWidth
        arrowHeight = typedArray.getDimension(R.styleable.Arrow_arrow_height, ARROW_HEIGHT_DEF)

        typedArray.recycle()

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPositiveArrow(canvas)
        invalidate()
    }


    private fun drawPositiveArrow(canvas: Canvas) {
        canvas.drawLine(15f, 5f, 15f, arrowHeight - 150, arrowPaint)
        canvas.drawLine(30f, 5f, 30f, arrowHeight - 150, arrowPaint)
        canvas.drawLine(15f, 5f, 30f, 5f, arrowPaint)
        canvas.drawLine(30f, 5f, 30f, arrowHeight - 150, arrowPaint)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight = (arrowHeight + paddingTop + paddingBottom).roundToInt()
        val desiredWidth = (arrowWidth + paddingStart + paddingEnd).roundToInt()
        val measureWidth = reconcileSize(desiredWidth, widthMeasureSpec)
        val measureHeight = reconcileSize(desiredHeight, heightMeasureSpec)
        setMeasuredDimension(measureWidth, measureHeight)
    }

    private fun reconcileSize(contentSize: Int, measureSpec: Int): Int {
        val mode = MeasureSpec.getMode(measureSpec)
        val specialSize = MeasureSpec.getSize(measureSpec)
        return when (mode) {
            MeasureSpec.EXACTLY -> specialSize
            MeasureSpec.AT_MOST -> if (contentSize < specialSize) return contentSize else specialSize
            MeasureSpec.UNSPECIFIED -> contentSize
            else -> contentSize
        }
    }

    private fun initLinePaint() {
        val paint = Paint()
        paint.apply {
            strokeWidth = 2f
            style = Paint.Style.FILL

        }
    }

    private fun initTrianglePaint() {

    }
}

