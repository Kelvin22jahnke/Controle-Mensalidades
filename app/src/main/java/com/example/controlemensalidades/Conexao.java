package com.example.controlemensalidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final  int version = 2;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table aluno(id integer primary key autoincrement, " +
                "nome varchar(50), endereco varchar(50), bairro varchar(50), telefone varchar(15), data varchar(10))");
        db.execSQL("create table mensalidade(id integer primary key autoincrement, " +
                "nomealuno varchar(50), datapagamento varchar(10), valor varchar(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("alter table aluno add column nascimento varchar(10)");
    }
}
