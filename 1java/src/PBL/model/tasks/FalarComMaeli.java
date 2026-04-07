package PBL.model.tasks;

import PBL.model.model.Jogador;

public class FalarComMaeli extends Atividade{
    public FalarComMaeli() {
        super("Conversar com Maeli", "...");
    }

    @Override
    public boolean executar(Jogador jogador) {
        if (jogador.getTempo() >= 3) {
            jogador.modificarTempo(-3);
            jogador.consequencia("Motivação", 15);
            jogador.consequencia("Conhecimento", 5);

            return true;
        }
        return false;
    }
}
