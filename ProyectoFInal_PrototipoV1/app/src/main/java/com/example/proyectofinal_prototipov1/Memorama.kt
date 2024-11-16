package com.dgfp.proyectojuego1

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import com.dgfp.practica14kt_controlp3.OnSelectedMemorama
import com.example.proyectofinal_prototipov1.R

class Memorama: LinearLayout {
    var btn1: ImageButton? = null
    var btn2: ImageButton? = null
    var btn3: ImageButton? = null
    var btn4: ImageButton? = null
    var btn5: ImageButton? = null
    var btn6: ImageButton? = null
    var btn7: ImageButton? = null
    var btn8: ImageButton? = null

    private var original = emptyArray<String>()
    private var par: Array<String?> = arrayOfNulls(8)
    private var touchBool = arrayOf(false, false, false, false, false, false, false, false)
    private var touch = 0
    private var select = 0

    private var listener: OnSelectedMemorama? = null
    fun setOnDateChangeListener(l: OnSelectedMemorama){
        listener = l
    }

    constructor(context: Context? ) : super(context){
        inicializar()
    }

    constructor(context: Context?, attrs: AttributeSet? ) : super(context, attrs){
        inicializar()
    }

    fun inicializar(){
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
    }

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
        changeImage()

    }


    fun select (pos: Int, veces: Int){
        var flag = false
        if(veces == 1 ){
            if(touchBool[pos]){
                touch = 0
            }else{
                touchBool[pos] = true
                select = pos
            }
        }else if (veces == 2){
            if(pos != select){
                if(par.get(pos) == par.get(select)){
                    touchBool[pos] = true
                    if(ganado()){
                        Toast.makeText(context, "¡Ganaste!",
                            Toast.LENGTH_LONG).show();
                    }
                }else if(touchBool[pos]){
                    touch = 1
                }else {
                    when (pos) {
                        0 -> btn1!!.setImageResource(context.getResources().getIdentifier(par.get(0).toString(),"drawable", context.getPackageName()))
                        1 -> btn2!!.setImageResource(context.getResources().getIdentifier(par.get(1).toString(),"drawable", context.getPackageName()))
                        2 -> btn3!!.setImageResource(context.getResources().getIdentifier(par.get(2).toString(),"drawable", context.getPackageName()))
                        3 -> btn4!!.setImageResource(context.getResources().getIdentifier(par.get(3).toString(),"drawable", context.getPackageName()))
                        4 -> btn5!!.setImageResource(context.getResources().getIdentifier(par.get(4).toString(),"drawable", context.getPackageName()))
                        5 -> btn6!!.setImageResource(context.getResources().getIdentifier(par.get(5).toString(),"drawable", context.getPackageName()))
                        6 -> btn7!!.setImageResource(context.getResources().getIdentifier(par.get(6).toString(),"drawable", context.getPackageName()))
                        7 -> btn8!!.setImageResource(context.getResources().getIdentifier(par.get(7).toString(),"drawable", context.getPackageName()))
                    }
                    flag = true
                }
                touch = 0
            }else{
                touch = 1
            }
        }
        if(flag){
            Thread.sleep(500)
            touchBool[select] = false
            flag = false
        }
    }

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
                    0 -> btn1!!.setImageResource(R.drawable.cards)
                    1 -> btn2!!.setImageResource(R.drawable.cards)
                    2 -> btn3!!.setImageResource(R.drawable.cards)
                    3 -> btn4!!.setImageResource(R.drawable.cards)
                    4 -> btn5!!.setImageResource(R.drawable.cards)
                    5 -> btn6!!.setImageResource(R.drawable.cards)
                    6 -> btn7!!.setImageResource(R.drawable.cards)
                    7 -> btn8!!.setImageResource(R.drawable.cards)
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

    fun ganado(): Boolean{
        for(i in 0..7){
            if(!touchBool[i]) {
                return false
            }
        }
        return true
    }

}

