package com.example.imc.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.imc.R
import com.example.imc.main.calculatorIMC.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val menu = findViewById<Button>(R.id.Menu_1)
        val menuImcApp = findViewById<Button>(R.id.IMC)
        menu.setOnClickListener{navigateCaloriasAPP()}
        menuImcApp.setOnClickListener{navigateImcAPP()}
    }
    private fun navigateCaloriasAPP(){
        val intent = Intent(this, PrimerAPP::class.java)

        startActivity(intent)
    }
    private fun navigateImcAPP(){
        val intent = Intent(this,ImcCalculatorActivity::class.java)

        startActivity(intent)
    }
}