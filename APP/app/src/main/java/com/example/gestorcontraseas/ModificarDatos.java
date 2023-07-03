package com.example.gestorcontraseas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class ModificarDatos extends AppCompatActivity {

    Datos dato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);

        Intent intent = getIntent();
        dato = (Datos) intent.getSerializableExtra("clave");
        int indice = (int) intent.getIntExtra("posicion",0);

        EditText tipoCuentaET = findViewById(R.id.tipoCuenta);
        if(dato.getTipoCuenta() == null){
            tipoCuentaET.setText(dato.getTipoCuenta());
        }

        EditText sitioET = findViewById(R.id.sitioWeb);
        sitioET.setText(dato.getSitio());


        EditText correoET = findViewById(R.id.correoElectronico);
        correoET.setText(dato.getCorreo());

        EditText contraET = findViewById(R.id.contrasena);
        contraET.setText(dato.getContra());

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
                            //ActualizarDatosenLaDB(tipoCuenta, sitio, correo, contra, rut, celular, telefonoFijo, nombres, apellidos, correoSecundario, direccion, otro1, otro2, otro3);
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
                    //ActualizarDatosenLaDB(tipoCuenta, sitio, correo, contra, rut, celular, telefonoFijo, nombres, apellidos, correoSecundario, direccion, otro1, otro2, otro3);
                    Snackbar mySnackbar = Snackbar.make(view, "Cuenta ingresada correctamente", 3000);
                    mySnackbar.show();
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
                EliminarDato(indice);
                finish();
                menu();
            }
        });
    }

    private void EliminarDato(int i){
        MyDatabaseHelper conn;
        conn = new MyDatabaseHelper(this);
        SQLiteDatabase db = conn.getWritableDatabase();
        String parametros = Integer.toString(i);
        db.delete("my_table", "id =?", new String[]{dato.getID()});
        conn.close();
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