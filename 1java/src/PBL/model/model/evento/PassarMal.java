package PBL.model.model.evento;

import PBL.model.model.Jogador;

public class PassarMal extends EventoAleatorio{
    public PassarMal() {
        super("Passando Mal", "...", 0.15);
    }

    //se ao comer no bandejão, o jogador teve o azar de passar mal
    @Override
    public void eventoConsequencia(Jogador jogador) {
        jogador.getEnergia().modificar(-10); //perde 10 de energia
        jogador.getMotivacao().modificar(-10); //perde 10 de motivação
        jogador.getSaude().modificar(-40); //perde 40 de saúde
    }
}
