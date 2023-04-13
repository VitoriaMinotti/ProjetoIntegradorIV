package com.example.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

//    public void cadastrarLogin(View v){
//        Intent i = new Intent(this, CadastrarLogin.class);
//        startActivity(i);
//    }
}