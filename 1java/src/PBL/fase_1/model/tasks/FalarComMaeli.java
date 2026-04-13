package PBL.fase_1.model.tasks;

import PBL.exception.TempoException;
import PBL.fase_1.model.Jogador;

public class FalarComMaeli extends Atividade{

    public FalarComMaeli() {
        super("Conversar com Maeli", "...");
    }
    //Ao falar com a diva da Maeli, você fica mais motivado

    @Override
    public void executar(Jogador jogador) throws TempoException {
        jogador.modificarTempo(-3); //gasta 3 pontos de tempo

        jogador.getMotivacao().modificar(15); //ganha 15 de motivação
        jogador.getConhecimento().modificar(5); //ganha 5 de conhecimento

    }
}