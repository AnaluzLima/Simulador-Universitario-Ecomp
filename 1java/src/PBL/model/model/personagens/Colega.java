package PBL.model.model.personagens;

import PBL.exception.TempoException;
import PBL.model.model.Jogador;

public class Colega extends NPC {
    public Colega(String nome){
        super(nome);
    }

    @Override
    public void interagir(Jogador jogador) throws TempoException {
        //Ao interagir com um colega:
        jogador.modificarTempo(-3); //você perde 3 pontos de tempo
        jogador.getMotivacao().modificar(10); //ganha 10 de motivação
        jogador.getCelular().modificarAmizade(this.getNome(), 1); //aumenta sua relação em +1

    }
}