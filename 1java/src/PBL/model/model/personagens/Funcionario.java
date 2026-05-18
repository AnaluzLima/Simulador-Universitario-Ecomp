package PBL.model.model.personagens;

import PBL.exception.TempoException;
import PBL.model.model.Jogador;

public class Funcionario extends NPC {

    public Funcionario(String nome) {
        super(nome);
    }

    @Override
    public void interagir(Jogador jogador) throws TempoException {
        //ao interagir com um funcionario da UEFS:
        jogador.modificarTempo(-1); //gasta 1 ponto de tempo
        jogador.getMotivacao().modificar(5); //ganha 5 de motivação
        jogador.getCelular().modificarAmizade(this.getNome(), 1); //aumenta sua relação em +1
    }
}