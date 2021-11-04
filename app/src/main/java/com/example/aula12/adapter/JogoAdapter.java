package com.example.aula12.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aula12.R;
import com.example.aula12.database.JogosDB;
import com.example.aula12.database.dao.JogoDAO;
import com.example.aula12.entity.Jogo;
import com.example.aula12.ui.FormActivity;

import java.util.List;

public class JogoAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Jogo> listaJogos;
    private JogoDAO jogoDAO;

    public JogoAdapter(Context context) {
        this.context = context;
        jogoDAO = JogosDB.getInstance(context).getJogoDAO();
        listaJogos = jogoDAO.getJogos();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.jogo_layout, parent, false);

        JogoViewHolder jogoViewHolder = new JogoViewHolder(itemView);

        return jogoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Jogo jogo = listaJogos.get(position);

        JogoViewHolder jogoViewHolder = (JogoViewHolder) holder;

        jogoViewHolder.tvTitulo.setText(jogo.getTitulo());
        jogoViewHolder.tvGenero.setText(jogo.getGenero());
        jogoViewHolder.tvAno.setText(String.valueOf(jogo.getAno()));

        jogoViewHolder.ibEditar.setOnClickListener(v -> {
            Intent editarIntent = new Intent(context, FormActivity.class);
            editarIntent.putExtra("jogo", jogo);
            context.startActivity(editarIntent);
        });

        jogoViewHolder.ibExcluir.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Deletar jogo")
                    .setMessage("Excluir este jogo?")
                    .setNegativeButton("Não", null)
                    .setPositiveButton("Sim", (dialogInterface, i) -> {
                        jogoDAO.deletarJogo(jogo);
                        updateDataSet();
                    });

        });

    }

    @Override
    public int getItemCount() {
        return listaJogos.size();
    }

    public void updateDataSet() {
        listaJogos.clear();
        listaJogos = jogoDAO.getJogos();
        notifyDataSetChanged();
    }
}
