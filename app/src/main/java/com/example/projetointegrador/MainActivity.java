package com.example.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.projetointegrador.databinding.ActivityCadastrarBinding;
import com.example.projetointegrador.databinding.ActivityLoginBinding;
import com.example.projetointegrador.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonSair.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this,Login.class));
        });
    }

    public void cadastrar(View v){
        Intent i = new Intent(this, Cadastrar.class);
        startActivity(i);
    }
    public void consultar(View v){
        Intent i = new Intent(this, Consultar.class);
        startActivity(i);
    }

}