package com.example.projetointegrador;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projetointegrador.databinding.ActivityCadastrarUsuarioBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class cadastrarUsuario extends AppCompatActivity {

    private ActivityCadastrarUsuarioBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        iniciaToolbar();
        binding.btnCadastro.setOnClickListener(v-> validaDados());
    }

    private void validaDados(){
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editPassword.getText().toString().trim();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                binding.progressBar2.setVisibility(View.VISIBLE);
                criaContaFirebase(email, senha);
            }else {
                Toast.makeText(this, "Informe uma senha! ", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Informe seu e-mail! ", Toast.LENGTH_SHORT).show();
        }
    }

    private void criaContaFirebase(String email, String senha){
        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
                startActivity(new Intent(this, conf_cria_conta.class));
            }else {
                Toast.makeText(this, "Ocorreu um erro! ", Toast.LENGTH_SHORT).show();
            }
            binding.progressBar2.setVisibility(View.GONE);
        });
    }

    private void iniciaToolbar(){
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }
}