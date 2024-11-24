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
import androidx.appcompat.content.res.AppCompatResources

class Trayecto : ScrollView {
    //Imagen de fondo
    private var fondo: Drawable? = null
    private var fondo1: Drawable? = null
    private var fondo2: Drawable? = null
    private var fondo3: Drawable? = null
    private var fondo4: Drawable? = null
    private var fondo5: Drawable? = null
    private var fondo6: Drawable? = null
    private var fondo7: Drawable? = null

    // Imagen para los niveles
    private var nivel1: Drawable? = null
    private var nivel2: Drawable? = null
    private var nivel3: Drawable? = null
    private var nivel4: Drawable? = null
    private var nivel5: Drawable? = null
    private var nivel6: Drawable? = null
    private var nivel7: Drawable? = null

    //Rectangulos
    private val cuadrado = Paint()

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()

    //SQLite
    var db: DBSQLite = DBSQLite(context)
    var dbBoolean = arrayOf(arrayOf(false, false, false, false, false),
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
        textNivel.color = Color.WHITE
//        val tf = Typeface.create(context, resources.getIdentifier("america", "font", context?.getPackageName()))
        val customTypeface = resources.getFont(R.font.pact)
        textNivel.typeface = customTypeface

        val courier = resources.getFont(R.font.courier)
        textPaint.typeface = courier

        cuadrado.style = Paint.Style.FILL
        cuadrado.color = Color.RED
//        comprobarBaseDeDatos()
    }

//    fun comprobarBaseDeDatos(){
//        for (i in 1..7){
//            for()
//            dbBoolean[i-1] = db.nivelDesbloqueado(6, i)
//        }
//    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = ancho/5
        var altonivel = (alto/2)
        var altonivel1 = altonivel/4
        fondo!!.draw(canvas)
        fondo1!!.draw(canvas)

//        nivel1!!.draw(canvas)
//        nivel2!!.draw(canvas)
//        nivel3!!.draw(canvas)
//        nivel4!!.draw(canvas)
//        nivel5!!.draw(canvas)

        textNivel.textAlign = Paint.Align.CENTER
        val xPos = (ancho / 2)

        canvas.drawText("Trayecto", xPos, 200f, textNivel)

        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()

        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.asiaback)
        fondo!!.setBounds(0, 0, ancho.toInt(), 2000)
        fondo1 = AppCompatResources.getDrawable(getContext(), R.drawable.americaback3)
        fondo1!!.setBounds(0, 2000, ancho.toInt(), 2000+777)

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4)


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