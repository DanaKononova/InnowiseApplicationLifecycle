package com.example.innowiseapplicationlifecycle

import android.app.Activity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class CounterActivity : AppCompatActivity() {

    private lateinit var counterView: CounterView
    private var counterValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        counterView = findViewById(R.id.counterView)

        counterView.setUpdateCounterLauncher(updateCounterLauncher)
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

}
