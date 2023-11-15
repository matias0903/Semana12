package com.example.semana12.Vista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.semana12.Controlador.ConexionHelper;
import com.example.semana12.Modelo.Usuario;
import com.example.semana12.R;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {

    ListView listViewUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;
    ConexionHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listViewUsuarios = findViewById(R.id.listar);
        conn = new ConexionHelper(getApplicationContext(),"bd_usuarios",null,1);
    }
}