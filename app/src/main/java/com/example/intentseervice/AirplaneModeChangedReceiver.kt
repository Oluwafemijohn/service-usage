package com.example.intentseervice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangedReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        var isAirplaneModeEnabled =  intent?.getBooleanExtra("state",false) ?: return
        if (isAirplaneModeEnabled){
            Toast.makeText(context, "Air plane mode enabled", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Air plane mode disabled", Toast.LENGTH_SHORT).show()
        }
        
    }
}