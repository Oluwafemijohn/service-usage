package com.example.intentseervice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var textView: TextView
    private lateinit var serviceTextView: TextView
    private lateinit var editText: EditText
    private lateinit var sendButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startButton =findViewById(R.id.start_button)
        stopButton =findViewById(R.id.stop_button)
        textView = findViewById(R.id.textview)
        serviceTextView = findViewById(R.id.service_text_view)
        editText = findViewById(R.id.editText)
        sendButton = findViewById(R.id.send_data )


        startButton.setOnClickListener{
//            Intent(this, MyIntentService::class.java).also {
//                startService(it)
//                textView.text = "Intent service running"
//            }
            Intent(this, MyService::class.java).also {
                startService(it)
                serviceTextView.text = "Service running"
            }
        }

        stopButton.setOnClickListener {
//            MyIntentService.stopService()
//            textView.text = "Service stopped"

            Intent(this, MyService::class.java).also {
                startService(it)
                serviceTextView.text = "Service stopped"
            }
        }

        sendButton.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val text = editText.text.toString()
                it.putExtra("EXTRA_DATA", text)
                startService(it)
            }
        }



    }
}