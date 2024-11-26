package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ModulosIntermedio: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_niveles)
        var continente = findViewById<ContinentesNiveles>(R.id.continenteslayout)
        var oceano = findViewById<OceanoNiveles>(R.id.oceanolayout)
        var aryan = findViewById<ArticaAntarticaNiveles>(R.id.aryanlayout)
        var america = findViewById<AmericaNiveles>(R.id.americalayout)
        var asia = findViewById<AsiaNiveles>(R.id.asialayout)
        var mexico = findViewById<MexicoNiveles>(R.id.mexicolayout)
        val tiponivel = getIntent().getStringExtra("tiponivel")?.toInt()

        val configuracion = findViewById<Configuracion>(R.id.configuracion)

        when (tiponivel) {
            0 ->  continente.visibility = View.VISIBLE
            1 ->  oceano.visibility = View.VISIBLE
            2 ->  aryan.visibility = View.VISIBLE
            3 ->  mexico.visibility = View.VISIBLE
            4 ->  america.visibility = View.VISIBLE
            5 ->  asia.visibility = View.VISIBLE
        }

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
}
