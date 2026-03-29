package PBL.model.tasks;

import PBL.model.Jogador;
import PBL.model.academico.Disciplina;
import PBL.model.evento.*;

public class AssistirAula extends Atividade{
    private Disciplina materia;
    public AssistirAula(){
        super("Assistir Aula", "...");
    }

    public void setMateria(Disciplina materia) {
        this.materia = materia;
    }

    @Override
    public boolean executar(Jogador jogador) {
        if(jogador.getTempo() >= 10){

            if (jogador.getHistorico().isSemestreGreve()) {
                return false;
            }

            EventoAleatorio prova = new ProvaSurpresa();
            if (prova.tentarAtivar()) {
                prova.eventoConsequencia(jogador);
                return true;
            }

            EventoAleatorio material = new MaterialCaro();
            if (material.tentarAtivar()) {
                material.eventoConsequencia(jogador);
            }

            jogador.modificarTempo(-10);
            jogador.consequencia("Energia", -20);
            jogador.consequencia("Conhecimento", 20);
            materia.modificarDesempenho(30);

            return true;
        }
        return false;
    }
}
