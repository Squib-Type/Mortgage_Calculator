package com.example.mortgage_calculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import com.example.mortgage_calculator.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val monthlyPayment = data?.getDoubleExtra("MONTHLY_PAYMENT", 0.0) ?: 0.0
                val totalPayment = data?.getDoubleExtra("TOTAL_PAYMENT", 0.0) ?: 0.0

                binding.monthlyPaymentsResults.text = String.format("Monthly Payment: $%.2f", monthlyPayment)
                binding.totalPaymentsResults.text = String.format("Total Payments: $%.2f", totalPayment)
            }
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val principal = binding.editPrincipal.text.toString().toDouble()
            val interestRate = binding.editInterestRate.text.toString().toDouble()
            val loanTerm = binding.editLoanTerm.text.toString().toInt()

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra("PRINCIPAL", principal)
                putExtra("INTEREST_RATE", interestRate)
                putExtra("LOAN_TERM", loanTerm)
            }

            resultLauncher.launch(intent)
        }

    }
}