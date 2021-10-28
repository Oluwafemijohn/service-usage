package com.example.intentseervice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService: Service() {
    override fun onBind(intent: Intent?): IBinder? = null
    val TAG = "MyTag"
    init {
        Log.d(TAG, "Service is running :.....: ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val dataString = intent?.getStringExtra("EXTRA_DATA")
        dataString?.let {
            Log.d(TAG, "onStartCommand: Service is running ")
        }

//        to create thread
//        Thread{
//            while(true) {
//
//            }
//        }.start()

//        without creating a separate thread, the code below freezes app
//        while(true) {
//
//        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: Service is been killed....")
    }

}