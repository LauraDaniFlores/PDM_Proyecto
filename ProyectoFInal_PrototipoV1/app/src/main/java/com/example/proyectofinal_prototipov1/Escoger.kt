package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat

class Escoger: View {
    private var imagen: Drawable? = null
    private var america: Drawable? = null

    var circle: Paint = Paint()
    var circlepeq: Paint = Paint()
    var circlepeq1: Paint = Paint()
    var circlepeq2: Paint = Paint()


    var boton: Paint = Paint()
    var text: Paint = Paint()
    var numbers = arrayOf(0, 1, 2, 3, 4)


    private var circleColor: Int = 0
    private var colorPeque: Int = 0
    private var verde: Int = 0
    private var rojo: Int = 0
    private var no: Int = 0

    var conti = arrayOf("América", "Europa", "Asia", "África", "Oceanía")
    var estadoCon = arrayOf(0,0,0,0,0)
    var coordx = arrayOf(220f,550f,700f,550f,830f)
    var coordy = arrayOf(260f,225f,260f,400f,470f)



    constructor(context: Context?): super(context){
        inicializa()
    }
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs){
        inicializa()
    }

    private fun inicializa() {
        ordenar()
        //asignamos los colores
        colorPeque = ResourcesCompat.getColor(resources, R.color.white, null)
        circleColor = ResourcesCompat.getColor(resources, R.color.black, null)
        verde = ResourcesCompat.getColor(resources, R.color.verde, null)
        rojo = ResourcesCompat.getColor(resources, R.color.rojo, null)

        //Inicializar el círculo
        circle.color = circleColor
        circle.style = Paint.Style.FILL
        circle.strokeWidth = 5f
        circlepeq.color = colorPeque
        circlepeq.style = Paint.Style.FILL
        circlepeq.strokeWidth = 5f

        circlepeq1.color = verde
        circlepeq1.style = Paint.Style.FILL
        circlepeq1.strokeWidth = 5f

        circlepeq2.color = rojo
        circlepeq2.style = Paint.Style.FILL
        circlepeq2.strokeWidth = 5f


        boton.color = circleColor
        boton.style = Paint.Style.STROKE
        boton.strokeWidth = 15f

        text.color = Color.argb(255, 100, 100, 100)
        text.textSize = 40f
    }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            imagen = AppCompatResources.getDrawable(getContext(), R.drawable.mapa)
            imagen!!.setBounds(30, 30, 1000, 707)
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        imagen!!.draw(canvas)
        for(i in 0..4){
            canvas.drawCircle (
                coordx.get(i),coordy.get(i), 15f, circle
            )
            if(estadoCon.get(i) == 0){
                canvas.drawCircle (
                    coordx.get(i),coordy.get(i), 8f, circlepeq
                )
            }else if(estadoCon.get(i) == 1){
                canvas.drawCircle (
                    coordx.get(i),coordy.get(i), 8f, circlepeq1
                )
            }else if(estadoCon.get(i) == 2){
                canvas.drawCircle (
                    coordx.get(i),coordy.get(i), 8f, circlepeq2
                )
            }
        }
        canvas.drawRect (
            100f,900f, 950f, 1050f, boton
        )
        canvas.drawText(conti.get((numbers.get(no))), 450f, 990f,text)
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        for(i in 0..4){
            if(event.x >= (coordx.get(i) - 30f) && event.x <= (coordx.get(i) + 30f) && event.y >= (coordy.get(i) - 30f) && event.y <= (coordy.get(i) + 30f)){
                if(estadoCon[i] != 1) {
                    if (numbers.get(no) == i) {
                        estadoCon[i] = 1
                        no++
                        limpiar()
                    } else {
                        estadoCon[i] = 2
                    }
                }
            }
        }
        if(no == 5){
            no = 0
        }
        invalidate()
        return super.onTouchEvent(event)
    }

    fun ordenar() {
        numbers.shuffle()
    }

    fun limpiar(){
        for(i in 0..4){
            if(estadoCon[i] == 2){
                estadoCon[i] = 0
            }
        }
        invalidate()
    }

}

