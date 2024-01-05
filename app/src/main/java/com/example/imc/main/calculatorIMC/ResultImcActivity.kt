package com.example.imc.main.calculatorIMC

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.KeyEventDispatcher.Component
import com.example.imc.R
import com.example.imc.main.calculatorIMC.ImcCalculatorActivity.Companion.IMC_KEY
import org.w3c.dom.Text

class ResultImcActivity : AppCompatActivity() {

    private lateinit var tvResult:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescription:TextView
    private lateinit var btnReCalculate:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imc)
        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0

        initcompotents()
        initListeners()
        initUI(result)
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener{onBackPressed()}
    }

    private fun initUI(result:Double){
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 ->{ //Bajo peso
                tvResult.text = getString(R.string.low)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_bajo))
                tvDescription.text = getString(R.string.lowWeight)
            }
            in 18.51..24.99->{ //Normal peso
                tvResult.text = getString(R.string.normal)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.peso_normal))
                tvDescription.text = getString(R.string.normalWeight)
            }
            in 25.00..29.99->{ // Sobrepeso
                tvResult.text = getString(R.string.high)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.sobrepeso))
                tvDescription.text = getString(R.string.highWeight)
            }
            in 30.00..99.00->{ //Obesidad
                tvResult.text = getString(R.string.veryhigh)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.obesidad))
                tvDescription.text = getString(R.string.veryHighWeight)
            }
            else-> { //Error
                tvResult.text = getString(R.string.error)
                tvIMC.text = getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }

        }

    }
    private fun initcompotents() {

        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }



}