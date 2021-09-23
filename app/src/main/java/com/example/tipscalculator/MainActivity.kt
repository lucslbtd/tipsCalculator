package com.example.tipscalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener{ calculateTip()}
    }

    private fun calculateTip() {

        val costOfService = binding.txtCost.text.toString().toDoubleOrNull()

        if (costOfService == null){
            binding.txtTip.text = ""
            return
        }

        val tipPercent = when (binding.radioBtnGroup.checkedRadioButtonId) {
            R.id.radioBtn_amazing -> 0.20
            R.id.radioBtn_good -> 0.18
            else -> 0.15
        }
        var tip = tipPercent * costOfService

        if (binding.switchRoundUp.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtTip.text = getString(R.string.txt_tip, formattedTip)
    }
}