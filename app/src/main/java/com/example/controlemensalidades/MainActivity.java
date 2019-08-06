package com.example.controlemensalidades;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnALunos;
    private  Button btnMensalidades;
    private Button  btnSobre;
    private Button btnPagamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnALunos = (Button) findViewById(R.id.btnAlunos);
        btnMensalidades = (Button) findViewById(R.id.btnMensalidades);
        btnSobre = (Button) findViewById(R.id.btnSOBRE);
        btnPagamentos = (Button) findViewById(R.id.btnPagamentos);

        btnALunos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 btnALunosActivity();
            }
        });
        btnMensalidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnMensalidadesActivity();
            }
        });
        btnSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSobreActivity();
            }
        });
        btnPagamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPagamentosActivity();
            }
        });
    }
    private void btnALunosActivity() {
        startActivity(new Intent(MainActivity.this, ListAlunosActivity.class)) ;
    }

    private  void btnMensalidadesActivity(){
        startActivity(new Intent(MainActivity.this, ListMensalidadesActivity.class)) ;
    }

    private  void btnSobreActivity(){
        startActivity(new Intent(MainActivity.this, SobreActivity.class)) ;
    }

    private  void btnPagamentosActivity(){
        startActivity(new Intent(MainActivity.this, AlunoMensalidadeActivity.class)) ;
    }

    @Override
    public void onBackPressed() {
        FecharApp();
    }
    private void FecharApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Deseja Realmente Sair do Aplicativo?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishActivity(1);
                finish();

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

}

