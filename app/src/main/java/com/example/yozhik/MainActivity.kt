package com.example.yozhik

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var messageCountView: TextView
    private lateinit var receiver: YozhikReceiver

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("App1Prefs", MODE_PRIVATE)
        messageCountView = findViewById(R.id.messageCountView)

        updateMessageCount()

        receiver = YozhikReceiver()
        val intentFilter = IntentFilter("com.example.SECRET_MESSAGE")
        registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED)

        val intent = Intent("com.example.SECRET_MESSAGE").apply {
            putExtra("message", "Loshadka!")
            setPackage("com.example.loshadka")
        }
        sendBroadcast(intent)
    }

    private fun updateMessageCount() {
        val count = sharedPreferences.getInt("messageCount", 0)
        messageCountView.text = "Сообщений получено: $count"
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    fun incrementMessageCount() {
        val currentCount = sharedPreferences.getInt("messageCount", 0)
        sharedPreferences.edit().putInt("messageCount", currentCount + 1).apply()
        updateMessageCount()
    }
}