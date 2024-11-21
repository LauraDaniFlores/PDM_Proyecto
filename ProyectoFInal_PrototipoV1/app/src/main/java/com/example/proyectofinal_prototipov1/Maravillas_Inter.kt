package com.example.proyectofinal_prototipov1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgfp.proyectojuego1.Memorama

class Maravillas_Inter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.logros)

        var logros = findViewById<MaravillasMundo>(R.id.maravillasMundo)

    }
}