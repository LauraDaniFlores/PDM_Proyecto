package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources

class AmericaNiveles : View {
    //Imagen de fondo
    private var fondo: Drawable? = null

    // Imagen para niveles
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

        textNivel.textAlign = Paint.Align.CENTER
        val xPos = (ancho / 2)

        canvas.drawText("América", xPos, 450f, textNivel)

        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()



        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.americaback1)
        fondo!!.setBounds(0, 0, ancho.toInt(), alto.toInt())

        nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.pin)
        nivel1!!.setBounds(anchonivel-40, (altonivel - altonivel1), anchonivel-40+200, (altonivel - altonivel1)+250)

        nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pin)
        nivel2!!.setBounds(anchonivel*3-50, (altonivel - (altonivel1) - 150), anchonivel*3-50+200, (altonivel - (altonivel1) - 150+250))

        nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pin)
        nivel3!!.setBounds((anchonivel*2)-90, (altonivel + 100), ((anchonivel*2)-90+200), (altonivel + 100 + 250))

        nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pin)
        nivel4!!.setBounds((anchonivel*3 -50), (altonivel+(altonivel1*2)-140), ((anchonivel*3)-50+200), (altonivel+(altonivel1*2))-140 + 250)

        nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pin)
        nivel5!!.setBounds((anchonivel*2 +60), (altonivel+(altonivel1*2) +100), ((anchonivel*2)+60+200), (altonivel+(altonivel1*2)+100) + 250)


    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4)

        //Nivel 1
        if(event.x >= anchonivel-40 && event.x <= anchonivel-40+200 && event.y >= (altonivel - altonivel1) && event.y <= (altonivel - altonivel1)+250){

        }

        //Nivel 2
        if(event.x >= anchonivel*3-50 && event.x <= anchonivel*3-50+200 && event.y >= altonivel - (altonivel1) && event.y <= (altonivel - (altonivel1) - 150+250)){

        }

        //Nivel 3
        if(event.x >= (anchonivel*2)-90 && event.x <= ((anchonivel*2)-90+200) && event.y >= (altonivel + 100) && event.y <= (altonivel + 100 + 250)){

        }

        //Nivel 4
        if(event.x >= (anchonivel*3 -50) && event.x <= ((anchonivel*3)-50+200) && event.y >=  (altonivel+(altonivel1*2)-140) && event.y <= (altonivel+(altonivel1*2))-140 + 250){

        }

        //Nivel 5
        if(event.x >= (anchonivel*2 +60) && event.x <=  ((anchonivel*2)+60+200) && event.y >= (altonivel+(altonivel1*2) +100) && event.y <= (altonivel+(altonivel1*2)+100) + 250){

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