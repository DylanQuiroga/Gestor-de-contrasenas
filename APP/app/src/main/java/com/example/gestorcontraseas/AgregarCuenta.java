package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AgregarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuenta);

        TextView tipoCuenta = findViewById(R.id.tipoCuenta);
        TextView sitio = findViewById(R.id.sitioWeb);
        TextView correo = findViewById(R.id.correoElectronico);
        TextView contra = findViewById(R.id.contrasena);

    }
}