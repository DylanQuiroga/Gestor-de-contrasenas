package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
        TablaAdapter adapter = new TablaAdapter(listaDatos);
        Tabla.setAdapter(adapter);
        //Reiniciar Tabla
        BotonVolver = findViewById(R.id.Volver);
    }

    private void consultarListaDatos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Datos dato = new Datos(null, null, null, null, null, null, null, null, null,null, null, null, null, null, null);
        Cursor cursor = db.rawQuery("SELECT * FROM my_table", null);

        if (cursor.moveToFirst()) {
            do {
                //@SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String tipoCuenta = cursor.getString(cursor.getColumnIndex("tipoCuenta"));
                @SuppressLint("Range") String sitio = cursor.getString(cursor.getColumnIndex("sitio"));
                @SuppressLint("Range") String correo = cursor.getString(cursor.getColumnIndex("correo"));
                @SuppressLint("Range") String contra = cursor.getString(cursor.getColumnIndex("contra"));
                @SuppressLint("Range") String rut = cursor.getString(cursor.getColumnIndex("rut"));
                @SuppressLint("Range") String celular = cursor.getString(cursor.getColumnIndex("celular"));
                @SuppressLint("Range") String telefonoFijo = cursor.getString(cursor.getColumnIndex("telefonoFijo"));
                @SuppressLint("Range") String nombres = cursor.getString(cursor.getColumnIndex("nombres"));
                @SuppressLint("Range") String apellidos = cursor.getString(cursor.getColumnIndex("apellidos"));
                @SuppressLint("Range") String correoSecundario = cursor.getString(cursor.getColumnIndex("correoSecundario"));
                @SuppressLint("Range") String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                @SuppressLint("Range") String otro1 = cursor.getString(cursor.getColumnIndex("otro1"));
                @SuppressLint("Range") String otro2 = cursor.getString(cursor.getColumnIndex("otro2"));
                @SuppressLint("Range") String otro3 = cursor.getString(cursor.getColumnIndex("otro3"));
                @SuppressLint("Range") String codigo = cursor.getString(cursor.getColumnIndex("codigo"));

                dato.setTipoCuenta(tipoCuenta);
                dato.setSitio(sitio);
                dato.setCorreo(correo);
                dato.setContra(contra);
                dato.setRut(rut);
                dato.setCelular(celular);
                dato.setTelefonoFijo(telefonoFijo);
                dato.setNombres(nombres);
                dato.setApellidos(apellidos);
                dato.setCorreoSecundario(correoSecundario);
                dato.setDireccion(direccion);
                dato.setOtro1(otro1);
                dato.setOtro2(otro2);
                dato.setOtro3(otro3);
                dato.setCodigo(codigo);
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