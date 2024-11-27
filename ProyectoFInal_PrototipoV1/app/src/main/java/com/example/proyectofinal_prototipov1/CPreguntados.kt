package com.example.proyectofinal_prototipov1

class CPreguntados(
    val question: String,
    val correctAnswer: String,
    val answers: List<String>,
    val imageResource: Int // ID del recurso de la imagen
) {
    var shuffledAnswers: List<String> = answers.shuffled()

    fun isCorrect(answer: String): Boolean {
        return answer == correctAnswer
    }
}