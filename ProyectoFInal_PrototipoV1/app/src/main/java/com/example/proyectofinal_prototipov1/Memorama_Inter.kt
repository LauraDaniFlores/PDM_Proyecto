package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgfp.proyectojuego1.Memorama


class Memorama_Inter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memorama)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var juego = findViewById<Memorama>(R.id.juego)
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)

        var continentes = arrayOf("mcontinente1", "mcontinente2", "mcontinente3", "mcontinente4", "mcontinente5")
        var aryan = arrayOf("maryan1", "maryan2", "maryan3", "maryan4", "maryan5")
        var america = arrayOf("mamerica1", "mamerica2", "mamerica3", "mamerica4", "mamerica5", "mamerica6", "mamerica7", "mamerica8")
        var asia = arrayOf("masia1", "masia2", "masia3", "masia4", "masia5", "masia6", "bchina", "bjapon", "bcorea", "btailandia", "bindonesia", "brusia", "bindia")
        var mexico1 = arrayOf("m1mex1", "m1mex2", "m1mex3", "m1mex4", "m1mex5", "m1mex6", "m1mex7", "m1mex8", "m1mex9")
        var mexico2 = arrayOf("m2mex1", "m2mex2", "m2mex3", "m2mex4", "m2mex5", "m2mex6", "m2mex7", "m2mex8")
        var oceanos = arrayOf("mocean1", "mocean2", "mocean3", "mocean4", "mocean5")

        val modulo = getIntent().getStringExtra("modulo")

        if (modulo != null) {
            when(modulo.toInt()){
                1 ->  juego.setArray(continentes)
                2 ->  juego.setArray(oceanos)
                3 ->  juego.setArray(aryan)
                4 ->  juego.setArray(mexico1)
                5 ->  juego.setArray(mexico2)
                6 ->  juego.setArray(america)
                7 ->  juego.setArray(asia)
            }
        }
        when (modulo!!.toInt()) {
            1 ->  configuraciones.setMusica("continentes")
            2 ->  configuraciones.setMusica("ocean")
            3 -> configuraciones.setMusica("aryan")
            4,5 ->  configuraciones.setMusica("mexico")
            6 ->  configuraciones.setMusica("america")
            7 ->  configuraciones.setMusica("asia")
        }
        configuraciones.setVolume()

        juego.setListenerScore(object : OnChangeScoreListener{
            override fun SetonScoreChange(puntaje: Int){
                configuraciones.actuaizarPuntaje(puntaje)
            }
        })
        juego.setOnTimeStotListener(object : OnTimeStopListener{
            override fun OnTimeStop(stop: Boolean) {
                if(stop){
                    configuraciones.detenerTiempo()
                    juego.setTiempo(configuraciones.gettime())
                    if (modulo != null) {
                        juego.insertardb(modulo.toInt())
                    }
                }
            }
        })
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
//        Toast.makeText(applicationContext, "Back Button Pressed", Toast.LENGTH_SHORT).show()
    }
    override fun onStop() {
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.detenerMusica() // Liberar recursos
        super.onStop()
    }

    override fun onStart() {
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.startMusica() // Empezar de nuevo la música
        super.onStart()
    }
}