package com.example.controlemensalidades;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public AlunoDAO (Context context ){
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public Long inserir(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("endereco",aluno.getEndereco());
        values.put("bairro",aluno.getBairro());
        values.put("telefone",aluno.getTelefone());
        values.put("data",aluno.getData());
        values.put("nascimento",aluno.getNascimento());
        return  banco.insert("aluno", null, values);
    }
    public List<Aluno>obterTodos(){
        List<Aluno>alunos = new ArrayList<>();
        Cursor cursor = banco.query("aluno", new String[]{"id", "nome", "endereco", "bairro", "telefone", "data","nascimento"},
         null, null, null, null, null);

        while (cursor.moveToNext()){
            Aluno a = new Aluno();
            a.setId(cursor.getInt(0));
            a.setNome(cursor.getString(1));
            a.setEndereco(cursor.getString(2));
            a.setBairro(cursor.getString(3));
            a.setTelefone(cursor.getString(4));
            a.setData(cursor.getString(5));
            a.setNascimento(cursor.getString(6));
            alunos.add(a);
        }
        return  alunos;
    }

    public void excluir(Aluno a){
        banco.delete("aluno", "id= ?", new String[]{a.getId().toString()});
    }

    //Método atualizar
    public void atualizar(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("endereco",aluno.getEndereco());
        values.put("bairro",aluno.getBairro());
        values.put("telefone",aluno.getTelefone());
        values.put("data",aluno.getData());
        values.put("nascimento",aluno.getNascimento());
        banco.update("aluno", values,
                "id= ?", new  String[]{aluno.getId().toString()});
    }
}
