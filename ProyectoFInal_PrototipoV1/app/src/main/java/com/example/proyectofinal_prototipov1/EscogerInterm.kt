package com.example.proyectofinal_prototipov1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class EscogerInterm: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.escoger)

        var escoger = findViewById<Escoger>(R.id.escoger)

        var conti = arrayOf("América", "Europa", "Asia", "África", "Oceanía")
        var coordx = arrayOf(220f,580f,730f,570f,870f)
        var coordy = arrayOf(260f,225f,260f,400f,470f)
        var image = "mapa"
        var coorimage = 707

//        escoger.setArrays(conti, image, coordx, coordy, coorimage)
    }
}