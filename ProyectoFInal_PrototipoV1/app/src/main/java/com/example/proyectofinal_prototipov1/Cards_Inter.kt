package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dgfp.proyectojuego1.Memorama

class Cards_Inter: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)

        var juego = findViewById<Cards>(R.id.cardsJuego)
        var continentes = arrayOf(arrayOf("Son grandes masas de tierra emergidas que conforman la superficie terrestre y están separadas por océanos", "Continentes"), arrayOf("¿Cuál continente más grande y poblado del mundo?", "Asia"), arrayOf("¿Cuál es el segundo continente más grande?", "América"), arrayOf("¿Cuál es el tercer continente más grande?", "África"),
            arrayOf("¿El segundo continente más pequeño? Con el 9% de la población mundial", "Europa"), arrayOf("¿Cuál es el continente más pequeño? Con un 2% de la población mundial", "Oceanía"))
        var oceanos = arrayOf(arrayOf("Son una masa continua de agua salada que cubre más del 70% de la superficie de la Tierra", "Océanos"), arrayOf("Así son denominados a los océanos más pequeños", "Mares, golfos y bahías"), arrayOf("¿Cuál es el océano más grande del planeta?", "Océano Pacífico"), arrayOf("¿Cuál es el segundo océano más grande? Divide el continente americano del continente europeo y africano", "Océano Atlántico"),
            arrayOf("¿Cuál es el tercer océano más grande?", "Océano Índico"), arrayOf("Océano reconocido como tal hasta el año 2000 por IHO y es el segundo más pequeño", "Océano Antártico"), arrayOf("Océano más pequeño, el más frío y el menos salado", "Océano Ártico"))
        var aryan = arrayOf(arrayOf("Se ubican en extremos opuestos del planeta", "Ártica y Antártica"), arrayOf("También llamado Polo Norte y está cubierto de mar de hielo", "Ártico"), arrayOf("Ocupa la sexta parte de la superficie terrestre", "Ártico"), arrayOf("Circunda el Polo Sur", "Antártica"),
            arrayOf("Es una región que no tiene población nativa", "Antártica"), arrayOf("Es el más frío, seco, ventoso y con mayor altura media", "Antártica"), arrayOf("Hay focas de Weddell, pingüinos y peces adaptados a vivir bajo 0 C", "Antártica"))
        var america = arrayOf(arrayOf("Por su gran extensión se suele dividir en tres subcontinentes", "América"), arrayOf("¿Cuáles son los subcontinentes en los que se divide América?", "América del Norte, América Central y América del Sur"), arrayOf("Algunos de los pueblos originarios de América", "Aztecas, Mayas e Incas"), arrayOf("¿Cuántos países constituyen América?", "35 países"),
            arrayOf("¿Cuáles son los idiomas más hablados de América? ", "Inglés y español"), arrayOf("Son los tres países más poblados y representan casi el 70% de la población", "Estados Unidos, Brasil y México "), arrayOf("¿En qué siglo los europeos exploraron y colonizaron las nuevas tierras?", "Siglo XV"))
        var asia = arrayOf(arrayOf("El continente más grande, con mayor cantidad de habitantes y el que presenta la mayor diversidad de culturas y lenguas del mundo", "Asia"), arrayOf("Es el único continente que comparte fronteras con otros dos: África y Europa", "Asia"), arrayOf("Son los dos países más poblados de Asia", "India y China"), arrayOf("¿Cuál es la religión predominante?", "Hinduísmo"),
            arrayOf("El pico más alto del planeta", "El monte Everest "), arrayOf("Es la cadena montañosa más alta del mundo", "Cordillera del Himalaya "), arrayOf("¿Cuál es el idioma más hablado?", "Chino mandarín, con más de 1.000 millones de personas "))

        var mexico1 = arrayOf(arrayOf("¿Cuántos estados tiene México?", "32 estados"), arrayOf("Capital de México", "Ciudad de México"), arrayOf("Capital de Jalisco", "Guadalajara"), arrayOf("¿Cuál es el estado más pequeño de México? ", "Tlaxcala"), arrayOf("¿Cuáles son los estados con mayor extensión territorial?", "Chihuahua, Sonora, Coahuila y Durango "), arrayOf("Chihuahua, Sonora, Coahuila y Durango ", "Estados Unidos, Guatemala, Belice"), arrayOf("¿Cuál es el nombre oficial de México?", "Estados Unidos Mexicanos"))
        var mexico2 = arrayOf(arrayOf("Primer presidente de México", "Guadalupe Victoria"), arrayOf("¿Durante cuál gobierno se dio un impulso notable al ferrocarril en México?", "Porfirio Díaz (1884-1911) "),
            arrayOf("¿Qué son las leyes de reforma?", "Conjunto de leyes promulgadas en México para separar la iglesia y el estado"), arrayOf("¿En qué año fue la revolución mexicana?", "1910 a 1920"), arrayOf("¿Cúal fue el movimiento independentista que inició la independencia de México?", "El Grito de Dolores el 16 de septiembre de 1810"))

        var modulo = getIntent().getStringExtra("modulo")

        if (modulo != null) {
            when(modulo.toInt()){
                1 ->  juego.setArray(continentes, 1)
                2 ->  juego.setArray(oceanos, 2)
                3 ->  juego.setArray(aryan, 3)
                4 ->  juego.setArray(mexico1, 4)
                5 ->  juego.setArray(mexico2, 5)
                6 ->  juego.setArray(america, 6)
                7 ->  juego.setArray(asia, 7)
            }
        }

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
                    var modu = modulo!!.toInt()
                    juego.insertardb(modu)
                }
            }
        })

    }
    override fun onStop() {
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.detenerMusica() // Liberar recursos
        super.onStop()
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
//        Toast.makeText(applicationContext, "Back Button Pressed", Toast.LENGTH_SHORT).show()
    }
    override fun onStart() {
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.startMusica() // Empezar de nuevo la música
        super.onStart()
    }

}