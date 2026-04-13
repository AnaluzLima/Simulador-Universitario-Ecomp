package PBL.fase_1.service;

import PBL.exception.*;
import PBL.fase_1.model.Disciplina;
import PBL.fase_1.model.Jogo;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.minigames.Minigame;
import PBL.fase_1.repository.JogoRepository;

/**Essa classe tem como objetivo controlar o ciclo de vida da partida e a máquina de estados do progresso acadêmico*/

public class JogoService {

    private final JogoRepository jogoR;

    public JogoService(JogoRepository jogoR) {
        this.jogoR = jogoR;
    }

    public void iniciarSemanaDeProvas(Jogador jogador) {
        //varre apenas as matérias que o jogador esta cursando para aplicar as provas
        for (Disciplina materia : jogador.getHistorico().getCursando()) {
            Minigame minigame = materia.getMinigame();
            minigame.jogar(materia.getDesempenho());

            double pontuacao = minigame.getPontuacao();

            materia.registrarNota(pontuacao);

        }
    }

    public void encerrarSemestre(Jogo jogo) {
        Jogador jogador = jogo.getJogador();

        //processa as aprovações/reprovações e limpa a grade atual
        jogador.getHistorico().fimSemestre();
        jogador.setSemestre(jogador.getSemestre() + 1);


        //se o aluno não tiver mais matérias pendentes, zerou o jogo
        if (jogador.getHistorico().isFormado()) {
            jogo.setFinalizado(true);
        }
        this.salvarJogo(jogo); //salva o progresso independente do resultado

    }

    //comunicação com o repository:

    public void salvarJogo(Jogo jogo) {
        jogoR.salvar(jogo);
    }

    public void apagarJogo(int slot) throws JogoException {
        jogoR.deletar(slot);
    }

    public Jogo carregarJogo(int slot) throws JogoException {
        //impede carregar um slot vazio
        if (!jogoR.existeSave(slot)) {
            throw new JogoException("Não há nenhum jogo neste slot.");
        }

        //jogos zerados não podem ser recarregados
        Jogo jogoSalvo = jogoR.carregar(slot);
        if(jogoSalvo.isFinalizado()){
            throw new JogoException("Este jogo já foi finalizado! Inicie um novo.");
        }

        return jogoSalvo;
    }
}