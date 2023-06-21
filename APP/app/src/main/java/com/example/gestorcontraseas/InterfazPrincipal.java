package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class InterfazPrincipal extends AppCompatActivity {
    ImageButton BotonBuscar, BotonAgregar;
    TextView Busqueda;
    RecyclerView Tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_principal);
        //Interfaz para buscar
        BotonBuscar = findViewById(R.id.BotonBusqueda);
        Busqueda = findViewById(R.id.BarraBusqueda);
        //Interfaz para agregar
        BotonAgregar = findViewById(R.id.Agregar);
        Tabla = findViewById(R.id.Tabla);
    }

    public void Listener(View view){
        try{
            if(view.getId() == BotonAgregar.getId()){
                Intent intent = new Intent(this, AgregarCuenta.class);
                startActivity(intent);
                finish();
            }
            else if(view.getId() == BotonBuscar.getId()){
                if(Busqueda.getText()==null){
                    //Avisar error
                }
                else{
                    //Se busca
                    Busqueda.setText(null);
                }

            }
            //else if revisar datos de tabla

        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
}