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
//        val tf = Typeface.create(context, resources.getIdentifier("america", "font", context?.getPackageName()))
        val customTypeface = resources.getFont(R.font.pact)
        textNivel.typeface = customTypeface

        val courier = resources.getFont(R.font.courier)
        textPaint.typeface = courier

        cuadrado.style = Paint.Style.FILL
        cuadrado.color = Color.RED
//        comprobarBaseDeDatos()
    }

    fun comprobarBaseDeDatos(){
        for (i in 1..5){
            dbBoolean[i-1] = db.nivelDesbloqueado(5, i)
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



        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.americaback3)
        fondo!!.setBounds(0, 0, ancho.toInt(), alto.toInt())

        if(dbBoolean[0]){
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.pin1)
        }else{
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado)
        }
        nivel1!!.setBounds(anchonivel+70, (altonivel - altonivel1-75), anchonivel+70+200, (altonivel - altonivel1-75)+250)

        if(dbBoolean[1]){
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pin2)
        }else{
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado)
        }
        nivel2!!.setBounds(anchonivel*3-140, (altonivel - (altonivel1) - 150), anchonivel*3-140+200, (altonivel - (altonivel1) - 150+250))

        if(dbBoolean[2]) {
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pin3)
        }else{
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado)
        }
        nivel3!!.setBounds((anchonivel*3-110), (altonivel-50), ((anchonivel*3-110)+200), (altonivel-50 + 250))

        if(dbBoolean[3]) {
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pin4)
        }else{
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado)
        }
        nivel4!!.setBounds((anchonivel*3 +160), (altonivel), ((anchonivel*3)+160+200), (altonivel + 250))

        if(dbBoolean[4]) {
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pin5)
        }else{
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado)
        }
        nivel5!!.setBounds((anchonivel*3+20), (altonivel+(altonivel1*2)-40), ((anchonivel*3+20)+200), (altonivel+(altonivel1*2)-40) + 250)


    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4)

        //Nivel 1
        if(event.x >= anchonivel-70 && event.x <= anchonivel+70+200 && event.y >= (altonivel - altonivel1-75) && event.y <= (altonivel - altonivel1-75)+250 && dbBoolean[0]){
            val intent = Intent(context, Cards_Inter::class.java)
            intent.putExtra("modulo", "6")
            context.startActivity(intent)
        }

        //Nivel 2
        if(event.x >= anchonivel*3-140 && event.x <= anchonivel*3-140+200 && event.y >= (altonivel - (altonivel1) - 150) && event.y <= (altonivel - (altonivel1) - 150+250) && dbBoolean[1]){
            val intent = Intent(context, Memorama_Inter::class.java)
            intent.putExtra("modulo", "6");
            context.startActivity(intent)
        }

        //Nivel 3
        if(event.x >= (anchonivel*3-110) && event.x <= ((anchonivel*3-110)+200) && event.y >= (altonivel-50) && event.y <= (altonivel-50 + 250) && dbBoolean[2]){
            val intent = Intent(context, EscogerInterm::class.java)
            intent.putExtra("modulo", "5");
            context.startActivity(intent)
        }

        //Nivel 4
        if(event.x >= (anchonivel*3 +160) && event.x <= ((anchonivel*3)+160+200) && event.y >= (altonivel) && event.y <= (altonivel + 250) && dbBoolean[3]){

        }

        //Nivel 5
        if(event.x >= (anchonivel*3+20) && event.x <= ((anchonivel*3+20)+200) && event.y >= (altonivel+(altonivel1*2)-40) && event.y <= (altonivel+(altonivel1*2)-40) + 250 && dbBoolean[4]){

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