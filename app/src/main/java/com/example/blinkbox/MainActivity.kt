package com.example.blinkbox

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.blinkbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nextBtn.setOnClickListener {
            val enteredNumber = binding.enterNumber.text.toString()
            if (enteredNumber.isNotEmpty()){
                val enteredNumberInt = enteredNumber.toInt()
                if (enteredNumberInt in 4..10){
                    startActivity(Intent(this,BinkBoxActivity::class.java).putExtra("enteredNumber",enteredNumberInt))
                    binding.enterNumber.text.clear()
                }else{
                    Toast.makeText(this, "Enter number between 4 to 10", Toast.LENGTH_SHORT).show()
                    binding.enterNumber.text.clear()
                }
            }else{
                Toast.makeText(this, "Enter Number", Toast.LENGTH_SHORT).show()
            }
        }
    }
}