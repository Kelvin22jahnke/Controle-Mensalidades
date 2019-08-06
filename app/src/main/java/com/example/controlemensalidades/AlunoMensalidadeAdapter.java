package com.example.controlemensalidades;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
public class AlunoMensalidadeAdapter extends BaseAdapter {
    private List<Mensalidade> mensalidades;
    private Activity activity;

    public AlunoMensalidadeAdapter(Activity activity, List<Mensalidade>mensalidades) {
        this.activity =activity;
        this.mensalidades = mensalidades;
    }

    @Override
    public int getCount() {
        return mensalidades.size();
    }

    @Override
    public Object getItem(int i) {
        return mensalidades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mensalidades.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = activity.getLayoutInflater().inflate(R.layout.item_aluno_mensalidade , viewGroup, false);
        TextView  alunonome = v.findViewById(R.id.txt_nome);
        TextView datapagamento = v.findViewById(R.id.txt_Pago);
        TextView Valorpagamento = v.findViewById(R.id.txt_Valor_Pago);
        Mensalidade m = mensalidades.get(i);
        alunonome.setText(m.getNomealuno());
        datapagamento.setText(m.getDatapagamento());
        Valorpagamento.setText(m.getValor());
        return v;
    }
}
