package com.example.imc.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.imc.R
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class PrimerAPP : AppCompatActivity() {

    private var maleIsSelect :Boolean = true
    private var femaleIsSelect :Boolean= false
    private var currentHeight : Int=160
    private var currentWeight : Int=60
    private var currentAge : Int=18

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var tvWeight:TextView
    private lateinit var rsWeight:RangeSlider
    private lateinit var tvAge:TextView
    private lateinit var rsAge:RangeSlider
    private lateinit var btnCalculator:Button

    companion object {
        const val CAL_KEY = "CAL_Result"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primer_app)

        initcomponents()
        initListeners()
        initUI()

    }

    private fun initUI() {
        genderColor()
    }

    private fun initcomponents() {
        viewMale = findViewById(R.id.maleView)
        viewFemale = findViewById(R.id.femaleView)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        rsWeight = findViewById(R.id.rsWeight)
        tvAge = findViewById(R.id.tvAge)
        rsAge = findViewById(R.id.rsAge)
        btnCalculator = findViewById(R.id.btnCalculator)
    }
    private fun initListeners() {
        viewMale.setOnClickListener{
            if(!maleIsSelect){
                changeGender()
            }
            genderColor()
        }
        viewFemale.setOnClickListener {
            if(!femaleIsSelect){
                changeGender()
            }
            genderColor()
        }
        rsHeight.addOnChangeListener{_,value,_ ->
            val decimalFormat = DecimalFormat("#.##")
            currentHeight = decimalFormat.format(value).toInt()
            tvHeight.text = ("$currentHeight cm")
        }

        rsWeight.addOnChangeListener{_,value,_ ->
            val decimalFormat = DecimalFormat("#.##")
            currentWeight = decimalFormat.format(value).toInt()
            tvWeight.text = ("$currentWeight Kg")
        }
        rsAge.addOnChangeListener{_,value,_ ->
            val decimalFormat = DecimalFormat("#.##")
            currentAge = decimalFormat.format(value).toInt()
            tvAge.text = ("$currentAge años")
        }

        btnCalculator.setOnClickListener{
            val result = calculateCalorias()
            navigateToResult(result)
        }
    }
    private fun calculateCalorias() : Double{
        val df = DecimalFormat("#.##")
        val cal = when{
            maleIsSelect -> {
                66 + (13.7 * currentWeight.toDouble()) + (5 * currentHeight.toDouble()) - (6.8 * currentAge.toDouble())
            }
            femaleIsSelect ->{
                655 + (9.6 * currentWeight.toDouble()) + (1.8 * currentHeight.toDouble()) - (4.7 * currentAge.toDouble())
            }
            else -> 0.00
        }
        return df.format(cal).toDouble()
    }
    private fun navigateToResult(result:Double){
        val intent = Intent(this,ResultActivity::class.java)
        intent.putExtra(CAL_KEY, result)
        startActivity(intent)
    }

    private fun changeGender(){
        maleIsSelect = !maleIsSelect
        femaleIsSelect = !femaleIsSelect
    }

    private fun genderColor(){
        viewMale.setCardBackgroundColor(getColorBackground(maleIsSelect))
        viewFemale.setCardBackgroundColor(getColorBackground(femaleIsSelect))
    }
    private fun getColorBackground(isSelectComponent:Boolean):Int {
        val realColor = if(isSelectComponent) {
            R.color.backgroundComponentSelected
        } else {
            R.color.backgroundComponent
        }
        return ContextCompat.getColor(this,realColor)
    }
}
