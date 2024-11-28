package com.example.proyectofinal_prototipov1

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import java.util.Date

class CustomView : View {

    val customTypeface = resources.getFont(R.font.courier)

    // Pinturas para diferentes elementos
    private val questionPaint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        typeface = customTypeface
    }
    private val answerPaint = Paint().apply {
        color = Color.WHITE
        textSize = 40f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
        typeface = customTypeface
    }
    private val selectedPaint = Paint().apply {
        color = Color.rgb(2,104,115)
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
        textSize = 45f
        isAntiAlias = true
        textAlign = Paint.Align.RIGHT
    }
    private val puntajePaint = Paint().apply {
        color = Color.BLACK
        textSize = 60f
        isAntiAlias = true
        textAlign = Paint.Align.LEFT
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

    //Variable tiempo
    private var tiempo = 0
    private var ctiempo: Drawable? = null

    private lateinit var countDownTimer: CountDownTimer

    // Pregunta y respuestas
    private var question: CPreguntados? = null

    //Puntaje
    private var puntaje = 0

    //Audio
    private var musicSuccess: MediaPlayer? = null
    private var musicError: MediaPlayer? = null

    // Base de datos
    var db: DBSQLite = DBSQLite(context)

    var listener: OnChangeScoreListener? = null
    fun setListenerScore(l: OnChangeScoreListener){
        listener = l
    }

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

        // Cancelar temporizador previo si existe
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }

        // Reiniciar el temporizador
        timeLeft = 15000 // Reinicia a 15 segundos
        startCountDown()

        //Inicializar el audio
        musicSuccess = MediaPlayer.create(context, R.raw.bien)
//        musicSuccess?.start()

        musicError = MediaPlayer.create(context, R.raw.error)
//        musicError?.start()

        // Forzar la actualización del lienzo
        invalidate()
    }




    var answerListener: OnAnswerSelectedListener? = null

    // Imagen escalada
    private val imageWidth = 900
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
        ctiempo!!.draw(canvas)
// Definir el margen
        val margin = 20f // Puedes ajustar este valor según lo que necesites
        val cornerRadius = 25f // Radio para las esquinas redondeadas

// Calcular las coordenadas ajustadas de la imagen
        val imageX = (width - questionImage.width) / 2f
        val imageY = 190f + margin // Ajustar también la posición vertical

// Dibujar el marco o fondo del margen con esquinas redondeadas
        val framePaint = Paint().apply {
            color = Color.rgb(2, 104, 115) // Color del margen
            style = Paint.Style.FILL
        }
        canvas.drawRoundRect(
            imageX - margin,
            imageY - margin,
            imageX + questionImage.width + margin,
            imageY + questionImage.height + margin,
            cornerRadius, // Radio horizontal de las esquinas
            cornerRadius, // Radio vertical de las esquinas
            framePaint
        )

// Crear un recorte circular para redondear la imagen dentro del marco
        val imageClipPath = android.graphics.Path().apply {
            addRoundRect(
                imageX,
                imageY,
                imageX + questionImage.width,
                imageY + questionImage.height,
                cornerRadius, // Radio horizontal
                cornerRadius, // Radio vertical
                android.graphics.Path.Direction.CW
            )
        }

// Aplicar el recorte y dibujar la imagen
        canvas.save()
        canvas.clipPath(imageClipPath)
        canvas.drawBitmap(questionImage, imageX, imageY, null)
        canvas.restore()

// Ajustar la posición inicial para otros elementos relacionados con la imagen
        val currentY = imageY + questionImage.height + 90f
        val maxWidth = width - 40f // Margen para el texto

        // Dibujar la pregunta
        val lines = splitTextToLines(question?.question ?: "", maxWidth, questionPaint)
        var lineY = currentY
        for (line in lines) {
            canvas.drawText(line, width / 2f, lineY, questionPaint)
            lineY += questionPaint.textSize + 20f // Incrementar espacio entre líneas
        }

        //Dibuja el puntaje
       // canvas.drawText("Puntaje: ${puntaje}",20f,110f,puntajePaint)
        // Dibujar el temporizador en la esquina superior derecha

        //tiempo = AppCompatResources.getDrawable(getContext(), R.drawable.time)
        canvas.drawText("${timeLeft / 1000}s", width - 50f, 105f, timerPaint)

        // Dibujar respuestas con botones menos anchos y con margen sombreado
        var startY = questionImage.height + 550f
        answerRects.clear()

        for (answer in question?.shuffledAnswers ?: emptyList()) {
            // Define un margen para que los botones sean menos anchos
            val marginX = 150f
            val rect = android.graphics.RectF(marginX, startY, width - marginX, startY + 120f)

            // Determinar el color del botón según la respuesta
            val buttonColor = when {
                hasSelectedAnswer && answer == question!!.correctAnswer -> Color.rgb(88, 214, 141) // Respuesta correcta
                hasSelectedAnswer && answer == selectedAnswer && !question!!.isCorrect(answer) -> Color.rgb(232,4,4) // Respuesta incorrecta seleccionada
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
                color = Color.rgb(2, 104, 115)
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


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val ancho = measuredWidth.toFloat()
        val cuadrado = 125
        val margen = 858
        val margen1 = 50

        ctiempo = AppCompatResources.getDrawable(getContext(), R.drawable.time)
        ctiempo!!.setBounds(margen, margen1, margen + 75, margen1+75)

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
                    if (isCorrect){
                        //Aunmenta Puntaje
                        puntaje+=20
                        listener!!.SetonScoreChange(puntaje)
                        //Guarda el tiempo en que tardo en contestar
                        tiempo += 15 -( timeLeft / 1000).toInt()

                        musicSuccess?.start()
                    }
                    if (!isCorrect) {
                        //Esta raro pero decrementa 5 si es incorrecta
                        musicError?.start()
                        musicSuccess?.stop()
                        puntaje = puntaje - 10
                        listener!!.SetonScoreChange(puntaje)

                        //Guarda el tiempo en que tardo en contestar
                        tiempo += 15 - ( timeLeft / 1000).toInt()
                        //Toast.makeText(context, "Respuesta incorrecta. Correcta: ${question!!.correctAnswer}", Toast.LENGTH_SHORT).show()
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
                    // Detener cualquier reproducción previa
                    musicError?.stop()
                    musicError?.prepare() // Resetea el estado a listo para reproducir de nuevo.

                    musicError?.start() // Inicia el sonido del error

                    //Le quita puntos si se le acaba el tiempo y no responde
                    musicError?.start()
                    musicSuccess?.stop()
                    //Guarda el tiempo en que tardo en contestar
                    tiempo += 15 - ( timeLeft / 1000).toInt()
                    puntaje = puntaje - 10
                    listener!!.SetonScoreChange(puntaje)
                    answerListener?.onAnswerSelected(false, selectedAnswer ?: "")
                    invalidate()
                }
            }
        }.start()
    }

    fun setTiempo(tiem: Int){
        tiempo = tiem
    }


    fun insertardb(modulo: Int){
        countDownTimer.cancel() // Cancela el temporizador antes de guardar en la base de datos
        if(puntaje < 50) {
            val intent = Intent(context, derrota_Inter::class.java)
            intent.putExtra("nivel", "4")
            intent.putExtra("modulo", modulo.toString())
            context.startActivity(intent)
        }else {

            var moduloaux = modulo
            var nivelaux = 4

            if(modulo > 5){
                moduloaux = modulo - 1
            }else if(modulo == 5){
                moduloaux = modulo - 1
                nivelaux = 8
            }
            if (db.nivelDesbloqueado(moduloaux, nivelaux+1)) {
                db.guardarRegistro(moduloaux, nivelaux, tiempo, puntaje, Date(), false)
            } else {
                db.guardarRegistro(moduloaux, nivelaux, tiempo, puntaje, Date(), true)
            }
            val intent = Intent(context, FelicidadesInter::class.java)

            intent.putExtra("nivel", nivelaux.toString())
            intent.putExtra("modulo", moduloaux.toString())
            intent.putExtra("puntaje", puntaje.toString())
            intent.putExtra("tiempo", tiempo.toString())

            context.startActivity(intent)

        }


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

        countDownTimer.cancel() // Cancela cualquier temporizador activo

        musicSuccess?.stop()
        musicSuccess?.release()
        musicSuccess = null

        musicError?.stop()
        musicError?.release()
        musicError = null
    }
}
