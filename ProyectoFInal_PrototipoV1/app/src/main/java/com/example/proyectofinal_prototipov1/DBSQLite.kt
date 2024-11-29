package com.example.proyectofinal_prototipov1
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.Date
import java.util.Vector

class DBSQLite(context: Context?) : SQLiteOpenHelper(context, TABLE_NAME, null, DATABASE_VERSION){
    companion object{
        //Método de SQLiteOpenHelper
        //Si se cmabia la estrucutra de la BD, se debe de incrementar la versión de la BD.
        val DATABASE_VERSION: Int = 1
        val TABLE_NAME: String = "TRAYECTO"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "modulo INTEGER, nivel INTEGER, tiempo INTEGER, puntaje INTEGER, fecha DATE, primeravez BOOLEAN)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //En caso de una nueva versión habría que actualizar las tablas
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //En caso de regresar a una versión anterior habría que actualizar las tablas
    }

    fun guardarRegistro(modulo: Int, nivel: Int, tiempo: Int, puntaje: Int, fecha: Date, primeravez: Boolean){
        val db = writableDatabase
        db.execSQL(
            ("INSERT INTO "+ TABLE_NAME + " VALUES(null, "
                    + modulo + ", " + nivel + ", " + tiempo + ", " + puntaje + ", '" + fecha + "', '" + primeravez + "')")
        )

    }

    fun listaDatos(): Vector<String> {
        val result = Vector<String>()
        val db = readableDatabase
        var cursor = db.rawQuery(
            "SELECT id, nombre " +
                    "FROM " + TABLE_NAME + " ORDER BY id ", null
        )
        while (cursor.moveToNext()){
            result.add(
                (cursor.getInt(0).toString() + " "
                        + cursor.getString(1)
                        )
            )
        }
        cursor.close()
        return result
    }

    fun eliminarTodo(){
        val db = writableDatabase
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    fun nivelDesbloqueado(modulo: Int, nivel: Int): Boolean{
        val result: Boolean = false
        val db = readableDatabase

        var rows = 0
        var nivelaux = nivel - 1
        var moduloaux = modulo

        if(nivelaux == 0){
            moduloaux -= 1
            if(moduloaux == 4){
                nivelaux = 10
            }else{
                nivelaux = 5
            }
        }

        var cursor = db.rawQuery(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE_NAME + " WHERE modulo = $moduloaux AND nivel = $nivelaux ", null
        )
        while (cursor.moveToNext()){
            rows = cursor.getInt(0)
        }
        cursor.close()
        if(rows > 0){
            return true
        }else if(rows == 0){
            return false
        }
        return false
    }

    fun moduloDesbloqueado(modulosModulo: Int): Boolean{
        val db = readableDatabase

        var rows = 0
        var moduloaux = modulosModulo-1
        var niveles = 5

        if(moduloaux == 0){
            return true
        }
        if(moduloaux == 3){
            niveles = 10
        }

        var cursor = db.rawQuery(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE_NAME + " WHERE modulo = $moduloaux AND nivel = $niveles ", null
        )
        while (cursor.moveToNext()){
            rows = cursor.getInt(0)
        }
        cursor.close()
        if(rows > 0){
            return true
        }else if(rows == 0){
            return false
        }
        return false
    }

    fun maravillaDesbloqueada(modulo: Int): Boolean{
        val db = readableDatabase

        var rows = 0
        var moduloaux = modulo
        var niveles = 5

        if(moduloaux == 5){
            niveles = 10
            moduloaux = 4
        }else if(moduloaux > 5){
            moduloaux -= 1
        }

        var cursor = db.rawQuery(
            "SELECT COUNT(*) " +
                    "FROM " + TABLE_NAME + " WHERE modulo = $moduloaux AND nivel = $niveles ", null
        )
        while (cursor.moveToNext()){
            rows = cursor.getInt(0)
        }
        cursor.close()
        if(rows > 0){
            return true
        }else if(rows == 0){
            return false
        }
        return false
    }

    fun Estadistica(modulo: Int, nivel: Int): Array<String>{
        val db = readableDatabase

        var rows = 0
        var datos: Array<String> = emptyArray()
        var moduloaux = modulo
        var niveles = nivel

        var cursor = db.rawQuery(
            "SELECT tiempo, puntaje, fecha " +
                    "FROM " + TABLE_NAME + " WHERE modulo = $moduloaux AND nivel = $niveles ORDER BY tiempo ASC", null
        )
        while (cursor.moveToNext()){
            rows = cursor.getInt(0)
            datos = arrayOf(cursor.getInt(0).toString(), cursor.getString(1), cursor.getString(2), "true")
            cursor.close()
            return datos
        }
        cursor.close()
        return arrayOf("null", "null", "null", "false")
    }

}