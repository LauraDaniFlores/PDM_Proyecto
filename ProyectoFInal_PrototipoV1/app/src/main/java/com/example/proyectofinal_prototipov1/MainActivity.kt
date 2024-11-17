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

        buttonJugar.setOnClickListener(evento)
    }
    private val evento = View.OnClickListener { v ->
        Toast.makeText(applicationContext, "Click botón", Toast.LENGTH_SHORT)
            .show()
        val i: Intent = Intent(
            this@MainActivity,
            Modulos::class.java
        )
        startActivity(i)

    }
}

