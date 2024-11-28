package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgfp.proyectojuego1.Memorama

class Maravillas_Inter : AppCompatActivity() {
    var btn: ImageButton? = null
    private lateinit var mediaPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logros)

        var logros = findViewById<MaravillasMundo>(R.id.maravillasMundo)
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