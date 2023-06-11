package com.example.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projetointegrador.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.txtCriarConta.setOnClickListener(view -> {
            startActivity(new Intent(this, cadastrarUsuario.class));
        });
        binding.txtRecupera.setOnClickListener(view -> startActivity(new Intent(this, RecuperaConta.class)));
        binding.buttonLogin.setOnClickListener(view -> validaDados());
    }

    private void validaDados(){
        String email = binding.editEmail.getText().toString().trim();
        String senha = binding.editPassword.getText().toString().trim();

        if (!email.isEmpty()) {
            if (!senha.isEmpty()) {
                loginFirebase(email, senha);
            }else {
                Toast.makeText(this, "Informe uma senha! ", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Informe seu e-mail! ", Toast.LENGTH_SHORT).show();
        }
    }

    private void loginFirebase(String email, String senha){
        mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
                startActivity(new Intent(this, conf_cria_conta.class));
            }else {
                Toast.makeText(this, "Ocorreu um erro! ", Toast.LENGTH_SHORT).show();
            }
        });
    }



}