package com.example.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projetointegrador.databinding.ActivityCadastrarUsuarioBinding;
import com.example.projetointegrador.databinding.ActivityRecuperaContaBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperaConta extends AppCompatActivity {

    private ActivityRecuperaContaBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperaContaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        iniciaToolbar();
        binding.btnRecuperaConta.setOnClickListener(v-> validaDados());
    }

    private void validaDados(){
        String email = binding.editEmail.getText().toString().trim();

        if (!email.isEmpty()) {
            recuperaContaFirebase(email);
        }else {
            binding.editEmail.setError("Informe seu e-mail!");
            Toast.makeText(this, "Informe seu e-mail! ", Toast.LENGTH_SHORT).show();
        }
    }

    private void recuperaContaFirebase(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(RecuperaConta.this, "Verifique seu E-mail!", Toast.LENGTH_SHORT).show();
            } else {
                binding.btnRecuperaConta.setError("!");
                Toast.makeText(RecuperaConta.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    private void iniciaToolbar(){
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }


}