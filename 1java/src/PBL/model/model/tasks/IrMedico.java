package PBL.model.model.tasks;

import PBL.exception.TempoException;
import PBL.model.model.Jogador;

public class IrMedico extends Atividade{

    public IrMedico() {
        super("Ir ao Médico", "...", "-5 Pontos de Tempo|+40 de Saúde");
    }

    //Ao ir pro médico por conta própia, o jogador gasta 5 pontos de tempo e recupera 40 de saúde

    @Override
    public void executar(Jogador jogador) throws TempoException {
        jogador.modificarTempo(-5);

        jogador.getSaude().modificar(40);
    }
}