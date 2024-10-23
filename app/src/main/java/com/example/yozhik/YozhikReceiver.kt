package com.example.yozhik

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast

class YozhikReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        if (message == "Yozhik!") {

            if (context is MainActivity) {
                context.incrementMessageCount()
            }

            Thread.sleep(5000)

            val intentBack = Intent("com.example.SECRET_MESSAGE").apply {
                putExtra("message", "Loshadka!")
                setPackage("com.example.loshadka")
            }
            context?.sendBroadcast(intentBack)
        }
    }

}