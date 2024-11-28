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
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import java.util.Date

class Arrastrar : View {
    //Imagenes
    private var imagen1: Drawable? = null
    private var imagen2: Drawable? = null
    private var imagen3: Drawable? = null
    private var imagen4: Drawable? = null

//    private var imagenes = arrayOf("mcontinente5", "mcontinente3", "mcontinente4",  "mcontinente2")
//    private var nc = arrayOf(1, 2, 3, 4)

    private var imagenes = emptyArray<String>()
    private var nc = emptyArray<String>()

    private var imagerandom = arrayOf(0,1,2,3)
    private var coordcirc = arrayOf(arrayOf(200f,200f), arrayOf(425f,200f),arrayOf(650f,200f),arrayOf(875f,200f))

    private var coormov = arrayOf(0f, 0f)
    private var acomodado = arrayOf(false, false, false, false)
    private var adentro = arrayOf(false, false, false, false)
    private var acabar = false
    private var puntaje = 0
    private var tiempo = 0
    private var circulo = 100f
    private var tiempo5seg = 0

    var margencua: Paint = Paint()
    var margenrojo: Paint = Paint()
    var margenverde: Paint = Paint()

    var rect: Paint = Paint()
    var circle: Paint = Paint()
    var text: Paint = Paint()
    var text1: Paint = Paint()


    private var azulagua: Int = 0
    private var verde: Int = 0
    private var rojo: Int = 0

    private var sobrecirculo = false
    private var sobrecirculoi = 0

    //Audio
    private var musicSuccess: MediaPlayer? = null
    private var musicError: MediaPlayer? = null


    //Listener
    var listener: OnChangeScoreListener? = null
    fun setListenerScore(l: OnChangeScoreListener) {
        listener = l
    }

    var listenertime: OnTimeStopListener? = null
    fun setOnTimeStotListener(l: OnTimeStopListener) {
        listenertime = l
    }

    var db: DBSQLite = DBSQLite(context)

    constructor(context: Context?) : super(context) {
        inicializa()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        inicializa()
    }

    fun setArray(images: Array<String>, ncc: Array<String>){
        imagenes = images
        nc = ncc
        ordenar()
    }

    private fun inicializa() {
        verde = ResourcesCompat.getColor(resources, R.color.verde, null)
        rojo = ResourcesCompat.getColor(resources, R.color.rojo, null)
        azulagua = ResourcesCompat.getColor(resources, R.color.azulagua, null)

        margencua.style = Paint.Style.STROKE
        margencua.color = azulagua
        margencua.strokeWidth = 15f

        margenrojo.style = Paint.Style.STROKE
        margenrojo.color = rojo
        margenrojo.strokeWidth = 15f

        margenverde.style = Paint.Style.STROKE
        margenverde.color = verde
        margenverde.strokeWidth = 15f

        rect.style = Paint.Style.FILL
        rect.color = azulagua
        rect.strokeWidth = 15f

        circle.style = Paint.Style.FILL
        circle.color = azulagua
        circle.strokeWidth = 15f

        text.textSize = 55f
        text.color = Color.WHITE
        text.textAlign = Paint.Align.CENTER
        val customTypeface = resources.getFont(R.font.courier)
        text.typeface = customTypeface

        text1.textSize = 55f
        text1.color = Color.BLACK
        text1.textAlign = Paint.Align.CENTER
        text1.typeface = customTypeface

        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var poner = (ancho/8)
//        for (i in 0..3){
//            coordcirc[i][0] = poner * (3 + (i * 2))
//        }
        //Inicializar el audio
        musicSuccess = MediaPlayer.create(context, R.raw.bien)
//        musicSuccess?.start()

        musicError = MediaPlayer.create(context, R.raw.error)
//        musicError?.start()


        ordenar()
    }

    fun ordenar() {
        imagerandom.shuffle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var margen = 100
        var anchoimg = ((ancho - (margen*3))/2).toInt()

//        imagen = AppCompatResources.getDrawable(getContext(), R.drawable.mapa)
        imagen1 = AppCompatResources.getDrawable(getContext(), context.getResources().getIdentifier(imagenes[imagerandom[0]], "drawable", context.getPackageName()))
        imagen1!!.setBounds(margen, 400, margen + anchoimg, 400 + anchoimg)
        imagen2 = AppCompatResources.getDrawable(getContext(), context.getResources().getIdentifier(imagenes[imagerandom[1]], "drawable", context.getPackageName()))
        imagen2!!.setBounds((margen*2) + anchoimg, 400, (margen*2)+ (anchoimg*2), 400 + anchoimg)
        imagen3 = AppCompatResources.getDrawable(getContext(), context.getResources().getIdentifier(imagenes[imagerandom[2]], "drawable", context.getPackageName()))
        imagen3!!.setBounds(margen, 400+anchoimg+margen, margen + anchoimg, 400+(anchoimg*2)+margen)
        imagen4 = AppCompatResources.getDrawable(getContext(), context.getResources().getIdentifier(imagenes[imagerandom[3]], "drawable", context.getPackageName()))
        imagen4!!.setBounds((margen*2) + anchoimg, 400+anchoimg+margen, (margen*2)+ (anchoimg*2), 400+(anchoimg*2)+margen)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var margen = 100f
        var anchoimg = ((ancho - (margen*3))/2).toFloat()

        canvas.drawColor(-1);
        imagen1!!.draw(canvas)
        imagen2!!.draw(canvas)
        imagen3!!.draw(canvas)
        imagen4!!.draw(canvas)

        if(acabar){
            if(acomodado[0]){
                canvas.drawRect(margen, 400f, margen + anchoimg, 400f + anchoimg, margenverde)
            }else if(!acomodado[0]){
                canvas.drawRect(margen, 400f, margen + anchoimg, 400f + anchoimg, margenrojo)
            }
            if(acomodado[1]){
                canvas.drawRect((margen*2) + anchoimg, 400f, (margen*2)+ (anchoimg*2), 400 + anchoimg, margenverde)
            }else if(!acomodado[1]){
                canvas.drawRect((margen*2) + anchoimg, 400f, (margen*2)+ (anchoimg*2), 400 + anchoimg, margenrojo)
            }
            if(acomodado[2]){
                canvas.drawRect(margen, 400+anchoimg+margen, margen + anchoimg, 400+(anchoimg*2)+margen, margenverde)
            }else if(!acomodado[2]){
                canvas.drawRect(margen, 400+anchoimg+margen, margen + anchoimg, 400+(anchoimg*2)+margen, margenrojo)
            }
            if(acomodado[3]){
                canvas.drawRect((margen*2) + anchoimg, 400+anchoimg+margen, (margen*2)+ (anchoimg*2), 400+(anchoimg*2)+margen, margenverde)
            }else if(!acomodado[3]){
                canvas.drawRect((margen*2) + anchoimg, 400+anchoimg+margen, (margen*2)+ (anchoimg*2), 400+(anchoimg*2)+margen, margenrojo)
            }

        }else{
            canvas.drawRect(margen, 400f, margen + anchoimg, 400f + anchoimg, margencua)
            canvas.drawRect((margen*2) + anchoimg, 400f, (margen*2)+ (anchoimg*2), 400 + anchoimg, margencua)
            canvas.drawRect(margen, 400+anchoimg+margen, margen + anchoimg, 400+(anchoimg*2)+margen, margencua)
            canvas.drawRect((margen*2) + anchoimg, 400+anchoimg+margen, (margen*2)+ (anchoimg*2), 400+(anchoimg*2)+margen, margencua)
        }
        for (i in 0..3){
            canvas.drawCircle(coordcirc[i][0], coordcirc[i][1], circulo, circle)
            canvas.drawText(nc[i].toString(), coordcirc[i][0], coordcirc[i][1]+20, text)
        }

        canvas.drawRect(margen, 400+(anchoimg*2)+(margen*2), ancho-margen, 400+(anchoimg*2)+(margen*2) + 200f, rect)
        canvas.drawText("Comprobar", ancho/2, 400+(anchoimg*2)+(margen*2) + 120f, text)
//        canvas.drawText(adentro.contentToString(), ancho/2, 100f, text1)
//        canvas.drawText(todoadentro.toString(), ancho/2, 200f, text1)
//        canvas.drawText(acomodado.contentToString(), ancho/2, 200f, text1)
        invalidate()

    }
    fun comprobar(flags: Array<Boolean>) : Int{
        var suma = 0
        for (i in 0..3){
            if(flags[i]){
                suma+= 1
            }
        }
        return suma
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val alto = measuredHeight.toFloat()
        val ancho = measuredWidth.toFloat()
        var margen = 100f
        var anchoimg = ((ancho - (margen*3))/2).toFloat()

        if (event.actionMasked == MotionEvent.ACTION_DOWN || event.actionMasked == MotionEvent.ACTION_POINTER_DOWN) {
            for(i in 0..3){
                if(event.x >= coordcirc[i][0] - circulo && event.x <= coordcirc[i][0] + circulo && event.y >= coordcirc[i][1] - circulo && event.y <= coordcirc[i][1] + circulo){
                    sobrecirculo = true
                    sobrecirculoi = i
                    coormov[0] = coordcirc[i][0]
                    coormov[1] = coordcirc[i][1]
                }
            }
        }
        if (event.actionMasked == MotionEvent.ACTION_MOVE) {
            if(sobrecirculo){
                if((event.x + circulo) >= ancho){
                    coordcirc[sobrecirculoi][0] = ancho - circulo
                }else if((event.x - circulo) <= 0f){
                    coordcirc[sobrecirculoi][0] = circulo
                }else{
                    coordcirc[sobrecirculoi][0] = event.x
                }

                if((event.y + circulo) >= alto){
                    coordcirc[sobrecirculoi][1] = alto - circulo
                }else if((event.y - circulo) <= 0f){
                    coordcirc[sobrecirculoi][1] = circulo
                }else{
                    coordcirc[sobrecirculoi][1] = event.y
                }
            }
        }
        if (event.actionMasked == MotionEvent.ACTION_UP
            || event.actionMasked == MotionEvent.ACTION_POINTER_UP
            || event.actionMasked == MotionEvent.ACTION_CANCEL){
            if(event.x >= margen && event.x <= ancho-margen && event.y >= 400+(anchoimg*2)+(margen*2) && event.y <= 400+(anchoimg*2)+(margen*2) + 200f){
                for(i in 0..3){
                    adentro[i] = true
                    if(coordcirc[i][0] >= margen && coordcirc[i][0] <= margen + anchoimg && coordcirc[i][1] >= 400f && coordcirc[i][1] <= 400 + anchoimg){

                    }else if(coordcirc[i][0] >= (margen*2) + anchoimg && coordcirc[i][0] <= (margen*2)+ (anchoimg*2) && coordcirc[i][1] >= 400f && coordcirc[i][1] <= 400 + anchoimg){

                    }else if(coordcirc[i][0] >= margen && coordcirc[i][0] <= margen + anchoimg && coordcirc[i][1] >= 400+anchoimg+margen && coordcirc[i][1] <= 400+(anchoimg*2)+margen){

                    }else if(coordcirc[i][0] >= (margen*2) + anchoimg && coordcirc[i][0] <= (margen*2)+ (anchoimg*2) && coordcirc[i][1] >= 400+anchoimg+margen && coordcirc[i][1] <= 400+(anchoimg*2)+margen){

                    }else{
                        adentro[i] = false
                    }
                }
                if(comprobar(adentro) == 4){
                    musicSuccess?.seekTo(0)
                    musicSuccess?.start()
                    for(i in 0..3){
                        if(coordcirc[i][0] >= margen && coordcirc[i][0] <= margen + anchoimg && coordcirc[i][1] >= 400f && coordcirc[i][1] <= 400 + anchoimg){
                            if(imagerandom[0] == i){
                                acomodado[0] = true
                            }
                        }else if(coordcirc[i][0] >= (margen*2) + anchoimg && coordcirc[i][0] <= (margen*2)+ (anchoimg*2) && coordcirc[i][1] >= 400f && coordcirc[i][1] <= 400 + anchoimg){
                            if(imagerandom[1] == i){
                                acomodado[1] = true
                            }
                        }else if(coordcirc[i][0] >= margen && coordcirc[i][0] <= margen + anchoimg && coordcirc[i][1] >= 400+anchoimg+margen && coordcirc[i][1] <= 400+(anchoimg*2)+margen){
                            if(imagerandom[2] == i){
                                acomodado[2] = true
                            }
                        }else if(coordcirc[i][0] >= (margen*2) + anchoimg && coordcirc[i][0] <= (margen*2)+ (anchoimg*2) && coordcirc[i][1] >= 400+anchoimg+margen && coordcirc[i][1] <= 400+(anchoimg*2)+margen){
                            if(imagerandom[3] == i){
                                acomodado[3] = true
                            }
                        }
                    }
                    acabar = true
                    var acabopuntos = comprobar(acomodado)
                    puntaje = acabopuntos*25
                    listener!!.SetonScoreChange(puntaje)
                    listenertime!!.OnTimeStop(true)

                }else{
                    musicError?.seekTo(0)
                    musicError?.start()
//                    Toast.makeText(context, "No están los cuatro adentro", Toast.LENGTH_SHORT).show()
                }
            }
            sobrecirculo = false
            sobrecirculoi = 0

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

    fun setTiempo(tiem: Int){
        tiempo = tiem
    }
    fun insertardb(modulo: Int){
        if(puntaje < 50) {
            val intent = Intent(context, derrota_Inter::class.java)
            intent.putExtra("nivel", "5")
            intent.putExtra("modulo", modulo.toString())
            context.startActivity(intent)
        }else {
            var moduloaux = modulo
            var nivelaux = 5
            if (modulo > 5) {
                moduloaux = modulo - 1
            } else if (modulo == 5) {
                moduloaux = 4
                nivelaux = 10
            }
            if (modulo == 4) {
                if (db.nivelDesbloqueado(moduloaux, nivelaux + 1)) {
                    db.guardarRegistro(moduloaux, nivelaux, tiempo, puntaje, Date(), false)
                } else {
                    db.guardarRegistro(moduloaux, nivelaux, tiempo, puntaje, Date(), true)
                }
            } else {
                if (db.nivelDesbloqueado(moduloaux + 1, 1)) {
                    db.guardarRegistro(moduloaux, nivelaux, tiempo, puntaje, Date(), false)
                } else {
                    db.guardarRegistro(moduloaux, nivelaux, tiempo, puntaje, Date(), true)
                }
            }

            val hilo1: Thread = object : Thread() {
                @Synchronized
                override fun run() {
                    while (true) {
                        try {
                            sleep(1000)
                            tiempo5seg++
                            if (tiempo5seg == 3) {

                                val intent = Intent(context, FelicidadesInter::class.java)

                                intent.putExtra("nivel", nivelaux.toString())
                                intent.putExtra("modulo", moduloaux.toString())
                                intent.putExtra("puntaje", puntaje.toString())
                                intent.putExtra("tiempo", tiempo.toString())

                                context.startActivity(intent)
                            }
                        } catch (e: InterruptedException) {
                        }
                    }
                }
            }
            hilo1.start()
        }
    }

}
