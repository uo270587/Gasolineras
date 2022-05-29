package com.example.myapplication.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory

class AdminSQLiteOpenHelper(context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table busqueda(codigo int primary key, idccaa text, idProvincia text ,idMUnicipio text,comunidad text,provincia text,municipio text,combustible text)")
        db.execSQL("create table gasolinera(codigo int primary key, " +
                "titulo text," +
                "horario text, " +
                "cp text," +
                "localidad text" +
                ",margen text," +
                "municipio text," +
                "provincia text," +
                "biodiesa text," +
                "bioetanol text," +
                "gasnaturalc text," +
                "gasnaturall text," +
                "gasespetroleo text," +
                "gasoleoa text," +
                "gasoleob text," +
                "gasoleopremium text," +
                "gasolina95E5 text," +
                "gasolina95E10 text," +
                "gasolina95E5Premium text," +
                "gasolina98E10 text," +
                "gasolina98E5 text," +
                "hidrogeno text," +
                "remision text," +
                "tipo_venta text," +
                "ester text," +
                "por_bioetanol text," +
                "direccion text)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }
}