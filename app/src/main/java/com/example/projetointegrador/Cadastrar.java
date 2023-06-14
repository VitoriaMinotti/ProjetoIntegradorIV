package com.example.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.example.projetointegrador.databinding.ActivityCadastrarBinding;
import java.util.ArrayList;

public class Cadastrar extends AppCompatActivity {

    private ActivityCadastrarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastrarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        iniciaToolbar();
        WebView web = (WebView) findViewById(R.id.Web);
        Ponte ponte = new Ponte(this);
        web.addJavascriptInterface(new Ponte(this), "Android");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());

        web.loadUrl("file:///android_asset/cadastro_confere.html");


    }

    private void iniciaToolbar() {
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    class Ponte {
        Context context;

        public Ponte(Context context) {
            this.context = context;
        }


        @JavascriptInterface
        public void inserirDados(String nomeResponsavel, String rgResponsavel, String cpfResponsavel,
                                 String nascimentoResponsavel, String foneResponsavel, String rendaResponsavel,
                                 String profissaoResponsavel, String aposentadoResponsavel, String dependenteResponsavel,
                                 String ruaMoradia, String numeroMoradia, String cidadeMoradia, String cepMoradia) {
            DatabaseHelper banco = new DatabaseHelper(context);

            try {
                // Tente inserir os dados no banco de dados
                banco.inserirResponsavel(nomeResponsavel, rgResponsavel, cpfResponsavel, nascimentoResponsavel, foneResponsavel, rendaResponsavel, profissaoResponsavel, aposentadoResponsavel, dependenteResponsavel);
                banco.inserirMoradia(ruaMoradia, numeroMoradia, cidadeMoradia, cepMoradia);

                Toast.makeText(context, "Dados enviados com sucesso", Toast.LENGTH_SHORT).show();

                // Redireciona para a atividade anterior
                finish();
            } catch (Exception e) {
                // Captura a exceção e exibe uma mensagem de erro
                Toast.makeText(context, "Erro ao enviar os dados: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
}

