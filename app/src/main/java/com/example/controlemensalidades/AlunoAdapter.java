package com.example.controlemensalidades;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AlunoAdapter extends BaseAdapter {

    private List<Aluno>alunos;
    private Activity activity;

    public AlunoAdapter(Activity activity, List<Aluno>alunos) {
        this.activity =activity;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_aluno, viewGroup, false);
        TextView nome = v.findViewById(R.id.txt_nome);
        TextView endereco = v.findViewById(R.id.txt_endereco);
        TextView bairro = v.findViewById(R.id.txt_bairro);
        TextView telefone = v.findViewById(R.id.txt_telefone);
        TextView data = v.findViewById(R.id.txt_data);
        TextView nascimento = v.findViewById(R.id.txt_Nascimento);
        Aluno a = alunos.get(i);
        nome.setText(a.getNome());
        endereco.setText(a.getEndereco());
        bairro.setText(a.getBairro());
        telefone.setText(a.getTelefone());
        data.setText(a.getData());
        nascimento.setText(a.getNascimento());
        return v;
    }
}
