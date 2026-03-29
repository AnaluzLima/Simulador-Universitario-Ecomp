package PBL.model.tasks;

import PBL.model.Jogador;
import PBL.model.academico.Disciplina;

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
