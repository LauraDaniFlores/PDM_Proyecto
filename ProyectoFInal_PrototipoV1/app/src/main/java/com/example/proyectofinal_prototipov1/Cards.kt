package com.example.proyectofinal_prototipov1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import java.util.Date


class Cards : View {

    val pText = TextPaint()
    var pTextPequeno: TextPaint = TextPaint()
    var PtextoRespuesta: TextPaint = TextPaint()
    val pRelleno = Paint()
    val cuadro = Paint()
    var siguiente: Drawable? = null
    var nextScreen: Boolean = false
    var index:Int = 0

    //puntaje y tiempo
    private var puntaje = 0
    private var tiempo = 0

    //Listener
    var listener: OnChangeScoreListener? = null
    fun setListenerScore(l: OnChangeScoreListener){
        listener = l
    }

    var listenertime: OnTimeStopListener? = null
    fun setOnTimeStotListener(l: OnTimeStopListener){
        listenertime = l
    }

    //Base de datos
    var db: DBSQLite = DBSQLite(context)

    //Sonido
    private var clickSound: MediaPlayer? = null
    private var musicError: MediaPlayer? = null


    var respuesta:Boolean = false
    var acabo: Boolean = false
//
    private var original = emptyArray<Array<String>>()

    private var tema:String = "Océanos"
    private var preguntas = emptyArray<String>()
    private var respuestaText =  emptyArray<String>()

    constructor(context: Context?) : super(context){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super (context, attrs){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?, detSyleAttr: Int) :
            super (context, attrs, detSyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    private fun inicializa() {
        // Inicializar el MediaPlayer con el sonido deseado
        clickSound = MediaPlayer.create(context, R.raw.efectobtn)
        musicError = MediaPlayer.create(context, R.raw.error)

        // Asignamos los colores
        pRelleno.color = ResourcesCompat.getColor(resources, R.color.lightblue, null)

        pRelleno.style = Paint.Style.FILL
        cuadro.style = Paint.Style.STROKE
        cuadro.strokeWidth = 2f

        // Tema
        pText.style = Paint.Style.FILL
        pText.color = Color.BLACK
        pText.textSize = 70f
        pText.textAlign = Paint.Align.CENTER

        // Pregunta y respuesta
        pTextPequeno.style = Paint.Style.FILL
        pTextPequeno.color = Color.BLACK
        pTextPequeno.textSize = 60f
        pTextPequeno.color = ResourcesCompat.getColor(resources, R.color.lightblue, null)

        PtextoRespuesta.style = Paint.Style.FILL
        PtextoRespuesta.color = Color.BLACK
        PtextoRespuesta.textSize = 60f

    }

    override fun onDraw(canvas: Canvas){
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        // Cuadrados
//        canvas.drawRoundRect(60f, 300f, ancho-60f, alto-150f, 20f, 20f, cuadro)
        canvas.drawRoundRect(60f, 300f, ancho-60f, 460f, 20f, 20f, pRelleno)
        canvas.drawRoundRect(60f, 500f, ancho-60f, (alto/2), 20f, 20f, cuadro)
//        canvas.drawRoundRect(60f, (alto/2)+300f, ancho-60f, alto-200, 20f, 20f, cuadro)

        siguiente!!.draw(canvas)


        // Si el juego no ha acabado
        if(!acabo &&  original.size != 0){
            canvas.drawText(tema, (canvas.width / 2).toFloat(), 400f, pText)

//            canvas.drawText(preguntas[index], 90f, 540f, pText)
            val mTextLayout = StaticLayout(
                original[index].get(0),
                pTextPequeno,
                canvas.width-150,
                Layout.Alignment.ALIGN_NORMAL,
                1.0f,
                0.0f,
                false
            )

            canvas.save()

            // calculate x and y position where your text will be placed
            var textX = 100f
            var textY = 530f

            canvas.translate(textX, textY)
            mTextLayout.draw(canvas)
            canvas.restore()
        }

        if(respuesta && original.size != 0){
            val mTextLayout = StaticLayout(
                original[index].get(1),
                pTextPequeno,
                canvas.width-200,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                false
            )

            canvas.save()

            // calculate x and y position where your text will be placed
            var textX = 100f
            var textY = alto/2 + 600f

            canvas.translate(textX, textY)
            mTextLayout.draw(canvas)
            canvas.restore()
        }

    }

    override fun onSizeChanged (w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //obtenemos las dimensiones del control
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        siguiente = AppCompatResources.getDrawable(getContext(), R.drawable.siguiente1)
        siguiente!!.setBounds((ancho-240).toInt(), (alto/2).toInt()+50, (ancho).toInt()-60, (alto/2).toInt()+250)
    }

    override fun onTouchEvent(event: MotionEvent) : Boolean {
        //obtenemos las dimensiones del control
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        if(event.x >= (ancho-240).toInt() && event.x <= (ancho).toInt()-60 && event.y >= (alto/2).toInt()-50 && event.y <= (alto/2).toInt()+250 && !acabo ){
            if(respuesta){
                clickSound?.seekTo(0)
                clickSound?.start() // Reproduce el sonido
                nextScreen = true
                respuesta = false
                index ++
                puntaje += 20
                listener!!.SetonScoreChange(
                    puntaje
                )
            }else if(!respuesta){
                musicError?.seekTo(0)
                musicError?.start()
            }
        }else if(event.x >= 60f && event.x <= ancho-60f){
            if(event.y >= (alto/2)+300f && event.y <= alto-200){
                respuesta = !respuesta
            }
        }


        if(index >= original.size-1){
            acabo = true
            respuesta = false
            listenertime!!.OnTimeStop(true)
        }

        this.invalidate()
        return super.onTouchEvent(event)
    }


    fun setArray(array: Array<Array<String>>, tema: Int){
        when (tema){
            1 -> this.tema = "CONTINENTES"
            2 -> this.tema = "OCÉANOS"
            3 -> this.tema = "ÁRTICA y ANTÁRTICA"
            4 -> this.tema = "MÉXICO ESTADOS"
            5 -> this.tema = "MÉXICO CAPITALES"
            6 -> this.tema = "AMÉRICA"
            7 -> this.tema = "ASIA"
        }

        original = array
        original.shuffle()
//        for(i in 0..original.size){
//            preguntas[i] = original.get(i).get(0)
//            respuestaText[i] = original.get(i).get(1)
//        }

    }


    override fun onMeasure(widthMeasureSpect: Int, heightMeasureSpect: Int) {
        val ancho = calcularAncho(widthMeasureSpect)
        val alto = calcularAlto(heightMeasureSpect)

        setMeasuredDimension(ancho, alto)
    }

    private fun calcularAlto (heightMeasureSpect: Int): Int{
        var res = 100
        val modo = MeasureSpec.getMode(heightMeasureSpect)
        val limite = MeasureSpec.getSize(heightMeasureSpect)
        if ( modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY){
            res = limite
        }
        return res
    }

    private fun calcularAncho ( widthMeasureSpect: Int) : Int {
        var res = 100
        val modo = MeasureSpec.getMode(widthMeasureSpect)
        val limite = MeasureSpec.getSize(widthMeasureSpect)
        if ( modo == MeasureSpec.AT_MOST || modo == MeasureSpec.EXACTLY){
            res = limite
        }
        return res
    }

    fun setTiempo(tiem: Int){
        tiempo = tiem
    }
    fun insertardb(modulo: Int){
        if(db.nivelDesbloqueado(1, 2)){
            db.guardarRegistro(modulo, 1, tiempo, puntaje, Date(), false)
        }else{
            db.guardarRegistro(modulo, 1, tiempo, puntaje, Date(), true)
        }
        val intent = Intent(context, FelicidadesInter::class.java)

        intent.putExtra("nivel", "1")
        intent.putExtra("modulo", modulo.toString())
        intent.putExtra("puntaje", puntaje.toString())
        intent.putExtra("tiempo", tiempo.toString())

        context.startActivity(intent)

    }

}