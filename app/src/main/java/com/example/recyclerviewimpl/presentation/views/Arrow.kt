package com.example.recyclerviewimpl.presentation.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
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
/**
 * Arrow default height
 */
private const val ARROW_HEIGHT_DEF = 10F
/**
 *Set arrow behaviour is it positive/negative
 */
private const val ARROW_STATE_DEF = true
/**
 * Distance between to lines of the arrow
 */
private const val DISTANCE = 4
/**
 * Coordinates of the left line of the arrow
 */
private const val START_LINE_ONE_X = 4
private const val START_LINE_ONE_Y = 0

/**
 *This value can change free space for the spearhead of the arrow
 */
private const val SPEARHEAD_ARROW = 1.3
/**
 * Triangle height
 */
private const val TRIANGLE_HEIGHT = 0.9
/**
 * Arrow paint width
 */
private const val ARROW_PAINT_WIDTH = 0.4f

/**
 * Animation duration
 */
private var ANIMATION_DURATION = 1000

class Arrow(context: Context, attrs: AttributeSet) : View(context, attrs), ArrowInterface {
    // Set up paints for canvas drawing.
    private val arrowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val arrowWidth: Float
    private var arrowHeight: Float
    private var arrowState: Boolean
    private var negativeColor: Int
    private var positiveColor: Int
    private var isAnimated: Boolean

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
        arrowHeight = (typedArray.getDimension(
            R.styleable.Arrow_arrow_height,
            ARROW_HEIGHT_DEF
        ) * resources.displayMetrics.density).roundToInt().toFloat()
        arrowWidth = (ARROW_PAINT_WIDTH * resources.displayMetrics.density)
        isAnimated = typedArray.getBoolean(R.styleable.Arrow_isAnimated, true)
        arrowState = typedArray.getBoolean(R.styleable.Arrow_isPositive, ARROW_STATE_DEF)
        arrowPaint.strokeWidth = arrowWidth * resources.displayMetrics.density
        arrowPaint.color = positiveColor

        isAnimated = typedArray.getBoolean(R.styleable.Arrow_isAnimated, true)
        arrowState = typedArray.getBoolean(R.styleable.Arrow_isPositive, ARROW_STATE_DEF)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawArrow(canvas)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight =
            (arrowHeight * SPEARHEAD_ARROW + paddingTop + paddingBottom).roundToInt()
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

    fun isAnimated(): Boolean = isAnimated

    fun getAnimationDurration() = ANIMATION_DURATION

    fun setAnimationDuration(duration: Int) {
        ANIMATION_DURATION = duration
    }

    override fun changeAnimationBehaviour(flag: Boolean) {
        isAnimated = flag
    }

    override fun setColor(isPositive: Boolean) {
        arrowPaint.color = if (isPositive) positiveColor else negativeColor
        invalidate()
    }

    fun setArrowRotation(costValue: Int) {
        var isRotated = false
        when {
            costValue > 0 -> {becomeNegative(isRotated)}
            costValue < 0 -> {becomePositive(isRotated)}
            else -> this.visibility = View.INVISIBLE
        }
    }

    private fun becomePositive(isRotated: Boolean) {
        var isRotated1 = isRotated
        this.setColor(false)
        if (isRotated1) {
            this.animate().rotation(360f).duration =
                this.getAnimationDurration().toLong()
            isRotated1 = false
        }
    }

    private fun becomeNegative(isRotated: Boolean) {
        var isRotated1 = isRotated
        this.setColor(true)
        if (!isRotated1) {
            this.animate().rotation(180f).duration =
                this.getAnimationDurration().toLong()
            isRotated1 = true
        }
    }

    val pointA = Point(START_LINE_ONE_X + DISTANCE / 2, (arrowHeight * SPEARHEAD_ARROW).toInt())
    private val pointB =
        Point(START_LINE_ONE_X + DISTANCE * 2, (arrowHeight * TRIANGLE_HEIGHT).toInt())
    private val pointC = Point(START_LINE_ONE_X + DISTANCE, arrowHeight.toInt())
    private val pointD = Point(START_LINE_ONE_X + DISTANCE, START_LINE_ONE_Y)
    private val pointE = Point(START_LINE_ONE_X, START_LINE_ONE_Y)
    private val pointF = Point(START_LINE_ONE_X, arrowHeight.toInt())
    private val pointG = Point(START_LINE_ONE_X - DISTANCE, (arrowHeight * TRIANGLE_HEIGHT).toInt())


}


