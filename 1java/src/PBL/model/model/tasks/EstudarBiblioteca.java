package PBL.model.model.tasks;

import PBL.exception.JogoException;
import PBL.model.model.Jogador;
import PBL.model.model.Disciplina;
import PBL.model.model.evento.EventoAleatorio;
import PBL.model.model.evento.MilagreAcademico;
import PBL.model.model.personagens.Colega;

public class EstudarBiblioteca extends Atividade {
    //ao ir estudar na biblioteca, você pode levar alguem junto
    private Colega acompanhante;
    private Disciplina materia;

    public EstudarBiblioteca(Colega acompanhante){
        super("Estudar na Biblioteca","...", "-10 Pontos de Tempo|-20 de Energia|+15 de Conhecimento|+25 de Desempenho|");
        this.acompanhante = acompanhante;
    }
    //Overloaging
    public EstudarBiblioteca(){
        super("Estudar na Biblioteca","...","-5 Pontos de Tempo|-15 de Energia|+10 de Conhecimento|+15 de Desempenho|");
        this.acompanhante = null;
    }

    public void setMateria(Disciplina materia){
        this.materia = materia;
    }
    public Disciplina getMateria(){
        return this.materia;
    }
    public Colega getAcompanhante(){
        return this.acompanhante;
    }

    @Override
    public void executar(Jogador jogador) throws JogoException {
        if (this.materia == null){
            throw new JogoException("Nenhuma matéria foi selecionada");
        }

        EventoAleatorio milagre = new MilagreAcademico(this.materia);

        if(acompanhante != null){ //se o jogador ta levando um colega
            jogador.modificarTempo(-10); //gasta 10 pontos de tempo
            jogador.getEnergia().modificar(-20); //gasta 20 de energia
            jogador.getConhecimento().modificar(15); //ganha 20 de conhecimento
            this.materia.modificarDesempenho(25); //ganha 25 de desempenho na materia
            jogador.getCelular().modificarAmizade(acompanhante.getNome(), 2); //aumenta a relação entre eles
        }
        else{ //se não
            jogador.modificarTempo(-5); //gasta 5 pontos de tempo
            jogador.getEnergia().modificar(-15); //gasta 15 de energia
            jogador.getConhecimento().modificar(10);
            this.materia.modificarDesempenho(15); //ganha 15 de desempenho na materia
        }
        milagre.tentarAtivar(jogador); //tenta ativar o milagre academico

    }
}