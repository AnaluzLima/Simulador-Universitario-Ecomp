package PBL.model.tasks;

import PBL.model.Jogador;

public class PraticarEsporte extends Atividade{
    public PraticarEsporte() {
        super("Praticar Atividade Física", "...");
    }

    @Override
    public boolean executar(Jogador jogador) {
        if(jogador.getTempo() >= 8){
            jogador.modificarTempo(-8);

            jogador.consequencia("Energia", -30);
            jogador.consequencia("Saúde", 15);
            jogador.consequencia("Motivação", 30);

            return true;
        }
        return false;

    }
}
