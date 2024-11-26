package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FelicidadesInter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nivel = getIntent().getStringExtra("nivel")?.toInt()
        val modulo = getIntent().getStringExtra("modulo")?.toInt()
        val puntaje = getIntent().getStringExtra("puntaje")?.toInt()
        val tiempo = getIntent().getStringExtra("tiempo")?.toInt()

        var felicidades = Felicidades(getApplicationContext())
        setContentView(felicidades)
        felicidades.setVariables(tiempo!!, puntaje!!, nivel!!, modulo!!)
//        felicidades.setVariables(100, 100, 1, 1)
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

    }
}