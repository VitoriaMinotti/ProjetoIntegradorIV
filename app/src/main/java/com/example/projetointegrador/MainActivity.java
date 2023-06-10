package com.example.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastrar(View v){
        Intent i = new Intent(this, Cadastrar.class);
        startActivity(i);
    }
//
//    public void consultar(View v){
//        Intent i = new Intent(this, consultar.class);
//        startActivity(i);
//    }
//
//    public void relatorio(View v){
//        Intent i = new Intent(this, relatorio.class);
//        startActivity(i);
//    }
}