package PBL.fase_1.model.tasks;

import PBL.exception.JogoException;
import PBL.fase_1.model.Jogador;

/**Essa classe é o molde para todas as demais atividades que podem ser executadas no campus*/

public abstract class Atividade {
    private String nome;
    private final String descricao;

    public Atividade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return this.nome;
    }
    protected void setNome(String newNome){
        this.nome = newNome;
    }
    public String getDescricao() {
        return this.descricao;
    }

    public abstract void executar(Jogador jogador)throws JogoException;;
}