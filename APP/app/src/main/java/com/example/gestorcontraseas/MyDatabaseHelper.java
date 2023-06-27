package com.example.gestorcontraseas;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE my_table (id INTEGER PRIMARY KEY AUTOINCREMENT, tipoCuenta TEXT, sitio TEXT, correo TEXT, contra TEXT, rut TEXT, celular TEXT, telefonoFijo TEXT, nombres TEXT, apellidos TEXT, correoSecundario TEXT, direccion TEXT, otro1 TEXT, otro2 TEXT, otro3 TEXT, codigo TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);
    }

    public List<String> getAllRecords() {
        List<String> records = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM my_table", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex("id"));
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

                String record = "ID: " + id + " | Tipo de cuenta: " + tipoCuenta + " | Sitio: " + sitio + " | Correo: " + correo + " | Contraseña: " + contra + " | Rut: " + rut + " | Celular: " + celular + " | Teléfono fijo: " + telefonoFijo + " | Nombres: " + nombres + " | Apellidos: " + apellidos + " | Correo secundario: " + correoSecundario + " | Dirección: " + direccion + " | Otro 1: " + otro1 + " | Otro 2: " + otro2 + " | Otro 3: " + otro3 + " | Codigo: " + codigo;
                records.add(record);
            } while (cursor.moveToNext());
        }

        return records;
    }

}
