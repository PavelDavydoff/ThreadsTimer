package com.example.threadstimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

const val ONE_SECOND = 1000L

class MainActivity : AppCompatActivity() {

    private var handler: Handler? = null

    private var editText: EditText? = null
    private var button: Button? = null
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.startButton)
        textView = findViewById(R.id.textView)

        handler = Handler(Looper.getMainLooper())

        button?.setOnClickListener {
            startTimer()
        }
    }

    private fun startTimer() {
        val startTime = System.currentTimeMillis()
        handler?.post {
            updateTimer(startTime)
        }
    }

    private fun updateTimer(startTime: Long): Runnable {
        return object : Runnable {
            override fun run() {
                textView?.text = (System.currentTimeMillis() - startTime).toString()
                handler?.postDelayed(this, ONE_SECOND)
            }
        }
    }
}