package com.example.mortgage_calculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val principal = intent.getDoubleExtra("PRINCIPAL", 0.0)
        val interestRate = intent.getDoubleExtra("INTEREST_RATE", 0.0)
        val loanTerm = intent.getIntExtra("LOAN_TERM", 0)

        val monthlyRate = interestRate / 12 / 100
        val numberOfPayments = loanTerm * 12

        val monthlyPayment = principal * (monthlyRate * (1+monthlyRate).pow(numberOfPayments.toDouble())) / ((1 + monthlyRate).pow(numberOfPayments.toDouble()) - 1)
        val totalPayment = monthlyPayment * numberOfPayments

        val resultIntent = Intent()
        resultIntent.putExtra("MONTHLY_PAYMENT", monthlyPayment)
        resultIntent.putExtra("TOTAL_PAYMENT", totalPayment)

        setResult(Activity.RESULT_OK, resultIntent)
        finish()

    }
}