package com.joseblandon.pruebaandroid_joseblandon;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    String nombre1,pass2,nombre,pass;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void refeshPrefs(){
        SharedPreferences misPreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        nombre1= misPreferencias.getString("nombre","");
        pass2= misPreferencias.getString("contraseña","");

    }
    public void registrarse (View v){
        Intent i = new Intent(MainActivity.this, RegistrateActivity.class);
        startActivity(i);
        finish();

    }

    public void ingresar (View v){
        refeshPrefs();
        EditText usuario=(EditText) findViewById(R.id.etUsuario);
        nombre= usuario.getText().toString();
        EditText passw=(EditText) findViewById(R.id.etPasswd);
        pass= passw.getText().toString();

        if(nombre.equals(nombre1) && pass.equals(pass2)){
            SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor= mispreferencias.edit();
            editor.putString("nombre", nombre1);
            editor.putInt(" ", 1);
            editor.commit();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            guardar(nombre);
            inputMethodManager.hideSoftInputFromWindow(passw.getWindowToken(), 0);


            Toast.makeText(MainActivity.this, "Bienvenido(a) " + nombre1, Toast.LENGTH_LONG).show();
            Intent i=new Intent(this,InicioActivity.class);
            startActivity(i);
            finish();

        }else {

            Toast.makeText(MainActivity.this, "Los datos no coinciden o el usuario no está registrado", Toast.LENGTH_LONG).show();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(passw.getWindowToken(), 0);
        }
    }

    private void guardar(String nombre)
    {
        Conexion score=new Conexion(this,"bdScore",null,2);
        SQLiteDatabase bd=score.getWritableDatabase();

        if(verificarDuplicado(nombre)!=true) {

            ContentValues registro = new ContentValues(); //Para guardar)

            registro.put("usuario", nombre);
            registro.put("animales", 0);
            registro.put("profesiones", 0);
            registro.put("frutas", 0);
            registro.put("casa", 0);
            registro.put("cuerpo", 0);
            bd.insert("puntaje", null, registro);
            bd.close();
            Toast.makeText(this, "Se guardó el usuario en la bd", Toast.LENGTH_SHORT).show();

        }
        else
        {

            Toast.makeText(this, "Ya existe este usuario en la base de datos", Toast.LENGTH_SHORT).show();

        }
    }

    public boolean verificarDuplicado(String nombre)

    {
        boolean bandera=false;

        Conexion puntaje=new Conexion(this,"bdScore",null,2);
        SQLiteDatabase  bd=puntaje.getWritableDatabase();


        c=bd.rawQuery("select *  from puntaje  where usuario='"+nombre+"'",null);
        if(c.moveToFirst()==true)
        {
            bandera=true;


        }
        bd.close();

        return bandera;

    }


}
