package com.example.proyectofinal_prototipov1

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ModulosIntermedio: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_niveles)
        var oceano = findViewById<OceanoNiveles>(R.id.oceanolayout)
        val tiponivel = getIntent().getStringExtra("tiponivel")?.toInt()
        when (tiponivel) {
            1 ->  oceano.visibility = View.VISIBLE
        }

    }
}
