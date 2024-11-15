package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ContinentesInter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulos)
        var continentes = ContinentesNiveles(this)
        setContentView(continentes)

    }

}