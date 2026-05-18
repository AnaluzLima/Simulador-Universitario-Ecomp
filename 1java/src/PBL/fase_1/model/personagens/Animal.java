package PBL.fase_1.model.personagens;

import PBL.exception.TempoException;
import PBL.fase_1.model.Jogador;

public class Animal extends NPC {
    private final double chanceAtaque;

    public Animal(String nome, double chanceAtaque){
        super(nome);
        this.chanceAtaque = chanceAtaque;
    }

    @Override
    public void interagir(Jogador jogador) throws TempoException {
        //ao interagir com um animal:
        jogador.modificarTempo(-1); //você perde 1 ponto de tempo

        if (Math.random() <= this.chanceAtaque) { //sorteia se ele vai te atacar ou não
            //Morder
            jogador.getSaude().modificar(-30); //perde 30 de saude
            jogador.getEnergia().modificar(-5); //perde 5 de energia
            jogador.getCelular().modificarAmizade(this.getNome(), -2); //diminui sua relação com ele em -2
        }
        else {
            //Carinho
            jogador.getMotivacao().modificar(10); //recebe 10 de motivacao
            jogador.getEnergia().modificar(5); //5 de energia
            jogador.getCelular().modificarAmizade(this.getNome(), 1); //aumenta sua relação com ele em 1
        }
    }
}