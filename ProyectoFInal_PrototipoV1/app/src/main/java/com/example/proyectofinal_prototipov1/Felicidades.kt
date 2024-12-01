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

class Felicidades: View {
    private var fondo: Drawable? = null

    private var tiempo = 0
    private var puntaje = 0
    private var nivel = 0
    private var modulo = 0
    private val text = Paint()
    private val text1 = Paint()
    private var azul = 0
    private var musicSuccess: MediaPlayer? = null
    //SQLite
    var db: DBSQLite = DBSQLite(context)



    fun setVariables(time: Int, score: Int, nive: Int, modul: Int){
        tiempo = time
        puntaje = score
        nivel = nive
        modulo = modul
    }
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

        text1.isAntiAlias = true
        text1.textSize = 60f
        text1.color = azul
        text1.textAlign = Paint.Align.CENTER
        text1.typeface = customTypeface
        musicSuccess = MediaPlayer.create(context, R.raw.success)
        musicSuccess?.start()
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        fondo!!.draw(canvas)
        canvas.drawText(nivel.toString(), 880f, 1350f, text1)
        canvas.drawText(modulo.toString(), 880f, 1480f, text1)
        canvas.drawText(puntaje.toString(), 880f, 2023f, text)


        var auxmin = 0
        var auxseg = 0
        var auxminstr = "00"
        var auxsegstr = "00"
        if(tiempo > 60){
            auxseg = tiempo%60
            auxmin = tiempo-auxseg
            auxmin = auxmin/60
            if(auxmin >= 10){
                auxminstr = auxmin.toString()
            }else{
                auxminstr = "0"+auxmin.toString()
            }
        }else{
            auxseg = tiempo
            auxminstr = "00"
        }
        if(auxseg >= 10){
            auxsegstr = auxseg.toString()
        }else{
            auxsegstr = "0"+auxseg.toString()
        }
        canvas.drawText(auxminstr+":"+auxsegstr, 880f, 1820f, text)

        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toInt()
        val ancho = measuredWidth.toInt()
        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.felicidades)
        fondo!!.setBounds(0, 0, ancho, alto)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        if (event.actionMasked == MotionEvent.ACTION_DOWN || event.actionMasked == MotionEvent.ACTION_POINTER_DOWN) {
            if(x >= 0 && x <= ancho && y >= 0 && y <= alto){
                var intent = Intent(context, ModulosIntermedio::class.java)
                if(modulo != 4){
                    if(nivel < 5){
                       intent.putExtra("tiponivel", (modulo-1).toString())
                    }else{
                        if(modulo == 6){
                            if(!db.primeraVezPasado(modulo, nivel)) {
                                intent = Intent(context, Maravillas_Inter::class.java)
                            }else{
                                intent.putExtra("tiponivel", (modulo-1).toString())
                            }
                        }else{
                            if(!db.primeraVezPasado(modulo, nivel)){
                                intent = Intent(context, Maravillas_Inter::class.java)
//                                context.startActivity(i)
                            }else{
                                intent.putExtra("tiponivel", modulo.toString())
                            }
                        }
                    }
                }else{
                    if(nivel < 10){
                        intent.putExtra("tiponivel", (modulo-1).toString())
                    }else{
                        if(!db.primeraVezPasado(modulo, nivel)) {
                            intent = Intent(context, Maravillas_Inter::class.java)
//                            context.startActivity(i)
                        }else{
                            intent.putExtra("tiponivel", modulo.toString())
                        }
                    }
                }
                context.startActivity(intent)
            }
        }
        invalidate()
        return super.onTouchEvent(event)
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
    fun hacerIntent(){
        val intent = Intent(context, Modulos::class.java)
        context.startActivity(intent)
    }
}