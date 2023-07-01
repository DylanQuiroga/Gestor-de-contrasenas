package com.example.gestorcontraseas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PinDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PinDB.db";
    private static final int DATABASE_VERSION = 1;

    public PinDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE my_table (id INTEGER PRIMARY KEY AUTOINCREMENT, Pin TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);
    }

    public boolean existeTabla(String nombreTabla) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name=?",
                new String[]{nombreTabla}
        );

        boolean existe = false;
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                existe = true;
            }
            cursor.close();
        }

        return existe;
    }

    public boolean verificarPin(String pin) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM my_table WHERE Pin=?",
                new String[]{PasswordEncryption.encrypt(pin)}
        );

        boolean coincide = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // Se encontró una coincidencia
                coincide = true;
            }
            cursor.close();
        }

        return coincide;
    }

    public boolean noHayValores() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM my_table",
                null
        );

        boolean noHayValores = true;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    noHayValores = false;
                }
            }
            cursor.close();
        }

        return noHayValores;
    }

}
