package com.example.proyectofinal_prototipov1

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat

class Trayecto : ScrollView {
    //Imagen de fondo
    private var fondo: Drawable? = null
    private var fondo1: Drawable? = null
    private var fondo2: Drawable? = null
    private var fondo3: Drawable? = null
    private var fondo4: Drawable? = null
    private var fondo5: Drawable? = null
    private var fondo6: Drawable? = null
    private var fondo7: Drawable? = null

    // Imagen para los niveles
    private var nivel1: Drawable? = null
    private var nivel2: Drawable? = null
    private var nivel3: Drawable? = null
    private var nivel4: Drawable? = null
    private var nivel5: Drawable? = null
    private var nivel6: Drawable? = null
    private var nivel7: Drawable? = null

    //Rectangulos
    private val cuadrado = Paint()
    private val circulo = Paint()
    private val circuloSelected = Paint()
    private val radio = 80f

    private var anchoE = 0
    private var altoE = 0

    //Textos
    private val textPaint = Paint()
    private val textNivel = Paint()
    private val textModulo = Paint()

    // Modulo
    var modulo = arrayOf(true, false, false, false, false, false, false)

    //SQLite
    var db: DBSQLite = DBSQLite(context)
    var dbBoolean = arrayOf(arrayOf(true, true, true, true, true),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false),
        arrayOf(false, false, false, false, false)
        )

    constructor(context: Context?): super(context){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        inicializa()

    }
    private fun inicializa() {
        textPaint.isAntiAlias = true
        textPaint.textSize = 60f
        textPaint.color = Color.WHITE
        textNivel.isAntiAlias = true
        textNivel.textSize = 150f
        textNivel.color = Color.BLACK
        textNivel.textAlign = Paint.Align.CENTER

        val customTypeface = resources.getFont(R.font.pact)
        textNivel.typeface = customTypeface

        textModulo.isAntiAlias = true
        textModulo.textSize = 110f
        textModulo.color = Color.BLACK
        textModulo.textAlign = Paint.Align.CENTER
        textModulo.typeface = customTypeface

        val courier = resources.getFont(R.font.courier)
        textPaint.typeface = courier

        cuadrado.style = Paint.Style.FILL
        cuadrado.color = Color.RED

        circulo.style = Paint.Style.FILL
        circulo.color = Color.BLACK

        circuloSelected.style = Paint.Style.FILL
        circuloSelected.color = ResourcesCompat.getColor(resources, R.color.azulagua, null)

//        comprobarBaseDeDatos()
    }

    fun comprobarBaseDeDatos(){
        for (i in 1..7){
            for(j in 1..5)
            dbBoolean[i-1][j-1] = db.nivelDesbloqueado(i, j)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = ancho/5
        var altonivel = (alto/2)
        var altonivel1 = altonivel/4

//        fondo!!.draw(canvas)
        val xPos = (ancho / 2)
        canvas.drawText("Trayecto", xPos, 300f, textNivel)

        var cx = ancho/2 - 300f
        var cy = 450f
        for(i in 0.. 6){
            if(i == 4){
                cx = ancho/2 - 200f
                cy = 650f
            }
            if(modulo[i]){
                canvas.drawCircle(cx, cy, radio, circuloSelected)
            }else{
                canvas.drawCircle(cx, cy, radio, circulo)
            }
            canvas.drawText((i+1).toString(),cx-20, cy+20,textPaint)
            cx += 200f
        }

        canvas.drawText("Continente", xPos, (alto/2 -30), textNivel)

        fondo1!!.draw(canvas)
        nivel1!!.draw(canvas)
        nivel2!!.draw(canvas)
        nivel3!!.draw(canvas)
        nivel4!!.draw(canvas)
        nivel5!!.draw(canvas)

        invalidate()
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var anchonivel = (ancho/5).toInt()
        var altonivel = (alto/2).toInt()
        var altonivel1 = (altonivel/4).toInt()

//        fondo = AppCompatResources.getDrawable(getContext(), R.drawable.asiaback)
//        fondo!!.setBounds(0, 0, ancho.toInt(), 2000)


        if(modulo[0]){
            fondo1 = AppCompatResources.getDrawable(getContext(), R.drawable.continent1)

            nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan1)
            if(dbBoolean[0][0]){ nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan2)
            }else{ nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado) }
            if(dbBoolean[0][1]){ nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan3)
            }else{ nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado) }
            if(dbBoolean[0][2]){ nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan4)
            }else{ nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado) }
            if(dbBoolean[0][3]){ nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.planetan5)
            }else{ nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.planetacandado) }
            anchoE = 200
            altoE = 200
        }else if(modulo[1]){
            fondo1 = AppCompatResources.getDrawable(getContext(), R.drawable.ocean1)
            if(dbBoolean[1][0]) { nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.coraln1)
            }else{ nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado) }
            if(dbBoolean[1][1]) { nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1n2)
            }else{ nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado) }
            if(dbBoolean[1][2]) { nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.coraln3)
            }else{ nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado) }
            if(dbBoolean[1][3]) { nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1n4)
            }else{ nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.coral1candado) }
            if(dbBoolean[1][4]) { nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado)
            }else{ nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.coralcandado) }
            anchoE = 286
            altoE = 219
        }else if(modulo[2]){
            fondo1 = AppCompatResources.getDrawable(getContext(), R.drawable.polos1)
            if(dbBoolean[2][0]) { nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.pino)
            }else{ nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.pinocandado) }
            if(dbBoolean[2][1]) { nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pino2)
            }else{ nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pinocandado) }
            if(dbBoolean[2][2]) { nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pino3)
            }else{ nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pinocandado) }
            if(dbBoolean[2][3]) { nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pino4)
            }else{ nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pinocandado) }
            if(dbBoolean[2][4]) { nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pinos)
            }else{ nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pinoscandado) }
            anchoE =268
            altoE = 250
        }else if(modulo[3]){

        }else if(modulo[4]){

        }else if(modulo[5]){
            fondo1 = AppCompatResources.getDrawable(getContext(), R.drawable.americaback1)
            if(dbBoolean[5][0]){ nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.pin1)
            }else{ nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado) }
            if(dbBoolean[5][1]){ nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pin2)
            }else{ nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado) }
            if(dbBoolean[5][2]) { nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pin3)
            }else{ nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado) }
            if(dbBoolean[5][3]) { nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pin4)
            }else{ nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado) }
            if(dbBoolean[5][4]) { nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pin5)
            }else{ nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.pincandado) }
            anchoE =200
            altoE = 250
        }else if(modulo[6]){
            fondo1 = AppCompatResources.getDrawable(getContext(), R.drawable.asiaback1)
            if(dbBoolean[6][0]) { nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.latern1)
            }else{ nivel1 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado) }
            if(dbBoolean[6][1]) { nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.latern2)
            }else{ nivel2 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado) }
            if(dbBoolean[6][2]) { nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.latern3)
            }else{ nivel3 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado) }
            if(dbBoolean[6][3]) { nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.latern4)
            }else{ nivel4 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado) }
            if(dbBoolean[6][4]) { nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.latern5)
            }else{ nivel5 = AppCompatResources.getDrawable(getContext(), R.drawable.laterncandado) }
            anchoE = 175
            altoE = 240
        }

        fondo1!!.setBounds(50, (alto/2 -200).toInt(), ancho.toInt()-50, (alto-50).toInt())

        nivel1!!.setBounds(100, (altonivel + (altonivel1-90)), 100+anchoE, (altonivel + (altonivel1)-90+altoE))
        nivel2!!.setBounds((anchonivel*2), (altonivel + (altonivel1-130)), ((anchonivel*2)+anchoE),  (altonivel + (altonivel1)-130+altoE))
        nivel3!!.setBounds((anchonivel*3)+100,  (altonivel + (altonivel1)-20), ((anchonivel*3)+100+anchoE), (altonivel + (altonivel1-20)+altoE))

        nivel4!!.setBounds((anchonivel*2)-150, (altonivel + ((altonivel1*2)) + 50), ((anchonivel*2)-150+anchoE), (altonivel + ((altonivel1*2)) + 50 + altoE))
        nivel5!!.setBounds((anchonivel*3)-50, (altonivel + ((altonivel1*2)) + 60), ((anchonivel*3)-50+anchoE), (altonivel + ((altonivel1*2)) + 60 + altoE))
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        var cx = ancho/2 - 300f
        var cy = 450f
        for(i in 0.. 6){
            if(i == 4){
                cx = ancho/2 - 200f
                cy = 650f
            }
            if(event.x >= cx - radio && event.x <= cx + radio && event.y >= cy - radio && event.y <= cy + radio){
                for(j in 0..6){
                    modulo[j] = false
                }
                modulo[i] = true
//                Toast.makeText(context, "modulo "+i, Toast.LENGTH_SHORT).show()
            }
            cx += 200f
        }
        invalidate()
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


}