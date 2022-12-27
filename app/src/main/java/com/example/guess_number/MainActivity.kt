package com.example.guess_number

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var lowerBound = 0
    var upperBound = 0
    lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val upperEdit = findViewById<TextView>(R.id.upper)
        val lowerEdit = findViewById<TextView>(R.id.lower)
        val button: Button = findViewById(R.id.button)

        textViewResult = findViewById(R.id.result)
        textViewResult.text = "Enter the boundaries"
        button.setOnClickListener {
            textViewResult.text = "Enter the boundaries"

            val inputU = upperEdit?.text.toString().trim()
            if (! inputU.isNullOrBlank()) {
                upperBound = inputU.toInt()
            }

            val inputL = lowerEdit?.text.toString().trim()
            if (! inputL.isNullOrBlank()) {
                lowerBound = inputL.toInt()
            }

            if (upperBound > lowerBound) {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("lowerBound", lowerBound)
                intent.putExtra("upperBound", upperBound)
                startActivityForResult(intent, 1)
            } else {
                val text = "The upper must be bigger than the lower one!"
                val toast = Toast.makeText(this, text, Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data!!.getIntExtra("result", 0)
                textViewResult.text = "Your digit is: $result"
            }
        }
    }
}