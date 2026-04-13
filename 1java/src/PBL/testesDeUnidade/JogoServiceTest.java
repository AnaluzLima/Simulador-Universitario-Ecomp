package PBL.testesDeUnidade;

import PBL.exception.JogoException;
import PBL.fase_1.model.Aparencia;
import PBL.fase_1.model.Disciplina;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.Jogo;
import PBL.fase_1.model.Mapa;
import PBL.fase_1.model.minigames.MinigameSoftware;
import PBL.fase_1.repository.JogoRepository;
import PBL.fase_1.service.JogoService;
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

    @BeforeEach
    public void inicializa() {
        jogoRepository = new JogoRepository();
        jogoService = new JogoService(jogoRepository);

        jogador = new Jogador("Luz", new Aparencia("Padrão"));
        Mapa mapa = new Mapa(true);
        jogo = new Jogo(1, jogador, mapa);

        programacao = new Disciplina("Programação", new MinigameSoftware(), 60, 0, null);
    }

    @Test
    public void test_Iniciar_Semana_De_Provas() throws Exception {
        //o jogador cursa a matéria de Programação
        jogador.getHistorico().adicionarPendente(programacao);
        jogador.getHistorico().matricular(Arrays.asList(programacao));

        //Service inicia a semana de provas
        jogoService.iniciarSemanaDeProvas(jogador);

        //Os minigames serão implementados futuramente, então a nota será definida manualmente
        programacao.registrarNota(9);

        assertEquals(9.0, programacao.getNota());
        assertTrue(programacao.isProvaFeita());
    }

    @Test
    public void test_Encerrar_Semestre_Nao_Formado_Avanca_Semestre_E_Salva() {
        //jogador tem uma matéria pendente, então não está formado. Semestre inicial é 1.
        jogador.getHistorico().adicionarPendente(programacao);

        //encerra o semestre
        jogoService.encerrarSemestre(jogo);

        //o semestre do jogador deve ter ido para 2
        assertEquals(2, jogador.getSemestre());

        //o jogo não pode ter sido finalizado
        assertFalse(jogo.isFinalizado());

        //o jogo deve ter sido salvo no repositório
        assertTrue(jogoRepository.existeSave(1));
    }

    @Test
    public void test_Encerrar_Semestre_Formado_Finaliza_Jogo() {
        //o jogador não tem matérias cursando nem pendentes.

        jogoService.encerrarSemestre(jogo);

        //o jogo deve ter detectado a formatura, finalizado e salvo.
        assertTrue(jogo.isFinalizado());
        assertTrue(jogoRepository.existeSave(1));
    }

    @Test
    public void test_Carregar_Jogo_Inexistente() {
        //tenta carregar o Slot 10, que está vazio
        JogoException excecao = assertThrows(JogoException.class, () -> {jogoService.carregarJogo(10);});

        assertEquals("Não há nenhum jogo neste slot.", excecao.getMessage());
    }

    @Test
    public void test_Carregar_Jogo_Finalizado() {
        //salva no repositório um jogo que já foi zerado
        jogo.setFinalizado(true);
        jogoService.salvarJogo(jogo);

        //tentar carregar um jogo encerrado deve impedir o jogador e pedir um Novo Jogo
        JogoException excecao = assertThrows(JogoException.class, () -> {jogoService.carregarJogo(1);});

        assertEquals("Este jogo já foi finalizado! Inicie um novo.", excecao.getMessage());
    }

    @Test
    public void test_Apagar_Save() throws JogoException {
        jogoService.salvarJogo(jogo); //salva um jogo

        //verifica se o jogo é exluido corretamente
        jogoService.apagarJogo(1);

        assertFalse(jogoRepository.existeSave(1));
    }

    @Test
    public void test_Apagar_Slot_Inexistente() throws JogoException {

        //deleta o jogo e verifica se foi excluido corretmente
        assertThrows(JogoException.class, () -> {jogoService.apagarJogo(1);;});
    }
}