package PBL.model.service;

import PBL.exception.*;
import PBL.model.model.*;
import PBL.model.model.minigames.*;
import PBL.model.repository.HistoricoRepository;
import PBL.model.repository.JogoRepository;
import PBL.model.repository.MinigameRepository;

/**Essa classe tem como objetivo controlar o ciclo de vida da partida e a máquina de estados do progresso acadêmico*/

public class JogoService {

    private final JogoRepository jogoR;
    private final HistoricoRepository historicoR;
    private final MinigameRepository minigameR;
    private final MinigameService minigameService = new MinigameService();

    public JogoService(JogoRepository jogoR, HistoricoRepository historicoR, MinigameRepository minigameR) {
        this.jogoR = jogoR;
        this.historicoR = historicoR;
        this.minigameR = minigameR;
    }

    public void prepararSemanaDeProvas(Jogador jogador) throws JogoException {
        //verifica se o aluno tem matérias matriculadas
        if (jogador.getHistorico().getCursando().isEmpty()) {
            throw new JogoException("Você não está matriculado em nenhuma disciplina.");
        }

        jogador.setFazendoProva(true); //trava o personagem

        //olha as matérias que o jogador está cursando
        for (Disciplina materia : jogador.getHistorico().getCursando()) {
            //só prepara a prova se ela ainda não foi feita
            if (!materia.isProvaFeita()) {
                Minigame minigame = materia.getMinigame();
                int desempenho = materia.getDesempenho();

                //identifica qual é a prova e usa o Service para sortear as perguntas
                if (minigame instanceof MinigameTexto) {
                    minigameService.prepararMinigameTexto((MinigameTexto) minigame, desempenho);
                } else if (minigame instanceof MinigameMatematica) {
                    minigameService.prepararMinigameMatematica((MinigameMatematica) minigame, desempenho);
                } else if (minigame instanceof MinigameSoftware) {
                    minigameService.prepararMinigameSoftware((MinigameSoftware) minigame, desempenho);
                } else if (minigame instanceof MinigameHardware) {
                    minigameService.prepararMinigameHardware((MinigameHardware) minigame, desempenho);
                }
            }
        }
    }

    public void encerrarSemestre(Jogo jogo) throws JogoException {
        Jogador jogador = jogo.getJogador();

        //processa as aprovações/reprovações e limpa a grade atual
        jogador.getHistorico().fimSemestre();
        jogador.setSemestre(jogador.getSemestre() + 1);

        jogador.modificarTempo(100);
        jogador.modificarDinheiro(50);
        jogador.getEnergia().setValor(100);
        jogador.getMotivacao().modificar(60);
        jogador.getSaude().setValor(100);
        jogador.setCansado(false);

        //se o aluno não tiver mais matérias pendentes, zerou o jogo
        if (jogador.getHistorico().isFormado()) {
            jogo.setFinalizado(true);
        }
        this.salvarJogo(jogo); //salva o progresso independente do resultado
    }

    //comunicação com o repository:
    public void salvarJogo(Jogo jogo) throws JogoException {
        jogoR.salvar(jogo);
    }

    public void apagarJogo(String slot) throws JogoException {
        jogoR.deletar(slot);
    }

    public Jogo carregarJogo(String slot, Mapa mapa) throws JogoException {
        //impede de carregar um slot vazio
        if (!jogoR.existeSave(slot)) {
            throw new JogoException("Não há nenhum jogo neste slot.");
        }

        //carrega o jogo do arquivo
        Jogo jogoSalvo = jogoR.carregar(slot);
        Jogador jogador = jogoSalvo.getJogador();

        //jogos zerados não podem ser recarregados
        if(jogoSalvo.isFinalizado()){
            throw new JogoException("Este jogo já foi finalizado! Inicie um novo.");
        }

        historicoR.reconstruirDisciplinas(jogador.getHistorico(), mapa, minigameR);

        //coloca o jogador no local que estava antes
        if (jogador.getLocalizacao() != null) {
            String nomeLocal = jogador.getLocalizacao().getNome();
            Local local = mapa.buscarLocalPorNome(nomeLocal);
            jogador.setLocalizacao(local);
        }

        //coloca o mapa completo de volta ao Jogo
        mapa.distribuirAulas(jogador);

        return jogoSalvo;
    }

    public Jogo carregarRascunho(String slot) throws JogoException {
        if (!jogoR.existeSave(slot)) {
            throw new JogoException("Não há nenhum jogo neste slot.");
        }
        return jogoR.carregar(slot);
    }
}