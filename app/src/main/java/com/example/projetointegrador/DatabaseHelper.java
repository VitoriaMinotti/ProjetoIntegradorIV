package com.example.projetointegrador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "pilar_social", null, 1);
    }

    public long inserirResponsavel(String nome, String rg, String cpf, String nascimento, String telefone, String renda, String profissao, String aposentado, String dependente) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome_responsavel", nome);
        values.put("rg_responsavel", rg);
        values.put("cpf_responsavel", cpf);
        values.put("nascimento_responsavel", nascimento);
        values.put("fone_responsavel", telefone);
        values.put("renda_responsavel", renda);
        values.put("profissao_responsavel", profissao);
        values.put("aposentado_responsavel", aposentado);
        values.put("dependente_responsavel", dependente);

        long id = db.insert("responsavel", null, values);

        return id;
    }

    public ArrayList<String> consultarResponsaveis(String nomeResponsavel) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM responsavel WHERE nome_responsavel LIKE ?";
        ArrayList<String> resultado = null;

        Cursor cursor = db.rawQuery(sql, new String[]{"%" + nomeResponsavel + "%"});
        if (cursor.moveToFirst()) {
            resultado = new ArrayList<String>();
            do {
                String nome = cursor.getString(1);
                String rg = cursor.getString(2);
                String cpf = cursor.getString(3);
                String nascimento = cursor.getString(4);
                String telefone = cursor.getString(5);
                String renda = cursor.getString(6);
                String profissao = cursor.getString(7);
                String aposentado = cursor.getString(8);
                String dependente = cursor.getString(9);

                resultado.add("Nome: " + nome + "\n RG: " + rg + "\n CPF: " + cpf + "\n Nascimento: " + nascimento +
                        "\n Telefone: " + telefone + "\n Renda: " + renda + "\n Profissão: " + profissao +
                        "\n Aposentado: " + aposentado + "\n Dependente: " + dependente);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return resultado;
    }

    public long inserirMoradia(String rua, String numero, String cidade, String cep) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rua_moradia", rua);
        values.put("numero_moradia", numero);
        values.put("cidade_moradia", cidade);
        values.put("cep_moradia", cep);

        long id = db.insert("moradia", null, values);

        return id;
    }

    public ArrayList<String> consultarMoradias() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM moradia";
        ArrayList<String> resultado = null;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            resultado = new ArrayList<String>();
            do {
                String rua = cursor.getString(1);
                String numero = cursor.getString(2);
                String cidade = cursor.getString(3);
                String cep = cursor.getString(4);

                resultado.add("Rua: " + rua + "\n Número: " + numero + "\n Cidade: " + cidade + "\n CEP: " + cep);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return resultado;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crie as tabelas necessárias no método onCreate
        db.execSQL("CREATE TABLE moradia (id INTEGER PRIMARY KEY, rua_moradia TEXT, numero_moradia TEXT, cidade_moradia TEXT, cep_moradia TEXT)");
        db.execSQL("CREATE TABLE responsavel (id INTEGER PRIMARY KEY, nome_responsavel TEXT, rg_responsavel TEXT, cpf_responsavel TEXT, nascimento_responsavel TEXT, fone_responsavel TEXT, renda_responsavel TEXT, profissao_responsavel TEXT, aposentado_responsavel TEXT, dependente_responsavel TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualize as tabelas, se necessário, no método onUpgrade
        db.execSQL("DROP TABLE IF EXISTS moradia");
        db.execSQL("DROP TABLE IF EXISTS responsavel");
        onCreate(db);
    }


}

