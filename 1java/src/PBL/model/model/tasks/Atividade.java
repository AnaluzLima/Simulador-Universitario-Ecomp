package PBL.model.model.tasks;

import PBL.exception.JogoException;
import PBL.model.model.Jogador;

/**Essa classe é o molde para todas as demais atividades que podem ser executadas no campus*/

public abstract class Atividade {
    private String nome;
    private final String descricao;
    private String consequencia;

    public Atividade(String nome, String descricao, String consequencia) {
        this.nome = nome;
        this.descricao = descricao;
        this.consequencia = consequencia;
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
    public String getConsequencia() {
        return this.consequencia;
    }

    public abstract void executar(Jogador jogador)throws JogoException;;
}