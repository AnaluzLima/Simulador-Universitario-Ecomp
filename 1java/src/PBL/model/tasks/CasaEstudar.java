package PBL.model.tasks;

import PBL.model.model.Jogador;
import PBL.model.model.Disciplina;
import PBL.model.evento.EventoAleatorio;
import PBL.model.evento.MilagreAcademico;

public class CasaEstudar extends Atividade{
    private Disciplina materia;
    public CasaEstudar(){
        super("Ir para Casa e Estudar até tarde", "...");
    }

    public void setMateria(Disciplina materia) {
        this.materia = materia;
    }

    @Override
    public boolean executar(Jogador jogador) {
        if (jogador.getTempo() >= 5) {
            jogador.modificarTempo(-5);

            EventoAleatorio milagre = new MilagreAcademico(this.materia);
            milagre.tentarAtivar();

            jogador.consequencia("Energia", -30);
            jogador.consequencia("Conhecimento", 20);
            jogador.consequencia("Saúde", -10);
            materia.modificarDesempenho(30);

            jogador.setCansado(true);
            return true;
        }
        return false;
    }
}
