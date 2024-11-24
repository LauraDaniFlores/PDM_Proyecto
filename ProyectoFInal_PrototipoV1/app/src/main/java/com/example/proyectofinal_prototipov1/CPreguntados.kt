package com.example.proyectofinal_prototipov1

class CPreguntados (
    val question: String,
    val correctAnswer: String,
    val answers: List<String>
) {
    var shuffledAnswers: List<String> = answers.shuffled()

    fun isCorrect(answer: String): Boolean {
        return answer == correctAnswer
    }
}