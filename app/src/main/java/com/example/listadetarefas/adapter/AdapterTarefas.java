package com.example.listadetarefas.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.listadetarefas.R;
import com.example.listadetarefas.model.Tarefa;

import java.util.List;

public class AdapterTarefas extends RecyclerView.Adapter<AdapterTarefas.ViewHolder> {

    private List<Tarefa> listaTarefas;

    public AdapterTarefas(List<Tarefa> listaTarefas) {
        this.listaTarefas = listaTarefas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_tarefas, viewGroup, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Tarefa tarefa = listaTarefas.get(i);

        viewHolder.tarefa.setText(tarefa.getTarefa());
    }

    @Override
    public int getItemCount() {
        return listaTarefas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tarefa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tarefa = itemView.findViewById(R.id.textViewTarefa);
        }
    }
}
