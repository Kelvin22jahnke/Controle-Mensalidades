package com.example.controlemensalidades;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class AlunoMensalidadeActivity extends AppCompatActivity {
    private ListView listView;
    private MensalidadeDAO daoMen;
    private List<Mensalidade>mensalidades;
    private List<Mensalidade> MensalidadesFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_mensalidade);
        setTitle("Lista Pagamentos de Alunos");
        listView = findViewById(R.id.lista_aluno_mensalidade);
        daoMen = new MensalidadeDAO(this);
        mensalidades = daoMen.obterTodasMensalidadesPagas();
        MensalidadesFiltradas.addAll(mensalidades);
        AlunoMensalidadeAdapter adaptador = new AlunoMensalidadeAdapter(this, MensalidadesFiltradas);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }
}
