package com.example.proyectofinal_prototipov1

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.preguntados)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        preguntados = findViewById(R.id.cpreguntados)
        preguntados.answerListener = this

        val questions = arrayOf(
            CPreguntados(
                "¿Cuántos estados contiene México?",
                "32 estados",
                listOf("30 estados", "31 estados", "32 estados", "28 estados"),
                R.drawable.estadomexico
            ),
            CPreguntados(
                "¿Cuál es el continente más grande y poblado del mundo?",
                "Asia",
                listOf("Asia", "América", "África", "Europa"),
                R.drawable.continentesimg
            ),
            CPreguntados(
                "¿Cuál es el tercer continente más grande?",
                "África",
                listOf("Asia", "América", "África", "Europa"),
                R.drawable.continentesimg
            ),
            CPreguntados(
                "¿Cuál es el segundo continente más grande?",
                "América",
                listOf("Asia", "América", "África", "Europa"),
                R.drawable.continentesimg
            ),
            CPreguntados(
                "¿Cuál es el continente más pequeño? Con un 2% de la población mundial",
                "Oceanía",
                listOf("Oceanía", "América", "África", "Asia"),
                R.drawable.continentesimg
            )
        )

        shuffledQuestions = questions.toList().shuffled()

        // Inicia la primera pregunta
        showNextQuestion()
    }

    // Función para mostrar la siguiente pregunta
    private fun showNextQuestion() {
        if (currentQuestionIndex < totalQuestions) {
            preguntados.setArray(shuffledQuestions[currentQuestionIndex])
            currentQuestionIndex++
        } else {
            Toast.makeText(this, "¡Juego terminado!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onAnswerSelected(isCorrect: Boolean, selectedAnswer: String) {
        val currentQuestion = shuffledQuestions[currentQuestionIndex - 1]
        val correctAnswer = currentQuestion.correctAnswer
        val message = if (isCorrect) {
            "¡Correcto! La respuesta es $selectedAnswer"
        } else {
            "Incorrecto. La respuesta correcta era $correctAnswer"
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        // Espera 5 segundos y luego avanza a la siguiente pregunta
        handler.postDelayed({
            showNextQuestion()
        }, 5000)
    }
}
