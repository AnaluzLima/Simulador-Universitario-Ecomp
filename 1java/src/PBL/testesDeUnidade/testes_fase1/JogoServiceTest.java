package PBL.testesDeUnidade.testes_fase1;

import PBL.exception.JogoException;
import PBL.model.model.Aparencia;
import PBL.model.model.Disciplina;
import PBL.model.model.Jogador;
import PBL.model.model.Jogo;
import PBL.model.model.Mapa;
import PBL.model.model.minigames.MinigameSoftware;
import PBL.model.model.minigames.MinigameTexto;
import PBL.model.repository.HistoricoRepository;
import PBL.model.repository.JogoRepository;
import PBL.model.repository.MinigameRepository;
import PBL.model.service.JogoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**Ao testar um service, o teste se torna mais um teste de integração do que de unidade*/

public class JogoServiceTest {

    private JogoRepository jogoRepository;
    private JogoService jogoService;
    private Jogador jogador;
    private Jogo jogo;
    private Disciplina programacao;
    private HistoricoRepository historicoRepository;
    private MinigameRepository minigameRepository;
    private Mapa mapa;

    @BeforeEach
    public void inicializa() throws JogoException {
        jogoRepository = new JogoRepository();
        historicoRepository = new HistoricoRepository();
        minigameRepository = new MinigameRepository();
        jogoService = new JogoService(jogoRepository, historicoRepository, minigameRepository);

        jogador = new Jogador("Luz", new Aparencia("Padrão"));
        mapa = new Mapa(true);
        jogo = new Jogo("a", jogador, mapa);

        programacao = new Disciplina("Programação", new MinigameSoftware(), 60, 0, null);
    }

    @Test
    public void test_Preparar_E_Jogar_Semana_De_Provas() throws JogoException {
        //prepara o cenário
        Jogador aluno = new Jogador("Luz", new Aparencia("padrão"));

        //cria uma disciplina focada em digitação
        MinigameTexto provaDigitacao = new MinigameTexto();
        Disciplina ptta = new Disciplina("PTTA", provaDigitacao, 60, 0, null);

        aluno.getHistorico().adicionarPendente(ptta);
        aluno.getHistorico().matricular(Arrays.asList(ptta));

        //o Service prepara a semana de provas
        jogoService.prepararSemanaDeProvas(aluno);

        //verifica se a preparação funcionou (o jogador está em prova e um texto foi sorteado)
        assertTrue(aluno.isFazendoProva());
        assertNotNull(provaDigitacao.getTextoAtual());

        //passamos para o método avaliar, como se o jogador tivesse digitado perfeitamente
        String textoSorteado = provaDigitacao.getTextoAtual();

        //simula que ele digitou o texto inteiro corretamente
        provaDigitacao.avaliarDigitacao(textoSorteado);

        //registra a nota obtida na matéria
        ptta.registrarNota(provaDigitacao.getPontuacao());

        assertEquals(10.0, ptta.getNota());
        assertTrue(ptta.isProvaFeita());
    }

    @Test
    public void test_Preparar_Provas_Sem_Materias() {
        Jogador alunoVazio = new Jogador("Luz", new Aparencia("padrão"));

        //se o aluno não tem matérias e tenta iniciar as provas, o service deve barrar
        JogoException excecao = assertThrows(JogoException.class, () -> {jogoService.prepararSemanaDeProvas(alunoVazio);});
        assertEquals("Você não está matriculado em nenhuma disciplina.", excecao.getMessage());
    }

    @Test
    public void test_Encerrar_Semestre_Nao_Formado() throws JogoException {
        //jogador tem uma matéria pendente, então não está formado. Semestre inicial é 1.
        jogador.getHistorico().adicionarPendente(programacao);

        //encerra o semestre
        jogoService.encerrarSemestre(jogo);

        //o semestre do jogador deve ter ido para 2
        assertEquals(2, jogador.getSemestre());

        //o jogo não pode ter sido finalizado
        assertFalse(jogo.isFinalizado());

        //o jogo deve ter sido salvo no repositório
        assertTrue(jogoRepository.existeSave("a"));
    }

    @Test
    public void test_Encerrar_Semestre_Formado() throws JogoException {
        //o jogador não tem matérias cursando nem pendentes.

        jogoService.encerrarSemestre(jogo);

        //o jogo deve ter detectado a formatura, finalizado e salvo.
        assertTrue(jogo.isFinalizado());
        assertTrue(jogoRepository.existeSave("a"));
    }

    @Test
    public void test_Carregar_Jogo_Finalizado() throws JogoException {
        //salva no repositório um jogo que já foi zerado
        jogo.setFinalizado(true);
        jogoService.salvarJogo(jogo);

        //tentar carregar um jogo encerrado deve impedir o jogador e pedir um Novo Jogo
        JogoException excecao = assertThrows(JogoException.class, () -> {jogoService.carregarJogo("a", mapa);});

        assertEquals("Este jogo já foi finalizado! Inicie um novo.", excecao.getMessage());
    }

    @Test
    public void test_Apagar_Save() throws JogoException {
        jogoService.salvarJogo(jogo); //salva um jogo

        //verifica se o jogo é exluido corretamente
        jogoService.apagarJogo("a");

        assertFalse(jogoRepository.existeSave("a"));
    }

    @Test
    public void test_Apagar_Slot_Inexistente() throws JogoException {

        //deleta o jogo e verifica se foi excluido corretmente
        assertThrows(JogoException.class, () -> {jogoService.apagarJogo("d");});
    }
    @Test
    public void test_Carregar_Jogo_Inexistente() {
        //tenta carregar o Slot 10, que está vazio
        JogoException excecao = assertThrows(JogoException.class, () -> {jogoService.carregarJogo("c", mapa);});

        assertEquals("Não há nenhum jogo neste slot.", excecao.getMessage());
    }

    //Os testes test_Apagar_Slot_Inexistente() e test_Carregar_Jogo_Inexistente() existem mais por desencargo de consciencia
    //porém, a ideia é que o jogador não tenha nem a opção de tentar fazer isso futuramente
}