package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenContra extends AppCompatActivity {
    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_-+=<>?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen_contra);

        Button boton = findViewById(R.id.buttongen);

        CheckBox mayusculas = findViewById(R.id.checkBox);
        CheckBox minusculas = findViewById(R.id.checkBox2);
        CheckBox numeros = findViewById(R.id.checkBox3);
        CheckBox simbolos = findViewById(R.id.checkBox4);

        EditText tamano = findViewById(R.id.editTextPhone);
        EditText textoFinal = findViewById(R.id.textoFinal);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textoFinal.setText("");
                if(tamano.getText().toString().isEmpty()){
                    Snackbar.make(view, "Ingrese la longitud de la contraseña", 3000).show();
                }else {
                    int longitud = Integer.parseInt(tamano.getText().toString());

                    if(longitud > 50){
                        longitud = 50;
                        Snackbar.make(view, "Limite de la longitud superado (50)", 3000).show();
                    }

                    Random random = new SecureRandom();
                    StringBuilder contra = new StringBuilder(longitud);
                    List<String> charCategories = new ArrayList<>();

                    if(mayusculas.isChecked()){
                        charCategories.add(UPPERCASE_CHARS);
                    }
                    if(minusculas.isChecked()){
                        charCategories.add(LOWERCASE_CHARS);
                    }
                    if(numeros.isChecked()){
                        charCategories.add(NUMBERS);
                    }
                    if(simbolos.isChecked()){
                        charCategories.add(SYMBOLS);
                    }
                    if(mayusculas.isChecked() == false && minusculas.isChecked() == false && numeros.isChecked() == false && simbolos.isChecked() == false){
                        Random rd = new Random();
                        int rand = rd.nextInt(4)+1;

                        if (rand == 1){
                            charCategories.add(UPPERCASE_CHARS);
                        }else if(rand == 2){
                            charCategories.add(LOWERCASE_CHARS);
                        }else if(rand == 3){
                            charCategories.add(NUMBERS);
                        }else if(rand == 4){
                            charCategories.add(SYMBOLS);
                        }
                    }

                    for (int i = 0; i < longitud; i++) {
                        String charCategory = charCategories.get(random.nextInt(charCategories.size()));
                        int position = random.nextInt(charCategory.length());
                        contra.append(charCategory.charAt(position));
                    }

                    textoFinal.setText(contra);

                }
            }
        });

    }
}