package com.dgfp.proyectojuego1

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.example.proyectofinal_prototipov1.DBSQLite
import com.example.proyectofinal_prototipov1.FelicidadesInter
import com.example.proyectofinal_prototipov1.OnChangeScoreListener
import com.example.proyectofinal_prototipov1.OnTimeStopListener
import com.example.proyectofinal_prototipov1.Perdiste
import com.example.proyectofinal_prototipov1.R
import com.example.proyectofinal_prototipov1.derrota_Inter
import java.util.Date

class Memorama: LinearLayout {
    // Declaración de ImageButton del layout
    var btn1: ImageButton? = null
    var btn2: ImageButton? = null
    var btn3: ImageButton? = null
    var btn4: ImageButton? = null
    var btn5: ImageButton? = null
    var btn6: ImageButton? = null
    var btn7: ImageButton? = null
    var btn8: ImageButton? = null

    var imagenCard = "cards"

    // Arrays de los nombres de las imágenes y boolean
    private var original = emptyArray<String>()
    private var par: Array<String?> = arrayOfNulls(8)
    // Array para saber si la carta esta volteada o no
    private var touchBool = arrayOf(false, false, false, false, false, false, false, false)

    // Variables para la lógica del juego
    private var touch = 0
    private var select = 0
    private var flag:Boolean = false

    // Variables del juego
    private var puntaje = 0
    private var tiempo = 0

    //Audio
    private var musicSuccess: MediaPlayer? = null
    private var musicError: MediaPlayer? = null


    // Base de datos
    var db: DBSQLite = DBSQLite(context)

    //Listener
    var listener: OnChangeScoreListener? = null
    fun setListenerScore(l: OnChangeScoreListener){
        listener = l
    }

    var listenertime: OnTimeStopListener? = null
    fun setOnTimeStotListener(l: OnTimeStopListener){
        listenertime = l
    }

    constructor(context: Context? ) : super(context){
        inicializar()
    }

    constructor(context: Context?, attrs: AttributeSet? ) : super(context, attrs){
        inicializar()
    }

    fun inicializar(){
        // Inflar con el layout e inicializar variables del laoyout
        val li = LayoutInflater.from(context)
        li.inflate(R.layout.memo_layout,this,true)
        btn1 = findViewById(R.id.imagen1)
        btn2 = findViewById(R.id.imagen2)
        btn3 = findViewById(R.id.imagen3)
        btn4 = findViewById(R.id.imagen4)
        btn5 = findViewById(R.id.imagen5)
        btn6 = findViewById(R.id.imagen6)
        btn7 = findViewById(R.id.imagen7)
        btn8 = findViewById(R.id.imagen8)

        asignarEventos()
        changeImage()

        //Inicializar el audio
        musicSuccess = MediaPlayer.create(context, R.raw.bien)
//        musicSuccess?.start()

        musicError = MediaPlayer.create(context, R.raw.error)
//        musicError?.start()

    }

    // Función para pasar el array y revolverlo
    fun setArray(array: Array<String>){
        var pos = 0
        original = array
        original.shuffle()
        for(i in 0..3){
            for (j in 0..1){
                par[pos] = original.get(i)
                pos++
            }
        }
        par.shuffle()
        for (i in 0..7) {
            touchBool[i] = false
        }
    }

    // Asignar eventos a cada botón
    fun asignarEventos(){
        btn1!!.setOnClickListener(evento)
        btn2!!.setOnClickListener(evento)
        btn3!!.setOnClickListener(evento)
        btn4!!.setOnClickListener(evento)
        btn5!!.setOnClickListener(evento)
        btn6!!.setOnClickListener(evento)
        btn7!!.setOnClickListener(evento)
        btn8!!.setOnClickListener(evento)
    }

    // Listener dependiendo del botón, se manda a llamar la función select con la posición del touch
    private val evento = OnClickListener { v ->
        touch ++
        if(v == btn1){
            select(0,touch)
        } else if(v == btn2){
            select(1,touch)
        }else if (v == btn3){
            select(2,touch)
        }else if(v == btn4){
            select(3,touch)
        }else if(v == btn5){
            select(4,touch)
        }else if(v == btn6){
            select(5,touch)
        }else if(v == btn7){
            select(6,touch)
        }else if(v == btn8){
            select(7,touch)
        }

    }

    // Lógica de voltear las cards
    fun select (pos: Int, veces: Int){
        // A la primera card seleccionada
        if(veces == 1 ){
            // SI la carta ya esta descubierta entonces se reicinia el contador
            if(touchBool[pos]){
                touch = 0
            }else{
                // Se pone en true la carta
                touchBool[pos] = true
                select = pos
            }
        // A la segunda card seleccionada
        }else if (veces == 2){
            // Si la carta es distinta a la primera seleccionada
            if(pos != select){
                // Si la carta es igual a la primera, se ha hecho un par
                if(par.get(pos) == par.get(select)){
                    touchBool[pos] = true
                    // Puntaje por cada par
                    puntaje += 25
                    // Música de success
                    musicSuccess?.start()
                    listener!!.SetonScoreChange(
                        puntaje
                    )
                    if(ganado()){
//                        Toast.makeText(context, "¡Ganaste!",
//                            Toast.LENGTH_LONG).show();
                        listenertime!!.OnTimeStop(true)
                    }
                }else if(touchBool[pos]){
                    // Si la carta es igual a la primera se reinicia
                    touch = 1
                }else {
                    // Si la carta es distinta a la primera seleccionada, pero no es la misma
                    touchBool[pos] = true
                    changeImage()
                    puntaje -= 5
                    musicError?.start()
                    listener!!.SetonScoreChange(
                        puntaje
                    )
                    flag = true
                }
                touch = 0
            }else{
                touch = 1
            }
        }
        if(flag){
//            Thread.sleep(3000)
            touchBool[select] = false
            touchBool[pos] = false
            flag = false
            //Log.d("canvas","$touchBool")
//            changeImage()
        }else{
            changeImage()
        }
    }

    // Función para poner la imagen que corresponde
    fun changeImage(){
        for(i in 0..7){
            if(touchBool.get(i)){
                when (i) {
                    0 -> btn1!!.setImageResource(context.getResources().getIdentifier(par.get(0).toString(),"drawable", context.getPackageName()))
                    1 -> btn2!!.setImageResource(context.getResources().getIdentifier(par.get(1).toString(),"drawable", context.getPackageName()))
                    2 -> btn3!!.setImageResource(context.getResources().getIdentifier(par.get(2).toString(),"drawable", context.getPackageName()))
                    3 -> btn4!!.setImageResource(context.getResources().getIdentifier(par.get(3).toString(),"drawable", context.getPackageName()))
                    4 -> btn5!!.setImageResource(context.getResources().getIdentifier(par.get(4).toString(),"drawable", context.getPackageName()))
                    5 -> btn6!!.setImageResource(context.getResources().getIdentifier(par.get(5).toString(),"drawable", context.getPackageName()))
                    6 -> btn7!!.setImageResource(context.getResources().getIdentifier(par.get(6).toString(),"drawable", context.getPackageName()))
                    7 -> btn8!!.setImageResource(context.getResources().getIdentifier(par.get(7).toString(),"drawable", context.getPackageName()))
                }
            }else{
                when (i) {
                    0 -> btn1!!.setImageResource(R.drawable.cards1)
                    1 -> btn2!!.setImageResource(R.drawable.cards1)
                    2 -> btn3!!.setImageResource(R.drawable.cards1)
                    3 -> btn4!!.setImageResource(R.drawable.cards1)
                    4 -> btn5!!.setImageResource(R.drawable.cards1)
                    5 -> btn6!!.setImageResource(R.drawable.cards1)
                    6 -> btn7!!.setImageResource(R.drawable.cards1)
                    7 -> btn8!!.setImageResource(R.drawable.cards1)
                }
            }
        }
    }

    fun shuffle (){
        par.shuffle()
        for (i in 0..7) {
            touchBool[i] = false
        }
        this.invalidate()
    }

    // Comprobar si ya se han completado todos los pares
    fun ganado(): Boolean{
        for(i in 0..7){
            if(!touchBool[i]) {
                return false
            }
        }
        return true
    }

    // Tiempo
    fun setTiempo(tiem: Int){
        tiempo = tiem
    }

    // Isertar datos en la base de datos
    fun insertardb(modulo: Int){
        if(puntaje < 50){
            val intent = Intent(context, derrota_Inter::class.java)
            intent.putExtra("nivel", "2")
            intent.putExtra("modulo", modulo.toString())
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }else {
            var nivel = 2
            var nivelCom = 3
            var moduloN = modulo
            if(modulo >= 5){
                moduloN = modulo - 1
                if(modulo == 5){
                    nivel = 7
                    nivelCom = 8
                }
            }

            if(db.nivelDesbloqueado(moduloN, nivelCom)){
                db.guardarRegistro(moduloN, nivel, tiempo, puntaje, Date(), false)
            }else{
                db.guardarRegistro(moduloN, nivel, tiempo, puntaje, Date(), true)
            }
            val intent = Intent(context, FelicidadesInter::class.java)

            intent.putExtra("nivel", nivel.toString())
            intent.putExtra("modulo", moduloN.toString())
            intent.putExtra("puntaje", puntaje.toString())
            intent.putExtra("tiempo", tiempo.toString())
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            context.startActivity(intent)

        }
    }

}

