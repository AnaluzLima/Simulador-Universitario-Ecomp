package PBL.controller;

import PBL.controller.jogo.SessaoJogo;
import PBL.exception.JogoException;
import PBL.model.model.Jogo;
import PBL.model.model.Local;
import PBL.model.model.tasks.Atividade;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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

    public String getNomeLocalAtual() {
        return this.jogoAtual.getJogador().getLocalizacao().getNome();
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
        atributos.put("Conhecimento", jogador.getConhecimento().getValor());
        atributos.put("Desempenho Acad.", jogador.getDesempenhoAcademico().getValor());

        return atributos;
    }

    public List<Atividade> getAtividadesLocalAtual() {
        return this.jogoAtual.getJogador().getLocalizacao().getAtvLocais();
    }

    public List<Local> getConexoesLocalAtual() {
        return this.jogoAtual.getJogador().getLocalizacao().getConexoes();
    }

    public List<String> getNomesConexoesLocalAtual() {
        List<String> nomes = new ArrayList<>();
        for (Local local : this.jogoAtual.getJogador().getLocalizacao().getConexoes()) {
            nomes.add(local.getNome());
        }
        return nomes;
    }

    //retorna os nomes das atividades
    public List<String> getNomesAtividadesLocalAtual() {
        List<String> nomes = new ArrayList<>();
        for (Atividade atv : this.jogoAtual.getJogador().getLocalizacao().getAtvLocais()) {
            nomes.add(atv.getNome());
        }
        return nomes;
    }

    //monta as strings para o Pop-up
    public String[] getDetalhesAtividade(String nomeAtividade) {
        for (Atividade atv : this.jogoAtual.getJogador().getLocalizacao().getAtvLocais()) {
            if (atv.getNome().equals(nomeAtividade)) {
                return new String[]{atv.getNome(), atv.getDescricao(), atv.getConsequencia()};
            }
        }
        return new String[]{"Desconhecido", "Sem descrição.", ""};
    }
    public void viajarPara(String nomeDestino) {
        for (Local local : this.jogoAtual.getJogador().getLocalizacao().getConexoes()) {
            if (local.getNome().equals(nomeDestino)) {
                this.jogoAtual.getJogador().setLocalizacao(local);
                break;
            }
        }
    }
    public void executarAtividade(String nomeAtividade) throws JogoException {
        for (Atividade atv : this.jogoAtual.getJogador().getLocalizacao().getAtvLocais()) {
            if (atv.getNome().equals(nomeAtividade)) {
                atv.executar(this.jogoAtual.getJogador());
                break;
            }
        }
    }
}