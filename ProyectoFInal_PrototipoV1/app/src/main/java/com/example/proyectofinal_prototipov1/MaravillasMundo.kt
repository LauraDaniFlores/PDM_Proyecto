package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat


class MaravillasMundo: View {
    val pText = TextPaint()
    val pTextMas = TextPaint()
    var pTextPequeno: TextPaint = TextPaint()
    val pRelleno = Paint()
    val cuadro = Paint()
    var fondo: Drawable? = null

    // Imágenes
    var ChinChenItza: Drawable? = null
    var Coliseo: Drawable? = null
    var CristoRedendor: Drawable? = null
    var MurallaChina: Drawable? = null
    var TajMajal: Drawable? = null
    var Hordania: Drawable? = null
    var MachuPichu: Drawable? = null

    // Vectores
    var light: Drawable? = null
    var cancel: Drawable? = null

    var mostrar:Boolean = false
    var maravilla: Int? = null

    // Texto
    var maravillasText = arrayOf("ChinChén Itzá", "Coliseo Romano", "Cristo Redendor", "Muralla China", "Taj Mahal", "Petra", "Machu Pichu")
    var paises = arrayOf("México", "Italia", "Brasil", "China", "India", "Jordania", "Perú")
    var datoCurioso = arrayOf("La pirámide de Kukulkan tiene 365 escalones, uno por cada día del año.", "", "", "", "", "", "")

    //SQLite
    var db: DBSQLite = DBSQLite(context)
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
        val customTypeface = resources.getFont(R.font.pact)

        pText.textSize = 40f
        pText.style = Paint.Style.FILL
        pTextMas.textSize = 100f
        pTextMas.style = Paint.Style.FILL
        pTextMas.textAlign = Paint.Align.CENTER
        pTextMas.color = Color.WHITE

        pTextPequeno.style = Paint.Style.FILL
        pTextPequeno.color = Color.WHITE
        pTextPequeno.textSize = 40f
        pTextPequeno.typeface = customTypeface

        cuadro.style = Paint.Style.STROKE
        cuadro.color = Color.WHITE
        cuadro.strokeWidth = 20f
        cuadro.color = ResourcesCompat.getColor(resources, R.color.ic_launcher_background, null)

        pRelleno.strokeWidth = 40f
        pRelleno.color = ResourcesCompat.getColor(resources, R.color.lightblue, null)
        pTextMas.typeface = customTypeface
//        val plain = Typeface.createFromAsset(context.assets, "kumbhsans.ttf")
//        pText.setTypeface(plain)

        val typeface = getResources().getFont(R.font.kumbhsans_extrabold)
        pText.setTypeface(typeface)
//        comprobarBaseDeDatos()
    }

    fun comprobarBaseDeDatos(){
        for (i in 1..7){
            desbloqueados[i-1] = db.maravillaDesbloqueada(i)
        }
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

        var xtext = (ancho/4-160)
        var ytext = (alto/4 -100)
        for(i in 0..6){
            if(i == 3){
                xtext = (ancho/2+210)
                ytext = (alto/4 -100)
            }else if(i == 6){
                xtext = 440f
            }else if(i == 5){
                xtext = (ancho/2+260)
            }
            canvas.drawText(maravillasText[i], xtext, ytext, pText)
            ytext += (alto/4 -100)
        }

        var xPos = ancho/2

        if(mostrar){
            canvas.drawRoundRect(ancho/4-100, alto/4-200, (ancho/4)*3+100, (alto/4)*3, 50f, 50f, pRelleno)
            canvas.drawRoundRect(ancho/4-100, alto/4-200, (ancho/4)*3+100, (alto/4)*3, 50f, 50f, cuadro)

            canvas.drawText("Dato Curioso", xPos, alto/4, pTextMas)
            light!!.draw(canvas)
            cancel!!.draw(canvas)

            val mTextLayout = StaticLayout(
                datoCurioso.get(0),
                pTextPequeno,
                ((ancho/4)*3-100).toInt(),
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                false
            )
            canvas.save()

            // calculate x and y position where your text will be placed
            var textX = ancho/4-100
            var textY = alto/4+200

            canvas.translate(textX, textY)
            mTextLayout.draw(canvas)
            canvas.restore()
        }
        invalidate()

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

        light = AppCompatResources.getDrawable(getContext(), R.drawable.baseline_lightbulb_24)
        light!!.setBounds((ancho/2-80).toInt(), ((alto/4)*3-200).toInt(), (ancho/2+80).toInt(), ((alto/4)*3-40).toInt())

        cancel = AppCompatResources.getDrawable(getContext(), R.drawable.baseline_cancel_24)
        cancel!!.setBounds(((ancho/4)*3).toInt(), (alto/4-200).toInt(), ((ancho/4)*3+100).toInt(), ((alto/4-100)).toInt())

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

            if(event.x in xtext..xtextE && event.y >= ytext && event.y <= ytextE && !mostrar && desbloqueados[i]) {
//                Toast.makeText(context, "Maravilla " + i+1,
//                    Toast.LENGTH_LONG).show();
                maravilla = i
                mostrar = true
            }

            ytext = ytextE.toInt()
            ytextE += (alto/4 -100).toInt()

        }

        if(mostrar && event.x in ((ancho/4)*3)..((ancho/4)*3+100) && event.y in (alto/4-200)..(alto/4-100)){
            mostrar = false
        }

        this.invalidate()
        return super.onTouchEvent(event)
    }

}