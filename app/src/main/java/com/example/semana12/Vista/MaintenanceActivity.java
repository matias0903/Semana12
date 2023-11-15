package com.example.semana12.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semana12.Controlador.ConexionHelper;
import com.example.semana12.Controlador.Utility;
import com.example.semana12.R;

public class MaintenanceActivity extends AppCompatActivity {

    EditText txtid;
    EditText txtnombre;
    EditText txtcorreo;
    Button btnConsultar, btnActualizar, btnEliminar;
    ConexionHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);

        conn = new ConexionHelper(getApplicationContext(),"bd_usuarios",null,1);
        txtid = findViewById(R.id.Id2);
        txtnombre = findViewById(R.id.Nombre2);
        txtcorreo = findViewById(R.id.Correo2);
        btnConsultar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Consultar();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });
    }
    private void Consultar(){
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {txtid.getText().toString()};

        try {
            Cursor cursor = db.rawQuery("SELECT " + Utility.CAMPO_NOMBRE + "," + Utility.CAMPO_CORREO +
                    " FROM " + Utility.TABLA_USUARIO + " WHERE " + Utility.CAMPO_ID + "=? " , parametros);
            cursor.moveToFirst();
            txtnombre.setText(cursor.getString(0));
            txtcorreo.setText(cursor.getString(1));
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"ATENCION, usuario no existe",
                    Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void Actualizar(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(Utility.CAMPO_NOMBRE, txtnombre.getText().toString());
        values.put(Utility.CAMPO_CORREO, txtcorreo.getText().toString());

        db.update(Utility.TABLA_USUARIO, values, Utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCION, se actualizo el usuario",
                Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    private void Eliminar(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {txtid.getText().toString()};

        db.delete(Utility.TABLA_USUARIO, Utility.CAMPO_ID + "=?", parametros);
        Toast.makeText(getApplicationContext(), "ATENCION, se elimino el usuario",
                Toast.LENGTH_SHORT).show();
        limpiar();
        db.close();
    }

    private void limpiar(){
        txtnombre.setText("");
        txtcorreo.setText("");
    }
}