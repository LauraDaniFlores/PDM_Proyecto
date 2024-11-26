package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyectofinal_prototipov1.DBSQLite.Companion.TABLE_NAME
import java.util.Date

class EscogerInterm: AppCompatActivity() {
    var modulo: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.escoger)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var configuraciones = findViewById<Configuracion>(R.id.configuracion)

        var escoger = findViewById<Escoger>(R.id.escoger)
        modulo = getIntent().getStringExtra("modulo")?.toInt()!!
        if (modulo != null) {
            modulo -= 1
        }
        var db: DBSQLite = DBSQLite(this)

        escoger.setListenerScore(object : OnChangeScoreListener{
            override fun SetonScoreChange(puntaje: Int){
                configuraciones.actuaizarPuntaje(puntaje)
            }
        })
        escoger.setOnTimeStotListener(object : OnTimeStopListener{
            override fun OnTimeStop(stop: Boolean) {
                if(stop){
                    configuraciones.detenerTiempo()
                    escoger.setTiempo(configuraciones.gettime())
                    escoger.insertardb(modulo+1)
                }
            }
        })

//        modulo = 2

//        Toast.makeText(this, "Intent " + modulo.toString(),
//            Toast.LENGTH_LONG).show()

        var conti = arrayOf(arrayOf("América", "Europa", "Asia", "África", "Oceanía"), arrayOf("Pacífico", "Atlántico", "Índico", "Antártico", "Ártico"), arrayOf("Ártica", "Antártica"),
            arrayOf("Baja California", "Sonora", "Sinaloa", "Chihuahua", "Coahuila", "Durango", "Nuevo León", "Tamaulipas", "Zacatecas", "Jalisco", "Nayarit", "Guanajuato", "Michoacán", "Guerrero", "Oaxaca", "Chiapas"),
            arrayOf("EUA", "México", "Brasil", "Chile", "Colombia", "Venezuela", "Perú", "Bolivia", "Argentina"), arrayOf("Rusia", "China", "India", "Mongolia", "Kazajistán", "Irán", "Turquía", "Indonesia"))
        var coordx = arrayOf(arrayOf(220f,580f,730f,570f,870f), arrayOf(690f,950f,340f,630f,650f), arrayOf(300f,820f), arrayOf(145f,280f,370f,400f,520f, 435f, 590f, 632f, 500f, 472f, 435f, 561f, 530f, 584f, 697f, 815f),
            arrayOf(500f,507f,850f,745f,705f, 748f, 700f, 770f, 800f),arrayOf(540f,470f,400f,530f,340f, 260f, 160f, 630f))
        var coordy = arrayOf(arrayOf(260f,225f,260f,400f,470f), arrayOf(400f,250f,450f,610f,90f), arrayOf(400f,400f),
            arrayOf(170f,230f,400f,300f,330f,410f, 370f, 420f, 445f, 547f, 500f, 532f, 590f, 640f, 655f, 670f),
            arrayOf(500f,625f,820f,990f,725f, 710f, 820f, 860f, 990f), arrayOf(250f,460f,550f,380f,370f, 485f, 442f, 725f))
        var image = arrayOf("mapa", "oceanosmapa", "articayantarticamapa", "mexicomapa", "americamapa1", "asiamapa")
        var coorimage = arrayOf(707f, 707f, 732f, 819f, 1185f, 784f)
        var numbers = arrayOf(arrayOf(0, 1, 2, 3, 4), arrayOf(0, 1, 2, 3, 4), arrayOf(0, 1), arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8), arrayOf(0, 1, 2, 3, 4, 5, 6, 7))


        escoger.setArrays(conti[modulo!!], image[modulo!!], coordx[modulo!!], coordy[modulo!!], coorimage[modulo!!], numbers[modulo!!])

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
