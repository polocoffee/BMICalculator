package com.banklannister.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.banklannister.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            weightPicker.minValue = 30
            weightPicker.maxValue = 150

            heightPicker.minValue = 100
            heightPicker.maxValue = 250

            weightPicker.setOnValueChangedListener { _, _, _ ->
                calculateBMI()
            }

            heightPicker.setOnValueChangedListener { _, _, _ ->
                calculateBMI()
            }

        }
    }

    private fun calculateBMI() {
        val height = binding.heightPicker.value
        val doubleHeight = height.toDouble() / 100

        val weight = binding.weightPicker.value

        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)

        binding.apply {
            resultTv.text = String.format("Your BMI is: %.2f", bmi)
            healthyTv.text = String.format("Consider %s", healthyMessage(bmi))
        }
    }

    private fun healthyMessage(bmi: Double): String {
        if (bmi < 18.50)
            return "Underweight"
        if (bmi < 25.0)
            return "Healthy"
        if (bmi< 30.0)
            return "Overweight"

        return "Obese"
    }
}