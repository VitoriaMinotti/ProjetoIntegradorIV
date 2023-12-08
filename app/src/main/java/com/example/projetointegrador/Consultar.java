
package com.example.projetointegrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.util.Log;
import com.example.projetointegrador.databinding.ActivityConsultarBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Após a página ser carregada, chame a função JavaScript
                web.loadUrl("javascript:consultarResponsaveis('nome_do_responsavel')");
            }
        });
        // Adicione a interface JavaScript necessária para a comunicação com o código Java
        web.addJavascriptInterface(new Ponte2(), "Android");

        // Copie os arquivos do android_asset para um diretório temporário externo
        String path = getExternalFilesDir(null).getPath() + "/temp/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

// Copie o arquivo bootstrap.bundle.min.js para o diretório temporário
        try {
            InputStream in = getAssets().open("bootstrap.bundle.min.js");
            OutputStream out = new FileOutputStream(path + "bootstrap.bundle.min.js");
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Webview - ", "Webview: " + web);

        web.loadUrl("file:///android_asset/consulta.html");
        // Configuração do botão de impressão
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWebPrintJob(web);
            }
        });
    }

    private void iniciaToolbar(){
        Toolbar toolbar = binding.toolbar;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }
    private void createWebPrintJob(WebView webView) {
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();
        String jobName = getString(R.string.app_name) + " Print Test";
        printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
    }
    class Ponte2 {

        @JavascriptInterface
        public String consultarResponsaveis(String nomeResponsavel) {
            Log.d("Ponte2 - ", "Consultar Responsaveis: " + nomeResponsavel);
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

    }

}