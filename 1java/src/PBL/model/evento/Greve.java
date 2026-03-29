package PBL.model.evento;

import PBL.model.Jogador;

public class Greve extends EventoAleatorio {

    public Greve() {
        super("Greve na Universidade", "...", 0.05);
    }

    @Override
    public void eventoConsequencia(Jogador jogador) {
        jogador.getHistorico().setSemestreGreve(true);
    }
}