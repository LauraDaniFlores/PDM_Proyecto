package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencia a los botones
        val buttonJugar: ImageButton = findViewById(R.id.jugar)
        val buttonCreditos: ImageButton = findViewById(R.id.creditos)
        var buttonLogros: ImageButton = findViewById(R.id.logros)

        // Inicializar MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.efectobtn)

        buttonJugar.setOnClickListener(evento)
        buttonLogros.setOnClickListener(evento)
        buttonCreditos.setOnClickListener(evento)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Liberar recursos
    }


    private val evento = View.OnClickListener { v ->
        Toast.makeText(applicationContext, "Click botón", Toast.LENGTH_SHORT)
            .show()
        when (v.getId()) {
            R.id.jugar -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.prepare()
                }
                mediaPlayer.start()
                val i = Intent(this@MainActivity, Modulos::class.java)
                startActivity(i)
            }
            R.id.logros -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.prepare()
                }
                mediaPlayer.start()
                val i: Intent = Intent(
                    this@MainActivity,
                    Maravillas_Inter::class.java
                )
                startActivity(i)
            }
            R.id.creditos-> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.prepare()
                }
                mediaPlayer.start()
                Toast.makeText(applicationContext, "Botón Créditos presionado", Toast.LENGTH_SHORT).show()
                val i: Intent = Intent(
                    this@MainActivity,
                    CreditosAct::class.java
                )
                startActivity(i)
            }
        }
    }
}
