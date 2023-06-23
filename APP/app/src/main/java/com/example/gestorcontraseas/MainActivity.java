package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PinDB pinDB = new PinDB(this);

        boolean comprobar = pinDB.noHayValores();

        if (comprobar){
            finish();
            crear();
        }else {
            finish();
            login();
        }

    }

    public void login() {
        Intent intent = new Intent(this, PinLogin.class);
        startActivity(intent);
    }

    public void crear() {
        Intent intent = new Intent(this, PinCrear.class);
        startActivity(intent);
    }

}