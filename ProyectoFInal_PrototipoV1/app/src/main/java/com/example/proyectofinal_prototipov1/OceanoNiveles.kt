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
//        canvas.drawText("1",155f, (altonivel + ((altonivel1*2))-30+110), textPaint)
//        canvas.drawText("2",(anchonivel+30+110), (altonivel + ((altonivel1*3))+ 75), textPaint)
//        canvas.drawText("3",((anchonivel*2)+30+125),  (altonivel + ((altonivel1*2))), textPaint)
//        canvas.drawText("4",((anchonivel*3)+70),  (altonivel + ((altonivel1*3)) + 150), textPaint)
//        canvas.drawText("5",((anchonivel*4)-70f+125),  (altonivel + ((altonivel1*2)) + 30+110), textPaint)

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

        nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.coraln1)
        nivel1!!.setBounds(30, (altonivel + ((altonivel1*2))-50), 316, (altonivel + ((altonivel1*2))-50+219))

        nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1n2)
        nivel2!!.setBounds(anchonivel+30, (altonivel + ((altonivel1*3)) - 30), (anchonivel+30+286), (altonivel + ((altonivel1*3))- 30 + 219))

        nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.coraln3)
        nivel3!!.setBounds((anchonivel*2)+30, (altonivel + ((altonivel1*2)) - 140), ((anchonivel*2)+30+286), (altonivel + ((altonivel1*2))- 140 + 219))

        nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1n4)
        nivel4!!.setBounds((anchonivel*3)-30, (altonivel + ((altonivel1*3)) + 50), ((anchonivel*3)-30+286), (altonivel + ((altonivel1*3)) + 50 + 219))

        nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado)
        nivel5!!.setBounds((anchonivel*4)-70, (altonivel + ((altonivel1*2)) + 30), ((anchonivel*4)-70+286), (altonivel + ((altonivel1*2)) + 30 + 219))


    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4)

        //Nivel 3
        if(event.x >= (anchonivel*2)+30 && event.x <= ((anchonivel*2)+30+286) && event.y >= (altonivel + ((altonivel1*2)) - 140) && event.y <= (altonivel + ((altonivel1*2))- 140 + 219)){
//            val intent = Intent(context, EscogerInterm::class.java)
//            context.startActivity(intent)
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