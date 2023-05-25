package com.example.innowiseapplicationlifecycle

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class CounterActivity : AppCompatActivity() {

    private lateinit var counterView: CounterView
    private var counterValue = 0
    private var startTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("Create")

        counterView = findViewById(R.id.counterView)

        counterView.setUpdateCounterLauncher(updateCounterLauncher)

        val prefs = SharedPrefs(getSharedPreferences("counter_prefs", MODE_PRIVATE))
        counterValue = prefs.getCounterToken()
        startTime = prefs.getTimeToken()
        counterView.updateValue(counterValue)
    }

    private val updateCounterLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val updatedValue = result.data?.getIntExtra("updatedValue", 0)
                if (updatedValue != null) {
                    counterValue = updatedValue
                    counterView.updateValue(counterValue)
                }
            }
        }

    override fun onStart() {
        super.onStart()
        val prefs = SharedPrefs(getSharedPreferences("counter_prefs", MODE_PRIVATE))
        counterValue = prefs.getCounterToken()
        startTime = prefs.getTimeToken()
        counterView.updateValue(counterValue)
    }

    override fun onRestart() {
        super.onRestart()
        val prefs = SharedPrefs(getSharedPreferences("counter_prefs", MODE_PRIVATE))
        counterValue += 2
        startTime = prefs.getTimeToken()
        counterValue -= (Date().time- startTime).toInt() / 1000 / 60
        counterView.updateValue(counterValue)
        prefs.setCounterToken(counterValue)
    }


    override fun onStop() {
        super.onStop()
        val prefs = SharedPrefs(getSharedPreferences("counter_prefs", MODE_PRIVATE))
        counterValue +=5
        counterView.updateValue(counterValue)
        prefs.setTimeToken(Date().time)
        prefs.setCounterToken(counterValue)
    }


}