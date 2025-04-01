package com.example.blinkbox

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blinkbox.adaptor.BlinkBoxAdaptor
import com.example.blinkbox.decorator.BlinkBoxHighlighter
import com.example.blinkbox.databinding.ActivityBinkBoxBinding

class BinkBoxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBinkBoxBinding
    private var enteredNumber: Int = 0
    private lateinit var highlighter: BlinkBoxHighlighter

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

        // Create highlighter decorator
        highlighter = BlinkBoxHighlighter(enteredNumber)
        binding.blinkBoxRecyclerView.addItemDecoration(highlighter)

        // Set Adapter with click listener
        binding.blinkBoxRecyclerView.adapter = BlinkBoxAdaptor(this, enteredNumber) { row, col ->
            highlighter.highlight(row, col)
            binding.blinkBoxRecyclerView.invalidate() // Refresh RecyclerView to draw lines
        }
    }
}
