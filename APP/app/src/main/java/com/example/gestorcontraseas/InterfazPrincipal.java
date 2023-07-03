package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class InterfazPrincipal extends AppCompatActivity {
    ImageButton BotonBuscar, BotonAgregar;
    Button BotonVolver;
    TextView Busqueda;
    RecyclerView Tabla;
    //Para la tabla
    ArrayList<Datos> listaDatos;

    MyDatabaseHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_principal);
        //Interfaz para buscar
        BotonBuscar = findViewById(R.id.BotonBusqueda);
        Busqueda = findViewById(R.id.BarraBusqueda);
        //Interfaz para agregar
        BotonAgregar = findViewById(R.id.Agregar);
        //Instancia DB
        conn = new MyDatabaseHelper(this);
        //Instacia Lista
        listaDatos = new ArrayList<>();
        //config de Tabla
        Tabla = findViewById(R.id.Tabla);
        Tabla.setLayoutManager(new LinearLayoutManager(this));
        consultarListaDatos();
        Context context = this;
        TablaAdapter adapter = new TablaAdapter(listaDatos, context);
        Tabla.setAdapter(adapter);
        //cerrar conexion DB
        conn.close();
        //Reiniciar Tabla
        BotonVolver = findViewById(R.id.Volver);
    }

    private void consultarListaDatos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Datos dato;
        Cursor cursor = db.rawQuery("SELECT * FROM my_table", null);

        if (cursor.moveToFirst()) {
            do {
                dato = new Datos(cursor.getString(0), //id
                                 cursor.getString(1), //Tipo de cuenta
                                 cursor.getString(2), //Sitio
                                 cursor.getString(3),//Correo
                                 cursor.getString(4),//Contrasena
                                 cursor.getString(5),//rut
                                 cursor.getString(6),//celular
                                 cursor.getString(7),//telefono Fijo
                                 cursor.getString(8),//Nombres
                                 cursor.getString(9),//Apellidos
                                 cursor.getString(10),//CorreoSecundario
                                 cursor.getString(11),//Direccion
                                 cursor.getString(12),//otro1
                                 cursor.getString(13),//otro2
                                 cursor.getString(14),//otro3
                                 cursor.getString(15));//Codigo para desencriptar
                listaDatos.add(dato);

            } while (cursor.moveToNext());
        }
    }

    public void Listener(View view){
        try{
            if(view.getId() == BotonAgregar.getId()){
                finish();
                Intent intent = new Intent(this, AgregarCuenta.class);
                startActivity(intent);
            }
            else if(view.getId() == BotonBuscar.getId()){
                if(Busqueda.getText()==null){
                    Toast.makeText(getApplicationContext(),"Debe especificar busqueda", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Se busca
                    Busqueda.setText(null);
                }
            }
            else if(view.getId() == BotonVolver.getId()){
                //reiniciarTabla
            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
}