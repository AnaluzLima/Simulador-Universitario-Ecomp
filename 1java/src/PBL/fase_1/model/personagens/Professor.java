package PBL.fase_1.model.personagens;

import PBL.exception.TempoException;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class Professor extends NPC {
    private final int rigor; // de 1 a 10
    private final List<Disciplina> materias;

    public Professor(String nome, int rigor){
        super(nome);
        this.materias = new ArrayList<>();
        this.rigor = rigor;
    }

    public void addMateria(Disciplina d) { //um professor pode ministrar mais de uma matéria
        this.materias.add(d);
    }

    @Override
    public void interagir(Jogador jogador) throws TempoException {
        jogador.modificarTempo(-3); //perde 3 de tempo

        jogador.getConhecimento().modificar(5); //ganha 5 de conhecimento
        jogador.getEnergia().modificar(-(this.rigor * 2)); //perde uma quantidade maior de energia quanto mais exigente
                                                           //for o professor
        jogador.getCelular().modificarAmizade(this.getNome(), 1); //aumenta a relacao
    }

    private int getRigor(){
        return this.rigor;
    }
    private List<Disciplina> getMaterias(){
        return new ArrayList<>(materias);
    }
}