package com.example.threadstimer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
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
        val currentTime = System.currentTimeMillis()
        val startTime = editText?.text.toString().toLong()
        handler?.post(
            updateTimer(startTime, currentTime)
        )
    }

    private fun updateTimer(startTime: Long, currentTime: Long): Runnable {
        return object : Runnable {
            override fun run() {
                val time = startTime - ((System.currentTimeMillis() - currentTime))/1000
                textView?.text = time.toString()
                if (time > 0) {
                    handler?.postDelayed(this, ONE_SECOND)
                }
                else{
                    val vibrator = applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    vibrator.vibrate(300L)
                    textView?.text = "Done!"
                }
            }
        }
    }
}