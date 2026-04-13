package PBL.fase_1.model.evento;

import PBL.fase_1.model.Jogador;

public class Greve extends EventoAleatorio {

    public Greve() {
        super("Greve na Universidade", "...", 0.05);
    }

    //se acontecer uma greve no semestre

    @Override
    public void eventoConsequencia(Jogador jogador) {
        //o historico ativa o estado de greve
        jogador.getHistorico().setSemestreGreve(true);
    }
}