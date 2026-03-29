package PBL.model.evento;

import PBL.model.Jogador;

public class ProvaSurpresa extends EventoAleatorio {

    public ProvaSurpresa() {
        super("Prova Surpresa", "...", 0.05);
    }

    @Override
    public void eventoConsequencia(Jogador jogador) {
        jogador.setFazendoProva(true);
    }
}