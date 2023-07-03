package com.example.gestorcontraseas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ModificarDatos extends AppCompatActivity {

    Datos dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);

        Intent intent = getIntent();
        dato = (Datos) intent.getSerializableExtra("clave");

        EditText tipoCuentaET = findViewById(R.id.tipoCuenta);
        if(dato.getTipoCuenta() == null){
            tipoCuentaET.setText(dato.getTipoCuenta());
        }

        EditText sitioET = findViewById(R.id.sitioWeb);
        sitioET.setText(dato.getSitio());


        EditText correoET = findViewById(R.id.correoElectronico);
        correoET.setText(dato.getCorreo());

        EditText contraET = findViewById(R.id.contrasena);
        contraET.setText(PasswordEncryption.decrypt(dato.getContra()));

        EditText rutET = findViewById(R.id.rut);
        if(dato.getRut() != null){
            rutET.setText(dato.getRut());
        }

        EditText celularET = findViewById(R.id.celular);
        if(dato.getCelular() != null){
            celularET.setText(dato.getCelular());
        }

        EditText telefonoFijoET = findViewById(R.id.telefonoFijo);
        if(dato.getTelefonoFijo() != null){
            telefonoFijoET.setText(dato.getTelefonoFijo());
        }

        EditText nombresET = findViewById(R.id.nombres);
        if(dato.getNombres() != null){
            nombresET.setText(dato.getNombres());
        }

        EditText apellidosET = findViewById(R.id.apellidos);
        if(dato.getApellidos() != null){
            apellidosET.setText(dato.getApellidos());
        }

        EditText correoSecundarioET = findViewById(R.id.correoSecundario);
        if(dato.getCorreoSecundario() != null){
            correoSecundarioET.setText(dato.getCorreoSecundario());
        }

        EditText direccionET = findViewById(R.id.direccion);
        if(dato.getDireccion() != null){
            direccionET.setText(dato.getDireccion());
        }

        EditText otro1ET = findViewById(R.id.otro1);
        if(dato.getOtro1() != null){
            otro1ET.setText(dato.getOtro1());
        }

        EditText otro2ET = findViewById(R.id.otro2);
        if(dato.getOtro2() != null){
            otro2ET.setText(dato.getOtro2());
        }

        EditText otro3ET = findViewById(R.id.otro3);
        if(dato.getOtro3() != null){
            otro3ET.setText(dato.getOtro3());
        }

        Button BotonEditar = findViewById(R.id.Editar);
        Button BotonEliminar = findViewById(R.id.Eliminar);

        ImageView imagen = findViewById(R.id.imagengranaje);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ALERTA").setMessage("Hay algunos cuadros sin información. ¿Desea continuar?");

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("ALERTA").setMessage("Rellena por lo menos los campos importantes");

        BotonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipoCuenta = tipoCuentaET.getText().toString();
                String sitio = sitioET.getText().toString();
                String correo = correoET.getText().toString();
                String contra = contraET.getText().toString();
                String rut = rutET.getText().toString();
                String celular = celularET.getText().toString();
                String telefonoFijo = telefonoFijoET.getText().toString();
                String nombres = nombresET.getText().toString();
                String apellidos = apellidosET.getText().toString();
                String correoSecundario = correoSecundarioET.getText().toString();
                String direccion = direccionET.getText().toString();
                String otro1 = otro1ET.getText().toString();
                String otro2 = otro2ET.getText().toString();
                String otro3 = otro3ET.getText().toString();

                if(correo.isEmpty() || tipoCuenta.isEmpty() || contra.isEmpty() || sitio.isEmpty()){
                    builder1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog dialog = builder1.create();
                    dialog.show();

                }else if (rut.isEmpty() || celular.isEmpty() || telefonoFijo.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || correoSecundario.isEmpty() || direccion.isEmpty() || otro1.isEmpty() || otro2.isEmpty() || otro3.isEmpty()){
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActualizarDatosenLaDB(dato.getID(), tipoCuenta, sitio, correo, contra, rut, celular, telefonoFijo, nombres, apellidos, correoSecundario, direccion, otro1, otro2, otro3);
                            Snackbar mySnackbar = Snackbar.make(view, "Cuenta guardada correctamente", 3000);
                            mySnackbar.show();
                            finish();
                            menu();
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                }else {
                    ActualizarDatosenLaDB(dato.getID(), tipoCuenta, sitio, correo, contra, rut, celular, telefonoFijo, nombres, apellidos, correoSecundario, direccion, otro1, otro2, otro3);
                    Snackbar mySnackbar = Snackbar.make(view, "Cuenta ingresada correctamente", 3000);
                    mySnackbar.show();
                    finish();
                    menu();
                }
            }
        });

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genContra();
            }
        });

        BotonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EliminarDato();
                finish();
                menu();
            }
        });
    }

    private void ActualizarDatosenLaDB(String ID, String tipoCuenta, String sitio, String correo, String contra, String rut, String celular, String telefonoFijo, String nombres, String apellidos, String correoSecundario, String direccion, String otro1, String otro2, String otro3){
        MyDatabaseHelper conn;
        conn = new MyDatabaseHelper(this);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();

        String code = PasswordEncryption.encrypt(contra);
        String contraHuffman = Huffman.encode(contra);

        values.put("TipoCuenta", tipoCuenta);
        values.put("sitio", sitio);
        values.put("correo", correo);
        values.put("contra", contraHuffman);
        values.put("rut", rut);
        values.put("celular", celular);
        values.put("telefonoFijo", telefonoFijo);
        values.put("nombres", nombres);
        values.put("apellidos", apellidos);
        values.put("correoSecundario", correoSecundario);
        values.put("direccion", direccion);
        values.put("otro1",otro1);
        values.put("otro2",otro2);
        values.put("otro3",otro3);
        values.put("codigo", code);

        db.update("my_table", values, "id =?", new String[]{ID});
        conn.close();
        db.close();
        Toast.makeText(getApplicationContext(),"Se ha actualizado cuenta", Toast.LENGTH_SHORT).show();

    }
    private void EliminarDato(){
        MyDatabaseHelper conn;
        conn = new MyDatabaseHelper(this);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.delete("my_table", "id =?", new String[]{dato.getID()});
        conn.close();
        db.close();
        Toast.makeText(getApplicationContext(),"Se ha eliminado cuenta", Toast.LENGTH_SHORT).show();
    }


    public void menu() {
        Intent intent = new Intent(this, InterfazPrincipal.class);
        startActivity(intent);
    }

    public void genContra() {
        Intent intent = new Intent(this, GenContra.class);
        startActivity(intent);
    }

}