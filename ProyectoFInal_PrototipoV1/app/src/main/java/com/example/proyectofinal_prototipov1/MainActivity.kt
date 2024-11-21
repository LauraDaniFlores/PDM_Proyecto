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

        // Asignar eventos a los botones
        buttonJugar.setOnClickListener {
            Toast.makeText(applicationContext, "Botón Jugar presionado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Modulos::class.java)
            startActivity(intent) // Abre la actividad "Modulos"
        }

        buttonCreditos.setOnClickListener {
            Toast.makeText(applicationContext, "Botón Créditos presionado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, CreditosAct::class.java)
            startActivity(intent) // Abre la actividad de Créditos
        }
    }
}
