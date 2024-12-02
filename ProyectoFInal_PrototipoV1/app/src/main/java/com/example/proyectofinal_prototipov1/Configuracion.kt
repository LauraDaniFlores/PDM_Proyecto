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

class Configuracion : View {
    //Imágenes de botones
    private var config: Drawable? = null
    private var casa: Drawable? = null
    private var musica: Drawable? = null
    private var musicano: Drawable? = null

    private var pausar: Drawable? = null
    private var play: Drawable? = null

    private var puntaje: Drawable? = null
    private var tiempo: Drawable? = null

    //Listener
    var listenertime: OnChangeTimeListener? = null
    fun setOnChangeTimeListener(l: OnChangeTimeListener){
        listenertime = l
    }


    //Rectángulos
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
    //Sonido
    private var clickSound: MediaPlayer? = null


    //Constructores
    constructor(context: Context?): super(context){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        inicializa()
        //Tomar valores del app
        val a = getContext().obtainStyledAttributes(attrs, R.styleable.Configuracion)
        casaBol = a.getString(R.styleable.Configuracion_casa).toBoolean()
        pausarBol = a.getString(R.styleable.Configuracion_pausar).toBoolean()
        puntajetiempo = a.getString(R.styleable.Configuracion_tiempopuntaje).toBoolean()
        musicString = a.getString(R.styleable.Configuracion_musica).toString()

        //Inicializar el nombre de la música
        music = MediaPlayer.create(context, resources.getIdentifier(musicString, "raw", context?.getPackageName()))
//        music = MediaPlayer.create(context, R.raw.jojisantuary)
//        music?.start()
        //Reproducir la música indefinidamente
        music?.setLooping(true)

        //Contar el tiempo
        val hilo1: Thread = object : Thread() {
            @Synchronized
            override  fun run(){
                while(true){
                    try{
                        if(!timeStop) {
                            sleep(1000)
                            time++
//                            listenertime!!.OnChangeTime(secondMusic())
                        }
                    }catch (e: InterruptedException){}
                }
            }
        }
        hilo1.start()
    }
    private fun inicializa() {
        //Inicializar el texto del tiempo y puntaje
        textPaint.isAntiAlias = true
        textPaint.textSize = 50f
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.color = Color.BLACK

        // Inicializar el MediaPlayer con el sonido de tocar botones
        clickSound = MediaPlayer.create(context, R.raw.efectobtn)

    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.argb(0, 255, 255, 255));
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        val margen = 50
        val margen1 = 25

        //Pintar la imágenes dependiendo del app
        config!!.draw(canvas)

        var auxmin = 0
        var auxseg = 0
        var auxminstr = ""
        var auxsegstr = ""
        config!!.draw(canvas)

        //Mostrar en la pantalla el tiempo en minutos y segundos
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

        //Tiempo imagen
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

        //Inicialización de todas las imágenes
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
                //Touch en botón de configuraciones, para desplegar todos los elementos
                if (event.x >= ancho.toInt() - cuadrado - margen && event.x <= ancho.toInt() - margen) {
                    clickSound?.seekTo(0)
                    clickSound?.start()
                    if (desplegar) {
                        desplegar = false
                    } else {
                        desplegar = true
                    }
                }

                //Si los otros íconos se ven
                if (desplegar) {
                    //Touch en la casa, se para la música, se agrega sonido y se hace una actividad a la pantalla principal
                    if (event.x >= ancho.toInt() - (cuadrado * 3) - (margen * 3) && event.x <= ancho.toInt() - (margen * 3) - (cuadrado*2)) {
                        music?.stop()
                        clickSound?.seekTo(0)
                        clickSound?.start()
                        val intent = Intent(context, MainActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        context.startActivity(intent)
                    }
                    //Touch en pausar (No se usa)
                    if(event.x >= ancho.toInt() - (cuadrado * 4) - (margen * 4) && event.x <= ancho.toInt() - (margen * 4) - (cuadrado * 3)){
                        if (pausaroplay) {
                            pausaroplay = false
                        } else {
                            pausaroplay = true
                        }
                    }
                    //Touch en la música, se pausa o se despausa
                    if(event.x >= ancho.toInt() - (cuadrado * 2) - (margen * 2) && event.x <= ancho.toInt() - (margen * 2) - (cuadrado * 1)){
                        clickSound?.seekTo(0)
                        clickSound?.start()
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
    //Pausar la música
    fun detenerMusica(){
        music?.pause()
//        music?.release()
    }
    //Despausar o empezar música
    fun startMusica(){
        music?.start()
    }
    fun setseekmusic(num: Int){
        music?.stop()
        music?.seekTo(num)
        music?.start()
    }

    //Areglar el volumen más bajo
    fun setVolume(){
        music?.setVolume(0.3f, 0.3f)
    }
    fun secondMusic(): Int?{
        var segundos = music?.currentPosition
        return segundos
    }
    //Actualizar la variable puntaje
    fun actuaizarPuntaje(puntaje: Int) {
        score = puntaje
    }
    fun detenerTiempo(){
        timeStop = true
    }
    fun gettime() : Int{
        return time
    }
    //Agregar el nombre de la música correspondiente
    fun setMusica(musica: String){
        music?.stop()
        musicString = musica
        music = MediaPlayer.create(context, resources.getIdentifier(musicString, "raw", context?.getPackageName()))
        music?.seekTo(0)
        music?.start()
    }
}