package PBL.model.tasks;

import PBL.model.model.Jogador;

public class IrMedico extends Atividade {

    public IrMedico() {
        super("Ir ao Médico", "...");
    }

    @Override
    public boolean executar(Jogador jogador) {
        if (jogador.getTempo() >= 5) {
            jogador.modificarTempo(-5);
            jogador.consequencia("Saúde", 40);


            return true;
        }
        return false;
    }
}
