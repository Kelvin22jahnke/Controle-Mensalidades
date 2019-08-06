package com.example.controlemensalidades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroAlunosActivity extends AppCompatActivity {

    private EditText nome;
    private EditText endereco;
    private  EditText  bairro;
    private EditText  telefone;
    private EditText nascimento;
    private EditText  data;
    private  AlunoDAO dao;
    private Aluno aluno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_alunos);
        setTitle("Cadastro de Alunos");

        nome = findViewById(R.id.editNome);
        endereco = findViewById(R.id.editEndereco);
        bairro = findViewById(R.id.editBairro);
        telefone = findViewById(R.id.editTelefone);
        data  = findViewById(R.id.editData);
        nascimento = findViewById(R.id.editNascimento);
        dao = new AlunoDAO(this);
        //Coloca Mascara nos componentes
        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, "(##)#####-####"));
        data.addTextChangedListener(MaskEditUtil.mask(data , "##/##/####"));
        nascimento.addTextChangedListener(MaskEditUtil.mask(nascimento , "##/##/####"));

        Intent it = getIntent();
        if (it.hasExtra("aluno")){
            aluno = (Aluno) it.getSerializableExtra("aluno");
            nome.setText(aluno.getNome());
            endereco.setText(aluno.getEndereco());
            bairro.setText(aluno.getBairro());
            telefone.setText(aluno.getTelefone());
            data.setText(aluno.getData());
            nascimento.setText(aluno.getNascimento());
        }
    }

    public void salvar(View view){

        if (aluno == null){
            aluno = new Aluno();
            aluno.setNome(nome.getText().toString());
            aluno.setEndereco(endereco.getText().toString());
            aluno.setBairro(bairro.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            aluno.setData(data.getText().toString());
            aluno.setNascimento(nascimento.getText().toString());
            long id  = dao.inserir(aluno);
            Toast.makeText(this, "Aluno Cadastrado com ID: " + id, Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(this, ListAlunosActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else{
            aluno.setNome(nome.getText().toString());
            aluno.setEndereco(endereco.getText().toString());
            aluno.setBairro(bairro.getText().toString());
            aluno.setTelefone(telefone.getText().toString());
            aluno.setData(data.getText().toString());
            aluno.setNascimento(nascimento.getText().toString());
            dao.atualizar(aluno);
            Toast.makeText(this, "Aluno Atualizado Com Sucesso. ", Toast.LENGTH_SHORT).show();
            Intent intent  = new Intent(this, ListAlunosActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(this, ListAlunosActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
       startActivity(intent);
    }
}
