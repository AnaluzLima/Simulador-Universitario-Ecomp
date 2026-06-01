package PBL.controller;

import PBL.controller.jogo.SessaoJogo;
import PBL.model.model.Jogador;
import PBL.model.model.Jogo;
import PBL.model.model.Local;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameplayController {

    private Jogo jogoAtual;

    public GameplayController() {
        //pega os dados do jogo que está rolando
        this.jogoAtual = SessaoJogo.getInstancia().getJogoAtivo();
    }

    public boolean isJogadorSpawnF6() {
        return this.jogoAtual.getJogador().isSpawnF6();
    }

    private Local getLocalAtual() {
        return this.jogoAtual.getJogador().getLocalizacao();
    }
    public String getCaminhoImagemLocalAtual() {
        return getLocalAtual().getImagem();
    }
    public double getPosicaoXLocalAtual() {
        return getLocalAtual().getPosX();
    }
    public double getPosicaoYLocalAtual() {
        return getLocalAtual().getPosY();
    }
    public double getTamanhoJogadorLocalAtual() {
        return getLocalAtual().getTamanhoJogador();
    }
    public int getDinheiroAtual() {
        return this.jogoAtual.getJogador().getDinheiro();
    }
    public int getTempoAtual() {
        return this.jogoAtual.getJogador().getTempo();
    }

    public Map<String, Integer> getAtributosJogador() {
        Map<String, Integer> atributos = new LinkedHashMap<>();

        var jogador = this.jogoAtual.getJogador();

        //adiciona os nomes e os valores
        atributos.put("Energia", jogador.getEnergia().getValor());
        atributos.put("Saúde", jogador.getSaude().getValor());
        atributos.put("Motivação", jogador.getMotivacao().getValor());
        atributos.put("Conhecimento", jogador.getConhecimento().getValor()); // Abreviado para caber bem
        atributos.put("Desempenho Acad.", jogador.getDesempenhoAcademico().getValor());

        return atributos;
    }
}