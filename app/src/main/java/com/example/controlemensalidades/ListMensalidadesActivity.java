package com.example.controlemensalidades;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class ListMensalidadesActivity extends AppCompatActivity {
    private ListView listView;
    private MensalidadeDAO daoMen;
    private List<Mensalidade>mensalidades;
    private List<Mensalidade> MensalidadesFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mensalidades);
        setTitle("Lista de Mensalidades");

        listView = findViewById(R.id.lista_mensalidades);
        daoMen = new MensalidadeDAO(this);
        mensalidades = daoMen.obterTodasMensalidades();
        MensalidadesFiltradas.addAll(mensalidades);
        MensalidadeAdapter adaptador = new MensalidadeAdapter(this, MensalidadesFiltradas);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

    }

    // Menu no ListMensalidades
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal_mensalidade, menu);

        SearchView sv =(SearchView) menu.findItem(R.id.app_bar_searchMensalidade).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                procuraMensalidade(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto_mensalidade, menu);
    }


    //Método Cadastrar
    public void cadastrarmensalidades(MenuItem menuItem){
        Intent it = new Intent(ListMensalidadesActivity.this, ListAlunosActivity.class);
        startActivity(it);

    }
    //Método Procura mensalidade //
    public void procuraMensalidade(String nomealuno){
        MensalidadesFiltradas.clear();
        for (Mensalidade m : mensalidades){
            if (m.getNomealuno().toLowerCase().contains(nomealuno.toLowerCase())){
                MensalidadesFiltradas.add(m);
            }

        }
        listView.invalidateViews();

    }

    //Método Excluir
    public void excluirMensalidades(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Mensalidade mensalidadeExcluir = MensalidadesFiltradas.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção!")
                .setMessage("Deseja realmente excluir a Mensalidade?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MensalidadesFiltradas.remove(mensalidadeExcluir);
                        mensalidades.remove(mensalidadeExcluir);
                        daoMen.excluirMensalidade(mensalidadeExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();

    }
    public void atualizarMensalidade(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Mensalidade mensalidadeAtualizar = MensalidadesFiltradas.get(menuInfo.position);
        Intent it = new Intent(this, CadastroMensalidadesActivity.class);
        it.putExtra("mensalidade", mensalidadeAtualizar);
        startActivity(it);
    }

    @Override
    public void onBackPressed() {
        Intent intent  = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void  onResume(){
        super.onResume();
        mensalidades = daoMen.obterTodasMensalidades();
        MensalidadesFiltradas.clear();
        MensalidadesFiltradas.addAll(mensalidades);
        listView.invalidateViews();
    }
}
