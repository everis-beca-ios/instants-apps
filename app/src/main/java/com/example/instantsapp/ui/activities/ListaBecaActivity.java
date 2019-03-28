package com.example.instantsapp.ui.activities;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.instantsapp.Constantes;
import com.example.instantsapp.Constantes.Resposta;
import com.example.instantsapp.R;
import com.example.instantsapp.model.modelodados.InfoUsuarios;
import com.example.instantsapp.ui.adapters.AlunosBecaAdapter;
import com.example.instantsapp.viewmodel.BecariosViewModel;

import java.util.List;

public class ListaBecaActivity extends AppCompatActivity implements AlunosBecaAdapter.AcoesNaLista {

    private RecyclerView rvListaBeca;
    private TextView tvBemVindo;
    private BecariosViewModel viewModel;
    private MutableLiveData<Resposta> resposta;
    private String usuario;
    private String senha = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listabeca);
        Toolbar toolbar = findViewById(R.id.toolbar_lista);
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        iniciarViews();

        Intent intent = getIntent();
        usuario = intent.getStringExtra("nome_usuario_logado");
        if(usuario!=null) {
            tvBemVindo.setText(getResources().getString(R.string.ola_usuario,usuario));
        }
    }

    private void iniciarViews() {
        rvListaBeca = findViewById(R.id.rv_lista_beca);
        rvListaBeca.setLayoutManager(new LinearLayoutManager(this));
        rvListaBeca.setAdapter(new AlunosBecaAdapter(this));
        tvBemVindo = findViewById(R.id.tv_bem_vindo);
        viewModel = ViewModelProviders
                .of(this).get(BecariosViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (viewModel.getListaUsuarios() == null) carregarValoresApi();
        else {
            if (rvListaBeca.getAdapter() != null)
                System.out.println("valor recebido: " + viewModel.getListaUsuarios());
                ((AlunosBecaAdapter) rvListaBeca.getAdapter())
                        .adicionarUsuarios(viewModel.getListaUsuarios());
        }
    }

    private void carregarValoresApi() {
        resposta = viewModel.carregarListaUsuarios();

        resposta.observe(this, new Observer<Resposta>() {
            @Override
            public void onChanged(Resposta resposta) {
                if (resposta != null) {
                    switch (resposta) {
                        case ONNEXT:

                            break;
                        case ONERROR:
                            //TODO: ADICIONE CLASSE PRA ERRO
                            break;
                        case ONCOMPLETE:
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        viewModel.retornarListaDb().observe(this, new Observer<List<InfoUsuarios>>() {
            @Override
            public void onChanged(@Nullable List<InfoUsuarios> listaUsuarios) {
                viewModel.setListaUsuarios(listaUsuarios);
                if (rvListaBeca.getAdapter() != null)
                    ((AlunosBecaAdapter) rvListaBeca.getAdapter())
                            .adicionarUsuarios(viewModel.getListaUsuarios());
                else {
                    System.out.println("listaUsuarios = [" + listaUsuarios + "]");
                }
            }
        });
    }

    @Override
    public void clickNoItem(InfoUsuarios infoUsuarios) {
        Intent intent = new Intent(this, DetalheBecarioActivity.class);
        intent.putExtra("dados_usuario",infoUsuarios);
        startActivity(intent);
    }
}
