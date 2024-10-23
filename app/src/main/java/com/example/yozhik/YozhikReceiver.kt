package com.example.yozhik

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class YozhikReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val handler = Handler(Looper.getMainLooper())
        val result = goAsync()
        val thread: Thread = object : Thread() {
            override fun run() {
                val message = intent?.getStringExtra("message")
                if (message == "Yozhik!") {

                    if (context is MainActivity) {
                        handler.post{
                            context.incrementMessageCount()
                        }
                    }

                    sleep(5000)

                    val intentBack = Intent("com.example.SECRET_MESSAGE").apply {
                        putExtra("message", "Loshadka!")
                        setPackage("com.example.loshadka")
                    }
                    context?.sendBroadcast(intentBack)
                }
                result.finish()
            }
        }
        thread.start()
    }

}