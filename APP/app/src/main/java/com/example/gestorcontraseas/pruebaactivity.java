package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class pruebaactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pruebaactivity);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startActivity(new Intent(this,PinLogin.class));
    }
}