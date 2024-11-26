package com.example.proyectofinal_prototipov1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat

class Trayecto : ScrollView {
    //Imagen de fondo
    private var fondo: Drawable? = null
    private var fondo1: Drawable? = null

    // Imagen para los niveles
    private var niveles = emptyArray<Array<Drawable>>()

    //Rectangulos
    private val cuadrado = Paint()
    private val circulo = Paint()
    private val circuloSelected = Paint()
    private val radio = 80f

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()
    private val textModulo = Paint()

    // Modulo
    var modulo = arrayOf(true, false, false)
    var pestana = 0
    var colores = arrayOf(arrayOf( ResourcesCompat.getColor(resources, R.color.verdeCon, null),  ResourcesCompat.getColor(resources, R.color.azulOce, null)),
        arrayOf( ResourcesCompat.getColor(resources, R.color.azulAr, null),  ResourcesCompat.getColor(resources, R.color.NaranjaMex, null),  ResourcesCompat.getColor(resources, R.color.NaranjaMex, null)), arrayOf(ResourcesCompat.getColor(resources, R.color.cafeAmer, null), ResourcesCompat.getColor(resources, R.color.cafeAsia, null)))

    //SQLite
    var db: DBSQLite = DBSQLite(context)
    var dbBoolean = arrayOf(arrayOf(true, true, true, true, true),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false)
        )

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

        val customTypeface = resources.getFont(R.font.pact)
        textNivel.typeface = customTypeface

        textModulo.isAntiAlias = true
        textModulo.textSize = 110f
        textModulo.color = Color.BLACK
        textModulo.textAlign = Paint.Align.CENTER
        textModulo.typeface = customTypeface

        val courier = resources.getFont(R.font.courier)
        textPaint.typeface = courier

        cuadrado.style = Paint.Style.FILL
        cuadrado.color = Color.RED

        circulo.style = Paint.Style.FILL
        circulo.color = Color.BLACK

        circuloSelected.style = Paint.Style.FILL
        circuloSelected.color = ResourcesCompat.getColor(resources, R.color.azulagua, null)

//        comprobarBaseDeDatos()
    }

    fun comprobarBaseDeDatos(){
        for (i in 1..7){
            for(j in 1..5) {
                dbBoolean[i - 1][j - 1] = db.nivelDesbloqueado(i, j)
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = ancho/5
        var altonivel = (alto/2)
        var altonivel1 = altonivel/4

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
        if(modulo[1]){
            altoC = 500f
            k = 2
            index = 10
        }else if(modulo[2]){
            index = 25
        }
        for(j in 0..k){
            circuloSelected.color = colores[pestana][j]
            for (i in 0..4){
                index ++
                canvas.drawRoundRect(10f+(ancho/5*i), 700f+(altoC*j), (ancho/5)+(ancho/5*i)-5, 1100f+(altoC*j), 20f, 20f, circuloSelected)
                canvas.drawText((index).toString(),80f+(ancho/5*i), 770f+(altoC*j), textPaint)
                var datos =  db.Estadistica(j+1, i+1)
                canvas.drawText(datos[0],15f+(ancho/5*i), 850f+(altoC*j), textPaint)
                canvas.drawText(datos[0],15f+(ancho/5*i), 920f+(altoC*j), textPaint)
                canvas.drawText(datos[0],15f+(ancho/5*i), 990f+(altoC*j), textPaint)
            }
        }
//        canvas.drawText("Hola",10f, 850f, textPaint)

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

//        fondo1!!.setBounds(50, (alto/2 -200).toInt(), ancho.toInt()-50, (alto-50).toInt())


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