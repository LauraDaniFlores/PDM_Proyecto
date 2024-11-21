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

        var continentes: ImageButton = findViewById(R.id.continentes)
        var oceanos: ImageButton = findViewById(R.id.oceanos)
        var aryan: ImageButton = findViewById(R.id.aryan)
        var mexico: ImageButton = findViewById(R.id.mexico)
        var america: ImageButton = findViewById(R.id.america)
//        var Asia: ImageButton = findViewById(R.id.Asia)

        continentes.setOnClickListener(evento)
        oceanos.setOnClickListener(evento)
        aryan.setOnClickListener(evento)
        mexico.setOnClickListener(evento)
        america.setOnClickListener(evento)
//        Asia.setOnClickListener(evento)
    }
    private val evento = View.OnClickListener { v ->
        val i: Intent = Intent(
            this@Modulos,
            ModulosIntermedio::class.java
        )
        when (v.getId()) {
            R.id.continentes -> {
                i.putExtra("tiponivel", "0")
            }
            R.id.oceanos -> {
                i.putExtra("tiponivel", "1")
            }
            R.id.aryan -> {
                i.putExtra("tiponivel", "2")
            }
            R.id.mexico -> {
                i.putExtra("tiponivel", "3")
            }
            R.id.america -> {
                i.putExtra("tiponivel", "4")
            }
//            R.id.Asia -> {
//                i.putExtra("tiponivel", "6")
//            }
        }
        startActivity(i)
    }
}
