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
    val cuadroFondo = Paint()
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
    var arriba: Boolean = false

    // Texto
    var maravillasText = arrayOf("ChinChén Itzá", "Coliseo Romano", "Cristo Redendor", "Muralla China", "Taj Mahal", "Petra", "Machu Pichu")
    var paises = arrayOf("México", "Italia", "Brasil", "China", "India", "Jordania", "Perú")
    var datoCurioso = arrayOf(
        arrayOf("La pirámide de Kukulkan tiene 365 escalones, uno por cada día del año.", "El complejo arqueológico de Chichén Itzá fue declarado patrimonio de la Humanidad por la Unesco en 1988, lo que dio lugar a la más rigurosa protección de esta antigua ciudad maya.", "La pirámide de Chichen Itzá fue erigida para honrar a Kukulkán, la serpiente emplumada. Esta deidad maya encargada de fertilizar la tierra es la principal protagonista del templo."),
        arrayOf("Se construyó en el siglo I y fue construido por el emperador Vespasiano y sus hijos Tito y Domiciano.", "Se estima que participaron entre 60,000 y 100,000 personas en su construcción.", "El Coliseo se construyó entre los años 72 y 80 d.C. Los juegos inaugurales del Coliseo se celebraron entre los años 80 y 81 d.C.", "El emperador Vespasiano inició la construcción del Coliseo, pero murió en el año 79 d.C., antes de que se terminara el piso superior. Su hijo, el emperador Tito, terminó el piso superior."),
        arrayOf( "La estatua mide 38 metros de alto, 28 de ancho, pesa 1145 toneladas, y se alza a 710 metros sobre el nivel del mar.", "La estatua del Cristo Redentor es completamente hueca, a excepción de las manos.",
                "De hecho, se puede entrar en el interior y salir por las manos o por la cabeza, aunque el acceso sólo está permitido a personal de mantenimiento y a miembros autorizados por la Arquidiócesis de Río.", "La estatua del Cristo Redentor fue construida en Francia y fue trasladada a Brasil por partes. Fueron necesarios cinco años de trabajo (de 1926 a 1931) para completar la obra. La construcción se hizo a la inversa, es decir, de la cabeza a los pies."),
        arrayOf("Su nombre original en chino es changcheng, que significa 'larga muralla'.", "Su construcción se extendió por más de 2,000 años.", "Es la estructura más larga construida por el hombre, con 21,196 kilómetros de longitud. \n" +
                "Su altura promedio es de 7.8 metros.", "La Gran Muralla China se construyó para proteger la frontera norte de China de los ataques de las tribus nómadas de Mongolia y Manchuria. Controlar la inmigración y la emigración y recaudar impuestos"),
        arrayOf("Se construyó en 22 años con el trabajo de más de 20,000 personas y más de 1,000 elefantes.", "El Taj Mahal puede cambiar de color hasta tres veces al día, dependiendo de la luz del sol. En la madrugada se ve rosáceo, en la mañana blanco brillante y en la noche dorado.", " El Taj Mahal es un mausoleo construido por el emperador mogol Sha Jahan en honor a su esposa preferida, Mumtaz Mahal (la «Elegida del Palacio» o la «Joya del Palacio»), muerta al dar a luz.", "Taj Mahal (abreviación del nombre de Mumtaz Mahal) significa también “La Joya del Palacio”."),
        arrayOf(" fue una de las ciudades más prósperas de Oriente Medio debido a su posición geográfica, gracias a la que confluyen hasta siete rutas de comercio.", "Petra es uno de los sitios arqueológicos en el que se mezclan las influencias de las tradiciones del antiguo Oriente y las de la arquitectura helenística.", "Fue bautizada como 'la ciudad para el día de mañana'", "El 80 % de la ciudad sigue enterrada."),
        arrayOf("Machu Picchu está conformada por 150 edificios; entre los cuales hay templos, santuarios, baños y casas. Así como también se pueden encontrar 100 tramos de escaleras hechas de piedra.", "Machu Picchu está dividida en 2 zonas: agrícola y urbana. Se estima que en esta ciudad escondida entre las montañas vivieron poco más de 1000 personas", "Machu Picchu nunca fue terminada, solo fue abandonada. Gracias a esto, los españoles no pudieron destruirla o modificarla como lo hicieron con otras ciudades incas."))
    var dato: String = ""
    //SQLite
    var db: DBSQLite = DBSQLite(context)
    var desbloqueados = arrayOf(false, false , false, false, false, false, false)


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
        pTextPequeno.textSize = 45f
        pTextPequeno.typeface = customTypeface

        cuadro.style = Paint.Style.STROKE
        cuadro.strokeWidth = 20f
        cuadro.color = ResourcesCompat.getColor(resources, R.color.ic_launcher_background, null)

        cuadroFondo.style = Paint.Style.FILL
        cuadroFondo.color = Color.WHITE

        pRelleno.strokeWidth = 40f
        pRelleno.color = ResourcesCompat.getColor(resources, R.color.lightblue, null)
        pTextMas.typeface = customTypeface
//        val plain = Typeface.createFromAsset(context.assets, "kumbhsans.ttf")
//        pText.setTypeface(plain)

        val typeface = getResources().getFont(R.font.kumbhsans_extrabold)
        pText.setTypeface(typeface)
        comprobarBaseDeDatos()
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

        canvas.drawRect(0f, 0f, ancho, alto, cuadroFondo)

        ChinChenItza!!.draw(canvas)
        Coliseo!!.draw(canvas)
        CristoRedendor!!.draw(canvas)
        MurallaChina!!.draw(canvas)
        TajMajal!!.draw(canvas)
        Hordania!!.draw(canvas)
        MachuPichu!!.draw(canvas)

        var xtext = (ancho/4-160)
        var ytext = (alto/4 -50)
        for(i in 0..6){
            if(i == 3){
                xtext = (ancho/2+210)
                ytext = (alto/4 -50)
            }else if(i == 6){
                xtext = 440f
                ytext = (alto - 300)
            }else if(i == 5){
                xtext = (ancho/2+260)
            }

            if(maravillasText[i].equals("Cristo Redendor")){
                canvas.drawText(maravillasText[i], xtext, ytext+15, pText)
            }else{
                canvas.drawText(maravillasText[i], xtext, ytext, pText)
            }
            ytext += (alto/4 - 70)
        }

        var xPos = ancho/2

        if(mostrar){
            canvas.drawRoundRect(ancho/4-100, alto/4-200, (ancho/4)*3+100, (alto/4)*3, 50f, 50f, pRelleno)
            canvas.drawRoundRect(ancho/4-100, alto/4-200, (ancho/4)*3+100, (alto/4)*3, 50f, 50f, cuadro)

            canvas.drawText("Dato Curioso", xPos, alto/4, pTextMas)
            light!!.draw(canvas)
            cancel!!.draw(canvas)

            val mTextLayout = StaticLayout(
                dato,
                pTextPequeno,
                ((ancho/4)*3-150).toInt(),
                Layout.Alignment.ALIGN_CENTER,
                1.0f,
                0.0f,
                false
            )
            canvas.save()

            // calculate x and y position where your text will be placed
            var textX = ancho/4-50
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
        Coliseo!!.setBounds(20, (alto/4-50).toInt(), (ancho/2-50).toInt(), (alto/2-150).toInt())

        CristoRedendor = AppCompatResources.getDrawable(getContext(), R.drawable.cristoredentor)
        if(!desbloqueados[2]){ CristoRedendor!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        CristoRedendor!!.setBounds(20, ((alto/2) -100).toInt(), (ancho/2-50).toInt(), ((alto/4)*3 -200).toInt())

        MurallaChina = AppCompatResources.getDrawable(getContext(), R.drawable.granmurallachina)
        if(!desbloqueados[3]){ MurallaChina!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        MurallaChina!!.setBounds((ancho/2 +50).toInt(), 20, (ancho).toInt(), (alto/4 -100).toInt())

        TajMajal = AppCompatResources.getDrawable(getContext(), R.drawable.tajmahal)
        if(!desbloqueados[4]){ TajMajal!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        TajMajal!!.setBounds((ancho/2 +50).toInt(), (alto/4-50).toInt(), (ancho).toInt(), (alto/2-150).toInt())

        Hordania = AppCompatResources.getDrawable(getContext(), R.drawable.petrajordania)
        if(!desbloqueados[5]){ Hordania!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        Hordania!!.setBounds((ancho/2 +50).toInt(),  ((alto/2) -100).toInt(), (ancho).toInt(), ((alto/4)*3 -200).toInt())

        MachuPichu = AppCompatResources.getDrawable(getContext(), R.drawable.machupichu)
        if(!desbloqueados[6]){ MachuPichu!!.colorFilter = ColorMatrixColorFilter(ColorMatrix().apply { setSaturation(0f)}) }
        MachuPichu!!.setBounds(400, ((alto/4)*3-200).toInt(), (ancho/2+200).toInt(), (alto -300).toInt())

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
                dato = dato(datoCurioso[maravilla!!])
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

    fun dato(array: Array<String>): String{
        array.shuffle()
        return array[0]
    }

}