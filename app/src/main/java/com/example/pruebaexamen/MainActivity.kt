package com.example.pruebaexamen

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var cv2sillas: CardView
    private lateinit var cv4sillas: CardView
    private lateinit var cv8sillas: CardView
    private var numAsientos=2
    private lateinit var btnAntes: FloatingActionButton
    private lateinit var btnDespues: FloatingActionButton
    private lateinit var tvHora: TextView
    private val horasPosibles= arrayOf("20:00","21:00","22:00","23:00")
    private var contHora=0
    private lateinit var txtField: TextInputEditText
    private lateinit var btnReservar: FloatingActionButton

    private lateinit var horaReserva:String
    private lateinit var nombreReserva:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        initListeners()
    }
    fun initComponents(){
        cv2sillas=findViewById(R.id.view2sillas)
        cv4sillas=findViewById(R.id.view4sillas)
        cv8sillas=findViewById(R.id.view8sillas)
        btnAntes=findViewById(R.id.btnMenos)
        btnDespues=findViewById(R.id.btnMas)
        tvHora=findViewById(R.id.tvHora)
        txtField=findViewById(R.id.tfNombre)
        btnReservar=findViewById(R.id.btnReservar)

    }
    fun initListeners(){
        cv2sillas.setOnClickListener(){
            numAsientos=2
            cambiarColorOpciones()
        }

        cv4sillas.setOnClickListener(){
            numAsientos=4
            cambiarColorOpciones()
        }

        cv8sillas.setOnClickListener(){
            numAsientos=8
            cambiarColorOpciones()
        }

        btnDespues.setOnClickListener(){
            if (contHora==3){
                contHora=0
            }
            else{
                contHora++
            }
            cambiarHora()
        }

        btnAntes.setOnClickListener(){
            if(contHora==0){
                contHora=3
            }
            else{
                contHora--
            }
            cambiarHora()
        }

        btnReservar.setOnClickListener(){
            horaReserva= tvHora.text.toString()
            nombreReserva=txtField.text.toString()
            cambiarVentana()
        }
    }

    fun cambiarColorOpciones()
    {
        when(numAsientos){
            2->{
                cv2sillas.setCardBackgroundColor(getColor(R.color.selected_elements_color))
                cv4sillas.setCardBackgroundColor(getColor(R.color.elements_bg_color))
                cv8sillas.setCardBackgroundColor(getColor(R.color.elements_bg_color))
            }
            4->{
                cv2sillas.setCardBackgroundColor(getColor(R.color.elements_bg_color))
                cv4sillas.setCardBackgroundColor(getColor(R.color.selected_elements_color))
                cv8sillas.setCardBackgroundColor(getColor(R.color.elements_bg_color))
            }
            8->{
                cv2sillas.setCardBackgroundColor(getColor(R.color.elements_bg_color))
                cv4sillas.setCardBackgroundColor(getColor(R.color.elements_bg_color))
                cv8sillas.setCardBackgroundColor(getColor(R.color.selected_elements_color))
            }
        }
    }

    fun cambiarHora(){
        tvHora.text=horasPosibles[contHora]
    }

    fun cambiarVentana(){
        val intentGA= Intent(this, ReservaResult::class.java)
        intentGA.putExtra("horaReserva", horaReserva)
        intentGA.putExtra("nombreReserva", nombreReserva)
        intentGA.putExtra("numAsientos", numAsientos)
        startActivity(intentGA)
    }
}