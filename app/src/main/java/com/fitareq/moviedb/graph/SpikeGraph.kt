package com.fitareq.moviedb.graph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SpikeGraph(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val dataPoints = mutableListOf<Pair<Long, Float>>()

    fun setData(data: List<Pair<Long, Float>>) {
        dataPoints.clear()
        dataPoints.addAll(data)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 3f
        paint.isAntiAlias = true

        val width = width.toFloat()
        val height = height.toFloat()

        val maxY = dataPoints.maxByOrNull { it.second }?.second ?: 1f

        val scaleX = width / (dataPoints.size - 1).toFloat()
        val scaleY = height / maxY

        var prevX = 0f
        var prevY = height - dataPoints[0].second * scaleY

        for ((timestamp, value) in dataPoints) {
            val x = prevX + scaleX
            val y = height - value * scaleY
            canvas.drawLine(prevX, prevY, x, y, paint)
            prevX = x
            prevY = y
        }


        canvas.drawLine(0f, 0f, 0f, height, axisLinePaint)
        canvas.drawLine(0f, height, width, height, axisLinePaint)
    }

    private val axisLinePaint = Paint().apply {
        color = Color.GRAY
        strokeWidth = 10f
    }
}
