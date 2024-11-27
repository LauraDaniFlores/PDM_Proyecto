package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class CustomView : View {

    // Pinturas para diferentes elementos
    private val questionPaint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }
    private val answerPaint = Paint().apply {
        color = Color.WHITE
        textSize = 40f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }
    private val selectedPaint = Paint().apply {
        color = Color.GREEN
        textSize = 40f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }
    private val incorrectPaint = Paint().apply {
        color = Color.RED
        textSize = 40f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }
    private val timerPaint = Paint().apply {
        color = Color.BLACK
        textSize = 60f
        isAntiAlias = true
        textAlign = Paint.Align.RIGHT
    }
    private val shadowPaint = Paint().apply {
        color = Color.GRAY
        setShadowLayer(10f, 0f, 5f, Color.LTGRAY)
        isAntiAlias = true
    }

    // Variables del juego
    private var answerRects = mutableListOf<Pair<String, FloatArray>>()
    private var selectedAnswer: String? = null
    private var hasSelectedAnswer = false
    private var timeLeft: Long = 15000 // 15 segundos en milisegundos
    private lateinit var countDownTimer: CountDownTimer

    // Pregunta y respuestas
    private var question: CPreguntados? = null

    fun setArray(pregunta: CPreguntados) {
        // Asignar la nueva pregunta
        question = pregunta
        question?.shuffledAnswers = question?.answers?.shuffled() ?: emptyList()

        // Restablecer el estado de selección
        selectedAnswer = null
        hasSelectedAnswer = false

        // Cargar la imagen asociada a la pregunta
        questionImage = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, pregunta.imageResource),
            imageWidth,
            imageHeight,
            false
        )

        // Reiniciar el temporizador
        timeLeft = 15000 // Reinicia a 15 segundos
        startCountDown()

        // Forzar la actualización del lienzo
        invalidate()
    }




    var answerListener: OnAnswerSelectedListener? = null

    // Imagen escalada
    private val imageWidth = 600
    private val imageHeight = 600
    private var questionImage: Bitmap = Bitmap.createScaledBitmap(
        BitmapFactory.decodeResource(resources, R.drawable.estadomexico),
        imageWidth,
        imageHeight,
        false
    )

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibujar la imagen escalada
        val imageX = (width - questionImage.width) / 2f
        canvas.drawBitmap(questionImage, imageX, 190f, null)

        // Ajustar pregunta en varias líneas si es necesario
        val maxWidth = width - 40f // Margen para el texto
        val lines = splitTextToLines(question?.question ?: "", maxWidth, questionPaint)

        // Dibujar pregunta, centrada verticalmente
        var currentY = questionImage.height + 280f
        for (line in lines) {
            canvas.drawText(line, width / 2f, currentY, questionPaint)
            currentY += questionPaint.textSize + 20f // Incrementar espacio entre líneas
        }

        // Dibujar el temporizador en la esquina superior derecha
        canvas.drawText("Tiempo: ${timeLeft / 1000}s", width - 50f, 110f, timerPaint)

        // Dibujar respuestas con botones menos anchos y con margen sombreado
        var startY = questionImage.height + 550f
        answerRects.clear()

        for (answer in question?.shuffledAnswers ?: emptyList()) {
            // Define un margen para que los botones sean menos anchos
            val marginX = 150f
            val rect = android.graphics.RectF(marginX, startY, width - marginX, startY + 120f)

            // Determinar el color del botón según la respuesta
            val buttonColor = when {
                hasSelectedAnswer && answer == question!!.correctAnswer -> Color.GREEN // Respuesta correcta
                hasSelectedAnswer && answer == selectedAnswer && !question!!.isCorrect(answer) -> Color.RED // Respuesta incorrecta seleccionada
                else -> Color.WHITE
            }

            // Dibujar botón
            canvas.drawRoundRect(rect, 20f, 30f, answerPaint.apply {
                color = buttonColor
                style = Paint.Style.FILL
            })

            // Dibujar contorno del botón
            canvas.drawRoundRect(rect, 20f, 30f, answerPaint.apply {
                style = Paint.Style.STROKE
                color = Color.BLACK
                strokeWidth = 5f
            })

            // Cambiar color del texto según el estado
            val textColor = if (hasSelectedAnswer && answer == question!!.correctAnswer) Color.WHITE else Color.BLACK
            answerPaint.style = Paint.Style.FILL
            answerPaint.color = textColor

            // Dibujar texto de la respuesta centrado
            val textX = (rect.left + rect.right) / 2
            val textY = rect.centerY() - (answerPaint.descent() + answerPaint.ascent()) / 2
            canvas.drawText(answer, textX, textY, answerPaint)

            // Guardar rectángulos para detección de clics
            answerRects.add(answer to floatArrayOf(rect.left, rect.top, rect.right, rect.bottom))
            startY += 150f // Separación entre botones de respuesta
        }
    }




    private fun splitTextToLines(text: String, maxWidth: Float, paint: Paint): List<String> {
        val words = text.split(" ")
        val lines = mutableListOf<String>()
        var currentLine = ""

        for (word in words) {
            val testLine = if (currentLine.isEmpty()) word else "$currentLine $word"
            if (paint.measureText(testLine) <= maxWidth) {
                currentLine = testLine
            } else {
                lines.add(currentLine)
                currentLine = word
            }
        }
        if (currentLine.isNotEmpty()) {
            lines.add(currentLine)
        }
        return lines
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (hasSelectedAnswer) return true

        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y

            for ((answer, rect) in answerRects) {
                if (x in rect[0]..rect[2] && y in rect[1]..rect[3]) {
                    selectedAnswer = answer
                    hasSelectedAnswer = true
                    val isCorrect = question!!.isCorrect(answer)
                    countDownTimer.cancel()
                    if (!isCorrect) {
                        Toast.makeText(context, "Respuesta incorrecta. Correcta: ${question!!.correctAnswer}", Toast.LENGTH_SHORT).show()
                    }
                    answerListener?.onAnswerSelected(isCorrect, answer)
                    invalidate()
                    return true
                }
            }
        }
        return false
    }

    private fun startCountDown() {
        countDownTimer = object : CountDownTimer(timeLeft, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished
                invalidate()
            }

            override fun onFinish() {
                if (!hasSelectedAnswer) {
                    // Marca la respuesta correcta en verde automáticamente
                    selectedAnswer = question?.correctAnswer
                    hasSelectedAnswer = true
                    Toast.makeText(
                        context,
                        "Tiempo terminado. Respuesta correcta: ${question?.correctAnswer}",
                        Toast.LENGTH_LONG
                    ).show()
                    answerListener?.onAnswerSelected(false, selectedAnswer ?: "")
                    invalidate()
                }
            }
        }.start()
    }
}
