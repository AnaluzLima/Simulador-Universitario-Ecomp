package PBL.model.personagens;

import PBL.model.Jogador;
import PBL.model.academico.Disciplina;
import java.util.ArrayList;
import java.util.List;

public class Professor extends NPC {
    //Nivel de Apreciação = relação
    // de 0 a 2: nenhum apreço;  de 2 a 4: mínima;  4 a 8: moderada;  de 8 a 9: alto; 10: aluno favorito
    private int rigor; //de 1 a 10
    private List<Disciplina> materias;

    public Professor(String nome, int rigor){
        super(nome);
        this.materias = new ArrayList<>();
        this.rigor = rigor;
    }

    public void addMateria(Disciplina d) {
        this.materias.add(d);
    }

    @Override
    public void interagir(Jogador jogador) {
        if (jogador.getTempo() >= 3) {
            jogador.modificarTempo(-3);

            jogador.consequencia("Conhecimento", 5);
            jogador.consequencia("Energia", -(this.rigor * 2));

            this.modificarRelacao(1);
        }
    }
}