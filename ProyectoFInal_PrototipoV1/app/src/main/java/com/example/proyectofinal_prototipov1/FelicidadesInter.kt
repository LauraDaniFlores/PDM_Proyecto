package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class FelicidadesInter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.felicidades)

        //Encontrar
        var felicidades = findViewById<Felicidades>(R.id.felicidades)

        //Tomar los intents de las otras clases
        val nivel = getIntent().getStringExtra("nivel")?.toInt()
        val modulo = getIntent().getStringExtra("modulo")?.toInt()
        val puntaje = getIntent().getStringExtra("puntaje")?.toInt()
        val tiempo = getIntent().getStringExtra("tiempo")?.toInt()

        //Pasar variables a la clase felicidades
        felicidades.setVariables(tiempo!!, puntaje!!, nivel!!, modulo!!)
    }

    //Función para que no puedas regresar
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

    }
}