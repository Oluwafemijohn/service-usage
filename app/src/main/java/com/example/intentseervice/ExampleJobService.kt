package com.example.intentseervice

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Looper
import android.util.Log
import android.widget.Toast

class ExampleJobService():JobService() {
    private val TAG = "ExampleJob"
    private var jobCancelled: Boolean = false
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob: ")
        doBackgroundJob(params)
        return true
    }

    private fun doBackgroundJob(params: JobParameters?) {
        Thread{
//            Looper.prepare()
            for (i in 0..10){
                Log.d(TAG, "doBackgroundJob: $i")
                try {
                    if (jobCancelled){
                        return@Thread
                    }
                    Thread.sleep(1000)
                }catch(e: InterruptedException){
                    e.printStackTrace()
                }
            }

            Log.d(TAG, "doBackgroundJob: Job finished")
            jobFinished(params, false)
        }.start()
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job cancelled before completions")
        Toast.makeText(this, "Job cancelled", Toast.LENGTH_SHORT).show()
        jobCancelled = true
        return true
    }
}