package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Arrastrar_Inter : AppCompatActivity() {
    var modulo: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arrastrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        var arrastrar = findViewById<Arrastrar>(R.id.arrastrar)

        modulo = getIntent().getStringExtra("modulo")?.toInt()!!
        if (modulo != null) {
            modulo -= 1
        }
        modulo = 2

        var imagenes = arrayOf(arrayOf("mcontinente5", "mcontinente3", "mcontinente4",  "mcontinente2"), arrayOf("mocean2", "mocean4", "mocean3", "mocean5"),
            arrayOf("pinguino", "reno", "orca", "osopolar"),
            arrayOf("aguascalientes", "colima", "guanajuato", "zacatecas"),
            arrayOf("hidalgo", "michoacan", "nuevoleon", "sonora"), arrayOf("america_sur", "america_all", "america_central", "america_norte"),
            arrayOf("bcorea", "bjapon", "btailandia", "bchina"))
        var nc = arrayOf(arrayOf("1", "2", "3", "4"), arrayOf("1", "2", "3", "4"), arrayOf("1", "2", "3", "4"),
            arrayOf("AGS", "COL", "GTO", "ZAC"), arrayOf("PAC", "MOR", "MTY", "HMO"),
            arrayOf("SUR", "AM", "CENT", "NORTE"), arrayOf("KOR", "JPN", "THA", "CHN"))
        var sentencia = arrayOf("Acomoda del continente más grande al más pequeño en territorio.", "Acomoda del océano más grande al más pequeño en territorio.",
            "Acomoda la especie con más ejemplares hasta la especie con menos ejemplares", "Pon el nombre arriba de la imagen del estado.",
            "Relaciona la capital con su estado. ", "Relaciona correctamente las divisiones de América.", "Asocia el nombre del país con su bandera.")

        arrastrar.setArray(imagenes[modulo], nc[modulo])

        var textSent = findViewById<TextView>(R.id.textSent)
        textSent.setText(sentencia[modulo])

        arrastrar.setListenerScore(object : OnChangeScoreListener{
            override fun SetonScoreChange(puntaje: Int){
                configuraciones.actuaizarPuntaje(puntaje)
            }
        })

        arrastrar.setOnTimeStotListener(object : OnTimeStopListener{
            override fun OnTimeStop(stop: Boolean) {
                if(stop){
                    configuraciones.detenerTiempo()
                    arrastrar.setTiempo(configuraciones.gettime())
                    arrastrar.insertardb(1)
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
//        Toast.makeText(applicationContext, "On Stop", Toast.LENGTH_SHORT).show()
        super.onStop()
    }
    override fun onStart() {
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.startMusica() // Empezar de nuevo la música
        super.onStart()
    }
}