package com.example.aula12.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aula12.R;
import com.example.aula12.database.JogosDB;
import com.example.aula12.database.dao.JogoDAO;
import com.example.aula12.entity.Jogo;

public class FormActivity extends AppCompatActivity {

    EditText etTitulo;
    EditText etGenero;
    EditText etAno;
    Button btnSalvar;

    JogoDAO jogoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitulo = findViewById(R.id.etTitulo);
        etGenero = findViewById(R.id.etGenero);
        etAno = findViewById(R.id.etAno);
        btnSalvar = findViewById(R.id.btnSalvar);

        jogoDAO = JogosDB.getInstance(this).getJogoDAO();

        Intent intent = getIntent();

        Jogo editarJogo;

        if (intent.hasExtra("jogo")) {
            editarJogo = (Jogo) intent.getSerializableExtra("jogo");
            etTitulo.setText(editarJogo.getTitulo());
            etGenero.setText(editarJogo.getGenero());
            etAno.setText(editarJogo.getAno());
        } else {
            editarJogo = null;
        }

        btnSalvar.setOnClickListener(v -> {
            if (etTitulo.getText().toString().isEmpty() || etGenero.getText().toString().isEmpty() || etAno.getText().toString().isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            } else {
                Jogo jogo = new Jogo(
                        0,
                        etTitulo.getText().toString(),
                        etGenero.getText().toString(),
                        etAno.getText().toString()
                );

                if (editarJogo == null) {
                    jogoDAO.salvar(jogo);
                } else {
                    jogo.setId(editarJogo.getId());
                    jogoDAO.editarJogo(jogo);
                }

                Toast.makeText(this, "Jogo cadastrado!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}