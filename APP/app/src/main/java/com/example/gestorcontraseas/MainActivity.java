package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    String LLAVE = "ASfnHF56RACTERFDQUIO1239";

    private static final int ENCRYPT = 1;
    private static final int DECRYPT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button boton = findViewById(R.id.boton);
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = "Hola cabrones jejeje";
                String st1 = null;
                String st2 = null;

                try {
                    st1 = CryptoUtils.encrypt(texto,LLAVE);
                    Log.d("MainActivity", st1);
                    st2 = CryptoUtils.decrypt(st1,LLAVE);
                    Log.d("MainActivity", st2);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                /*values.put("tipoCuenta", 1);
                values.put("sitio", "steam.com");
                values.put("correo", "teamcompleto123@gmail.com");
                values.put("contra", "123456789");
                values.put("nombreUsuario", "DeadCell");
                values.put("rut", "12.345.678-9");
                values.put("celular","+56912345678");

                db.insert("my_table",null,values);

                List<String> records = dbHelper.getAllRecords();

                for(String record : records){
                    System.out.println(record);
                }*/
            }
        });
    }

    private void imprimirDB(List<String> records){
        for(String record : records) System.out.println(record);
    }

    private void ingresarDatos(String tc, String st, String ce, String cn, String nu, String rut, String cel, String tf, String ns, String as, String cs, String dr, String o1, String o2, String o3, SQLiteDatabase DB){
        ContentValues values = new ContentValues();
        values.put("TipoCuenta", tc);
        values.put("sitio", st);
        values.put("correo", ce);
        values.put("contra", cn);
        values.put("nombreUsuario", nu);
        values.put("rut", rut);
        values.put("celular",cel);
        values.put("telefonoFijo", tf);
        values.put("nombres", ns);
        values.put("apellidos", as);
        values.put("correoSecundario", cs);
        values.put("direccion", dr);
        values.put("otro1",o1);
        values.put("otro2",o2);
        values.put("otro3",o3);
        DB.insert("my_table", null, values);
    }

}