package PBL.model.tasks;

import PBL.model.model.Jogador;
import PBL.model.model.Disciplina;
import PBL.model.evento.EventoAleatorio;
import PBL.model.evento.MilagreAcademico;

public class EstudarBiblioteca extends Atividade{
    private boolean levarColega;
    private Disciplina materia;
    public EstudarBiblioteca(boolean levarColega){
        super("Estudar na Biblioteca","...");
        this.levarColega = levarColega;
    }

    public void setMateria(Disciplina materia) {
        this.materia = materia;
    }

    @Override
    public boolean executar(Jogador jogador) {
        if((jogador.getTempo() >= 5 && !levarColega) || (jogador.getTempo() >=10 && levarColega) ){

            EventoAleatorio milagre = new MilagreAcademico(this.materia);
            milagre.tentarAtivar();

            if(levarColega){
                jogador.modificarTempo(-10);
                jogador.consequencia("Energia", -20);
                jogador.consequencia("Conhecimento", 20);
                materia.modificarDesempenho(25);

            }
            else{
                jogador.modificarTempo(-5);
                jogador.consequencia("Energia", -15);
                jogador.consequencia("Conhecimento", 10);
                materia.modificarDesempenho(15);
            }
            return true;
        }
        return false;
    }
}
