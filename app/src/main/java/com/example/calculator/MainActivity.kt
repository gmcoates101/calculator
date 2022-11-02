package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.exp

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
    }

    fun onCalculate(view: View) {
        var result = "10"

        resultTextView.text = result
    }

    fun onClear(view: View) {
        resultTextView.text = ""
    }

    fun onDigit(view: View) {
        val button: Button = view as Button

        resultTextView.append(button.text)
    }

    fun onOperand(view: View) {
        val button: Button = view as Button
        val operand = button.text
        val lastChar = resultTextView.text[resultTextView.text.length -1]
        val operands = arrayOf ("/", "*", "-", "+")

        if (!operands.contains<String>(lastChar.toString())) {
            resultTextView.append("\n${operand}\n")
        }
    }

    fun onPoint(view: View) {
        val point = '.'
        if (!resultTextView.text.contains(point)) {
            resultTextView.append(point.toString())
        }
    }
}