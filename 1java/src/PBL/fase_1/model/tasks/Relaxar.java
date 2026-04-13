package PBL.fase_1.model.tasks;

import PBL.exception.TempoException;
import PBL.fase_1.model.Jogador;

public class Relaxar extends Atividade{

    public Relaxar(){
        super("Relaxar na Praça", "...");
    }

    //o jogador pode relaxar um pouco na praça

    @Override
    public void executar(Jogador jogador) throws TempoException {
        jogador.modificarTempo(-3); //gasta 3 pontos de tempo

        jogador.getMotivacao().modificar(5); //ganha 5 de motivação
        jogador.getSaude().modificar(3); //ganha 3 de saúde
        jogador.getEnergia().modificar(8); //ganha 8 de energia
    }
}