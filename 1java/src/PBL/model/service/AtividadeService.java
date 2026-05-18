package PBL.model.service;

import PBL.exception.*;
import PBL.model.model.Jogador;
import PBL.model.model.tasks.Atividade;

/**Essa classe garante que, ao executar uma atividade, os eventos paralelos sejam testados, o registro no celular seja feito,
 * o desempenho acadêmico seja atualizado e monitora a redução dos atributos vitais para acionar o sistema de "emergência"
 * (Desmaio) caso a saúde ou energia cheguem a zero.*/

public class AtividadeService {
    public void realizarAtividade(Jogador jogador, Atividade atividade) throws JogoException {

        //executa a ação da atividade passando o service de eventos para lidar com surpresas
        atividade.executar(jogador);
        jogador.atualizarDesempenho();
        jogador.getCelular().adicionarRegistro(atividade.getNome());

        //se o jogador for levado ao limite, ele sofre punições e o jogo será momentaneamente é interrompido.
        if (jogador.getEnergia().getValor() == 0) {
            jogador.getEnergia().setValor(20);
            jogador.getSaude().modificar(-20);
            throw new DesmaioException("Sua visão escureceu... Você desmaiou de exaustão e foi levado ao Hospital.");

        } else if (jogador.getSaude().getValor() == 0) {
            jogador.getEnergia().setValor(20);
            jogador.getSaude().setValor(20);
            throw new DesmaioException("Você não aguentou e apagou... A equipe médica te atendeu.");
        }
    }
}