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
    //Imagen de fondo
    private var fondo: Drawable? = null
    private var fondo1: Drawable? = null

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

    // Gif
    private var gif: AnimationDrawable? = null

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

        textNivel.isAntiAlias = true
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

        var index = 0
        for(i in 0..5){
            for (j in 0..9){
                if(i != 3)
                    if(j == 5) break
                datos.set(index, db.Estadistica(i+1, j+1))
//                db.Estadistica(i, j)
                index++
            }
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var altonivel = (alto/2)

//        fondo!!.draw(canvas)
        val xPos = (ancho / 2)
        canvas.drawText("Trayecto", xPos, 300f, textNivel)

        var cx = ancho/2 - 200f
        var cy = 450f
        for(i in 0.. 2){
            if(modulo[i]){
                circuloSelected.color = ResourcesCompat.getColor(resources, R.color.azulagua, null)
                canvas.drawCircle(cx, cy, radio, circuloSelected)
            }else{
                canvas.drawCircle(cx, cy, radio, circulo)
            }
            canvas.drawText((i+1).toString(),cx-20, cy+20,textPaint)
            cx += 200f
        }
        var index = 0
        var altoC = 600f
        var k = 1
        var pos = 0
        var nivel = 0

        if(modulo[1]){
            altoC = 500f
            k = 2
            index = 10
            pos = 4
        }else if(modulo[2]){
            pos = 2
            index = 25
            gif!!.draw(canvas)
//            gif!!.start()
        }else if(modulo[0]){
            gif!!.draw(canvas)
//            gif!!.start()
        }

        for(j in 0..k){
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
                canvas.drawRoundRect(10f+(ancho/5*i), 700f+(altoC*j), (ancho/5)+(ancho/5*i)-5, 1100f+(altoC*j), 20f, 20f, circuloSelected)
                canvas.drawText((index).toString(),80f+(ancho/5*i), 770f+(altoC*j), textPaint)
                if(datos[index-1][3] == "true"){
                    canvas.drawText("Tiempo:",15f+(ancho/5*i), 850f+(altoC*j), ptext)
                    canvas.drawText(datos[index-1][0] + " seg",15f+(ancho/5*i), 890f+(altoC*j), ptext)
                    canvas.drawText("Puntaje:",15f+(ancho/5*i), 930f+(altoC*j), ptext)
                    canvas.drawText(datos[index-1][1],15f+(ancho/5*i), 970f+(altoC*j), ptext)
//                    canvas.drawText("Fecha:",15f+(ancho/5*i), 1010f+(altoC*j), ptext)
//                    var formatter = DateTimeFormatter.ofPattern("yyyy")
//                    var formattedDate = datos[index-1][2].format(formatter)
//                    canvas.drawText(formattedDate,15f+(ancho/5*i), 1040f+(altoC*j), ptext)
                }
            }
        }

        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()

//        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.asiaback)
//        fondo!!.setBounds(0, 0, ancho.toInt(), 2000)

        gif = AppCompatResources.getDrawable(
            getContext(),
            R.drawable.mundo_giratorio
        ) as AnimationDrawable?

        gif!!.setBounds((ancho/2-150).toInt(), (alto/4*3).toInt(), (ancho/2+150).toInt(), (alto/4*3+300).toInt())
        gif!!.start()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        var cx = ancho/2 - 200f
        var cy = 450f
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