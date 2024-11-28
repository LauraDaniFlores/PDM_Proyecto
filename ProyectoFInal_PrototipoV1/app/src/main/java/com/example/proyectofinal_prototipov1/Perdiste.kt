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
import androidx.core.content.res.ResourcesCompat

class Perdiste: View {
    private var fondo: Drawable? = null

    private var nivel = 0
    private var modulo = 0
    private val text = Paint()
    private val text1 = Paint()
    private val pboton = Paint()
    private var azul = 0
    private var musicSuccess: MediaPlayer? = null

    constructor(context: Context?): super(context){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        inicializa()
    }
    private fun inicializa() {
        azul = ResourcesCompat.getColor(resources, R.color.azulagua, null)
        text.isAntiAlias = true
        text.textSize = 60f
        text.color = Color.WHITE
        text.textAlign = Paint.Align.CENTER
        val customTypeface = resources.getFont(R.font.courier)
        text.typeface = customTypeface

        pboton.color = azul
        pboton.style = Paint.Style.FILL

        text1.isAntiAlias = true
        text1.textSize = 60f
        text1.color = azul
        text1.textAlign = Paint.Align.CENTER
        text1.typeface = customTypeface
        musicSuccess = MediaPlayer.create(context, R.raw.perder)
        musicSuccess?.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toInt()
        val ancho = measuredWidth.toInt()
        fondo!!.draw(canvas)
        canvas.drawRoundRect(100F, (alto/4*3+50).toFloat(), (ancho-100).toFloat(),
            (alto/4*3+200).toFloat(), 30f, 30f, pboton)
        canvas.drawRoundRect(100F, (alto/4*3+250).toFloat(), (ancho-100).toFloat(),
            (alto/4*3+400).toFloat(), 30f, 30f, pboton)

        canvas.drawText("Volver a intentar", (ancho/2).toFloat(), ((alto/4*3+150).toFloat()), text)
        canvas.drawText("Menú", (ancho/2).toFloat(), ((alto/4*3+350).toFloat()), text)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toInt()
        val ancho = measuredWidth.toInt()
        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.derrota)
        fondo!!.setBounds(0, 0, ancho, alto)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        if(event.x >= 100f && event.x <= ancho-100 ){
            if(event.y >= (alto/4*3+50) && event.y <= (alto/4*3+200)){
                var intent: Intent? = null
                when(nivel){
                    2 ->  intent = Intent(context, Memorama_Inter::class.java)
                    3 ->  intent = Intent(context, EscogerInterm::class.java)
                    4 ->  intent = Intent(context, Peguntados_Inter::class.java)
                    5 ->  intent = Intent(context, Arrastrar_Inter::class.java)
                }
                if (intent != null) {
                    intent.putExtra("modulo", (modulo).toString())
                }
                context.startActivity(intent)
            }else if(event.y >= (alto/4*3+250) && event.y <= (alto/4*3+400)){
                val intent = Intent(context, ModulosIntermedio::class.java)
                if(modulo != 4){
                    if(nivel < 5){
                        intent.putExtra("tiponivel", (modulo-1).toString())
                    }else{
                        intent.putExtra("tiponivel", modulo.toString())
                    }
                }else{
                    if(nivel < 10){
                        intent.putExtra("tiponivel", (modulo-1).toString())
                    }else{
                        intent.putExtra("tiponivel", modulo.toString())
                    }
                }
                context.startActivity(intent)
            }
        }

        invalidate()
        return super.onTouchEvent(event)
    }

    fun setVariables(nive: Int, modul: Int){
        nivel = nive
        modulo = modul
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