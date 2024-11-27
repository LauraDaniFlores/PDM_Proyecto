package com.example.proyectofinal_prototipov1

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources

class Trayecto_Inter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trayecto)

        var trayecto = findViewById<Trayecto>(R.id.trayecto)

    }
}