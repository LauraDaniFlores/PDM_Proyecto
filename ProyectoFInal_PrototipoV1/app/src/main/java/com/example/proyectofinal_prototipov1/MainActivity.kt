package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencia a los botones
        val buttonJugar: ImageButton = findViewById(R.id.jugar)
        val buttonCreditos: ImageButton = findViewById(R.id.creditos)
        var buttonLogros: ImageButton = findViewById(R.id.logros)
        var buttonCarrera: ImageButton = findViewById(R.id.carrera)

        buttonJugar.setOnClickListener(evento)
        buttonLogros.setOnClickListener(evento)
        buttonCreditos.setOnClickListener(evento)
        buttonCarrera.setOnClickListener(evento)
    }
    private val evento = View.OnClickListener { v ->
        Toast.makeText(applicationContext, "Click botón", Toast.LENGTH_SHORT)
            .show()
        when (v.getId()) {
            R.id.jugar -> {
                val i: Intent = Intent(
                    this@MainActivity,
                    Modulos::class.java
                )
                startActivity(i)
            }
            R.id.logros -> {
                val i: Intent = Intent(
                    this@MainActivity,
                    Maravillas_Inter::class.java
                )
                startActivity(i)
            }
            R.id.carrera -> {
                val i: Intent = Intent(
                    this@MainActivity,
                    Trayecto_Inter::class.java
                )
                startActivity(i)
            }
            R.id.creditos-> {
                Toast.makeText(applicationContext, "Botón Créditos presionado", Toast.LENGTH_SHORT).show()
                val i: Intent = Intent(
                    this@MainActivity,
                    CreditosAct::class.java
                )
                startActivity(i)
            }
        }
    }
}
