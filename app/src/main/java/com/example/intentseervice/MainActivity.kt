package com.example.intentseervice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton =findViewById(R.id.start_button)
        stopButton =findViewById(R.id.stop_button)
        textView = findViewById(R.id.textview)

        startButton.setOnClickListener{
            Intent(this, MyIntentService::class.java).also {
                startService(it)
                textView.text = "Service running"
            }
        }

        stopButton.setOnClickListener {
            MyIntentService.stopService()
        }



    }
}