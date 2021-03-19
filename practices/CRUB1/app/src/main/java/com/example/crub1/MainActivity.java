package com.example.crub1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText cod, nombre,cedula;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cod = findViewById(R.id.txtid);
        nombre = findViewById(R.id.txtnombre);
        cedula = findViewById(R.id.txtcedula);


    }

    public void insertar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administration",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = cod.getText().toString();
        String nom = nombre.getText().toString();
        String ced = cedula.getText().toString();
        ContentValues dates = new ContentValues();
        dates.put("id",codigo);
        dates.put("nombre",nom);
        dates.put("cedula",ced);
        bd.insert("datos",null,dates);
        bd.close();
        cod.setText("");
        nombre.setText("");
        cedula.setText("");
        Toast.makeText(getApplicationContext(),"Se guardaron los datos",Toast.LENGTH_LONG).show();


    }
    public void editar (View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administration",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = cod.getText().toString();
        String nom = nombre.getText().toString();
        String ced = cedula.getText().toString();
        ContentValues dates = new ContentValues();
        dates.put("id",codigo);
        dates.put("nombre",nom);
        dates.put("cedula",ced);
        int cant= bd.update("datos",dates,"id="+ codigo,null);
        bd.close();
        if(cant == 1)
        {
            Toast.makeText(this,"Se editaron los datos",Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this,"No existe el dato con el codigo ingresado",Toast.LENGTH_LONG).show();
        }
    }
    public void buscarporid(View view)
    {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = cod.getText().toString();
        Cursor fila = bd.rawQuery("SELECT nombre, cedula FROM datos WHERE id="+ codigo,null);
        if(fila.moveToFirst()){
            nombre.setText(fila.getString(0));
            cedula.setText(fila.getString(1));

        }
        else {
            Toast.makeText(this,"No existe un dato con dicho codigo",Toast.LENGTH_LONG).show();
            bd.close();
        }
    }
    public  void eliminar(View view){
        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this,"administration",null,1);
        SQLiteDatabase bd= admin.getWritableDatabase();
        String codigo = cod.getText().toString();
        int cant = bd.delete("datos","id="+codigo,null);
        bd.close();
        cod.setText("");
        nombre.setText("");
        cedula.setText("");
        if(cant==1)
        {
            Toast.makeText(this,"Se borraron los datos",Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(this,"No exite un dato con dicho codigo",Toast.LENGTH_SHORT).show();

        }


    }
    public void cancelar(View view)
    {
        cod.setText("");
        nombre.setText("");
        cedula.setText("");

    }

}