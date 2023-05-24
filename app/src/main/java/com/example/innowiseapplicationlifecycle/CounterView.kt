package com.example.innowiseapplicationlifecycle

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class CounterView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var updateCounterLauncher: ActivityResultLauncher<Intent>? = null
    private val counterText: TextView
    private val tapButton: Button
    private val colorView: View

    private var value = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.counter_layout, this, true)

        counterText = findViewById(R.id.counterText)
        tapButton = findViewById(R.id.tapButton)
        colorView = findViewById(R.id.colorView)

        setButtonListeners()
    }

    fun setUpdateCounterLauncher(launcher: ActivityResultLauncher<Intent>) {
        updateCounterLauncher = launcher
    }

    private fun setButtonListeners() {
        tapButton.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                tapButton.viewTreeObserver.removeOnGlobalLayoutListener(this)

                val colorViewWidth = colorView.width
                val buttonWidth = colorViewWidth / 3
                val params = tapButton.layoutParams
                params.width = buttonWidth
                params.height = buttonWidth
                tapButton.layoutParams = params
            }
        })

        tapButton.setOnClickListener {
            val intent = Intent(context, UpdateCounterActivity::class.java)
            intent.putExtra("counterValue", value)
            updateCounterLauncher?.launch(intent)
        }
    }

    fun updateValue(newValue: Int) {
        value = newValue
        counterText.text = value.toString()

        val radius = value
        val roundedCorner = GradientDrawable().apply {
            cornerRadii = floatArrayOf(
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat(),
                radius.toFloat()
            )
            color = ContextCompat.getColorStateList(context, R.color.yellow)
        }
        colorView.background = roundedCorner
    }

}