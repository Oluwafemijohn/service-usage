package com.example.intentseervice

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext

class ExampleJobService(content: Context):JobService() {
    private val TAG = "ExampleJob"
    private var jobCancelled: Boolean = false
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "onStartJob: ")
        Toast.makeText(this, "Job started", Toast.LENGTH_SHORT).show()
        doBackgroundJob(params)
        return true
    }

    private fun doBackgroundJob(params: JobParameters?) {
        Thread{
            for (i in 0..10){
                Log.d(TAG, "doBackgroundJob: $i")
                Toast.makeText(this, "task $i ", Toast.LENGTH_SHORT).show()
                try {
                    if (jobCancelled){
                        return@Thread
                    }
                    Thread.sleep(1000)
                }catch(e: InterruptedException){
                    e.printStackTrace()
                }
            }
            Toast.makeText(this, "Job finished", Toast.LENGTH_SHORT).show()
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