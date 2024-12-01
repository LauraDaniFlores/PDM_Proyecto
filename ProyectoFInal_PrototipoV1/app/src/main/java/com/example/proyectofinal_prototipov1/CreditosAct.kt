package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CreditosAct : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creditos) // Usa el layout de créditos

        var boton = findViewById<ImageButton>(R.id.logoview)
        // Inicializar MediaPlayer con el efecto de sonido
        mediaPlayer = MediaPlayer.create(this, R.raw.efectobtn)

        boton.setOnClickListener(evento)
    }
    private val evento = View.OnClickListener { v ->
        // Reproducir el sonido
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
        mediaPlayer.start()
        val i: Intent = Intent(
            this@CreditosAct,
            MainActivity::class.java
        )
        this.startActivity(i)
    }

}