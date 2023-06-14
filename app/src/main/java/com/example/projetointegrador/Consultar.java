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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConsultarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        iniciaToolbar();
        WebView web = (WebView) findViewById(R.id.Web);
        Consultar.Ponte ponte = new Ponte(this);
        web.addJavascriptInterface(new Ponte(this), "Android");
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("file:///android_asset/consulta.html");
    }

    private void iniciaToolbar(){
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
        public String consultarResponsaveis() {
            DatabaseHelper banco = new DatabaseHelper(context);
            ArrayList<String> listaResponsaveis = banco.consultaResponsaveis();

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
            DatabaseHelper banco = new DatabaseHelper(context);
            ArrayList<String> listaMoradias = banco.consultaMoradias();

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

        @JavascriptInterface
        public void inserirResponsavel(String nome, String rg, String cpf, String nascimento, String telefone, double renda, String profissao, String aposentado, int dependente) {
            DatabaseHelper banco = new DatabaseHelper(context);
            long id = banco.insereResponsavel(nome, rg, cpf, nascimento, telefone, renda, profissao, aposentado, dependente);
            if (id > 0) {
                Toast.makeText(context, "Responsável inserido com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Erro na inserção do responsável!", Toast.LENGTH_LONG).show();
            }
        }

        @JavascriptInterface
        public void inserirMoradia(String rua, int numero, String cidade, String cep) {
            DatabaseHelper banco = new DatabaseHelper(context);
            long id = banco.insereMoradia(rua, numero, cidade, cep);
            if (id > 0) {
                Toast.makeText(context, "Moradia inserida com sucesso!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Erro na inserção da moradia!", Toast.LENGTH_LONG).show();
            }
        }
    }
}