package com.example.controlemensalidades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CadastroMensalidadesActivity extends AppCompatActivity {
    private EditText datapagamento;
    private EditText valor;
    private EditText nomealuno;
    private AlunoDAO dao;
    private Mensalidade mensalidade;
    private MensalidadeDAO daoMen;
    private Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mensalidades);
        setTitle("Cadastro de Mensalidades");

        nomealuno = findViewById(R.id.editNomeAluno);
        datapagamento = findViewById(R.id.editDataMensalidade);
        valor = findViewById(R.id.editValor);
        datapagamento.addTextChangedListener(MaskEditUtil.mask(datapagamento , "##/##/####"));
        valor.addTextChangedListener(MaskEditUtil.mask(valor, "##.##"));

        daoMen = new MensalidadeDAO(this);

        dao = new AlunoDAO(this);

        //Recebe nome do aluno da listagem de alunos
        Intent it = getIntent();
        if (it.hasExtra("aluno_nome")){
            aluno = (Aluno) it.getSerializableExtra("aluno_nome");
            nomealuno.setText(aluno.getNome());
        }

        //Recebe os dados da Mensalidade da listagem para Atualizar
        Intent ite = getIntent();
        if (ite.hasExtra("mensalidade")){
            mensalidade = (Mensalidade) it.getSerializableExtra("mensalidade");
            nomealuno.setText(mensalidade.getNomealuno());
            datapagamento.setText(mensalidade.getDatapagamento());
            valor.setText(mensalidade.getValor());
        }

    }
    public void salvarmensalidade(View view){

        if (mensalidade == null){
            mensalidade = new Mensalidade();
            mensalidade.setNomealuno(nomealuno.getText().toString());
            mensalidade.setDatapagamento(datapagamento.getText().toString());
            mensalidade.setValor(valor.getText().toString());

            long id  = daoMen.inserirMensalidade(mensalidade);
            Toast.makeText(this, "Mensalidade Cadastrada com ID: " + id, Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(this, ListMensalidadesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
             startActivity(intent);
        }else{
            mensalidade.setNomealuno(nomealuno.getText().toString());
            mensalidade.setDatapagamento(datapagamento.getText().toString());
            mensalidade.setValor(valor.getText().toString());
            daoMen.atualizarMensalidade(mensalidade);
            Toast.makeText(this, "Mensalidade Atualizada Com Sucesso. ", Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(this, ListMensalidadesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }
}
