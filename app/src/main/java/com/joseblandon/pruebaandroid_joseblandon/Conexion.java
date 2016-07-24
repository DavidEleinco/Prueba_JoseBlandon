package com.joseblandon.pruebaandroid_joseblandon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by to-bl on 24/07/2016.
 */
public class Conexion extends SQLiteOpenHelper {

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override


    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table puntaje(usuario text primary key , correo text,contrasena integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists puntaje");

        db.execSQL("create table puntaje(usuario text primary key , animales integer,profesiones integer,frutas integer, casa integer, cuerpo integer)");

    }



}
