package com.example.aula12.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aula12.R;

public class JogoViewHolder extends RecyclerView.ViewHolder {

    TextView tvTitulo;
    TextView tvGenero;
    TextView tvAno;
    ImageButton ibEditar;
    ImageButton ibExcluir;

    public JogoViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitulo = itemView.findViewById(R.id.tvTitulo);
        tvGenero = itemView.findViewById(R.id.tvGenero);
        tvAno = itemView.findViewById(R.id.tvAno);
        ibEditar = itemView.findViewById(R.id.ibEditar);
        ibExcluir = itemView.findViewById(R.id.ibExcluir);
    }
}
