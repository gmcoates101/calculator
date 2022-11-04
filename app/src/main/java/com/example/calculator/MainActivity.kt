package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var point = false
    private var operator = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)
    }

    fun onCalculate(view: View) {
        val result = "10"

        Toast.makeText(this, resultTextView.text, Toast.LENGTH_LONG).show()

        resultTextView.text = result
    }

    fun onClear(view: View) {
        resultTextView.text = ""
        point = false
        operator = true
    }

    fun onDigit(view: View) {
        val button: Button = view as Button

        resultTextView.append(button.text)
        operator = false
    }

    fun onOperator(view: View) {
        val button: Button = view as Button
        val operand = button.text
        val lastChar = resultTextView.text[resultTextView.text.length -1]
        val operands = arrayOf ("/", "*", "-", "+")

        val op = (view as Button).text

        if (!operator) {
            resultTextView.append(" $op\n")
            point = false
            operator = true
        }
    }

    fun onPoint(view: View) {
        if (!point) {
            resultTextView.append(".")
            point = true
        }
    }
}