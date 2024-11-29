package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Peguntados_Inter : AppCompatActivity(), OnAnswerSelectedListener {
    private lateinit var preguntados: CustomView
    private val handler = Handler(Looper.getMainLooper())

    // Lista de preguntas y estado de progreso
    private lateinit var shuffledQuestions: List<CPreguntados>
    private var currentQuestionIndex = 0
    private var totalQuestions = 5 // Número de preguntas a responder
    var modulo = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.preguntados)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        modulo = getIntent().getStringExtra("modulo")?.toInt()!!
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)

        when (modulo!!.toInt()) {
            1 ->  configuraciones.setMusica("continentes")
            2 ->  configuraciones.setMusica("ocean")
            3 -> configuraciones.setMusica("aryan")
            4,5 ->  configuraciones.setMusica("mexico")
            6 ->  configuraciones.setMusica("america")
            7 ->  configuraciones.setMusica("asia")
        }
        configuraciones.setVolume()
//        modulo=4


        preguntados = findViewById(R.id.cpreguntados)
        preguntados.answerListener = this


        when(modulo){

            1 -> {
                val questions = arrayOf(
                    CPreguntados(
                        "¿Cuántos estados contiene México?",
                        "32 estados",
                        listOf("30 estados", "31 estados", "32 estados", "28 estados"),
                        R.drawable.imgestadomx
                    ),
                    CPreguntados(
                        "¿Cuál es el continente más grande y poblado del mundo?",
                        "Asia",
                        listOf("Asia", "América", "África", "Europa"),
                        R.drawable.muchaspersonas
                    ),
                    CPreguntados(
                        "¿Cuál es el tercer continente más grande?",
                        "África",
                        listOf("Asia", "América", "África", "Europa"),
                        R.drawable.continentemas
                    ),
                    CPreguntados(
                        "¿Cuál es el segundo continente más grande?",
                        "América",
                        listOf("Asia", "América", "África", "Europa"),
                        R.drawable.personasamerica
                    ),
                    CPreguntados(
                        "¿Cuál es el continente más pequeño? Con un 2% de la población mundial",
                        "Oceanía",
                        listOf("Oceanía", "América", "África", "Asia"),
                        R.drawable.oceaniaimg
                    )
                )

                shuffledQuestions = questions.toList().shuffled()
            }

            2 -> {
                val questions = arrayOf(
                    CPreguntados(
                        "Son una masa continua de agua salada que cubre más del 70% de la superficie de la Tierra",
                        "Océanos",
                        listOf("Océanos", "Océano Pacífico", "Mares, golfos y bahías", "Océano Atlántico"),
                        R.drawable.moceanosnew
                    ),
                    CPreguntados(
                        "Océano más pequeño, el más frío y el menos salado",
                        "Océano Ártico",
                        listOf("Océanos", "Océano Ártico", "Mares, golfos y bahías", "Océano Atlántico"),
                        R.drawable.oceanoartico
                    ),
                    CPreguntados(
                        "¿Cuál es el tercer océano más grande?",
                        "Océano Índico",
                        listOf("Océanos", "Océano Ártico", "Océano Índico", "Océano Atlántico"),
                        R.drawable.oceanoindico
                    ),
                    CPreguntados(
                        "Océano reconocido como tal hasta el año 2000 por IHO y es el segundo más pequeño",
                        "Océano Antártico",
                        listOf("Océano Antártico", "Océano Ártico", "Océano Índico", "Océano Atlántico"),
                        R.drawable.oceanoantartico
                    ),
                    CPreguntados(
                        "Así son denominados a los océanos más pequeños",
                        "Mares, golfos y bahías",
                        listOf("Océanos", "Océano Pacífico", "Mares, golfos y bahías", "Océano Atlántico"),
                        R.drawable.oceanospequenos
                    )
                )

                shuffledQuestions = questions.toList().shuffled()
            }

            3 -> {
                val questions = arrayOf(
                    CPreguntados(
                        "Se ubican en extremos opuestos del planeta",
                        "Ártica y Antártica",
                        listOf("Antártica", "México", "Ártico", "Ártica y Antártica"),
                        R.drawable.antarartica
                    ),
                    CPreguntados(
                        "Es una región que no tiene población nativa",
                        "Antártica",
                        listOf("Antártica", "Estados Unidos", "Ártico", "Ártica y Antártica"),
                        R.drawable.imgantartica
                    ),
                    CPreguntados(
                        "Es el más frío, seco, ventoso y con mayor altura media",
                        "Antártica",
                        listOf("Antártica", "Estados Unidos", "Ártico", "Ártica y Antártica"),
                        R.drawable.antarimgs
                    ),
                    CPreguntados(
                        "Ocupa la sexta parte de la superficie terrestre",
                        "Ártico",
                        listOf("Antártica", "Estados Unidos", "Ártico", "Ártica y Antártica"),
                        R.drawable.articoimgs
                    ),
                    CPreguntados(
                        "Circunda el Polo Sur",
                        "Antártica",
                        listOf("Antártica", "Estados Unidos", "Ártico", "Ártica y Antártica"),
                        R.drawable.antarticanew
                    )
                )

                shuffledQuestions = questions.toList().shuffled()
            }

            4 -> {
                val questions = arrayOf(
                    CPreguntados(
                        "¿Cuántos estados tiene México?",
                        "32 Estados",
                        listOf("30 Estados", "31 Estados", "32 Estados", "29 Estados"),
                        R.drawable.estadosmexi
                    ),
                    CPreguntados(
                        "Capital de México",
                        "Ciudad de México",
                        listOf("Ixtapalapa", "Tlalnepantla", "Ciudad de México", "Xochimilco"),
                        R.drawable.capitalmx
                    ),
                    CPreguntados(
                        "Capital de Jalisco",
                        "Guadalajara",
                        listOf("Guadalajara", "Estados Unidos", "Miradores", "Teocaltiche"),
                        R.drawable.capjalisco
                    ),
                    CPreguntados(
                        "¿Cuál es el estado más pequeño de México?",
                        "Tlaxcala",
                        listOf("Guadalajara", "Tlaxcala", "Miradores", "Teocaltiche"),
                        R.drawable.estadotlax
                    ),
                    CPreguntados(
                        "¿Cuál es el nombre oficial de México?",
                        "Estados Unidos Mexicanos",
                        listOf("El Tri", "Estados Unidos Mexicanos", "Caifanes", "CDMX"),
                        R.drawable.estadosumx
                    )
                )

                shuffledQuestions = questions.toList().shuffled()
            }

            5 -> {
                val questions = arrayOf(
                    CPreguntados(
                        "Primer presidente de México",
                        "Guadalupe Victoria",
                        listOf("Guadalupe Victoria", "Vicente Fox", "Enrique Peñanieto", "López Obrador"),
                        R.drawable.presidenteimg
                    ),
                    CPreguntados(
                        "¿Durante cuál gobierno se dio un impulso notable al ferrocarril en México?",
                        "Porfirio Díaz (1884-1911)",
                        listOf("Porfirio Díaz (1884-1911)", "Porfirio Díaz (2003-2007)", "Emiliano Zapata (1934-1967)", "López Obrador (2019-2024)"),
                        R.drawable.preguntasmex1
                    ),
                    CPreguntados(
                        "¿En qué año fue la revolución mexicana?",
                        "1910 a 1920",
                        listOf("1910 a 1920", "2014 a 2023", "1673 a 1734", "1900 a 1920"),
                        R.drawable.revolucionmex
                    ),
                    CPreguntados(
                        "¿Que fecha fue el movimiento independentista que inició la independencia de México?",
                        "16 de septiembre de 1810",
                        listOf("16 de septiembre de 1810", "10 de noviembre de 1930", "17 de septiembre de 1820", "16 de octubre de 1890"),
                        R.drawable.independenciamex
                    ),
                    CPreguntados(
                        "¿Qué son las leyes de reforma?",
                        "Leyes que separan la iglesia y el estado",
                        listOf("Leyes que separan la iglesia y el estado", "Conjunto de leyes promulgadas", "Conjunto de leyes que catalogan a México", "Conjunto de leyes para separar el pueblo"),
                        R.drawable.leyesreforma
                    )
                )

                shuffledQuestions = questions.toList().shuffled()
            }

            6 -> {
                val questions = arrayOf(
                    CPreguntados(
                        "Por su gran extensión se suele dividir en tres subcontinentes",
                        "América",
                        listOf("América", "México", "Ártico", "Asia"),
                        R.drawable.descuamerica
                    ),
                    CPreguntados(
                        "¿Cuáles son los subcontinentes en los que se divide América?",
                        "Norte, Central y del Sur",
                        listOf("Norte, Central y del Sur", "Norte, del Sur y Oeste", "Norte, Sur y Central", "Norte, Sur y Oeste"),
                        R.drawable.ncsamerica
                    ),
                    CPreguntados(
                        "Algunos de los pueblos originarios de América",
                        "Aztecas, Mayas e Incas",
                        listOf("Aztecas, Mayas e Incas", "Ecatepec y Chapanecos", "Incas y Chichen Itzas", "Españoles y Aztecas"),
                        R.drawable.pueblosoriginarios
                    ),
                    CPreguntados(
                        "¿Cuántos países constituyen América?",
                        "35 Países",
                        listOf("35 Países", "32 Países", "25 Países", "37 Países"),
                        R.drawable.paisesamerica
                    ),
                    CPreguntados(
                        "¿Cuáles son los idiomas más hablados en América?",
                        "Inglés y Español",
                        listOf("Inglés y Español", "Inglés y Japones", "Inglés", "Inglés Canadiense"),
                        R.drawable.conversando
                    )
                )

                shuffledQuestions = questions.toList().shuffled()
            }

            7 -> {
                val questions = arrayOf(
                    CPreguntados(
                        "El continente más grande, con mayor cantidad de habitantes y el que presenta la mayor diversidad de culturas y lenguas del mundo",
                        "Asia",
                        listOf("América", "México", "Japón", "Asia"),
                        R.drawable.asiamap
                    ),
                    CPreguntados(
                        "Es el único continente que comparte fronteras con otros dos: África y Europa",
                        "Asia",
                        listOf("Oceanía", "Europa", "Japón", "Asia"),
                        R.drawable.personasfron
                    ),
                    CPreguntados(
                        "Son los dos países más poblados de Asia",
                        "India y China",
                        listOf("India y China", "Japón y China", "Tokyo y Corea", "Corea y Japón"),
                        R.drawable.indiachina
                    ),
                    CPreguntados(
                        "¿Cuál es la religión predominante?",
                        "Hinduísmo",
                        listOf("Hinduísmo", "Budismo", "Catolismo", "Cristianismo"),
                        R.drawable.hinduismo
                    ),
                    CPreguntados(
                        "El pico más alto del planeta",
                        "El monte Everest",
                        listOf("El monte Everest", "Popocatépetl", "K2", "Cervino"),
                        R.drawable.monteeve
                    )
                )

                shuffledQuestions = questions.toList().shuffled()
            }

        }



        // Inicia la primera pregunta
        showNextQuestion()

        preguntados.setListenerScore(object : OnChangeScoreListener{
            override fun SetonScoreChange(puntaje: Int){
                configuraciones.actuaizarPuntaje(puntaje)
            }
        })
    }


    // Función para mostrar la siguiente pregunta
    private fun showNextQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            preguntados.setArray(shuffledQuestions[currentQuestionIndex])
            currentQuestionIndex++
        } else {
            preguntados.insertardb(modulo!!)
            //Toast.makeText(this, "¡Juego terminado!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAnswerSelected(isCorrect: Boolean, selectedAnswer: String) {
        val currentQuestion = shuffledQuestions[currentQuestionIndex - 1]
        val correctAnswer = currentQuestion.correctAnswer
        // Espera 5 segundos y luego avanza a la siguiente pregunta
        handler.postDelayed({
            showNextQuestion()
        }, 3000)
    }
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
//        Toast.makeText(applicationContext, "Back Button Pressed", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.detenerMusica() // Liberar recursos
//        Toast.makeText(applicationContext, "On Stop", Toast.LENGTH_SHORT).show()
        handler.removeCallbacksAndMessages(null)
        super.onStop()
        // Libera recursos si es necesario
        detenerPreguntados()
    }

    override fun onPause() {
        super.onPause()
        // Detén cualquier lógica o sonido que se esté ejecutando
        detenerPreguntados()

        // Si tienes música en la configuración, detén la música
        val configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.detenerMusica()
    }

    private fun detenerPreguntados() {
        // Detén las tareas o sonidos en segundo plano
        handler.removeCallbacksAndMessages(null) // Detiene cualquier Runnable pendiente
        //preguntados.detenerSonidos() // Asegúrate de tener una función en CustomView para detener sonidos
        //Toast.makeText(this, "Juego pausado", Toast.LENGTH_SHORT).show()
    }



    override fun onResume() {
        super.onResume()
        // Reinicia la música si es necesario
        val configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.startMusica()
    }

    override fun onStart() {
        var configuraciones = findViewById<Configuracion>(R.id.configuracion)
        configuraciones.startMusica() // Empezar de nuevo la música
        super.onStart()
    }
}
