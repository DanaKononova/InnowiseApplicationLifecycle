package com.example.innowiseapplicationlifecycle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UpdateCounterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_counter)

        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val updateButton = findViewById<Button>(R.id.updateButton)

        val counterValue = intent.getIntExtra("counterValue", 0)

        updateButton.setOnClickListener {
            val updatedValue = counterValue + 10
            val intent = Intent().apply {
                putExtra("updatedValue", updatedValue)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}

