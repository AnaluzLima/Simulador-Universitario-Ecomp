package PBL.testesDeUnidade;

import PBL.model.academico.Historico;
import PBL.model.academico.Disciplina;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class TesteHistorico {
    private Historico hJogador;

    @Before
    public void setUp() {
        hJogador = new Historico();
    }

    @Test
    public void testCarregarHistorico() {
        assertNotNull(hJogador.getPendentes());
        assertNotNull(hJogador.getCursando());
        assertEquals(7, hJogador.getCursando().size());
        assertEquals(0, hJogador.getAprovadas().size());
    }

    @Test
    public void testMatricular(){
        Disciplina exa614 = hJogador.getCursando().getFirst();
        assertFalse(hJogador.matricular(exa614));

        Disciplina exa704 = hJogador.getPendentes().getFirst();
        assertFalse(hJogador.matricular(exa704));

        Disciplina tec221 = hJogador.getPendentes().get(2);
        assertTrue(hJogador.matricular(tec221));
    }

    @Test
    public void testFimSemestre(){
        Disciplina aprovada = hJogador.getCursando().get(0);
        Disciplina reprovada = hJogador.getCursando().get(1);

        aprovada.setNota(8.0);
        aprovada.setProvaFeita(true);

        reprovada.setNota(2.0);
        reprovada.setProvaFeita(true);

        hJogador.fimSemestre();

        assertFalse(hJogador.getCursando().contains(aprovada));
        assertFalse(hJogador.getCursando().contains(reprovada));

        assertTrue(hJogador.getAprovadas().contains(aprovada));
        assertTrue(hJogador.getPendentes().contains(reprovada));

        assertFalse(reprovada.isProvaFeita());
        assertEquals(0.0, reprovada.getNota(), 0.001);
        assertEquals(0, reprovada.getDesempenho());

    }

    @Test
    public void testSemanaPtova(){
        List<Disciplina> cursando = hJogador.getCursando();

        cursando.get(0).setProvaFeita(true);
        cursando.get(1).setProvaFeita(true);

        List<Disciplina> provasPendentes = hJogador.getSemanaProva();

        assertEquals(5, provasPendentes.size());

        for(Disciplina d : provasPendentes) {
            assertFalse(d.isProvaFeita());
        }
    }

    @Test
    public void testFormar(){
        assertFalse(hJogador.formado());

        hJogador.getCursando().clear();
        hJogador.getPendentes().clear();

        assertTrue(hJogador.formado());
    }
}