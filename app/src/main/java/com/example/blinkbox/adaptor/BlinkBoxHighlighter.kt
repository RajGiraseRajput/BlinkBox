package com.example.blinkbox.decorator

import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BlinkBoxHighlighter(private val gridSize: Int) : RecyclerView.ItemDecoration() {

    private var selectedRow: Int = -1
    private var selectedCol: Int = -1
    private val paint = Paint()

    init {
        paint.color = 0xFF00FF00.toInt() // Green Color
        paint.strokeWidth = 5f
    }

    fun highlight(row: Int, col: Int) {
        selectedRow = row
        selectedCol = col
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (selectedRow == -1 || selectedCol == -1) return

        // Calculate item size
        val itemSize = parent.width / gridSize

        // Draw horizontal line (across the row)
        val startX = 0f
        val stopX = parent.width.toFloat()
        val yPosition = selectedRow * itemSize + (itemSize / 2f)
        canvas.drawLine(startX, yPosition, stopX, yPosition, paint)

        // Draw vertical line (across the column)
        val startY = 0f
        val stopY = parent.height.toFloat()
        val xPosition = selectedCol * itemSize + (itemSize / 2f)
        canvas.drawLine(xPosition, startY, xPosition, stopY, paint)
    }
}
