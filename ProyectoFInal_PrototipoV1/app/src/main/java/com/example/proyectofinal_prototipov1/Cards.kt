package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat


class Cards : View {

    val pText = TextPaint()
    val pTextPequeno = TextPaint()
    val pRelleno = Paint()
    val cuadro = Paint()
    var fondo: Drawable? = null
    var siguiente: Drawable? = null
    var nextScreen: Boolean = false
    var index:Int = 0

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
        // Asignamos los colores
        pRelleno.color = ResourcesCompat.getColor(resources, R.color.lightblue, null)

        pRelleno.style = Paint.Style.FILL
        cuadro.style = Paint.Style.STROKE
        cuadro.strokeWidth = 2f

        // Tema
        pText.style = Paint.Style.FILL
        pText.color = Color.BLACK
        pText.textSize = 70f

        // Pregunta y respuesta
        pTextPequeno.style = Paint.Style.FILL
        pTextPequeno.color = Color.BLACK
        pTextPequeno.textSize = 60f
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

//       fondo!!.draw(canvas)

        // Cuadrados
        canvas.drawRoundRect(60f, 300f, ancho-60f, alto-150f, 20f, 20f, cuadro)
        canvas.drawRoundRect(60f, 300f, ancho-60f, 460f, 20f, 20f, pRelleno)

        // Si el juego no ha acabado
        if(!acabo){

            pText.textAlign = Paint.Align.CENTER
            val xPos = (canvas.width / 2)
            canvas.drawText(tema, xPos.toFloat(), 400f, pText)

//            canvas.drawText(tema, ancho/2-180, 400f, pText)
//            canvas.drawText(preguntas[index], 90f, 540f, pText)
            val mTextLayout = StaticLayout(
                preguntas[index],
                pTextPequeno,
                ancho.toInt()-200,
                Layout.Alignment.ALIGN_CENTER,
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

        if(respuesta){
            val mTextLayout = StaticLayout(
                respuestaText[index],
                pTextPequeno,
                ancho.toInt()-200,
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                false
            )
            canvas.save()

            // calculate x and y position where your text will be placed
            var textX = 100f
            var textY = alto/2 +200

            canvas.translate(textX, textY)
            mTextLayout.draw(canvas)
            canvas.restore()
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
        siguiente!!.setBounds((ancho-250).toInt(), (alto/2).toInt(), (ancho).toInt()-50, (alto/2).toInt()+200)
    }

    override fun onTouchEvent(event: MotionEvent) : Boolean {
        //obtenemos las dimensiones del control
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        if(event.x >= (ancho-250).toInt() && event.x <= (ancho).toInt()-50 && event.y >= (alto/2).toInt() && event.y <= (alto/2).toInt()+200){
            nextScreen = true
            respuesta = false
            index ++
        }else if(event.x >= 60f && event.x <= ancho-60f){
            if(event.y >= 300f && event.y <= alto-150f){
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


    fun setArray(array: Array<Array<String>>, tema: Int){
        when (tema){
            1 -> this.tema = "Continentes"
            2 -> this.tema = "Océanos"
            3 -> this.tema = "Ártica y Antártica"
            4 -> this.tema = "México estados"
            5 -> this.tema = "México capitales"
            6 -> this.tema = "América"
            7 -> this.tema = "Asia"
        }

        var pos = 0
        original = array
        original.shuffle()
        for(i in 0..original.size){
            preguntas[pos] = original.get(i).get(0)
            respuestaText[pos] = original.get(i).get(1)
            pos++
        }

    }

}