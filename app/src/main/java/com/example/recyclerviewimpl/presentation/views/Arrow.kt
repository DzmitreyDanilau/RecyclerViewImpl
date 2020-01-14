package com.example.recyclerviewimpl.presentation.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
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
const val DISTANCE = 4
const val START_LINE_ONE_X = 4
const val START_LINE_ONE_Y = 0


class Arrow(context: Context, attrs: AttributeSet) : TextView(context, attrs) {
    // Set up paints for canvas drawing.
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowWidth: Float
    private var arrowHeight: Float
    private var isPositive: Boolean
    private var arrowColor = 0
    private var negativeColor: Int
    private var positiveColor: Int

    init {
        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.Arrow, 0, 0)
        negativeColor = typedArray.getColor(
            R.styleable.Arrow_color_negative,
            ContextCompat.getColor(context, R.color.arrowColor_negative)
        )
        positiveColor = typedArray.getColor(
            R.styleable.Arrow_color_positive,
            ContextCompat.getColor(context, R.color.arrowColor_positive)
        )
        arrowWidth = (0.4f * resources.displayMetrics.density)
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
        setColor(isPositive)
        drawArrow(canvas)
        invalidate()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight = (arrowHeight * 1.3 + paddingTop + paddingBottom).roundToInt()
        val desiredWidth = (arrowWidth * 1.5 + paddingStart + paddingEnd).roundToInt()
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


    private fun drawArrow(canvas: Canvas) {


        canvas.drawLine(
            pointA.x.toFloat(),
            pointA.y.toFloat(),
            pointB.x.toFloat(),
            pointB.y.toFloat(),
            arrowPaint
        )
        canvas.drawLine(
            pointB.x.toFloat(),
            pointB.y.toFloat(),
            pointC.x.toFloat(),
            pointC.y.toFloat(),
            arrowPaint
        )
        canvas.drawLine(
            pointC.x.toFloat(),
            pointC.y.toFloat(),
            pointD.x.toFloat(),
            pointD.y.toFloat(),
            arrowPaint
        )
        canvas.drawLine(
            pointD.x.toFloat(),
            pointD.y.toFloat(),
            pointE.x.toFloat(),
            pointE.y.toFloat(),
            arrowPaint
        )
        canvas.drawLine(
            pointE.x.toFloat(),
            pointE.y.toFloat(),
            pointF.x.toFloat(),
            pointF.y.toFloat(),
            arrowPaint
        )
        canvas.drawLine(
            pointF.x.toFloat(),
            pointF.y.toFloat(),
            pointG.x.toFloat(),
            pointG.y.toFloat(),
            arrowPaint
        )
        canvas.drawLine(
            pointG.x.toFloat(),
            pointG.y.toFloat(),
            pointA.x.toFloat(),
            pointA.y.toFloat(),
            arrowPaint
        )
    }

    fun setColor(flag: Boolean) {
        if (flag) {
            arrowPaint.color = positiveColor
        } else arrowPaint.color = negativeColor
    }

    fun setBehaviour(behviour: Boolean) {
        isPositive = behviour
    }

    private val pointA = Point(START_LINE_ONE_X + DISTANCE / 2, (arrowHeight * 1.3).toInt())
    private val pointB = Point(START_LINE_ONE_X + DISTANCE * 2, (arrowHeight * 0.9).toInt())
    private val pointC = Point(START_LINE_ONE_X + DISTANCE, arrowHeight.toInt())
    private val pointD = Point(START_LINE_ONE_X + DISTANCE, START_LINE_ONE_Y)
    private val pointE = Point(START_LINE_ONE_X, START_LINE_ONE_Y)
    private val pointF = Point(START_LINE_ONE_X, arrowHeight.toInt())
    private val pointG = Point(START_LINE_ONE_X - DISTANCE, (arrowHeight * 0.9).toInt())


}


