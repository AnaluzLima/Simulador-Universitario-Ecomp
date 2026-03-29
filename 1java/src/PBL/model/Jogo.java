package PBL.model;

import PBL.model.locais.Local;

//Não finalizado

public class Jogo {
    private Jogador jogador;
    private Local spawn;

    public Jogo(Jogador jogador, Local spawn) {
        this.jogador = jogador;
        this.spawn = spawn;
    }

    public Jogador getJogador(){
        return this.jogador;
    }
    public Local getSpawn(){
        return this.spawn;
    }
}