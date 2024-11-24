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
import androidx.appcompat.content.res.AppCompatResources

class OceanoNiveles: View {
    //Imagen de fondo
    private var fondo: Drawable? = null
    private var nivel1: Drawable? = null
    private var nivel2: Drawable? = null
    private var nivel3: Drawable? = null
    private var nivel4: Drawable? = null
    private var nivel5: Drawable? = null


    //Rectangulos
    private val cuadrado = Paint()

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()

    //SQLite
    var db: DBSQLite = DBSQLite(context)
    var dbBoolean = arrayOf(false, false, false, false, false)

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
        textNivel.textAlign = Paint.Align.CENTER
//        val tf = Typeface.create(context, resources.getIdentifier("america", "font", context?.getPackageName()))
        val customTypeface = resources.getFont(R.font.pact)
        textNivel.typeface = customTypeface

        val courier = resources.getFont(R.font.courier)
        textPaint.typeface = courier


        cuadrado.style = Paint.Style.FILL
        cuadrado.color = Color.RED
        comprobarBaseDeDatos()
    }

    fun comprobarBaseDeDatos(){
        for (i in 1..5){
            dbBoolean[i-1] = db.nivelDesbloqueado(2, i)
        }
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = ancho/5
        var altonivel = (alto/2)
        var altonivel1 = altonivel/4
        fondo!!.draw(canvas)
        nivel1!!.draw(canvas)
        nivel2!!.draw(canvas)
        nivel3!!.draw(canvas)
        nivel4!!.draw(canvas)
        nivel5!!.draw(canvas)

        canvas.drawText("Océano", ancho/2, 500f, textNivel)


        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()


        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.ocean)
        fondo!!.setBounds(0, 0, ancho.toInt(), alto.toInt())

        if(dbBoolean[0]) {
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.coraln1)
        }else{
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado)
        }
        nivel1!!.setBounds(30, (altonivel + ((altonivel1*2))-50), 316, (altonivel + ((altonivel1*2))-50+219))


        if(dbBoolean[1]) {
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1n2)
        }else{
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado)
        }
        nivel2!!.setBounds(anchonivel+30, (altonivel + ((altonivel1*3)) - 30), (anchonivel+30+286), (altonivel + ((altonivel1*3))- 30 + 219))


        if(dbBoolean[2]) {
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.coraln3)
        }else{
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado)
        }
        nivel3!!.setBounds((anchonivel*2)+30, (altonivel + ((altonivel1*2)) - 140), ((anchonivel*2)+30+286), (altonivel + ((altonivel1*2))- 140 + 219))


        if(dbBoolean[3]) {
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1n4)
        }else{
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1candado)
        }
        nivel4!!.setBounds((anchonivel*3)-30, (altonivel + ((altonivel1*3)) + 50), ((anchonivel*3)-30+286), (altonivel + ((altonivel1*3)) + 50 + 219))


        if(dbBoolean[4]) {
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado)
        }else{
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado)
        }
        nivel5!!.setBounds((anchonivel*4)-70, (altonivel + ((altonivel1*2)) + 30), ((anchonivel*4)-70+286), (altonivel + ((altonivel1*2)) + 30 + 219))

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4)

        //Nivel 1
        if(event.x >= 30 && event.x <= 316 && event.y >= (altonivel + ((altonivel1*2))-50) && event.y <= (altonivel + ((altonivel1*2))-50+219) && dbBoolean[0]){
            val intent = Intent(context, Cards_Inter::class.java)
            intent.putExtra("modulo", "2")
            context.startActivity(intent)
        }

        //Nivel 2
        if(event.x >= anchonivel+30 && event.x <= (anchonivel+30+286) && event.y >= (altonivel + ((altonivel1*3)) - 30) && event.y <= (altonivel + ((altonivel1*3))- 30 + 219) && dbBoolean[1]){
            val intent = Intent(context, Memorama_Inter::class.java)
            intent.putExtra("modulo", "2")
            context.startActivity(intent)
        }

        //Nivel 3
        if(event.x >= (anchonivel*2)+30 && event.x <= ((anchonivel*2)+30+286) && event.y >= (altonivel + ((altonivel1*2)) - 140) && event.y <= (altonivel + ((altonivel1*2))- 140 + 219) && dbBoolean[2]){
//            val intent = Intent(context, EscogerInterm::class.java)
//            context.startActivity(intent)
        }

        //Nivel 4
        if(event.x >= (anchonivel*3)-30 && event.x <= ((anchonivel*3)-30+286) && event.y >= (altonivel + ((altonivel1*3)) + 50) && event.y <= (altonivel + ((altonivel1*3)) + 50 + 219) && dbBoolean[3]){
        }

        //Nivel 5
        if(event.x >= (anchonivel*4)-70 && event.x <= ((anchonivel*4)-70+286) && event.y >= (altonivel + ((altonivel1*2)) + 30) && event.y <= (altonivel + ((altonivel1*2)) + 30 + 219) && dbBoolean[4]){
        }


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