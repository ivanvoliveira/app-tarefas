package com.example.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO {

    private SQLiteDatabase salva;
    private SQLiteDatabase le;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        salva = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("tarefa", tarefa.getTarefa());

        try {
            salva.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO SALVAR", "Tarefa salva com sucesso");
        } catch (Exception e){
            Log.i("INFO SALVAR", "Erro ao salvar tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("tarefa", tarefa.getTarefa());

        try {
            String[] args = {tarefa.getId().toString()};
            salva.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args);
            Log.i("INFO ATUALIZAR", "Tarefa atualizada com sucesso");
        } catch (Exception e){
            Log.i("INFO ATUALIZAR", "Erro ao atualizar tarefa" + e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        try {
            String[] args = {tarefa.getId().toString()};
            salva.delete(DbHelper.TABELA_TAREFAS,"id=?", args);
            Log.i("INFO DELETAR", "Tarefa removida com sucesso");
        } catch (Exception e){
            Log.i("INFO DELETAR", "Erro ao remover tarefa" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;" ;
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()){
            Tarefa tarefa = new Tarefa();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("tarefa"));

            tarefa.setId(id);
            tarefa.setTarefa(nomeTarefa);

            tarefas.add(tarefa);
        }

        return tarefas;
    }
}
