package com.example.imc.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.example.imc.R
import com.example.imc.main.PrimerAPP.Companion.CAL_KEY

class ResultActivity : AppCompatActivity() {
    private lateinit var tvCalorias:TextView
    private lateinit var btnReCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val resultado:Double = intent.extras?.getDouble(CAL_KEY)?: -1.0

        initComponents()
        initListeners()
        initUI(resultado)
    }

    private fun initUI(resultado:Double) {
        tvCalorias.text = resultado.toString()
    }

    private fun initComponents(){
        btnReCalculate = findViewById(R.id.btnReCalculate)
        tvCalorias = findViewById(R.id.resultCalories)
    }

    private fun initListeners(){
        btnReCalculate.setOnClickListener{onBackPressed()}
    }
}
