package com.example.imc.main.calculatorIMC

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.imc.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat
import kotlin.math.log

class ImcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected : Boolean = true
    private var isFemaleSelected : Boolean = false
    private var currentWeight : Int = 60
    private var currentAge : Int = 20
    private var currentHeight : Int = 120


    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var buttonSubtractWeight:FloatingActionButton
    private lateinit var buttonPlusWeight:FloatingActionButton
    private lateinit var tvWeight:TextView
    private lateinit var buttonPlusAge:FloatingActionButton
    private lateinit var buttonSubtractAge:FloatingActionButton
    private lateinit var tvAge:TextView
    private lateinit var btnCalculator:Button

    companion object{
        const val IMC_KEY = "IMC_Result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents(){
        viewMale = findViewById(R.id.maleView)
        viewFemale = findViewById(R.id.femaleView)
        tvHeight= findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        buttonPlusWeight = findViewById(R.id.buttonPlusWeight)
        buttonSubtractWeight = findViewById(R.id.buttonSubtractWeight)
        tvWeight = findViewById(R.id.tvWeight)
        buttonPlusAge = findViewById(R.id.buttonPlusAge)
        buttonSubtractAge = findViewById(R.id.buttonSubtractAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculator = findViewById(R.id.btnCalculator)
    }

    private fun initListeners(){
        viewMale.setOnClickListener{
            if(!isMaleSelected){
                changeGender()
            }
            genderColor()
        }
        viewFemale.setOnClickListener{
            if(!isFemaleSelected){
                changeGender()
            }
            genderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val decimalFormat = DecimalFormat("#.##")
            currentHeight = decimalFormat.format(value).toInt()
            tvHeight.text = ("$currentHeight cm")
        }
        buttonSubtractWeight.setOnClickListener{
            if(currentWeight>0){
                currentWeight--
            }
            setWeight()
        }
        buttonPlusWeight.setOnClickListener{
            currentWeight++
            setWeight()
        }
        //EDAD
        buttonSubtractAge.setOnClickListener{
            if(currentAge>0){
                currentAge--
            }
            setAge()
        }
        buttonPlusAge.setOnClickListener{
            currentAge++
            setAge()
        }

        btnCalculator.setOnClickListener{
            val result = calculateIMC()
            nagivateToResult(result)
        }
    }

    private fun nagivateToResult(result:Double) {
        val intent = Intent(this,ResultImcActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC() : Double{
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
    }
    private fun setAge(){
        tvAge.text = currentAge.toString()
    }
    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }

    private fun changeGender(){

        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }
    private fun genderColor()
    {
        viewMale.setCardBackgroundColor(getColorBackground(isMaleSelected))
        viewFemale.setCardBackgroundColor(getColorBackground(isFemaleSelected))
    }

    private fun getColorBackground(isSelectComponent:Boolean):Int{

        val realColor = if(isSelectComponent){
            R.color.backgroundComponentSelected
        }else{
            R.color.backgroundComponent
        }

        return ContextCompat.getColor(this,realColor)
    }

    private fun initUI() {
        genderColor()
        setWeight()
        setAge()
    }
}