package com.example.proyectofinal_prototipov1

import android.app.PendingIntent.getActivity
import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat


class maravillasMundo: View {
    val pText = TextPaint()
    val pRelleno = Paint()
    val cuadro = Paint()
    var fondo: Drawable? = null

    var ChinChenItza: Drawable? = null
    var Coliseo: Drawable? = null
    var CristoRedendor: Drawable? = null
    var MurallaChina: Drawable? = null
    var TajMajal: Drawable? = null
    var Hordania: Drawable? = null
    var MachuPichu: Drawable? = null

    var maravillasText = arrayOf("ChinChén Itzá", "Coliseo Romano", "Cristo Redendor", "Muralla China", "Taj Mahal", "Petra", "Machu Pichu")
    var paises = arrayOf("México", "Italia", "Brasil", "CHina", "India", "Jordania", "Perú")
    var desbloqueados = arrayOf(true, false , false, false, false, false, false)
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
        pText.textSize = 30f
        pText.style = Paint.Style.FILL
//        val plain = Typeface.createFromAsset(context.assets, "kumbhsans.ttf")
//        pText.setTypeface(plain)

        val typeface = getResources().getFont(R.font.kumbhsans_extrabold)
        pText.setTypeface(typeface)
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

        ChinChenItza!!.draw(canvas)

        Coliseo!!.draw(canvas)
        CristoRedendor!!.draw(canvas)
        MurallaChina!!.draw(canvas)
        TajMajal!!.draw(canvas)
        Hordania!!.draw(canvas)
        MachuPichu!!.draw(canvas)

        var xtext = (ancho/4-120)
        var ytext = (alto/4 -100)
        for(i in 0..6){
            if(i == 3){
                xtext = (ancho/2+250)
                ytext = (alto/4 -100)
            }else if(i == 6){
                xtext = 490f
            }
            canvas.drawText(maravillasText[i], xtext, ytext, pText)
            ytext += (alto/4 -100)
        }

    }

    override fun onSizeChanged (w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        //obtenemos las dimensiones del control
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()


        ChinChenItza = AppCompatResources.getDrawable(getContext(), R.drawable.chinchenitza)
        if(!desbloqueados[0]){ ChinChenItza!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        ChinChenItza!!.setBounds(20, 20, (ancho/2 - 50).toInt(), (alto/4 -100).toInt())

        Coliseo = AppCompatResources.getDrawable(getContext(), R.drawable.coliseoromanoo)
        if(!desbloqueados[1]){ Coliseo!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        Coliseo!!.setBounds(20, (alto/4-100).toInt(), (ancho/2-50).toInt(), (alto/2-200).toInt())

        CristoRedendor = AppCompatResources.getDrawable(getContext(), R.drawable.cristoredentor)
        if(!desbloqueados[2]){ CristoRedendor!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        CristoRedendor!!.setBounds(20, ((alto/4)*2 -200).toInt(), (ancho/2 -50).toInt(), ((alto/4)*3 -300).toInt())

        MurallaChina = AppCompatResources.getDrawable(getContext(), R.drawable.granmurallachina)
        if(!desbloqueados[3]){ MurallaChina!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        MurallaChina!!.setBounds((ancho/2 +50).toInt(), 20, (ancho).toInt(), (alto/4 -100).toInt())

        TajMajal = AppCompatResources.getDrawable(getContext(), R.drawable.tajmahal)
        if(!desbloqueados[4]){ TajMajal!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        TajMajal!!.setBounds((ancho/2 +50).toInt(), (alto/4-100).toInt(), (ancho).toInt(), (alto/2-200).toInt())

        Hordania = AppCompatResources.getDrawable(getContext(), R.drawable.petrajordania)
        if(!desbloqueados[5]){ Hordania!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        Hordania!!.setBounds((ancho/2 +50).toInt(),  ((alto/4)*2 -200).toInt(), (ancho).toInt(), ((alto/4)*3 -300).toInt())

        MachuPichu = AppCompatResources.getDrawable(getContext(), R.drawable.machupichu)
        if(!desbloqueados[6]){ MachuPichu!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        MachuPichu!!.setBounds(400, ((alto/4)*3-300).toInt(), (ancho/2+200).toInt(), (alto -400).toInt())


    }

    override fun onTouchEvent(event: MotionEvent) : Boolean {
        //obtenemos las dimensiones del control
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        var xtext = 20f
        var xtextE = (ancho/2 - 50)
        var ytext = 20
        var ytextE =  (alto/4 -100)
        for(i in 0..6){
            if(i == 3){
                xtext = (ancho/2 +50)
                xtextE = ancho
                ytext = 20
                ytextE =  (alto/4 -100)
            }else if(i == 6){
                xtext = 400f
                xtextE = (ancho/2+200)
            }
            if(event.x in xtext..xtextE && event.y >= ytext && event.y <= ytextE){
                Toast.makeText(context, "Maravilla " + i+1,
                    Toast.LENGTH_LONG).show();
            }

            ytext = ytextE.toInt()
            ytextE += (alto/4 -100).toInt()

        }

        this.invalidate()
        return super.onTouchEvent(event)
    }

}