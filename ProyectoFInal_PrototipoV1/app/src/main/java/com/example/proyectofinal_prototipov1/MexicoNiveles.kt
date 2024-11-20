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

class MexicoNiveles : View {
    //Imagen de fondo
    private var fondo: Drawable? = null
    private var nivel1: Drawable? = null
    private var nivel2: Drawable? = null
    private var nivel3: Drawable? = null
    private var nivel4: Drawable? = null
    private var nivel5: Drawable? = null

    private var nivel6: Drawable? = null
    private var nivel7: Drawable? = null
    private var nivel8: Drawable? = null
    private var nivel9: Drawable? = null
    private var nivel10: Drawable? = null


    //Rectangulos
    private val cuadrado = Paint()

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()


    constructor(context: Context?) : super(context) {
        inicializa()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
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
        var anchonivel = ancho / 5
        var altonivel = (alto / 2)
        var altonivel1 = altonivel / 4
        fondo!!.draw(canvas)
        nivel1!!.draw(canvas)
        nivel2!!.draw(canvas)
        nivel3!!.draw(canvas)
        nivel4!!.draw(canvas)
        nivel5!!.draw(canvas)
        nivel6!!.draw(canvas)
        nivel7!!.draw(canvas)
        nivel8!!.draw(canvas)
        nivel9!!.draw(canvas)
        nivel10!!.draw(canvas)

        canvas.drawText("México", ancho / 2, 500f, textNivel)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho / 5).toInt()
        var altonivel = (alto / 2).toInt()
        var altonivel1 = (altonivel / 4).toInt()


        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.mexico)
        fondo!!.setBounds(0, 0, ancho.toInt(), alto.toInt())

        nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn1)
        nivel1!!.setBounds(
            30,
            (altonivel + ((altonivel1 * 2)) - 100),
            30 + 200,
            (altonivel + ((altonivel1 * 2)) - 100 + 260)
        )

        nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn2)
        nivel2!!.setBounds(
            30 + 230,
            (altonivel + ((altonivel1 * 2)) - 50),
            30 + 230 + 200,
            (altonivel + ((altonivel1 * 2)) - 50 + 260)
        )

        nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn3)
        nivel3!!.setBounds(
            (anchonivel * 2) + 30,
            (altonivel + ((altonivel1 * 2)) - 170),
            ((anchonivel * 2) + 30 + 200),
            (altonivel + ((altonivel1 * 2)) - 170 + 260)
        )

        nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn4)
        nivel4!!.setBounds(
            (anchonivel * 3) - 10,
            (altonivel + ((altonivel1 * 2)) + 10),
            ((anchonivel * 3) - 10 + 200),
            (altonivel + ((altonivel1 * 2)) + 10 + 260)
        )

        nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.cactuscandado)
        nivel5!!.setBounds(
            (anchonivel * 4) - 10,
            (altonivel + ((altonivel1 * 2)) - 130),
            ((anchonivel * 4) - 10 + 200),
            (altonivel + ((altonivel1 * 2)) - 130 + 260)
        )

        nivel10 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan10)
        nivel10!!.setBounds(
            10,
            (altonivel + ((altonivel1 * 2)) + 190),
            10 + 227,
            (altonivel + ((altonivel1 * 2)) + 190 + 176)
        )

        nivel9 = AppCompatResources.getDrawable(getContext(), R.drawable.rocacandado)
        nivel9!!.setBounds(
            30 + 200,
            (altonivel + ((altonivel1 * 2)) + 280),
            30 + 200 + 227,
            (altonivel + ((altonivel1 * 2)) + 280 + 176)
        )

        nivel8 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan8)
        nivel8!!.setBounds(
            (anchonivel * 2) + 10,
            (altonivel + ((altonivel1 * 2)) + 170),
            ((anchonivel * 2) + 10 + 227),
            (altonivel + ((altonivel1 * 2)) + 170 + 176)
        )
        nivel7 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan7)
        nivel7!!.setBounds(
            (anchonivel * 3) - 10,
            (altonivel + ((altonivel1 * 2)) + 290),
            ((anchonivel * 3) - 10 + 227),
            (altonivel + ((altonivel1 * 2)) + 290 + 176)
        )

        nivel6 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan6)
        nivel6!!.setBounds(
            (anchonivel * 4) - 10,
            (altonivel + ((altonivel1 * 2)) + 180),
            ((anchonivel * 4) - 10 + 227),
            (altonivel + ((altonivel1 * 2)) + 180 + 176)
        )

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho / 5).toInt()
        var altonivel = (alto / 2).toInt()
        var altonivel1 = (altonivel / 4)

        //Nivel 1
        if (event.x >= 50 && event.x <= 210 && event.y >= (altonivel + ((altonivel1 * 2)) - 100) && event.y <= (altonivel + ((altonivel1 * 2)) + 160)) {

        }else if(event.x >= 280 && event.x <= 440 && event.y >= (altonivel + ((altonivel1 * 2)) - 50) && event.y <= (altonivel + ((altonivel1 * 2)) + 210)){

        }else if(event.x >= (anchonivel * 2) + 50 && event.x <= ((anchonivel * 2) + 210) && event.y >= (altonivel + ((altonivel1 * 2)) - 170) && event.y <= (altonivel + ((altonivel1 * 2)) + 90)){

        }else if(event.x >= (anchonivel * 3) + 10 && event.x <= ((anchonivel * 3) + 170) && event.y >= (altonivel + ((altonivel1 * 2)) + 10) && event.y <= (altonivel + ((altonivel1 * 2)) + 270)){

        }else if(event.x >= (anchonivel * 4) + 10 && event.x <= ((anchonivel * 4) + 170) && event.y >= (altonivel + ((altonivel1 * 2)) - 130) && event.y <= (altonivel + ((altonivel1 * 2)) - 130 + 260)){

        }

        //Nivel 6
        if(event.x >= (anchonivel * 4) + 10 && event.x <= ((anchonivel * 4) + 207) && event.y >= (altonivel + ((altonivel1 * 2)) + 180) && event.y <= (altonivel + ((altonivel1 * 2)) + 180 + 176)){

        }else if(event.x >= (anchonivel * 3) + 10 && event.x <= ((anchonivel * 3) + 207) && event.y >= (altonivel + ((altonivel1 * 2)) + 290) && event.y <= (altonivel + ((altonivel1 * 2)) + 290 + 176)){

        }else if(event.x >= (anchonivel * 2) + 30 && event.x <= ((anchonivel * 2) + 207) && event.y >= (altonivel + ((altonivel1 * 2)) + 170) && event.y <= (altonivel + ((altonivel1 * 2)) + 170 + 176)){

        }else if(event.x >= 250 && event.x <= 437 && event.y >= (altonivel + ((altonivel1 * 2)) + 280) && event.y <= (altonivel + ((altonivel1 * 2)) + 280 + 176)){

        }else if(event.x >= 30 && event.x <=  217 && event.y >= (altonivel + ((altonivel1 * 2)) + 190) && event.y <= (altonivel + ((altonivel1 * 2)) + 190 + 176)){

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
        if (modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY) {
            res = limite
        }
        return res
    }

    private fun calcularAncho(widthMeasureSpec: Int): Int {
        var res = 100
        val modo = MeasureSpec.getMode(widthMeasureSpec)
        val limite = MeasureSpec.getSize(widthMeasureSpec)
        if (modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY) {
            res = limite
        }
        return res
    }
}