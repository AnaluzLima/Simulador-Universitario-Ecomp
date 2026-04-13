package PBL.fase_1.model.evento;
import PBL.fase_1.model.Disciplina;
import PBL.fase_1.model.Jogador;

public class MilagreAcademico extends EventoAleatorio{
    private Disciplina materia;
    public MilagreAcademico(Disciplina materia) {
        super("Milagre Acadêmico", "...", 0.30);
        this.materia = materia;
    }

    public Disciplina getMateria(){
        return this.materia;
    }

    //se, ao estudar, ele acabar tendo a chance de ter visto exatamente uma questão que o professor colocou na prova

    @Override
    public void eventoConsequencia(Jogador jogador) {
        jogador.getMotivacao().modificar(10); //aumenta em 10 sua motivação
        materia.setMilagreAcademico(true); //ativa o estado de milagre academico para aquela materia
    }
}
