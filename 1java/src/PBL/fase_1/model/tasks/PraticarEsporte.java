package PBL.fase_1.model.tasks;

import PBL.exception.TempoException;
import PBL.fase_1.model.Jogador;

public class PraticarEsporte extends Atividade{

    public PraticarEsporte() {
        super("Praticar Atividade Física", "...");
    }

    //o jogador pode praticar esportes na quadra

    @Override
    public void executar(Jogador jogador) throws TempoException {
        jogador.modificarTempo(-8); //gasta 8 pontos de tempo

        jogador.getEnergia().modificar(-30); //gasta 30 de energia
        jogador.getSaude().modificar(15); //ganha 15 de saúde
        jogador.getMotivacao().modificar(30); //ganha 30 de motivação
    }
}