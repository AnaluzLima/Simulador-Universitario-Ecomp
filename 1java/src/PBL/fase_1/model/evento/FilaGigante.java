package PBL.fase_1.model.evento;

import PBL.exception.*;
import PBL.fase_1.model.Jogador;

public class FilaGigante extends EventoAleatorio{
    public FilaGigante() {
        super("Fila Gigante", "...", 0.30);
    }

    //Se você for no bandejão e tiver o azar de pegar a fila gigante:

    @Override
    public void eventoConsequencia(Jogador jogador) throws TempoException {
        jogador.modificarTempo(-5); //perde 5 pontos de tempo
        jogador.getEnergia().modificar(-10); //perde 10 pontos de energia
        jogador.getMotivacao().modificar(-15); //perde 15 de motivação
    }
}
