package PBL.testesDeUnidade;

import PBL.model.Jogador;
import PBL.model.Atributo;
import PBL.model.academico.Disciplina;
import org.junit.*;
import static org.junit.Assert.*;

public class TesteJogador {
    private Jogador jogador;

    @Before
    public void setUp() {
        jogador = new Jogador("Luz");
    }

    @Test
    public void testEstadoInicial() {
        assertEquals("Luz", jogador.getNome());
        assertEquals(50.0, jogador.getDinheiro(), 0.001);
        assertEquals(100, jogador.getTempo());
        assertEquals(1, jogador.getSemestre());
        assertEquals(0.0, jogador.getScore(), 0.001);
        assertFalse(jogador.isCansado());
        assertFalse(jogador.isFazendoProva());

        assertEquals(5, jogador.getStatus().size());
        assertNotNull(jogador.getHistorico());
    }

    @Test
    public void testModificarDinheiro() {
        assertTrue(jogador.modificarDinheiro(20));
        assertEquals(70, jogador.getDinheiro());

        assertTrue(jogador.modificarDinheiro(-30));
        assertEquals(40, jogador.getDinheiro());

        assertFalse(jogador.modificarDinheiro(-50));
        assertEquals(40, jogador.getDinheiro());
    }

    @Test
    public void testModificarTempo() {
        assertTrue(jogador.modificarTempo(-40));
        assertEquals(60, jogador.getTempo());

        assertFalse(jogador.modificarTempo(-80));
        assertEquals(60, jogador.getTempo());
    }

    @Test
    public void testConsequencia() {
        jogador.consequencia("Energia", -30);

        for (Atributo a : jogador.getStatus()) {
            if (a.getNome().equals("Energia")) {
                assertEquals(70, a.getValor());
                break;
            }
        }

        jogador.consequencia("Energia", -75);
        for (Atributo a : jogador.getStatus()) {
            if (a.getNome().equals("Energia")) {
                assertEquals(0, a.getValor());
                break;
            }
        }

        jogador.consequencia("Energia", 150);
        for (Atributo a : jogador.getStatus()) {
            if (a.getNome().equals("Energia")) {
                assertEquals(100, a.getValor());
                break;
            }
        }
    }

    @Test
    public void testDesempenhoListaVazia() {
        jogador.getHistorico().getCursando().clear();
        jogador.atualiDesempenho(); //não fez a divisão por 0
        assertTrue(true);
    }

    @Test
    public void testDesempenhoMediaCorreta() {
        Disciplina d1 = jogador.getHistorico().getCursando().get(0);
        Disciplina d2 = jogador.getHistorico().getCursando().get(1);

        d1.setDesempenho(100);
        d2.setDesempenho(50);

        jogador.getHistorico().getCursando().clear();
        jogador.getHistorico().getCursando().add(d1);
        jogador.getHistorico().getCursando().add(d2);

        jogador.atualiDesempenho();

        for (Atributo a : jogador.getStatus()) {
            if (a.getNome().equals("Desempenho Acadêmico")) {
                assertEquals(75, a.getValor());
                break;
            }
        }
    }

    @Test
    public void testAvancaSemestre() {
        int semestreAtual = jogador.getSemestre();

        jogador.avancaSemestre();

        assertEquals(semestreAtual + 1, jogador.getSemestre());

        assertTrue(jogador.getHistorico().getCursando().isEmpty());
    }

    @Test
    public void testModEstado() {
        jogador.setCansado(true);
        assertTrue(jogador.isCansado());

        jogador.setFazendoProva(true);
        assertTrue(jogador.isFazendoProva());
    }
}