package com.example.listadetarefas.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadetarefas.R;
import com.example.listadetarefas.helper.TarefaDAO;
import com.example.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private EditText editTextTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        String dados = getIntent().getExtras().getString("tituloActivity");

        /*Bundle dados = getIntent().getExtras();
        String dadosString = dados.getString("tituloActivity");*/

        getSupportActionBar().setTitle(dados);

        editTextTarefa = findViewById(R.id.editTextTarefa);

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if (tarefaAtual != null){
            editTextTarefa.setText(tarefaAtual.getTarefa());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_tarefa, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemSalvar :
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaAtual != null) {
                    String nomeTarefa = editTextTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setTarefa(nomeTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        if (tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(this, "Sucesso ao atualizar tarefa!", Toast.LENGTH_SHORT).show();
                        } else {
                            finish();
                            Toast.makeText(this, "Erro ao atualizar tarefa!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else {
                    String nomeTarefa = editTextTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setTarefa(nomeTarefa);

                        if (tarefaDAO.salvar(tarefa)) {
                            finish();
                            Toast.makeText(this, "Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Erro ao salvar tarefa", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
