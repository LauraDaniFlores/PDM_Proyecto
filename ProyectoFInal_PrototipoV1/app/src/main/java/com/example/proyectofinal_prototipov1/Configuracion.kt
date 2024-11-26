package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
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

class Configuracion : View {
    //Imagen de fondo
    private var config: Drawable? = null
    private var casa: Drawable? = null
    private var musica: Drawable? = null
    private var musicano: Drawable? = null

    private var pausar: Drawable? = null
    private var play: Drawable? = null

    private var puntaje: Drawable? = null
    private var tiempo: Drawable? = null


    //Rectangulos
    private val cuadrado = Paint()

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()

    //Tiempo
    private var time = 0
    private var timeStop = false

    //Puntaje
    private var score = 0


    //App
    private var casaBol: Boolean = false
    private var pausarBol: Boolean = false
    private var musicString: String = ""
    private var puntajetiempo: Boolean = false

    //Para cambiar las imágenes
    private var desplegar = false
    private var pausaroplay = true
    private var sonidoono = true


    //Audio
    private var music: MediaPlayer? = null
    private var maxVolume = 40f


    constructor(context: Context?): super(context){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        inicializa()
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.Configuracion)
        casaBol = a.getString(R.styleable.Configuracion_casa).toBoolean()
        pausarBol = a.getString(R.styleable.Configuracion_pausar).toBoolean()
        puntajetiempo = a.getString(R.styleable.Configuracion_tiempopuntaje).toBoolean()
        musicString = a.getString(R.styleable.Configuracion_musica).toString()
        music = MediaPlayer.create(context, resources.getIdentifier(musicString, "raw", context?.getPackageName()))
//        music = MediaPlayer.create(context, R.raw.jojisantuary)
        music?.start()
        music?.setLooping(true);

//        music?.setVolume(30f,30f)

        val hilo1: Thread = object : Thread() {
            @Synchronized
            override  fun run(){
                while(true){
                    try{
                        if(!timeStop) {
                            sleep(1000)
                            time++
                        }
                    }catch (e: InterruptedException){}
                }
            }
        }
        hilo1.start()
    }
    private fun inicializa() {
        textPaint.isAntiAlias = true
        textPaint.textSize = 50f
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.color = Color.BLACK
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.argb(0, 255, 255, 255));
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        val margen = 50
        val margen1 = 25
        config!!.draw(canvas)

        var auxmin = 0
        var auxseg = 0
        var auxminstr = ""
        var auxsegstr = ""
        config!!.draw(canvas)
        if(puntajetiempo!!){
            tiempo!!.draw(canvas)
            puntaje!!.draw(canvas)
//            canvas.drawText(time.toString(), (margen*2) + 75f, margen1 + 50f, textPaint)
            if(time > 60){
                auxseg = time%60
                auxmin = time-auxseg
                auxmin = auxmin/60
                if(auxmin >= 10){
                    auxminstr = auxmin.toString()
                }else{
                    auxminstr = "0"+auxmin.toString()
                }
            }else{
                auxseg = time
                auxminstr = "00"
            }
            if(auxseg >= 10){
                auxsegstr = auxseg.toString()
            }else{
                auxsegstr = "0"+auxseg.toString()
            }
            canvas.drawText(auxminstr+":"+auxsegstr, (margen*2) + 75f, margen1 + 50f, textPaint)
            canvas.drawText(score.toString(), (margen*2) + 75f, (margen1*2) + 50f + 75 + 15, textPaint)
        }

        if(desplegar){
            if(casaBol){
                casa!!.draw(canvas)
            }
            if(pausarBol){
                if(pausaroplay){
                    pausar!!.draw(canvas)
                }else{
                    play!!.draw(canvas)
                }
            }
            if(sonidoono){
                musica!!.draw(canvas)
            }else{
                musicano!!.draw(canvas)
            }
        }

//        canvas.drawText(pausaroplay.toString(),30f, 30f,textPaint)

        tiempo!!.setBounds(margen, margen1, margen + 75, margen1 + 75)

        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        val cuadrado = 125
        val margen = 50
        val margen1 = 25
        config = AppCompatResources.getDrawable(getContext(), R.drawable.configuraciones)
        config!!.setBounds( ancho.toInt() - cuadrado - margen, margen, ancho.toInt()-margen, margen + cuadrado)
        musica = AppCompatResources.getDrawable(getContext(), R.drawable.sonido)
        musica!!.setBounds( ancho.toInt() - (cuadrado*2) - (margen*2), margen, ancho.toInt()-(margen*2) - cuadrado, margen + cuadrado)
        musicano = AppCompatResources.getDrawable(getContext(), R.drawable.sonidosin)
        musicano!!.setBounds( ancho.toInt() - (cuadrado*2) - (margen*2), margen, ancho.toInt()-(margen*2) - cuadrado, margen + cuadrado)
        casa = AppCompatResources.getDrawable(getContext(), R.drawable.casamenu)
        casa!!.setBounds( ancho.toInt() - (cuadrado*3) - (margen*3), margen, ancho.toInt()-(margen*3) - (cuadrado*2), margen + cuadrado)
        pausar = AppCompatResources.getDrawable(getContext(), R.drawable.pausar)
        pausar!!.setBounds(ancho.toInt() - (cuadrado * 4) - (margen * 4), margen, ancho.toInt() - (margen * 4) - (cuadrado * 3), margen + cuadrado)
        play = AppCompatResources.getDrawable(getContext(), R.drawable.play)
        play!!.setBounds(ancho.toInt() - (cuadrado * 4) - (margen * 4), margen, ancho.toInt() - (margen * 4) - (cuadrado * 3), margen + cuadrado)

        tiempo = AppCompatResources.getDrawable(getContext(), R.drawable.time)
        tiempo!!.setBounds(margen, margen1, margen + 75, margen1 + 75)
        puntaje = AppCompatResources.getDrawable(getContext(), R.drawable.star)
        puntaje!!.setBounds(margen, (margen1*2) + 75, margen + 75, (margen1*2) + (75*2))

    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        val cuadrado = 125
        val margen = 50



        if (event.actionMasked == MotionEvent.ACTION_DOWN || event.actionMasked == MotionEvent.ACTION_POINTER_DOWN) {
            if (event.y >= margen && event.y <= margen + cuadrado) {
                if (event.x >= ancho.toInt() - cuadrado - margen && event.x <= ancho.toInt() - margen) {
                    if (desplegar) {
                        desplegar = false
                    } else {
                        desplegar = true
                    }
                }
                if (desplegar) {
                    if (event.x >= ancho.toInt() - (cuadrado * 3) - (margen * 3) && event.x <= ancho.toInt() - (margen * 3) - (cuadrado*2)) {
                        music?.stop()
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                    }
                    if(event.x >= ancho.toInt() - (cuadrado * 4) - (margen * 4) && event.x <= ancho.toInt() - (margen * 4) - (cuadrado * 3)){
                        if (pausaroplay) {
                            pausaroplay = false
                        } else {
                            pausaroplay = true
                        }
                    }
                    if(event.x >= ancho.toInt() - (cuadrado * 2) - (margen * 2) && event.x <= ancho.toInt() - (margen * 2) - (cuadrado * 1)){
                        if (sonidoono) {
                            sonidoono = false
                            music?.pause()
                        } else {
                            sonidoono = true
                            music?.start()
                        }
                    }

                }
            }
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
    fun pausaJuego() : Boolean{
        return pausarBol
    }
    fun detenerMusica(){
        music?.stop()
        music?.release()
    }

    fun actuaizarPuntaje(puntaje: Int) {
        score = puntaje
    }
    fun detenerTiempo(){
        timeStop = true
    }
    fun gettime() : Int{
        return time
    }
    fun setMusica(musica: String){
        music?.stop()
        musicString = musica
        music = MediaPlayer.create(context, resources.getIdentifier(musicString, "raw", context?.getPackageName()))
        music?.start()
    }
}