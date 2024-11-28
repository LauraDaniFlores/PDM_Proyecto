package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources

class Trayecto_Inter : AppCompatActivity() {
    var btn: ImageButton? = null
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trayecto)

        var trayecto = findViewById<Trayecto>(R.id.trayecto)

        var btn = findViewById<ImageButton>(R.id.regreso)
        // Inicializar MediaPlayer con el efecto de sonido
        mediaPlayer = MediaPlayer.create(this, R.raw.efectobtn)

        btn.setOnClickListener(evento)

    }

    private val evento = View.OnClickListener { v ->
        // Reproducir el sonido
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
        mediaPlayer.start()
        var intent: Intent? = null
        when (v.id ) {
            R.id.regreso ->  intent = Intent(this, MainActivity::class.java)
        }
        this.startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos de MediaPlayer
        mediaPlayer.release()
    }
}