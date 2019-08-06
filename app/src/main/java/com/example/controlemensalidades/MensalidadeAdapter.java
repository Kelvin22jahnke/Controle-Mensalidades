package com.example.controlemensalidades;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MensalidadeAdapter extends BaseAdapter {
    private List<Mensalidade> mensalidades;
    private Activity activity;

    public MensalidadeAdapter(Activity activity, List<Mensalidade>mensalidades) {
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
        View v = activity.getLayoutInflater().inflate(R.layout.item_mensalidade, viewGroup, false);
        TextView  nomealuno = v.findViewById(R.id.txt_nomealuno);
        TextView datapagamento = v.findViewById(R.id.txt_datapagamento);
        TextView valor = v.findViewById(R.id.txt_valor);
        Mensalidade m = mensalidades.get(i);
        nomealuno.setText(m.getNomealuno());
        datapagamento.setText(m.getDatapagamento());
        valor.setText(m.getValor());
        return v;
    }
}
