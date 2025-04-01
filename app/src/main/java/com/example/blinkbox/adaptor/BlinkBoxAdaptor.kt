package com.example.blinkbox.adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkbox.R
import com.example.blinkbox.databinding.BlinkBoxItemBinding

class BlinkBoxAdaptor(
    private val context: Context,
    private val gridSize: Int,
    private val onBoxClicked: (Int, Int) -> Unit
) : RecyclerView.Adapter<BlinkBoxAdaptor.BlinkBoxViewHolder>() {

    class BlinkBoxViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = BlinkBoxItemBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlinkBoxViewHolder {
        return BlinkBoxViewHolder(
            LayoutInflater.from(context).inflate(R.layout.blink_box_item, parent, false)
        )
    }

    override fun getItemCount(): Int = gridSize * gridSize

    override fun onBindViewHolder(holder: BlinkBoxViewHolder, position: Int) {
        val row = position / gridSize
        val col = position % gridSize

        holder.itemView.setOnClickListener {
            onBoxClicked(row, col)
        }
    }
}
