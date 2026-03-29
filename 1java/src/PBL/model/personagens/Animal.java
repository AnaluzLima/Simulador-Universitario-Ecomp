package PBL.model.personagens;

import PBL.model.Jogador;

public class Animal extends NPC {
    private String especie;
    private double chanceAtaque;
    //relacao
    //de 0 a 2: estranho;  de 2 a 4: conhecido;  4 a 8: adora; de 8 a 10: humano favorito

    public Animal(String nome, String especie, double chanceAtaque){
        super(nome);
        this.especie = especie;
        this.chanceAtaque = chanceAtaque;
    }

    @Override
    public void interagir(Jogador jogador) {
        if (jogador.getTempo() >= 1) {
            jogador.modificarTempo(-1);
            if (Math.random() <= this.chanceAtaque) {
                jogador.consequencia("Saúde", -30);
                jogador.consequencia("Energia", -5);
                this.modificarRelacao(-2);

            } else {
                jogador.consequencia("Motivação", 10);
                jogador.consequencia("Energia", 5);
                this.modificarRelacao(1);
            }
        }
    }
}