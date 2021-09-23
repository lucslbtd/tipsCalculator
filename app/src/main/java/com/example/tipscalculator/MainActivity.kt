package com.example.tipscalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding
import java.lang.Math.ceil
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener{ calculateTip()}
    }

    fun calculateTip() {

        val stringCost = binding.txtCost.text.toString()
        val costOfService = stringCost.toDouble()

        val selectedId = binding.radioBtnGroup.checkedRadioButtonId
        val tipPercent = when (selectedId) {
            R.id.radioBtn_amazing -> 0.2
            R.id.radioBtn_good -> 0.18
            else -> 0.15
        }
        var tip = tipPercent * costOfService
        val isRoundUp = binding.switchRoundUp.isChecked
        if (isRoundUp) {
            tip = ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.txtTip.text = getString(R.string.txt_tip, formattedTip)
    }
}