package com.example.proyectofinal_prototipov1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources

class ArticaAntarticaNiveles: View {
    //Imagen de fondo
    private var fondo: Drawable? = null

    //Sonido
    private var clickSound: MediaPlayer? = null

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
        // Inicializar el MediaPlayer con el sonido deseado
        clickSound = MediaPlayer.create(context, R.raw.efectobtn)
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
        comprobarBaseDeDatos()

    }

    fun comprobarBaseDeDatos(){
        for (i in 1..5){
            dbBoolean[i-1] = db.nivelDesbloqueado(3, i)
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

        canvas.drawText("Ártica &", xPos, 450f, textNivel)
        canvas.drawText("Antártica", xPos, 580f, textNivel)

        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()


        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.polos)
        fondo!!.setBounds(0, 0, ancho.toInt(), alto.toInt())

        if(dbBoolean[0]) {
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.iglue1)
        }else{
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.igluecandado)
        }
        nivel1!!.setBounds(10, (altonivel + ((altonivel1*2))-50), 460, (altonivel + ((altonivel1*2))-50+340))

        if(dbBoolean[1]) {
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pino2)
        }else{
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pinocandado)
        }
        nivel2!!.setBounds(anchonivel+50, (altonivel + (altonivel1) - 90), (anchonivel+50+268), (altonivel + (altonivel1- 30 + 250)))

        if(dbBoolean[2]) {
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pino3)
        }else{
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pinocandado)
        }
        nivel3!!.setBounds((anchonivel*2)+110, (altonivel + 30), ((anchonivel*2)+110+240), (altonivel + 60 + 250))

        if(dbBoolean[3]) {
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pino4)
        }else{
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pinocandado)
        }
        nivel4!!.setBounds((anchonivel*4)-80, (altonivel + 100), ((anchonivel*4)-70+286), (altonivel + 100 + 370))

        if(dbBoolean[4]) {
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pino5)
        }else{
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pinoscandado)
        }
        nivel5!!.setBounds((anchonivel*3)-30, (altonivel + ((altonivel1*2)) + 100), ((anchonivel*3)-50+268), (altonivel + ((altonivel1*3)) + 250))


    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4)

        //Nivel 1
        if(event.x >= 10 && event.x <= 460 && event.y >=  (altonivel + ((altonivel1*2))-50) && event.y <= (altonivel + ((altonivel1*2))-50+340) && dbBoolean[0]){
            clickSound?.start()
            val intent = Intent(context, Cards_Inter::class.java)
            intent.putExtra("modulo", "3")
            context.startActivity(intent)
        }

        //Nivel 2
        if(event.x >= anchonivel+50 && event.x <= (anchonivel+50+268) && event.y >= (altonivel + (altonivel1) - 90) && event.y <= (altonivel + (altonivel1- 30 + 250)) && dbBoolean[1]){
            clickSound?.start()
            val intent = Intent(context, Memorama_Inter::class.java)
            intent.putExtra("modulo", "3");
            context.startActivity(intent)
        }

        //Nivel 3
        if(event.x >= (anchonivel*2)+110 && event.x <= ((anchonivel*2)+110+240) && event.y >= (altonivel + 30) && event.y <= (altonivel + 60 + 250) && dbBoolean[2]){
            clickSound?.start()
            val intent = Intent(context, EscogerInterm::class.java)
            intent.putExtra("modulo", "3");
            context.startActivity(intent)
        }

        //Nivel 4
        if(event.x >= (anchonivel*4)-80 && event.x <=  ((anchonivel*4)-70+286) && event.y >= (altonivel + 100) && event.y <= (altonivel + 100 + 370) && dbBoolean[3]){
            clickSound?.start()
            val intent = Intent(context, Peguntados_Inter::class.java)
            intent.putExtra("modulo","3");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }


        //Nivel 5
        if(event.x >= (anchonivel*3)-30 && event.x <= ((anchonivel*3)-50+268) && event.y >= (altonivel + ((altonivel1*2)) + 100) && event.y <= (altonivel + ((altonivel1*3)) + 250) && dbBoolean[4]){
            clickSound?.start()
            val intent = Intent(context, Arrastrar_Inter::class.java)
            intent.putExtra("modulo","3");
            context.startActivity(intent)
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

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clickSound?.release()
        clickSound = null
    }

}