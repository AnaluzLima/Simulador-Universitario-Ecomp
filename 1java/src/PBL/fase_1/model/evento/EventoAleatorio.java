package PBL.fase_1.model.evento;

import PBL.exception.JogoException;
import PBL.fase_1.model.Jogador;

/**Essa classe define tudo que os demais eventos aleatórios também terão*/

public abstract class EventoAleatorio{
    private final String nome;
    private final String descricao;
    private final double probabilidade; //probabilidade do evento ocorrer

    public EventoAleatorio(String nome, String descricao, double probabilidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidade = probabilidade;
    }

    public String getNome(){
        return this.nome;
    }
    public String getDescricao(){
        return this.descricao;
    }
    public double getProbabilidade(){
        return this.probabilidade;
    }

    public boolean tentarAtivar(Jogador jogador) throws JogoException {
        //Math.random() gera um double entre 0 e 1
        //se for menor ou igual à probabilidade do evento (ex: 0.30 para 30%), o evento ocorre.

        if (Math.random() <= this.getProbabilidade()){
            this.eventoConsequencia(jogador);
            return true;
        }
        return false;
    }

    public abstract void eventoConsequencia(Jogador jogador) throws JogoException;

}