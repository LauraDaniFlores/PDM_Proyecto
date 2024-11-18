package com.example.proyectofinal_prototipov1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Modulos : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modulos)

        var continentes: ImageButton = findViewById(R.id.continentes)
        var oceanos: ImageButton = findViewById(R.id.oceanos)
        var aryan: ImageButton = findViewById(R.id.aryan)
        var mexicoEsCap: ImageButton = findViewById(R.id.mexicoEsCap)
        var mexicoEs: ImageButton = findViewById(R.id.mexicoEs)
        var america: ImageButton = findViewById(R.id.america)
        var Asia: ImageButton = findViewById(R.id.Asia)

        continentes.setOnClickListener(evento)
        oceanos.setOnClickListener(evento)
        aryan.setOnClickListener(evento)
        mexicoEsCap.setOnClickListener(evento)
        mexicoEs.setOnClickListener(evento)
        america.setOnClickListener(evento)
        Asia.setOnClickListener(evento)
    }
    val evento = View.OnClickListener { v ->
        val i: Intent = Intent(
            this@Modulos,
            ModulosIntermedio::class.java
        )
        when (v.getId()) {
            R.id.continentes -> {
                i.putExtra("tiponivel", "0")
            }
            R.id.oceanos -> {
                i.putExtra("tiponivel", "1")
            }
            R.id.aryan -> {
                i.putExtra("tiponivel", "2")
            }
            R.id.mexicoEsCap -> {
                i.putExtra("tiponivel", "3")
            }
            R.id.mexicoEs -> {
                i.putExtra("tiponivel", "4")
            }
            R.id.america -> {
                i.putExtra("tiponivel", "5")
            }
            R.id.Asia -> {
                i.putExtra("tiponivel", "6")
            }
        }
        startActivity(i)
    }

//    private val evento = View.OnClickListener { v ->
//        val i: Intent = Intent(
//            this@Modulos,
//            ModulosIntermedio::class.java
//        )
//        if(v == continentes){
//            i.putExtra("tiponivel", "0")
//        }else if(v == oceanos){
//            i.putExtra("tiponivel", "1")
//        }else if(v == aryan){
//            i.putExtra("tiponivel", "2")
//        }else if(v == mexicoEsCap){
//            i.putExtra("tiponivel", "3")
//        }else if(v == mexicoEs){
//            i.putExtra("tiponivel", "4")
//        }else if(v == america){
//            i.putExtra("tiponivel", "5")
//        }else if(v == Asia){
//            i.putExtra("tiponivel", "6")
//        }
//        startActivity(i)
//
//    }

}
