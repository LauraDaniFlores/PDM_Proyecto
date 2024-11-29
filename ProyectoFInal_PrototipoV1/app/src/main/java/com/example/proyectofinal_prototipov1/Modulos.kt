package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Modulos : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulos)

        val db: DBSQLite = DBSQLite(this)

        // Inicializar MediaPlayer con el efecto de sonido
        mediaPlayer = MediaPlayer.create(this, R.raw.efectobtn)

        val continentes: ImageButton = findViewById(R.id.continentes)
        val oceanos: ImageButton = findViewById(R.id.oceanos)
        val aryan: ImageButton = findViewById(R.id.aryan)
        val mexico: ImageButton = findViewById(R.id.mexico)
        val america: ImageButton = findViewById(R.id.america)
        val Asia: ImageButton = findViewById(R.id.Asia)

        // Cambiar imágenes según el estado del módulo
        if (db.moduloDesbloqueado(2)) {
            oceanos.setImageResource(R.drawable.boceanos) }
        if (db.moduloDesbloqueado(3)) {
            aryan.setImageResource(R.drawable.barticaya) }
        if (db.moduloDesbloqueado(4)) {
            mexico.setImageResource(R.drawable.bmexico) }
        if (db.moduloDesbloqueado(5)) {
            america.setImageResource(R.drawable.bamerica) }
        if (db.moduloDesbloqueado(6)) {
            Asia.setImageResource(R.drawable.basia)}
        
        // Asignar eventos de clic
        continentes.setOnClickListener(evento)
        oceanos.setOnClickListener(evento)
        aryan.setOnClickListener(evento)
        mexico.setOnClickListener(evento)
        america.setOnClickListener(evento)
        Asia.setOnClickListener(evento)
    }

    private val evento = View.OnClickListener { v ->
        // Reproducir el sonido
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
        mediaPlayer.start()

        // Lanzar la actividad con el dato correspondiente
        val i = Intent(this@Modulos, ModulosIntermedio::class.java)
        when (v.id) {
            R.id.continentes -> i.putExtra("tiponivel", "0")
            R.id.oceanos -> i.putExtra("tiponivel", "1")
            R.id.aryan -> i.putExtra("tiponivel", "2")
            R.id.mexico -> i.putExtra("tiponivel", "3")
            R.id.america -> i.putExtra("tiponivel", "4")
            R.id.Asia -> i.putExtra("tiponivel", "5")
        }
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Liberar recursos de MediaPlayer
        mediaPlayer.release()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        onStop()
        val i = Intent(this@Modulos, MainActivity::class.java)
        startActivity(i)
    }
}
