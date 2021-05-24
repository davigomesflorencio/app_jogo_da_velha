package com.example.gamevelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var bt1:ImageButton
    private lateinit var bt2:ImageButton
    private lateinit var bt3:ImageButton
    private lateinit var bt4:ImageButton
    private lateinit var bt5:ImageButton
    private lateinit var bt6:ImageButton
    private lateinit var bt7:ImageButton
    private lateinit var bt8:ImageButton
    private lateinit var bt9:ImageButton

    private lateinit var txt_turno:TextView
    private lateinit var txt_vencedor:TextView

    private var vez:Boolean = true
    private var num_jogadas:Int = 0
    private val Jogador1 = arrayListOf<Int>()
    private val Jogador2 = arrayListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt1 = findViewById(R.id.bt1)
        bt2 = findViewById(R.id.bt2)
        bt3 = findViewById(R.id.bt3)
        bt4 = findViewById(R.id.bt4)
        bt5 = findViewById(R.id.bt5)
        bt6 = findViewById(R.id.bt6)
        bt7 = findViewById(R.id.bt7)
        bt8 = findViewById(R.id.bt8)
        bt9 = findViewById(R.id.bt9)

        txt_turno = findViewById(R.id.turno)
        txt_vencedor = findViewById(R.id.vencedor)

        bt1.setOnClickListener(clickListener)
        bt2.setOnClickListener(clickListener)
        bt3.setOnClickListener(clickListener)
        bt4.setOnClickListener(clickListener)
        bt5.setOnClickListener(clickListener)
        bt6.setOnClickListener(clickListener)
        bt7.setOnClickListener(clickListener)
        bt8.setOnClickListener(clickListener)
        bt9.setOnClickListener(clickListener)

    }

    private val clickListener = View.OnClickListener { view ->

        when (view.getId()) {
            R.id.bt1 -> clickbt(view,1)
            R.id.bt2 -> clickbt(view,2)
            R.id.bt3 -> clickbt(view,3)
            R.id.bt4 -> clickbt(view,4,)
            R.id.bt5 -> clickbt(view,5)
            R.id.bt6 -> clickbt(view,6)
            R.id.bt7 -> clickbt(view,7)
            R.id.bt8 -> clickbt(view,8)
            R.id.bt9 -> clickbt(view,9)
        }
    }

    private fun clickbt(view:View,position:Int){
        num_jogadas+=1
        if (vez) {
            view.setBackgroundResource(R.drawable.fla)
            txt_turno.text = "Vez do Jogador do Vasco"
            Jogador1.add(position)

        }else{
            view.setBackgroundResource(R.drawable.vsc)
            txt_turno.text = "Vez do Jogador do Flamengo"
            Jogador2.add(position)
        }
        view.isEnabled = false
        vez = !(vez)
        checaResultado()
    }


    private fun checaResultado(){
        val possiveisResultados = listOf(
            listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9), listOf(1, 4, 7),
            listOf(2, 5, 8), listOf(3, 6, 9), listOf(1, 5, 9), listOf(3, 5, 7)
        )

        possiveisResultados.forEach {
            if (Jogador1.containsAll(it)) {
                txt_turno.text = ""
                DesativaBts()
                txt_vencedor.text = "Vencedor: Flamengo"
            }
        }

        possiveisResultados.forEach {
            if (Jogador2.containsAll(it)) {
                txt_turno.text = ""
                DesativaBts()
                txt_vencedor.text = "Vencedor: Vasco"
            }
        }
        if(num_jogadas==9){
            txt_turno.text = ""
            txt_vencedor.text = "Vencedor: Ninguém , Deu Velha"
        }
    }

    private fun DesativaBts(){
        bt1.isEnabled = false
        bt2.isEnabled = false
        bt3.isEnabled = false
        bt4.isEnabled = false
        bt5.isEnabled = false
        bt6.isEnabled = false
        bt7.isEnabled = false
        bt8.isEnabled = false
        bt9.isEnabled = false
    }
}