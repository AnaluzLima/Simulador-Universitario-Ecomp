package PBL.model.model.evento;

import PBL.model.model.Jogador;

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