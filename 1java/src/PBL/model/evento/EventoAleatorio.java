package PBL.model.evento;

import PBL.model.Jogador;

public abstract class EventoAleatorio{
    private String nome;
    private String descricao;
    private double probabilidade;

    public EventoAleatorio(String nome, String descricao, double probabilidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.probabilidade = probabilidade;
    }

    public double gerarAleatorio(){
        return Math.random();
    }

    public boolean tentarAtivar() {
        return this.gerarAleatorio() <= this.probabilidade;
    }

    public abstract void eventoConsequencia(Jogador jogador);
}