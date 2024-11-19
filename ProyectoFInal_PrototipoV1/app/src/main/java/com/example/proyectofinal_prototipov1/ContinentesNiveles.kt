package com.example.proyectofinal_prototipov1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.activity.OnBackPressedCallback


class ContinentesNiveles : View {
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

        canvas.drawText("Continentes", ancho/2, 500f, textNivel)

        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/3).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()


        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.continent)
        fondo!!.setBounds(0, 0, ancho.toInt(), alto.toInt())

        nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan1)
        nivel1!!.setBounds(((ancho/2)-100).toInt(), ((alto/2)-80).toInt(), ((ancho/2)+100).toInt(), ((alto/2)+200-80).toInt())

        nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan2)
        nivel2!!.setBounds(((ancho - 200 - 40).toInt()), (( altonivel + altonivel1- 120).toInt()), ((ancho - 40).toInt()), (( altonivel + altonivel1- 120 + 200).toInt()))

        nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan3)
        nivel3!!.setBounds(((ancho - 200 - 40).toInt()), (( altonivel + altonivel1 + 300).toInt()), ((ancho - 40).toInt()), (( altonivel + altonivel1 + 300 + 200).toInt()))

        nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan4)
        nivel4!!.setBounds(((150).toInt()), (( altonivel + altonivel1 + 390).toInt()), ((200 + 150).toInt()), (( altonivel + altonivel1 + 390 + 200).toInt()))

        nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado)
        nivel5!!.setBounds(((40).toInt()), (( altonivel + altonivel1- 170).toInt()), ((200 + 40).toInt()), (( altonivel + altonivel1- 170 + 200).toInt()))


    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/3).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()

        //Nivel 1
        if(event.x >= ((ancho/2)-100) && event.x <= (ancho/2)+100 && event.y >= ((alto/2)-80) - 140 && event.y <= ((alto/2)+200-80)){
            val intent = Intent(context, Modulos::class.java)
            context.startActivity(intent)
        }

//        nivel1!!.setBounds(((ancho/2)-100).toInt(), ((alto/2)-80).toInt(), ((ancho/2)+100).toInt(), ((alto/2)+200-80).toInt())
//
//        nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan2)
//        nivel2!!.setBounds(((ancho - 200 - 40).toInt()), (( altonivel + altonivel1- 120).toInt()), ((ancho - 40).toInt()), (( altonivel + altonivel1- 120 + 200).toInt()))
//
//        nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan3)
//        nivel3!!.setBounds(((ancho - 200 - 40).toInt()), (( altonivel + altonivel1 + 300).toInt()), ((ancho - 40).toInt()), (( altonivel + altonivel1 + 300 + 200).toInt()))
//
//        nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan4)
//        nivel4!!.setBounds(((150).toInt()), (( altonivel + altonivel1 + 390).toInt()), ((200 + 150).toInt()), (( altonivel + altonivel1 + 390 + 200).toInt()))
//
//        nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado)
//        nivel5!!.setBounds(((40).toInt()), (( altonivel + altonivel1- 170).toInt()), ((200 + 40).toInt()), (( altonivel + altonivel1- 170 + 200).toInt()))

//        //Nivel 3
//        if(event.x >= (anchonivel*2)+30 && event.x <= ((anchonivel*2)+30+286) && event.y >= (altonivel + ((altonivel1*2)) - 140) && event.y <= (altonivel + ((altonivel1*2))- 140 + 219)){
//            val intent = Intent(context, Modulos::class.java)
//            context.startActivity(intent)
//        }
//
//        //Nivel 2
//        if(event.x >= anchonivel+30 && event.x <= ((anchonivel*2)+30+286) && event.y >= (altonivel + ((altonivel1*2)) - 140) && event.y <= (altonivel + ((altonivel1*2))- 140 + 219)){
//            val i = Intent(context, Memorama_Inter::class.java)
//            i.putExtra("modulo", "1")
//            context.startActivity(i)
//        }


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