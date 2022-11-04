package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var lastPoint = false
    private var lastOperator = false
    private var lastDigit = false

    // TODO: Default result to 0 and handle when next tap
    // TODO: Prevent first char being operator
    // TODO: Percentage feature
    // TODO: Add Backspace feature

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)
        onClear(resultTextView)
    }

    fun onClear(view: View) {
        resultTextView.text = "0"
        lastPoint = false
        lastOperator = false
        lastDigit = false
    }

    fun onDigit(view: View) {
        resultTextView.append((view as Button).text)
        lastDigit = true
        lastPoint = false
        lastOperator = false
    }

    fun onOperator(view: View) {
        val operator = (view as Button).text.toString()

        if (operator == "-") {
            if (!lastDigit || lastOperator)  {
                resultTextView.append("-")
            }
        } else if (!lastOperator) {
            resultTextView.append(" $operator ")
            lastOperator = true
        }

        lastPoint = false
        lastDigit = false
    }

    fun onPoint(view: View) {
        var pointString = "."
        if (resultTextView.text.isNullOrBlank()) {
            pointString = "0."
        }

        if (!lastPoint) {
            resultTextView.append(pointString)
            lastPoint = true
        }
    }

    fun onCalculate(view: View) {

        try {
            val (op1, op, op2) = resultTextView.text.split(' ')

            val result = when(op) {
                "*" -> (op1.toDouble() * op2.toDouble()).toString()
                "/" -> (op1.toDouble() / op2.toDouble()).toString()
                "+" -> (op1.toDouble() + op2.toDouble()).toString()
                else -> (op1.toDouble() - op2.toDouble()).toString()
            }

            lastOperator = false
            lastDigit = true
            lastPoint = result.contains('.')

            resultTextView.text = removeDotZero(result)
        } catch(ex: Exception) {
            Toast.makeText(this, "Expression is not complete!", Toast.LENGTH_LONG).show()
        }
    }

    private fun removeDotZero(str: String): String {
        val strLen = str.length

        if (str.endsWith(".0")) {
            return str.substring(0, strLen - 2)
        }

        if (str.endsWith("0")) {
            return str.substring(0, strLen - 1)
        }

        return str
    }
}