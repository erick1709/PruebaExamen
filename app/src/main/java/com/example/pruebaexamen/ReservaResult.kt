package com.example.pruebaexamen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ReservaResult : AppCompatActivity() {
    private lateinit var tvReserva:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reserva_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        val horaReserva : String? = intent.extras?.getString("horaReserva")
        val nombreReserva : String? = intent.extras?.getString("nombreReserva")
        val numAsientos : Int? = intent.extras?.getInt("numAsientos")
        val texto: String="mesa reservada para "+numAsientos+" personas a nombre de "+nombreReserva+", a las "+horaReserva
        tvReserva.text= texto
    }

    private fun initComponents(){
        tvReserva=findViewById(R.id.tvReserva)
    }
}