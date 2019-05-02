package com.example.listadetarefas.model;

import java.io.Serializable;

public class Tarefa implements Serializable {
    public Long id;
    public String tarefa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
