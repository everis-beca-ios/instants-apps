package com.example.instantsapp.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instantsapp.R;
import com.example.instantsapp.model.modelodados.InfoUsuarios;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlunosBecaAdapter extends RecyclerView.Adapter<AlunosBecaAdapter.BecariosViewHolder> {
    private List<InfoUsuarios> listaUsuarios;

    @NonNull
    @Override
    public BecariosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_becarios, viewGroup, false);
        return new BecariosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BecariosViewHolder becariosViewHolder, int i) {
        InfoUsuarios aluno = listaUsuarios.get(i);
        becariosViewHolder.tvNome.setText(aluno.getName());
        becariosViewHolder.tvDescricao.setText(aluno.getDesc());
        Picasso.get()
                .load(aluno.getPicture())
                .resize(300,300)
                .into(becariosViewHolder.imagemAvatar);
    }

    @Override
    public int getItemCount() {
        return (listaUsuarios!=null) ? listaUsuarios.size() : 0;
    }

    public void adicionarUsuarios(List<InfoUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
        notifyDataSetChanged();
    }

    class BecariosViewHolder extends RecyclerView.ViewHolder {

        ImageView imagemAvatar;
        final TextView tvNome;
        final TextView tvDescricao;

        BecariosViewHolder(@NonNull View itemView) {
            super(itemView);
            imagemAvatar = itemView.findViewById(R.id.img_avatar);
            tvNome = itemView.findViewById(R.id.tv_nome);
            tvDescricao = itemView.findViewById(R.id.tv_descricao);
        }
    }
}
