package com.example.proyectofinal_prototipov1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
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
    var margen: Paint = Paint()
    var text: Paint = Paint()
    var textmas: Paint = Paint()
    var puntajetext: Paint = Paint()


    private var circleColor: Int = 0
    private var colorPeque: Int = 0
    private var verde: Int = 0
    private var rojo: Int = 0
    private var no: Int = 0
    private var azulagua: Int = 0


    var puntaje = 0
    var estadoCon = arrayOf(0,0,0,0,0,0,0,0,0,0)

//    var numbers = arrayOf(0, 1, 2, 3, 4)
//    var conti = arrayOf("Baja California", "Sonora", "Sinaloa", "Chihuahua", "Coahuila", "Durango", "Nuevo León", "Tamaulipas", "Zacatecas", "Jalisco")
//    var coordx = arrayOf(145f,280f,370f,400f,520f, 435f, 590f, 632f, 500f, 472f)
//    var coordy = arrayOf(170f,230f,400f,300f,330f,410f, 370f, 420f, 445f, 547f)
//    var image = "mexicomapa"
//    var coorimage = 819f

    var numbers = emptyArray<Int>()
    var conti = emptyArray<String>()
    var coordx = emptyArray<Float>()
    var coordy = emptyArray<Float>()
    var image: String? = null
    var coorimage: Float? = null


    fun setArrays(nombres: Array<String>, imagen: String?, coordx1: Array<Float>, coordy1: Array<Float>, coor: Float, num: Array<Int>){
        image = imagen
        conti = nombres
        coordx = coordx1
        coordy = coordy1
        coorimage = coor
        numbers = num
        ordenar()
    }

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
        azulagua = ResourcesCompat.getColor(resources, R.color.azulagua, null)

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


        boton.style = Paint.Style.FILL
        boton.color = azulagua
        boton.strokeWidth = 5f


        margen.style = Paint.Style.STROKE
        margen.color = azulagua
        margen.strokeWidth = 15f

        text.color = Color.argb(255, 100, 100, 100)
        text.textSize = 55f
        text.color = Color.WHITE
        text.textAlign = Paint.Align.CENTER
        val customTypeface = resources.getFont(R.font.courier)
        text.typeface = customTypeface

        textmas.textSize = 35f
        textmas.color = Color.WHITE
        textmas.textAlign = Paint.Align.CENTER
        textmas.typeface = customTypeface


        puntajetext.textSize = 35f
        puntajetext.color = Color.BLACK


    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        imagen = AppCompatResources.getDrawable(getContext(), context.getResources().getIdentifier(image,"drawable", context.getPackageName()))

//        imagen = AppCompatResources.getDrawable(getContext(), R.drawable.mapa)
        imagen!!.setBounds(30, 30, (ancho-30).toInt(), coorimage!!.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()

        canvas.drawColor(-1);
        imagen!!.draw(canvas)
        canvas.drawRect(30f, 30f, ancho-30, coorimage!!.toFloat(), margen)
        var length = conti.size - 1
        length = 4
        for(i in 0..length){
            canvas.drawCircle (
                coordx.get(i)!!,coordy.get(i)!!, 18f, circle
            )
            if(estadoCon.get(i) == 0){
                canvas.drawCircle (
                    coordx.get(i)!!,coordy.get(i)!!, 10f, circlepeq
                )
            }else if(estadoCon.get(i) == 1){
                canvas.drawCircle (
                    coordx.get(i)!!,coordy.get(i)!!, 10f, circlepeq1
                )
            }else if(estadoCon.get(i) == 2){
                canvas.drawCircle (
                    coordx.get(i)!!,coordy.get(i)!!, 10f, circlepeq2
                )
            }
        }
        canvas.drawRect (
            30f,coorimage!! + 100f, ancho - 30f, coorimage!! + 100f + 180f, boton
        )
        canvas.drawText(conti.get(numbers.get(no))!!, ancho/2, coorimage!! + 200f ,text)

        var j = 0
        var h = 0
        for(i in 0..no){
            if(estadoCon.get(numbers.get(i)) == 1){
//                canvas.drawRoundRect(50f, coorimage + (300f + (h*50)), ((ancho/3)*j) - 50f, coorimage + (400f + (h*100)), 20f, 20f, boton)
                canvas.drawRoundRect(20f + (j * (ancho/3)) + (h *(ancho/6)), coorimage!! + (330f + (h*150)), ((ancho/3) + (j * ((ancho/3)))) - 20f + (h *(ancho/6)), coorimage!! + (430f + (h*150)), 40f, 40f, boton)
                canvas.drawText(conti.get((numbers.get(i))), ((ancho/6) + (j * (ancho/3))) + (h *(ancho/6)), coorimage!! + (400f + (h*150)) ,textmas)
                j++
                if(j == 3){
                    h = 1
                    j = 0
                }
            }
        }
        canvas.drawText(numbers.contentToString(), 30f, 1500f,puntajetext)
        canvas.drawText(numbers.get(no).toString(), 30f, 1600f,puntajetext)

        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        for(i in 0..4){
            if(event.x >= (coordx.get(i)!! - 30f) && event.x <= (coordx.get(i)!! + 30f) && event.y >= (coordy.get(i)!! - 30f) && event.y <= (coordy.get(i)!! + 30f)){
                if(estadoCon[i] != 1) {
                    if (numbers.get(no) == i) {
                        estadoCon[i] = 1
                        puntaje += 20
                        if(no < 5){
                            no++
                        }
                        if(no == 5){
                            no = 4
                            Toast.makeText(context, "Ganaste", Toast.LENGTH_SHORT)
                                .show()
                        }
                        limpiar()
                    } else {
                        estadoCon[i] = 2
                        puntaje -= 5
                    }
                }
            }
        }
//        if(no == 5){
//            no = 0
//        }

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


