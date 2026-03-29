package PBL.model.tasks;

import PBL.model.Jogador;

public abstract class Atividade {
    private String nome;
    private String descricao;

    public Atividade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }
    public String getDescricao() {
        return this.descricao;
    }

    public abstract boolean executar(Jogador jogador);

}

