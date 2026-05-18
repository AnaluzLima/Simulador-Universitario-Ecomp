package PBL.model.model.evento;

import PBL.model.model.Disciplina;
import PBL.model.model.Jogador;

public class ProvaSurpresa extends EventoAleatorio {
    private Disciplina materia;

    public ProvaSurpresa(Disciplina materia) {
        super("Prova Surpresa", "...", 0.05);
        this.materia = materia;
    }

    //se, ao ir assistir uma aula, o jogador tiver o azar de ser dia de prova surpresa
    @Override
    public void eventoConsequencia(Jogador jogador) {
        jogador.setFazendoProva(true); //ativa o estado de fazendo prova
        this.materia.setProvaSurpresaAtiva(true); //ativa a prova surpresa na disciplina
    }
}