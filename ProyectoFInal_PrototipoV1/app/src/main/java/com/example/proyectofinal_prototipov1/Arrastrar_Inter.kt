package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Arrastrar_Inter : AppCompatActivity() {
    // variable para el módulo del Intent
    var modulo: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.arrastrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Elementos del layout
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        var arrastrar = findViewById<Arrastrar>(R.id.arrastrar)

        //Variable intent módulo
        modulo = getIntent().getStringExtra("modulo")?.toInt()!!

        //Canción de fondo
        when (modulo!!.toInt()) {
            1 ->  configuraciones.setMusica("continentes")
            2 ->  configuraciones.setMusica("ocean")
            3 -> configuraciones.setMusica("aryan")
            4,5 ->  configuraciones.setMusica("mexico")
            6 ->  configuraciones.setMusica("america")
            7 ->  configuraciones.setMusica("asia")
        }
        //Música un poco más baja
        configuraciones.setVolume()

        if (modulo != null) {
            modulo -= 1
        }
//        modulo = 2

        //Array de nombres de las imágenes, de lo que irá en las bolitas y la sentencia.
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

        //Darle el valor correspondiente al array de nombres de imágenes y de nombres de las bolitas
        arrastrar.setArray(imagenes[modulo], nc[modulo])

        //Dar valor a la sentencia del textView del layout
        var textSent = findViewById<TextView>(R.id.textSent)
        textSent.setText(sentencia[modulo])

        //Listener de cambio de valor del puntaje
        arrastrar.setListenerScore(object : OnChangeScoreListener{
            override fun SetonScoreChange(puntaje: Int){
                configuraciones.actuaizarPuntaje(puntaje)
            }
        })

        //Cuando se acaba el juego
        arrastrar.setOnTimeStotListener(object : OnTimeStopListener{
            override fun OnTimeStop(stop: Boolean) {
                if(stop){
                    configuraciones.detenerTiempo()
                    arrastrar.setTiempo(configuraciones.gettime())
                    arrastrar.insertardb(modulo+1)
                }
            }
        })

    }

    //Botón de regreso
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        val i = Intent(this@Arrastrar_Inter, ModulosIntermedio::class.java)
        when (modulo!!.toInt()) {
            0 -> i.putExtra("tiponivel", "0")
            1 -> i.putExtra("tiponivel", "1")
            2 -> i.putExtra("tiponivel", "2")
            3 -> i.putExtra("tiponivel", "3")
            4 -> i.putExtra("tiponivel", "4")
            5 -> i.putExtra("tiponivel", "5")
        }
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(i)

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