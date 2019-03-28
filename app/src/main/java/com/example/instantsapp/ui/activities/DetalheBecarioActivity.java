package com.example.instantsapp.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instantsapp.R;
import com.example.instantsapp.model.modelodados.InfoUsuarios;
import com.squareup.picasso.Picasso;

public class DetalheBecarioActivity extends AppCompatActivity {

    private InfoUsuarios infoUsuarios;

    private ImageView avatarGrande;
    private ImageView voltar;
    private TextView tvNome;
    private TextView tvId;
    private TextView tvDetalhes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_becario);
        avatarGrande = findViewById(R.id.img_avatar_grande);
        voltar = findViewById(R.id.bt_voltar);
        tvNome = findViewById(R.id.tv_detalhes_nome);
        tvId = findViewById(R.id.tv_detalhes_id);
        tvDetalhes = findViewById(R.id.tv_detalhes_descricao);
    }

    @Override
    protected void onStart() {
        super.onStart();
        infoUsuarios = getIntent().getParcelableExtra("dados_usuario");
        if(infoUsuarios!=null) {
            tvNome.setText(infoUsuarios.getName());
            tvId.setText(getResources().getString(R.string.arroma_id,infoUsuarios.getId()));
            tvDetalhes.setText(infoUsuarios.getDesc());
            Picasso.get()
                    .load(infoUsuarios.getPicture())
                    .resize(500,500)
                    .into(avatarGrande);
        }

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
