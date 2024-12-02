package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class derrota_Inter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perdiste)
        var perdiste = findViewById<Perdiste>(R.id.perdiste)

        // Inicializar componente del layout
        val nivel = getIntent().getStringExtra("nivel")?.toInt()
        val modulo = getIntent().getStringExtra("modulo")?.toInt()

        perdiste.setVariables(nivel!!, modulo!!)
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {

    }
}