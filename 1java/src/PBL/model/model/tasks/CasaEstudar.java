package PBL.model.model.tasks;

import PBL.exception.JogoException;
import PBL.model.model.Jogador;
import PBL.model.model.Disciplina;
import PBL.model.model.evento.EventoAleatorio;
import PBL.model.model.evento.MilagreAcademico;

public class CasaEstudar extends Atividade{
    private Disciplina materia;

    public CasaEstudar(){
        super("Estudar em Casa", "...","-5 Pontos de Tempo|-30 de Energia|+20 de Conhecimento|-10 de Saúde|+30 de Desempenho");
    }

    public void setMateria(Disciplina materia){
        this.materia = materia;
    }

    //Ir para casa estudar significa ficar estudando até tarde

    @Override
    public void executar(Jogador jogador)throws JogoException {
        if (this.materia == null){
            throw new JogoException("Nenhuma matéria foi selecionada");
        }
        jogador.modificarTempo(-5); //perde 5 de tempo

        EventoAleatorio milagre = new MilagreAcademico(this.materia);
        milagre.tentarAtivar(jogador);

        jogador.getEnergia().modificar(-30); //perde 30 de energia
        jogador.getConhecimento().modificar(20); //ganha 20 de conhecimento
        jogador.getSaude().modificar(-10); //perde 10 de saude
        materia.modificarDesempenho(30); //aumenta o desempenho na materia em 30

        jogador.setCansado(true); //jogador está cansado
    }
}