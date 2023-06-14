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

    public long insereResponsavel(String nome, String rg, String cpf, String nascimento, String telefone, double renda, String profissao, String aposentado, int dependente) {
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
        db.close();
        return id;
    }

    public ArrayList<String> consultaResponsaveis() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM responsavel";
        ArrayList<String> resultado = null;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            resultado = new ArrayList<String>();
            do {
                String nome = cursor.getString(1);
                String rg = cursor.getString(2);
                String cpf = cursor.getString(3);
                String nascimento = cursor.getString(4);
                String telefone = cursor.getString(5);
                double renda = cursor.getDouble(6);
                String profissao = cursor.getString(7);
                String aposentado = cursor.getString(8);
                int dependente = cursor.getInt(9);

                resultado.add("Nome: " + nome + "\nRG: " + rg + "\nCPF: " + cpf + "\nNascimento: " + nascimento +
                        "\nTelefone: " + telefone + "\nRenda: " + renda + "\nProfissão: " + profissao +
                        "\nAposentado: " + aposentado + "\nDependente: " + dependente);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return resultado;
    }

    public long insereMoradia(String rua, int numero, String cidade, String cep) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rua_moradia", rua);
        values.put("numero_moradia", numero);
        values.put("cidade_moradia", cidade);
        values.put("cep_moradia", cep);

        long id = db.insert("moradia", null, values);
        db.close();
        return id;
    }

    public ArrayList<String> consultaMoradias() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM moradia";
        ArrayList<String> resultado = null;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            resultado = new ArrayList<String>();
            do {
                String rua = cursor.getString(1);
                int numero = cursor.getInt(2);
                String cidade = cursor.getString(3);
                String cep = cursor.getString(4);

                resultado.add("Rua: " + rua + "\nNúmero: " + numero + "\nCidade: " + cidade + "\nCEP: " + cep);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return resultado;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crie as tabelas necessárias no método onCreate
        db.execSQL("CREATE TABLE moradia (id INTEGER PRIMARY KEY, rua_moradia TEXT, numero_moradia INTEGER, cidade_moradia TEXT, cep_moradia TEXT)");
        db.execSQL("CREATE TABLE responsavel (id INTEGER PRIMARY KEY, nome_responsavel TEXT, rg_responsavel TEXT, cpf_responsavel TEXT, nascimento_responsavel TEXT, fone_responsavel TEXT, renda_responsavel REAL, profissao_responsavel TEXT, aposentado_responsavel TEXT, dependente_responsavel INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualize as tabelas, se necessário, no método onUpgrade
        db.execSQL("DROP TABLE IF EXISTS moradia");
        db.execSQL("DROP TABLE IF EXISTS responsavel");
        onCreate(db);
    }
}

