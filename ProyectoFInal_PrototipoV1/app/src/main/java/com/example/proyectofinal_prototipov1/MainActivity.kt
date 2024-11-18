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

        // Encontrar los botones en el layout
        val buttonJugar: ImageButton = findViewById(R.id.jugar)
        val buttonLogros: ImageButton = findViewById(R.id.logros)

        // Asignar el mismo listener a ambos botones
        buttonJugar.setOnClickListener(evento)
        buttonLogros.setOnClickListener(evento)
    }

    private val evento = View.OnClickListener { v ->
        when (v.id) {
            R.id.jugar -> {
                Toast.makeText(applicationContext, "Iniciando juego...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, Modulos::class.java)
                startActivity(intent)
            }
            R.id.logros -> {
                Toast.makeText(applicationContext, "Abriendo logros...", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, maravillasMundo::class.java)
                startActivity(intent)
            }
            else -> {
                Toast.makeText(applicationContext, "Botón desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
