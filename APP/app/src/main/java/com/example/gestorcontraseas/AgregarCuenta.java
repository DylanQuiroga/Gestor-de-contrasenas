package com.example.gestorcontraseas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class AgregarCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuenta);

        EditText tipoCuentaET = findViewById(R.id.tipoCuenta);
        EditText sitioET = findViewById(R.id.sitioWeb);
        EditText correoET = findViewById(R.id.correoElectronico);
        EditText contraET = findViewById(R.id.contrasena);
        EditText rutET = findViewById(R.id.rut);
        EditText celularET = findViewById(R.id.celular);
        EditText telefonoFijoET = findViewById(R.id.telefonoFijo);
        EditText nombresET = findViewById(R.id.nombres);
        EditText apellidosET = findViewById(R.id.apellidos);
        EditText correoSecundarioET = findViewById(R.id.correoSecundario);
        EditText direccionET = findViewById(R.id.direccion);
        EditText otro1ET = findViewById(R.id.otro1);
        EditText otro2ET = findViewById(R.id.otro2);
        EditText otro3ET = findViewById(R.id.otro3);

        Button agregar = findViewById(R.id.agregar);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ALERTA").setMessage("Hay algunos cuadros sin información. ¿Desea continuar?");

        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("ALERTA").setMessage("Rellena por lo menos los campos importantes");

        agregar.setOnClickListener(new View.OnClickListener() {
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

                if(correo.isEmpty() || tipoCuenta.isEmpty() || contra.isEmpty()){
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
                            ingresarDatosALaDB(tipoCuenta, sitio, correo, contra, rut, celular, telefonoFijo, nombres, apellidos, correoSecundario, direccion, otro1, otro2, otro3);
                            Snackbar mySnackbar = Snackbar.make(view, "Cuenta ingresada correctamente", 3000);
                            mySnackbar.show();
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
                    ingresarDatosALaDB(tipoCuenta, sitio, correo, contra, rut, celular, telefonoFijo, nombres, apellidos, correoSecundario, direccion, otro1, otro2, otro3);
                    Snackbar mySnackbar = Snackbar.make(view, "Cuenta ingresada correctamente", 3000);
                    mySnackbar.show();
                }

            }
        });

    }

    private void ingresarDatosALaDB(String tipoCuenta, String sitio, String correo, String contra, String rut, String celular, String telefonoFijo, String nombres, String apellidos, String correoSecundario, String direccion, String otro1, String otro2, String otro3){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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
        db.insert("my_table", null, values);

        List<String> records = dbHelper.getAllRecords();

        for(String record : records){
            System.out.println(record);
        }

        /*String st0 = PasswordEncryption.decrypt("xEr93JAll71TVEa3Kfewgw==");
        HuffmanNode root = Huffman.buildHuffmanTree(Huffman.getFrequencies(st0));
        String st2 = Huffman.decode("01010111001110011100", root);

        System.out.println(st0);
        System.out.println(st2);*/
    }

}