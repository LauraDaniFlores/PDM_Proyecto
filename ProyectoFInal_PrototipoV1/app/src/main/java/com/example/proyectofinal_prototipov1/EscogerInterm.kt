package com.example.proyectofinal_prototipov1

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EscogerInterm: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.escoger)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var escoger = findViewById<Escoger>(R.id.escoger)
        var modulo = getIntent().getStringExtra("modulo")?.toInt()
        if (modulo != null) {
            modulo -= 1
        }
        modulo = 2


        Toast.makeText(this, "Intent " + modulo.toString(),
            Toast.LENGTH_LONG).show()

        var conti = arrayOf(arrayOf("América", "Europa", "Asia", "África", "Oceanía"), arrayOf("Pacífico", "Atlántico", "Índico", "Antártico", "Ártico"), arrayOf("Baja California", "Sonora", "Sinaloa", "Chihuahua", "Coahuila", "Durango", "Nuevo León", "Tamaulipas", "Zacatecas", "Jalisco"))
        var coordx = arrayOf(arrayOf(220f,580f,730f,570f,870f), arrayOf(690f,950f,340f,630f,650f), arrayOf(145f,280f,370f,400f,520f, 435f, 590f, 632f, 500f, 472f))
        var coordy = arrayOf(arrayOf(260f,225f,260f,400f,470f), arrayOf(400f,250f,450f,610f,90f), arrayOf(170f,230f,400f,300f,330f,410f, 370f, 420f, 445f, 547f))
        var image = arrayOf("mapa", "oceanosmapa", "mexicomapa")
        var coorimage = arrayOf(707f, 707f, 819f)
        var numbers = arrayOf(arrayOf(0, 1, 2, 3, 4), arrayOf(0, 1, 2, 3, 4), arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))


        escoger.setArrays(conti[modulo!!], image[modulo!!], coordx[modulo!!], coordy[modulo!!], coorimage[modulo!!], numbers[modulo!!])


    }
}