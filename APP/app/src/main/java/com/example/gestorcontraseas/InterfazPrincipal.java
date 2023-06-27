package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        conn = new MyDatabaseHelper(getApplicationContext());
        //Instacia Lista
        listaDatos = new ArrayList<>();
        //config de Tabla
        Tabla = findViewById(R.id.Tabla);
        Tabla.setLayoutManager(new LinearLayoutManager(this));
        consultarListaDatos();
        TablaAdapter adapter = new TablaAdapter(listaDatos);
        Tabla.setAdapter(adapter);
        //Reiniciar Tabla
        BotonVolver = findViewById(R.id.Volver);
    }

    private void consultarListaDatos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Datos dato = null;
        Cursor cursor = db.rawQuery("SELECT * FROM mydatabase", null);

        while(cursor.moveToNext()){
            dato.setTipoCuenta(cursor.getString(0));
            dato.setSitio(cursor.getString(1));
            dato.setCorreo(cursor.getString(2));
            dato.setContra(cursor.getString(3));
            dato.setRut(cursor.getString(4));
            dato.setCelular(cursor.getString(5));
            dato.setTelefonoFijo(cursor.getString(6));
            dato.setNombres(cursor.getString(7));
            dato.setApellidos(cursor.getString(8));
            dato.setCorreoSecundario(cursor.getString(9));
            dato.setDireccion(cursor.getString(10));
            dato.setOtro1(cursor.getString(11));
            dato.setOtro2(cursor.getString(12));
            dato.setOtro3(cursor.getString(13));
            dato.setCodigo(cursor.getString(14));
            listaDatos.add(dato);
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