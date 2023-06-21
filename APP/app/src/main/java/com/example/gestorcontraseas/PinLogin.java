package com.example.gestorcontraseas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class PinLogin extends AppCompatActivity implements View.OnClickListener {

    View view_01,view_02,view_03,view_04;
    Button btn_01,btn_02,btn_03,btn_04,btn_05,btn_06,btn_07,btn_08,btn_09,btn_00,btn_clear;
    ArrayList<String> numbers_list = new ArrayList<>();
    String passCode = "";
    String num_01,num_02,num_03,num_04;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_login);
        initializeComponents();
    }

    private void initializeComponents(){
        view_01 = findViewById(R.id.view_01);
        view_02 = findViewById(R.id.view_02);
        view_03 = findViewById(R.id.view_03);
        view_04 = findViewById(R.id.view_04);

        btn_01 = findViewById(R.id.btn_01);
        btn_02 = findViewById(R.id.btn_02);
        btn_03 = findViewById(R.id.btn_03);
        btn_04 = findViewById(R.id.btn_04);
        btn_05 = findViewById(R.id.btn_05);
        btn_06 = findViewById(R.id.btn_06);
        btn_07 = findViewById(R.id.btn_07);
        btn_08 = findViewById(R.id.btn_08);
        btn_09 = findViewById(R.id.btn_09);
        btn_00 = findViewById(R.id.btn_00);
        btn_clear = findViewById(R.id.btn_clear);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);
        btn_04.setOnClickListener(this);
        btn_05.setOnClickListener(this);
        btn_06.setOnClickListener(this);
        btn_07.setOnClickListener(this);
        btn_08.setOnClickListener(this);
        btn_09.setOnClickListener(this);
        btn_00.setOnClickListener(this);
        btn_clear.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_01:
                numbers_list.add("1");
                passNumber(numbers_list);
                break;
            case R.id.btn_02:
                numbers_list.add("2");
                passNumber(numbers_list);
                break;
            case R.id.btn_03:
                numbers_list.add("3");
                passNumber(numbers_list);
                break;
            case R.id.btn_04:
                numbers_list.add("4");
                passNumber(numbers_list);
                break;
            case R.id.btn_05:
                numbers_list.add("5");
                passNumber(numbers_list);
                break;
            case R.id.btn_06:
                numbers_list.add("6");
                passNumber(numbers_list);
                break;
            case R.id.btn_07:
                numbers_list.add("7");
                passNumber(numbers_list);
                break;
            case R.id.btn_08:
                numbers_list.add("8");
                passNumber(numbers_list);
                break;
            case R.id.btn_09:
                numbers_list.add("9");
                passNumber(numbers_list);
                break;
            case R.id.btn_00:
                numbers_list.add("0");
                passNumber(numbers_list);
                break;
            case R.id.btn_clear:
                numbers_list.clear();
                passNumber(numbers_list);
                break;
        }
    }

    private void passNumber(ArrayList<String> numbers_list) {
        if(numbers_list.size() == 0){
            view_01.setBackgroundResource(R.drawable.bg_view_grey_oval);
            view_02.setBackgroundResource(R.drawable.bg_view_grey_oval);
            view_03.setBackgroundResource(R.drawable.bg_view_grey_oval);
            view_04.setBackgroundResource(R.drawable.bg_view_grey_oval);
        } else {
            switch (numbers_list.size()){
                case 1:
                    num_01 = numbers_list.get(0);
                    view_01.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    break;
                case 2:
                    num_02 = numbers_list.get(1);
                    view_01.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    break;
                case 3:
                    num_03 = numbers_list.get(2);
                    view_01.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    break;
                case 4:
                    num_04 = numbers_list.get(3);
                    view_01.setBackgroundResource(R.drawable.bg_view_blue_oval);
                    passCode = num_01 + num_02 + num_03 + num_04;
                    if(getPassCode().length() == 0){
                        savePassCode(passCode);
                    } else {
                        matchPassCode();
                    }
                    break;
            }
        }
    }

    private void matchPassCode(){
        if(getPassCode().equals(passCode)){
            startActivity(new Intent(this,pruebaactivity.class));
        }else{
            Toast.makeText(this,"Pin incorrecto, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
        }
    }

    private SharedPreferences.Editor savePassCode(String passCode){
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("passcode",passCode);
        editor.commit();

        return editor;
    }

    private String getPassCode(){
        SharedPreferences preferences = getSharedPreferences("passcode_pref", Context.MODE_PRIVATE);
        return preferences.getString("passcode","");
    }
}