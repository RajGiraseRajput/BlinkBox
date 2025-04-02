package com.example.blinkbox

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blinkbox.adaptor.BlinkBoxAdaptor
import com.example.blinkbox.databinding.ActivityBinkBoxBinding

class BinkBoxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBinkBoxBinding
    private var enteredNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBinkBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            enteredNumber = intent.getIntExtra("enteredNumber", 0)
        }

        // Setup RecyclerView with Grid Layout
        binding.blinkBoxRecyclerView.layoutManager = GridLayoutManager(this, enteredNumber)
        binding.blinkBoxRecyclerView.adapter = BlinkBoxAdaptor(this, enteredNumber) { row, col ->
            blinkRelatedItems(row, col)
        }
    }

    private fun blinkRelatedItems(row: Int, col: Int) {
        val recyclerView = binding.blinkBoxRecyclerView

        for (i in 0 until enteredNumber) {
            // Blink item in the same row at the same column index
            blinkItem(recyclerView.findViewHolderForAdapterPosition(row * enteredNumber + i)?.itemView)

            // Blink item in the same column at the same row index
            blinkItem(recyclerView.findViewHolderForAdapterPosition(i * enteredNumber + col)?.itemView)

            // Blink item in the primary diagonal (↘ direction)
            if (row + i < enteredNumber && col + i < enteredNumber) {
                blinkItem(recyclerView.findViewHolderForAdapterPosition((row + i) * enteredNumber + (col + i))?.itemView)
            }
            if (row - i >= 0 && col - i >= 0) {
                blinkItem(recyclerView.findViewHolderForAdapterPosition((row - i) * enteredNumber + (col - i))?.itemView)
            }

            // Blink item in the secondary diagonal (↙ direction)
            if (row + i < enteredNumber && col - i >= 0) {
                blinkItem(recyclerView.findViewHolderForAdapterPosition((row + i) * enteredNumber + (col - i))?.itemView)
            }
            if (row - i >= 0 && col + i < enteredNumber) {
                blinkItem(recyclerView.findViewHolderForAdapterPosition((row - i) * enteredNumber + (col + i))?.itemView)
            }
        }
    }

    private fun blinkItem(view: View?) {
        view?.let {
            val animator = ObjectAnimator.ofFloat(it, View.ALPHA, 1f, 0f, 1f)
            animator.duration = 500
            animator.repeatCount = 3
            animator.start()
        }
    }
}



//package com.example.blinkbox
//
//import android.animation.ObjectAnimator
//import android.os.Bundle
//import android.view.View
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.GridLayoutManager
//import com.example.blinkbox.adaptor.BlinkBoxAdaptor
//import com.example.blinkbox.databinding.ActivityBinkBoxBinding
//
//class BinkBoxActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityBinkBoxBinding
//    private var enteredNumber: Int = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        binding = ActivityBinkBoxBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        if (intent.extras != null) {
//            enteredNumber = intent.getIntExtra("enteredNumber", 0)
//        }
//
//        // Setup RecyclerView with Grid Layout
//        binding.blinkBoxRecyclerView.layoutManager = GridLayoutManager(this, enteredNumber)
//        binding.blinkBoxRecyclerView.adapter = BlinkBoxAdaptor(this, enteredNumber) { row, col ->
//            blinkBoxes(row, col)
//        }
//    }
//
//    private fun blinkBoxes(row: Int, col: Int) {
//        val recyclerView = binding.blinkBoxRecyclerView
//
//        for (i in 0 until enteredNumber) {
//            // Blink items in the same row
//            blinkItem(recyclerView.findViewHolderForAdapterPosition(row * enteredNumber + i)?.itemView)
//
//            // Blink items in the same column
//            blinkItem(recyclerView.findViewHolderForAdapterPosition(i * enteredNumber + col)?.itemView)
//        }
//    }
//
//    private fun blinkItem(view: View?) {
//        view?.let {
//            val animator = ObjectAnimator.ofFloat(it, View.ALPHA, 1f, 0f, 1f)
//            animator.duration = 500
//            animator.repeatCount = 3
//            animator.start()
//        }
//    }
//}
