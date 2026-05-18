package PBL.model.service;

import PBL.exception.*;
import PBL.model.model.*;
import PBL.model.repository.HistoricoRepository;
import PBL.model.repository.JogoRepository;
import PBL.model.repository.MinigameRepository;

/**Essa classe tem como objetivo controlar o ciclo de vida da partida e a máquina de estados do progresso acadêmico*/

public class JogoService {

    private final JogoRepository jogoR;
    private final HistoricoRepository historicoR;
    private final MinigameRepository minigameR;

    public JogoService(JogoRepository jogoR, HistoricoRepository historicoR, MinigameRepository minigameR) {
        this.jogoR = jogoR;
        this.historicoR = historicoR;
        this.minigameR = minigameR;
    }

    public void iniciarSemanaDeProvas(Jogador jogador) {
        //varre apenas as matérias que o jogador esta cursando para aplicar as provas
        for (Disciplina materia : jogador.getHistorico().getCursando()) {
            //Minigame minigame = materia.getMinigame();
            //minigame.jogar(materia.getDesempenho());

            //double pontuacao = minigame.getPontuacao();

            //materia.registrarNota(pontuacao);

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

    public void apagarJogo(int slot) throws JogoException {
        jogoR.deletar(slot);
    }

    public Jogo carregarJogo(int slot, Mapa mapa) throws JogoException {
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
        jogoSalvo.setMapa(mapa);
        mapa.distribuirAulas(jogador);

        return jogoSalvo;
    }
}