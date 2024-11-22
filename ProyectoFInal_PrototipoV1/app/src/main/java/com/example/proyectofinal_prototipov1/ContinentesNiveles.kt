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
import java.util.Date


class ContinentesNiveles : View {
    //Imagen de fondo
    private var fondo: Drawable? = null
    private var nivel1: Drawable? = null
    private var nivel2: Drawable? = null
    private var nivel3: Drawable? = null
    private var nivel4: Drawable? = null
    private var nivel5: Drawable? = null

    //SQLite
    var db: DBSQLite = DBSQLite(context)
    var dbBoolean = arrayOf(false, false, false, false)


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
//        db.guardarRegistro(1, 2, 10, 100, Date(), true)
        comprobarBaseDeDatos()
    }

    fun comprobarBaseDeDatos(){
        for (i in 2..5){
            dbBoolean[i-2] = db.nivelDesbloqueado(1, i)
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

//        if(db.nivelDesbloqueado(1, 2)){
//            canvas.drawText("Desbloqueado", ancho/2, 800f, textNivel)
//        }
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

        if(dbBoolean[0]){
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan2)
        }else{
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado)
        }
        nivel2!!.setBounds(((ancho - 200 - 40).toInt()), (( altonivel + altonivel1- 120).toInt()), ((ancho - 40).toInt()), (( altonivel + altonivel1- 120 + 200).toInt()))

        if(dbBoolean[1]){
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan3)
        }else{
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan3)
        }
        nivel3!!.setBounds(((ancho - 200 - 40).toInt()), (( altonivel + altonivel1 + 300).toInt()), ((ancho - 40).toInt()), (( altonivel + altonivel1 + 300 + 200).toInt()))

        if(dbBoolean[2]){
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan4)
        }else{
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado)
        }
        nivel4!!.setBounds(((150).toInt()), (( altonivel + altonivel1 + 390).toInt()), ((200 + 150).toInt()), (( altonivel + altonivel1 + 390 + 200).toInt()))

        if(dbBoolean[3]){
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan5)
        }else{
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado)
        }
        nivel5!!.setBounds(((40).toInt()), (( altonivel + altonivel1- 170).toInt()), ((200 + 40).toInt()), (( altonivel + altonivel1- 170 + 200).toInt()))


    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/3).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()

        //Nivel 1
        if(event.x >= ((ancho/2)-100) && event.x <= (ancho/2)+100 && event.y >= ((alto/2)-80) && event.y <= ((alto/2)+200-80)){
            val intent = Intent(context, EscogerInterm::class.java)
            context.startActivity(intent)
        }else if(event.x >= (ancho - 200 - 40) && event.x <= (ancho - 40) && event.y >= ( altonivel + altonivel1- 120) && event.y <= ( altonivel + altonivel1- 120 + 200) && dbBoolean[0]){
            val intent = Intent(context, Memorama_Inter::class.java)
            intent.putExtra("modulo", 1);
            context.startActivity(intent)
        }else if(event.x >= (ancho - 200 - 40) && event.x <= (ancho - 40) && event.y >= ( altonivel + altonivel1 + 300) && event.y <= ( altonivel + altonivel1 + 300 + 200) && dbBoolean[1]){
            val intent = Intent(context, EscogerInterm::class.java)
            context.startActivity(intent)
        }else if(event.x >= (150) && event.x <= (200 + 150) && event.y >= ( altonivel + altonivel1 + 390) && event.y <= ( altonivel + altonivel1 + 390 + 200) && dbBoolean[2]){
            val intent = Intent(context, EscogerInterm::class.java)
            context.startActivity(intent)
        }else if(event.x >= (40) && event.x <= (200 + 40) && event.y >= ( altonivel + altonivel1- 170) && event.y <= ( altonivel + altonivel1- 170 + 200) && dbBoolean[3]){

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