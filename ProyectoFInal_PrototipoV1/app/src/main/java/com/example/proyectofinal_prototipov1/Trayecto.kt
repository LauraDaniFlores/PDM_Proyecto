package com.example.proyectofinal_prototipov1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class Trayecto : ScrollView {
    //Rectangulos
    private val cuadrado = Paint()
    private val circulo = Paint()
    private val circuloSelected = Paint()
    private val radio = 80f

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()
    private val textModulo = Paint()
    private val ptext = Paint()

    // Modulo
    var modulo = arrayOf(true, false, false)
    var pestana = 0
    var colores = arrayOf(arrayOf( ResourcesCompat.getColor(resources, R.color.verdeCon, null),  ResourcesCompat.getColor(resources, R.color.azulOce, null)),
        arrayOf( ResourcesCompat.getColor(resources, R.color.azulAr, null),  ResourcesCompat.getColor(resources, R.color.NaranjaMex, null),  ResourcesCompat.getColor(resources, R.color.NaranjaMex, null)), arrayOf(ResourcesCompat.getColor(resources, R.color.cafeAmer, null), ResourcesCompat.getColor(resources, R.color.cafeAsia, null)))

    val datos = Array(35) { arrayOf("","","", "false") }

    //SQLite
    var db: DBSQLite = DBSQLite(context)

    constructor(context: Context?): super(context){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        inicializa()

    }
    private fun inicializa() {
        textPaint.isAntiAlias = true
        textPaint.textSize = 60f
        textPaint.color = Color.WHITE

        textNivel.textSize = 150f
        textNivel.color = Color.BLACK
        textNivel.textAlign = Paint.Align.CENTER

        ptext.isAntiAlias = true
        ptext.textSize = 40f
        ptext.color = Color.WHITE

        val customTypeface = resources.getFont(R.font.pact)
        textNivel.typeface = customTypeface

        textModulo.isAntiAlias = true
        textModulo.textSize = 110f
        textModulo.color = Color.BLACK
        textModulo.textAlign = Paint.Align.CENTER
        textModulo.typeface = customTypeface

        val courier = resources.getFont(R.font.courier)
        textPaint.typeface = courier
        ptext.typeface = courier

        cuadrado.style = Paint.Style.FILL
        cuadrado.color = Color.RED

        circulo.style = Paint.Style.FILL
        circulo.color = Color.BLACK

        circuloSelected.style = Paint.Style.FILL
        circuloSelected.color = ResourcesCompat.getColor(resources, R.color.azulagua, null)

        // Llenará una matriz con los datos de la base de datos del mejor puntaje y tiempo
        var index = 0
        for(i in 0..5){
            for (j in 0..9){
                if(i != 3)
                    if(j == 5) break
                datos.set(index, db.Estadistica(i+1, j+1))
                index++
            }
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var altonivel = (alto/2)
        val xPos = (ancho / 2)

        // Título
        textNivel.color = Color.BLACK
        canvas.drawText("Trayecto", xPos, 120f, textNivel)

        // Círculos de las tres pestañas de información
        var cx = ancho/2 - 200f
        var cy = 270f
        for(i in 0.. 2){
            // Si esta seleccionada se pondrá de color azul
            if(modulo[i]){
                circuloSelected.color = ResourcesCompat.getColor(resources, R.color.azulagua, null)
                canvas.drawCircle(cx, cy, radio, circuloSelected)
            }else{
                // Si no esta seleccionada se pondrá de color negro
                canvas.drawCircle(cx, cy, radio, circulo)
            }
            // Número
            canvas.drawText((i+1).toString(),cx-20, cy+20,textPaint)
            cx += 200f
        }

        // Módulo
        var index = 0
        // Alto
        var altoC = 600f
        // Número de filas
        var k = 1
        //
        var pos = 0
        // Número del nivel 1 al 35
        var nivel = 0

        if(modulo[1]){
            altoC = 450f
            k = 2
            index = 10
            pos = 4
        }else if(modulo[2]){
            pos = 2
            index = 25
        }

        for(j in 0..k){
            // Cambiar color dependiendo del módulo
            circuloSelected.color = colores[pestana][j]
            pos++
            nivel = 0
            if(pos == 5){
                pos = 4
                nivel = 5
            }
            for (i in 0..4){
                index ++
                nivel ++
                // Rectángulos de información y el número
                canvas.drawRoundRect(10f+(ancho/5*i), 500f+(altoC*j), (ancho/5)+(ancho/5*i)-5, 900f+(altoC*j), 20f, 20f, circuloSelected)
                canvas.drawText((index).toString(),80f+(ancho/5*i), 570f+(altoC*j), textPaint)
                // Si hay información entonces dibujar el texto del tiempo y puntaje
                if(datos[index-1][3] == "true"){
                    canvas.drawText("Tiempo:",15f+(ancho/5*i), 650f+(altoC*j), ptext)
                    canvas.drawText(datos[index-1][0] + " seg",15f+(ancho/5*i), 690f+(altoC*j), ptext)
                    canvas.drawText("Puntaje:",15f+(ancho/5*i), 730f+(altoC*j), ptext)
                    canvas.drawText(datos[index-1][1],15f+(ancho/5*i), 770f+(altoC*j), ptext)
                }
            }
        }

        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        // Dimensiones para los tres circulos
        // Si se selecciona uno los otros se ponen en falso y ese en verdadero y se guarda la pestaña
        var cx = ancho/2 - 200f
        var cy = 270f
        for(i in 0.. 2){
            if(event.x >= cx - radio && event.x <= cx + radio && event.y >= cy - radio && event.y <= cy + radio ){
                for(j in 0..2){
                    modulo[j] = false
                }
                modulo[i] = true
                pestana = i
//                Toast.makeText(context, "modulo "+i, Toast.LENGTH_SHORT).show()
            }
            cx += 200f
        }

        invalidate()
        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val ancho = calcularAncho(widthMeasureSpec)
        val alto = calcularAlto(heightMeasureSpec)
        setMeasuredDimension(ancho, alto)
    }

    private fun calcularAlto(heightMeasureSpec: Int): Int {
        var res = 100
        val modo = MeasureSpec.getMode(heightMeasureSpec)
        val limite = MeasureSpec.getSize(heightMeasureSpec)
        if(modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY){
            res = limite
        }
        return res
    }

    private fun calcularAncho(widthMeasureSpec: Int): Int {
        var res = 100
        val modo = MeasureSpec.getMode(widthMeasureSpec)
        val limite = MeasureSpec.getSize(widthMeasureSpec)
        if(modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY){
            res = limite
        }
        return res
    }


}