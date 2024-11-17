package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat

class Cards : View {

    val pText = TextPaint()
    val pRelleno = Paint()
    val cuadro = Paint()
    var fondo: Drawable? = null
    var siguiente: Drawable? = null
    var nextScreen: Boolean = false
    var index:Int = 0

    var respuesta:Boolean = false
    var acabo: Boolean = false
//
    private var original = emptyArray<String>()
//    private var preguntas = emptyArray<String>()
//    private var respuestaText = emptyArray<String>()

    private var tema:String = "Océanos"
    private var preguntas = arrayOf("Pregunta 1", "Pregunta 2", "Pregunta 3")
    private var respuestaText = arrayOf("Respuesta 1", "Respuesta 2", "Respuesta 3")

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
        // Asignamos los colores
        pRelleno.color = ResourcesCompat.getColor(resources, R.color.lightblue, null)
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

    override fun onDraw(canvas: Canvas){
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

//        fondo!!.draw(canvas)

        pRelleno.style = Paint.Style.FILL
        cuadro.style = Paint.Style.STROKE
        cuadro.strokeWidth = 2f
        canvas.drawRoundRect(60f, 60f, ancho-60f, alto/2+ 400f, 20f, 20f, cuadro)
//        canvas.drawRoundRect(60f, alto/2, ancho-60f, alto/2+400, 20f, 20f, cuadro)
        canvas.drawRoundRect(60f, 60f, ancho-60f, 200f, 20f, 20f, pRelleno)

        // Pregunta
        pText.style = Paint.Style.FILL
        pText.color = Color.BLACK
        pText.textSize = 70f

        if(!acabo){
            canvas.drawText(preguntas[index], 90f, 300f, pText)
            canvas.drawText(tema, ancho/2-180, 140f, pText)
        }



//        var staticLayout = StaticLayout.Builder
//            .obtain("Pregunta", 90, ((alto/2)-100).toInt(), pText, (ancho-90).toInt())
//            .build()
//
//        staticLayout.draw(canvas)

        if(respuesta){
            canvas.drawText(respuestaText[index], 90f, alto/2 +100, pText)
        }

        siguiente!!.draw(canvas)

    }

    override fun onSizeChanged (w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //obtenemos las dimensiones del control
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.map)
        fondo!!.setBounds(40, (alto/2).toInt()-100, (ancho).toInt()-20, (alto-100).toInt())
        fondo!!.setAlpha(100)

        siguiente = AppCompatResources.getDrawable(getContext(), R.drawable.siguiente)
        siguiente!!.setBounds((ancho-250).toInt(), (alto/2).toInt()-350, (ancho).toInt()-50, (alto/2).toInt()-150)
    }

    override fun onTouchEvent(event: MotionEvent) : Boolean {
        //obtenemos las dimensiones del control
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        if(event.x >= (ancho-250).toInt() && event.x <= (ancho).toInt()-50 && event.y >= (alto/2).toInt()-350 && event.y <= (alto/2).toInt()-150){
            nextScreen = true
            respuesta = false
            index ++
        }else if(event.x >= 60f && event.x <= ancho-60f){
            if(event.y >= 60f && event.y <= alto/2 + 400f){
                respuesta = !respuesta
            }
        }

//        if(index > original.size){
//            acabo = true
//            respuesta = false
//        }

        this.invalidate()
        return super.onTouchEvent(event)
    }


//    fun setArray(array: Array<String>){
//        var pos = 0
//        original = array
//        original.shuffle()
//        for(i in 0..3){
//            for (j in 0..1){
//                preguntas[pos] = original.get(i)
//                pos++
//            }
//        }
//    }
}