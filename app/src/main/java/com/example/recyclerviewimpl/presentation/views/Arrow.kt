package com.example.recyclerviewimpl.presentation.views

import android.content.Context
import android.graphics.*
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
const val ARROW_IS_POSITIVE_DEF = true


class Arrow(context: Context, attrs: AttributeSet) : View(context, attrs) {
    // Set up paints for canvas drawing.
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowWidth: Float
    private var arrowHeight: Float
    private var isPositive: Boolean
    private var arrowColor = 0

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.Arrow, 0, 0)
        arrowPaint.color = typedArray.getColor(
            R.styleable.Arrow_color_negative,
            ContextCompat.getColor(context, R.color.arrowColor_negative)
        )
        arrowWidth = (0.5f * resources.displayMetrics.density)
        arrowPaint.strokeWidth = arrowWidth * resources.displayMetrics.density
        arrowHeight = (typedArray.getDimension(
            R.styleable.Arrow_arrow_height,
            ARROW_HEIGHT_DEF
        ) * resources.displayMetrics.density).roundToInt().toFloat()
        isPositive = typedArray.getBoolean(R.styleable.Arrow_isPositive, ARROW_IS_POSITIVE_DEF)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        drawArrow(isPositive, canvas)
        drawArrow(canvas)
        invalidate()
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


//    private fun drawArrow(isPositive: Boolean, canvas: Canvas) {
//        if (isPositive) drawPositiveArrow(canvas) else drawArrow(canvas)
//    }

//    private fun drawPositiveArrow(canvas: Canvas) {
//        canvas.drawLine(10f, arrowHeight, 10f, arrowHeight - arrowHeight * 0.55f, arrowPaint)
//        canvas.drawLine(15f, arrowHeight, 15f, arrowHeight - arrowHeight * 0.55f, arrowPaint)
//        canvas
//            .drawLine(10f, arrowHeight - 1, 15f, arrowHeight - 1, arrowPaint)
//
//    }

    private fun drawArrow(canvas: Canvas) {
        canvas.drawLine(12.5f, arrowHeight * 0.7f, 25f, (arrowHeight * 0.55f) - 1, arrowPaint)
        canvas.drawLine(25f, (arrowHeight * 0.55f) - 1, 0f, (arrowHeight * 0.55f) - 1, arrowPaint)
        canvas.drawLine(0f, (arrowHeight * 0.55f) - 1, 12.5f, arrowHeight * 0.7f, arrowPaint)
        canvas.drawLine(10f, 2f, 10f, arrowHeight * 0.55f, arrowPaint)
        canvas.drawLine(15f, 2f, 15f, arrowHeight * 0.55f, arrowPaint)
        canvas.drawLine(11f, 3f, 16f, 3f, arrowPaint)
    }

    fun setNegativeColor() {
        arrowColor = ContextCompat.getColor(context, R.color.arrowColor_positive)
    }

}

