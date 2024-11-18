package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Modulos : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulos)
        var oceanos: ImageButton = findViewById(R.id.oceanos)

        oceanos.setOnClickListener(evento)
    }
    private val evento = View.OnClickListener { v ->
        Toast.makeText(applicationContext, "Click botón oceanos", Toast.LENGTH_SHORT)
            .show()
        startActivity(intent)
        val i: Intent = Intent(
            this@Modulos,
            ContinentesInter::class.java
        )
        startActivity(i)
    }
}
