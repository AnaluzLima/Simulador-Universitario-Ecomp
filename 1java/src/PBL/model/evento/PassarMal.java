package PBL.model.evento;

import PBL.model.Jogador;

public class PassarMal extends EventoAleatorio{
    public PassarMal() {
        super("Passando Mal", "...", 0.15);
    }

    @Override
    public void eventoConsequencia(Jogador jogador) {
        jogador.consequencia("Motivação", -10);
        jogador.consequencia("Energia", -10);
        jogador.consequencia("Saúde", -40);
    }
}
