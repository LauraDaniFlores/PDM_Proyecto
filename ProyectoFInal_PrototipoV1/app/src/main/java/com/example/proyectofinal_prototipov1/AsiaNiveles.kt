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

class AsiaNiveles : View {
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
            dbBoolean[i-1] = db.nivelDesbloqueado(6, i)
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

        canvas.drawText("Asia", xPos, 450f, textNivel)

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
        fondo!!.setBounds(0, 0, ancho.toInt(), alto.toInt())

        // Mostrar nivel desbloquedo o bloqueado
        if(dbBoolean[0]) {
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.latern1)
        }else{
            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado)
        }
        nivel1!!.setBounds(anchonivel*3, (altonivel - (altonivel1) - 100), anchonivel*3+175, (altonivel - (altonivel1) - 100+240))

        if(dbBoolean[1]) {
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.latern2)
        }else{
            nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado)
        }
        nivel2!!.setBounds(anchonivel+20, (altonivel - (altonivel1*2)+30), anchonivel+20+175,  (altonivel - (altonivel1*2)+30)+240)

        if(dbBoolean[2]) {
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.latern3)
        }else{
            nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado)
        }
        nivel3!!.setBounds(60, (altonivel-100), (60+175), (altonivel-100 + 240))

        if(dbBoolean[3]) {
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.latern4)
        }else{
            nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado)
        }
        nivel4!!.setBounds(anchonivel*2-100, ((alto-(altonivel1*3)).toInt()), (anchonivel*2-100+175),
            ((alto-(altonivel1*3)) + 240).toInt()
        )

        if(dbBoolean[4]) {
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.latern5)
        }else{
            nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado)
        }
        nivel5!!.setBounds((anchonivel*3)+80, ((alto-(altonivel1*3)+40).toInt()), (anchonivel*3)+80+175,
            ((alto-(altonivel1*3)+40+240).toInt())
        )

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4)

        //Nivel 1
        if(event.x >= anchonivel*3 && event.x <= anchonivel*3+175 && event.y >= (altonivel - (altonivel1) - 100) && event.y <= (altonivel - (altonivel1) - 100)+240 && dbBoolean[0]){
            clickSound?.start()
            val intent = Intent(context, Cards_Inter::class.java)
            intent.putExtra("modulo", "7")
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

        //Nivel 2
        if(event.x >= anchonivel+20 && event.x <= anchonivel+20+175 && event.y >= (altonivel - (altonivel1*2)+30) && event.y <= (altonivel - (altonivel1*2)+30)+240 && dbBoolean[1]){
            clickSound?.start()
            val intent = Intent(context, Memorama_Inter::class.java)
            intent.putExtra("modulo", "7")
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

        //Nivel 3
        if(event.x >= 60 && event.x <= 235 && event.y >= (altonivel-100) && event.y <= (altonivel-100 + 240) && dbBoolean[2]){
            clickSound?.start()
            val intent = Intent(context, EscogerInterm::class.java)
            intent.putExtra("modulo", "7");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

        //Nivel 4
        if(event.x >= anchonivel*2-100 && event.x <= anchonivel*2+75 && event.y >= ((alto-(altonivel1*3)).toInt()) && event.y <= ((alto-(altonivel1*3)).toInt()) +240 && dbBoolean[3]){
            clickSound?.start()
            val intent = Intent(context, Peguntados_Inter::class.java)
            intent.putExtra("modulo","7");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }

        //Nivel 5
        if(event.x >= (anchonivel*3)+80 && event.x <= (anchonivel*3)+80+175 && event.y >= ((alto-(altonivel1*3)+40).toInt()) && event.y <= ((alto-(altonivel1*3)+40).toInt())+240 && dbBoolean[4]){
            clickSound?.start()
            val intent = Intent(context, Arrastrar_Inter::class.java)
            intent.putExtra("modulo","7");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
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