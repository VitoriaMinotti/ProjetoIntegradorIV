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

import com.example.projetointegrador.databinding.ActivityCadastrarBinding;
import com.example.projetointegrador.databinding.ActivityConsultarBinding;

import java.util.ArrayList;

public class Consultar extends AppCompatActivity {

    private ActivityConsultarBinding binding;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsultarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        iniciaToolbar();

        web = binding.Web;
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());

        // Adicione a interface JavaScript necessária para a comunicação com o código Java
        web.addJavascriptInterface(new Ponte2(), "Android");
        web.loadUrl("javascript:consultarResponsaveis('nome_do_responsavel')");

        web.loadUrl("file:///android_asset/consulta.html");
    }

    private void iniciaToolbar(){
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    class Ponte2 {

        @JavascriptInterface
        public String consultarResponsaveis(String nomeResponsavel) {
            DatabaseHelper banco = new DatabaseHelper(getApplicationContext());
            ArrayList<String> listaResponsaveis = banco.consultarResponsaveis(nomeResponsavel);

            String mensagem = "";
            if (listaResponsaveis != null) {
                for (int i = 0; i < listaResponsaveis.size(); i++) {
                    mensagem += listaResponsaveis.get(i);
                }
            } else {
                mensagem = "Não há dados de responsáveis!";
            }
            return mensagem;
        }


        @JavascriptInterface
        public String consultarMoradias() {
            DatabaseHelper banco = new DatabaseHelper(getApplicationContext());
            ArrayList<String> listaMoradias = banco.consultarMoradias();

            String mensagem = "";
            if (listaMoradias != null) {
                for (int i = 0; i < listaMoradias.size(); i++) {
                    mensagem += listaMoradias.get(i);
                }
            } else {
                mensagem = "Não há dados de moradias!";
            }
            return mensagem;
        }

    }
}
