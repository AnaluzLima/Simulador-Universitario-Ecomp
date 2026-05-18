package PBL.testesDeUnidade.testes_fase2;

import PBL.fase_1.model.Disciplina;
import PBL.fase_1.model.Historico;
import PBL.fase_1.model.Mapa;
import PBL.fase_1.repository.HistoricoRepository;
import PBL.fase_1.repository.MinigameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricoRepositoryTest {

    private HistoricoRepository historicoRepo;
    private MinigameRepository minigameRepo;
    private Mapa mapa;

    @BeforeEach
    public void inicializa() {
        historicoRepo = new HistoricoRepository();
        minigameRepo = new MinigameRepository();
        mapa = new Mapa(true);
        //instancia os locais minimamente para o teste não quebrar
        mapa.getCampus().add(new PBL.fase_1.model.Local("Salas de Aula do Módulo 5"));
        mapa.getCampus().add(new PBL.fase_1.model.Local("LEDS"));
    }

    @Test
    public void test_Carregar_Historico_Gera_Grade_Completa() {
        Historico historico = new Historico();
        historicoRepo.carregarHistorico(historico, mapa, minigameRepo);

        //verifica se gerou as matérias pendentes e as do primeiro semestre
        assertFalse(historico.getPendentes().isEmpty());
        assertFalse(historico.getCursando().isEmpty());

        //qm ta cursando o 1º semestre deve ter 7 matérias (TFH, MI Alg, Intro ECOMP, Alg 1, Intro Calc, Intro Elet, PTTA)
        assertEquals(7, historico.getCursando().size());
    }

    @Test
    public void test_Reconstruir_Disciplinas_Transients_Nulos() {
        Historico historicoSalvo = new Historico();

        //simula uma matéria vindo do JSON com os transients quebrados
        Disciplina disciplinaQuebrada = new Disciplina("MI Algoritmos", null, 60, 0, null);
        historicoSalvo.getCursando().add(disciplinaQuebrada);

        historicoRepo.reconstruirDisciplinas(historicoSalvo, mapa, minigameRepo);

        //pega a matéria arrumada
        Disciplina disciplinaConsertada = historicoSalvo.getCursando().iterator().next();

        //as regras fixas que o Gson ignorou devem ter sido devolvidas à matéria!
        assertNotNull(disciplinaConsertada.getMinigame());
        assertNotNull(disciplinaConsertada.getSala());
        assertEquals("Software", disciplinaConsertada.getMinigame().getArea());
    }
}