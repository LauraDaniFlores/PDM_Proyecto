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
        var manual: ImageButton = findViewById(R.id.tutorial)

        buttonJugar.setOnClickListener(evento)
        manual.setOnClickListener(evento)
    }
    private val evento = View.OnClickListener { v ->
        when (v.getId()) {
            R.id.jugar -> {
                val i: Intent = Intent(
                    this@MainActivity,
                    Modulos::class.java
                )
                startActivity(i)
            }
            R.id.tutorial ->{
                val i: Intent = Intent(this@MainActivity,
                    Manual::class.java
                )
                startActivity(i)
            }
        }
    }
}

