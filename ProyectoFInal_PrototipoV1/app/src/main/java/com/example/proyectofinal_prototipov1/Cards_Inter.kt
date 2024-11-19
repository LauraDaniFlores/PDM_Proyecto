package com.example.proyectofinal_prototipov1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgfp.proyectojuego1.Memorama

class Cards_Inter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cards)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        var juego = findViewById<Cards>(R.id.cardsJuego)
        var continentes = arrayOf(arrayOf("¿Continente más grande?", "Asia"))

        val modulo = getIntent().getStringExtra("modulo")

        Toast.makeText(this, "Intent " + modulo.toString(),
            Toast.LENGTH_LONG).show()

        if (modulo != null) {
            when(modulo.toInt()){
                1 ->  juego.setArray(continentes, 1)
            }
        }

    }

}