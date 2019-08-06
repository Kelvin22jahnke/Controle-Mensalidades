package com.example.controlemensalidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class MensalidadeDAO {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public MensalidadeDAO(Context context ){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }
   public Long inserirMensalidade(Mensalidade  mensalidade){
       ContentValues values = new ContentValues();
        values.put("nomealuno",mensalidade.getNomealuno());
        values.put("datapagamento",mensalidade.getDatapagamento());
        values.put("valor",mensalidade.getValor());
        return  banco.insert("mensalidade", null, values);
   }

   public List<Mensalidade> obterTodasMensalidades(){
        List<Mensalidade>mensalidades = new ArrayList<>();
        Cursor cursor = banco.query("mensalidade", new String[]{"id", "nomealuno", "datapagamento", "valor"},
                null, null, null, null, null);

        while (cursor.moveToNext()){
            Mensalidade m = new Mensalidade();
            m.setId(cursor.getInt(0));
            m.setNomealuno(cursor.getString(1));
            m.setDatapagamento(cursor.getString(2));
            m.setValor(cursor.getString(3));
            mensalidades.add(m);
        }
        return  mensalidades;
    }

    public List<Mensalidade> obterTodasMensalidadesPagas(){
        String sql = "select * from mensalidade";
        Cursor cursor = banco.rawQuery(sql,null);
        List<Mensalidade>mensalidades = new ArrayList<>();
        while (cursor.moveToNext()){
            Mensalidade m = new Mensalidade();
            m.setId(cursor.getInt(0));
            m.setNomealuno(cursor.getString(1));
            m.setDatapagamento(cursor.getString(2));
            m.setValor(cursor.getString(3));
            mensalidades.add(m);
        }
        return  mensalidades;
    }
    public void excluirMensalidade(Mensalidade m){
       banco.delete("mensalidade", "id= ?", new String[]{m.getId().toString()});
    }

    //Método atualizar
    public void atualizarMensalidade(Mensalidade mensalidade){
       ContentValues values = new ContentValues();
       values.put("nomealuno", mensalidade.getNomealuno());
       values.put("datapagamento",mensalidade.getDatapagamento());
       values.put("valor",mensalidade.getValor());
        banco.update("mensalidade", values,
                "id= ?", new  String[]{mensalidade.getId().toString()});
   }



}
