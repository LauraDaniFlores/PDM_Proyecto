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
        var buttonJugar: ImageButton = findViewById(R.id.jugar)
        var buttonLogros: ImageButton = findViewById(R.id.logros)

        buttonJugar.setOnClickListener(evento)
        buttonLogros.setOnClickListener(evento)
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
        }

    }


}

