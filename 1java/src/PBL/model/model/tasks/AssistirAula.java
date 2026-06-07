package PBL.model.model.tasks;

import PBL.exception.GreveException;
import PBL.exception.JogoException;
import PBL.model.model.Jogador;
import PBL.model.model.Disciplina;
import PBL.model.model.evento.EventoAleatorio;
import PBL.model.model.evento.MaterialCaro;
import PBL.model.model.evento.ProvaSurpresa;

//Essa classe representa a ação de assistir uma aula

public class AssistirAula extends Atividade {
    private Disciplina materia; //materia da aula que o jogador vai assistir

    public AssistirAula() {
        super("Assistir Aula: ", "...", "-10 Pontos de Tempo|-20 de Energia|+20 de Conhecimento|+30 de Desempenho");
    }

    public void setMateria(Disciplina materia) {
        this.materia = materia;
        this.setNome("Assistir Aula: " + materia.getNome()); //ao setar a matéria, define o noov nome dela
    }

    @Override
    public void executar(Jogador jogador) throws JogoException {

        if (jogador.getHistorico().isSemestreGreve()) { //verifica se o semestre está em greve
            throw new GreveException("Você não pode assistir às aulas, pois o semestre está em greve.");
        }

        jogador.modificarTempo(-10); //gasta 10 pontos de tempo

        //tenta ativar a prova surpresa ou se tem que comprar um material caro
        EventoAleatorio prova = new ProvaSurpresa(this.materia);
        prova.tentarAtivar(jogador);

        EventoAleatorio material = new MaterialCaro();
        material.tentarAtivar(jogador);

        jogador.getEnergia().modificar(-20); //gasta 20 de energia

        if (!this.materia.isProvaSurpresaAtiva()){ //se o jogador não estiver fazendo a prova surpresa
            if (jogador.isCansado()) { //se o jogador ta cansado, ele aprende menos
                jogador.getConhecimento().modificar(10);
                this.materia.modificarDesempenho(15);
            } else {
                jogador.getConhecimento().modificar(20);
                this.materia.modificarDesempenho(30);
            }
        }
    }
}