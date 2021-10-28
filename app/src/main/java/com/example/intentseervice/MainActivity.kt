package com.example.intentseervice

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var startIntentServiceButton: Button
    private lateinit var stopIntentServiceButton: Button
    private lateinit var startServiceButton: Button
    private lateinit var stopServiceButton: Button
    private lateinit var startJobButton: Button
    private lateinit var cancelJobButton: Button
    private lateinit var intentServiceTextView:TextView
    private lateinit var serviceTextView: TextView
    private lateinit var editText: EditText
    private lateinit var sendButton: Button
    private lateinit var receiver: AirplaneModeChangedReceiver

    private val TAG = "MAINACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startIntentServiceButton =findViewById(R.id.start_intent_service_button)
        stopIntentServiceButton = findViewById(R.id.stop_intent_service_button)
        startServiceButton =findViewById(R.id.start_service_button)
        stopServiceButton = findViewById(R.id.stop_service_button)
        startJobButton = findViewById(R.id.start_job_button)
        cancelJobButton = findViewById(R.id.cancel_job_button)
        intentServiceTextView = findViewById(R.id.intent_service_textview)
        serviceTextView = findViewById(R.id.service_text_view)
        editText = findViewById(R.id.editText)
        sendButton = findViewById(R.id.send_data )

        receiver = AirplaneModeChangedReceiver()

        //BroadcastReceiver
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }

        startIntentServiceButton.setOnClickListener{
            //For service
            startIntentService()
        }

        stopIntentServiceButton.setOnClickListener {
            stopIntentService()
        }
        startServiceButton.setOnClickListener {
            startService()
        }

        stopServiceButton.setOnClickListener {
            stopService()
        }

        //For sending data o service
        sendButton.setOnClickListener {
            sendData()
        }
        startJobButton.setOnClickListener {
            scheduleJob()
        }
        cancelJobButton.setOnClickListener {
            cancelJob()
        }

    }

    private fun scheduleJob() {
        val  componentName =  ComponentName(this, ExampleJobService::class.java  )
        val info = JobInfo.Builder(123,componentName)
            .setRequiresCharging(true)
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
            .setPersisted(true)
            .setPeriodic(15*60*1000)
            .build()
        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as (JobScheduler)
        val result = scheduler.schedule(info)
        if (result == JobScheduler.RESULT_SUCCESS){
            Log.d(TAG, "Job scheduled")
        }else{

            Log.d(TAG, "Job scheduling failled")
        }
    }

    private fun cancelJob() {
        val scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as (JobScheduler)
        scheduler.cancel( 123)
        Log.d(TAG, "cancelJob: Jobcancelled")
    }

    private fun startIntentService(){
        // For intentseervice
        Intent(this, MyIntentService::class.java).also {
            startService(it)
            intentServiceTextView.text = "Intent service running"
        }
    }
    private fun stopIntentService(){
        //For intent service
        MyIntentService.stopService()
        intentServiceTextView.text = "Service stopped"
    }
    private fun startService(){
        Intent(this, MyService::class.java).also {
            startService(it)
            serviceTextView.text = "Service running"
        }
    }

    private fun stopService(){
        // For service
        Intent(this, MyService::class.java).also {
            startService(it)
            serviceTextView.text = "Service stopped"
        }
    }

    private fun sendData(){
        Intent(this, MyService::class.java).also {
            val text = editText.text.toString()
            it.putExtra("EXTRA_DATA", text)
            startService(it)
            serviceTextView.text = "Data sent to service stopped"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}