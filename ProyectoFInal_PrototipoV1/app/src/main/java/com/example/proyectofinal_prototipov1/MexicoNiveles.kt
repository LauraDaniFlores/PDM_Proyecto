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


    //Rectángulos
    private val cuadrado = Paint()

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()

    //SQLite
    var db: DBSQLite = DBSQLite(context)
    var dbBoolean = arrayOf(false, false, false, false, false, false, false, false, false, false)

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

        comprobarBaseDeDatos()
    }
    fun comprobarBaseDeDatos(){
        for (i in 1..10){
            dbBoolean[i-1] = db.nivelDesbloqueado(4, i)
        }
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

        if(dbBoolean[0]) {
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn1)
        }else{
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.cactuscandado)
        }
        nivel1!!.setBounds(
            30,
            (altonivel + ((altonivel1 * 2)) - 100),
            30 + 200,
            (altonivel + ((altonivel1 * 2)) - 100 + 260)
        )


        if(dbBoolean[1]) {
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn2)
        }else{
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.cactuscandado)
        }
        nivel2!!.setBounds(
            30 + 230,
            (altonivel + ((altonivel1 * 2)) - 50),
            30 + 230 + 200,
            (altonivel + ((altonivel1 * 2)) - 50 + 260)
        )


        if(dbBoolean[2]) {
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn3)
        }else{
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.cactuscandado)
        }
        nivel3!!.setBounds(
            (anchonivel * 2) + 30,
            (altonivel + ((altonivel1 * 2)) - 170),
            ((anchonivel * 2) + 30 + 200),
            (altonivel + ((altonivel1 * 2)) - 170 + 260)
        )


        if(dbBoolean[3]) {
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.cactusn4)
        }else{
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.cactuscandado)
        }
        nivel4!!.setBounds(
            (anchonivel * 3) - 10,
            (altonivel + ((altonivel1 * 2)) + 10),
            ((anchonivel * 3) - 10 + 200),
            (altonivel + ((altonivel1 * 2)) + 10 + 260)
        )


        if(dbBoolean[4]) {
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.cactuscandado)
        }else{
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.cactuscandado)
        }
        nivel5!!.setBounds(
            (anchonivel * 4) - 10,
            (altonivel + ((altonivel1 * 2)) - 130),
            ((anchonivel * 4) - 10 + 200),
            (altonivel + ((altonivel1 * 2)) - 130 + 260)
        )


        if(dbBoolean[9]) {
            nivel10 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan10)
        }else{
            nivel10 = AppCompatResources.getDrawable(getContext(), R.drawable.rocacandado)
        }
        nivel10!!.setBounds(
            10,
            (altonivel + ((altonivel1 * 2)) + 190),
            10 + 227,
            (altonivel + ((altonivel1 * 2)) + 190 + 176)
        )


        if(dbBoolean[8]) {
            nivel9 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan9)
        }else{
            nivel9 = AppCompatResources.getDrawable(getContext(), R.drawable.rocacandado)
        }
        nivel9!!.setBounds(
            30 + 200,
            (altonivel + ((altonivel1 * 2)) + 280),
            30 + 200 + 227,
            (altonivel + ((altonivel1 * 2)) + 280 + 176)
        )


        if(dbBoolean[7]) {
            nivel8 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan8)
        }else{
            nivel8 = AppCompatResources.getDrawable(getContext(), R.drawable.rocacandado)
        }
        nivel8!!.setBounds(
            (anchonivel * 2) + 10,
            (altonivel + ((altonivel1 * 2)) + 170),
            ((anchonivel * 2) + 10 + 227),
            (altonivel + ((altonivel1 * 2)) + 170 + 176)
        )


        if(dbBoolean[6]) {
            nivel7 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan7)
        }else{
            nivel7 = AppCompatResources.getDrawable(getContext(), R.drawable.rocacandado)
        }
        nivel7!!.setBounds(
            (anchonivel * 3) - 10,
            (altonivel + ((altonivel1 * 2)) + 290),
            ((anchonivel * 3) - 10 + 227),
            (altonivel + ((altonivel1 * 2)) + 290 + 176)
        )


        if(dbBoolean[5]) {
            nivel6 = AppCompatResources.getDrawable(getContext(), R.drawable.rocan6)
        }else{
            nivel6 = AppCompatResources.getDrawable(getContext(), R.drawable.rocacandado)
        }
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
            val intent = Intent(context, Cards_Inter::class.java)
            intent.putExtra("modulo", "4")
            context.startActivity(intent)
        }else if(event.x >= 280 && event.x <= 440 && event.y >= (altonivel + ((altonivel1 * 2)) - 50) && event.y <= (altonivel + ((altonivel1 * 2)) + 210)){
            val intent = Intent(context, Memorama_Inter::class.java)
            intent.putExtra("modulo", "4")
            context.startActivity(intent)
        }else if(event.x >= (anchonivel * 2) + 50 && event.x <= ((anchonivel * 2) + 210) && event.y >= (altonivel + ((altonivel1 * 2)) - 170) && event.y <= (altonivel + ((altonivel1 * 2)) + 90)){

        }else if(event.x >= (anchonivel * 3) + 10 && event.x <= ((anchonivel * 3) + 170) && event.y >= (altonivel + ((altonivel1 * 2)) + 10) && event.y <= (altonivel + ((altonivel1 * 2)) + 270)){

        }else if(event.x >= (anchonivel * 4) + 10 && event.x <= ((anchonivel * 4) + 170) && event.y >= (altonivel + ((altonivel1 * 2)) - 130) && event.y <= (altonivel + ((altonivel1 * 2)) - 130 + 260)){

        }

        //Nivel 6
        if(event.x >= (anchonivel * 4) + 10 && event.x <= ((anchonivel * 4) + 207) && event.y >= (altonivel + ((altonivel1 * 2)) + 180) && event.y <= (altonivel + ((altonivel1 * 2)) + 180 + 176)){
            val intent = Intent(context, Cards_Inter::class.java)
            intent.putExtra("modulo", "5")
            context.startActivity(intent)
        }else if(event.x >= (anchonivel * 3) + 10 && event.x <= ((anchonivel * 3) + 207) && event.y >= (altonivel + ((altonivel1 * 2)) + 290) && event.y <= (altonivel + ((altonivel1 * 2)) + 290 + 176)){
            val intent = Intent(context, Memorama_Inter::class.java)
            intent.putExtra("modulo", "5")
            context.startActivity(intent)
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