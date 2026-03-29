package PBL.testesDeUnidade;

import PBL.model.academico.Disciplina;
import org.junit.*;
import static org.junit.Assert.*;

public class TesteDisciplina {
    private Disciplina dis2;

    @Before
    public void setUp() {
        dis2 = new Disciplina("Algoritmos 2", 60, 0, "M5");
    }

    @Test
    public void testAdicionarPreRequisito() {
        Disciplina dis1 = new Disciplina("Algoritmos 1", 60, 0, "Pavilhão");
        assertTrue(dis2.getPreRequisitos().isEmpty());

        dis2.addPreRequisito(dis1);

        assertFalse(dis2.getPreRequisitos().isEmpty());
        assertEquals(1, dis2.getPreRequisitos().size());
        assertEquals("Algoritmos 1", dis2.getPreRequisitos().get(0).getNome());
    }

    @Test
    public void testAdicionarCoRequisito() {
        Disciplina dis1 = new Disciplina("MI Programação", 30, 0, "M5");
        assertTrue(dis2.getCoRequisitos().isEmpty());

        dis2.addCoRequisito(dis1);

        assertFalse(dis2.getCoRequisitos().isEmpty());
        assertEquals(1, dis2.getCoRequisitos().size());
        assertEquals("MI Programação", dis2.getCoRequisitos().get(0).getNome());
    }

    @Test
    public void testLimitesDeDesempenho() {
        assertEquals(0, dis2.getDesempenho());

        dis2.modificarDesempenho(65);
        assertEquals(65, dis2.getDesempenho());

        dis2.modificarDesempenho(50);
        assertEquals(100, dis2.getDesempenho());

        dis2.modificarDesempenho(-150);
        assertEquals(0, dis2.getDesempenho());
    }

    @Test
    public void testRegistrarNotaBonus() {
        dis2.setDesempenho(80);
        dis2.registrarNota(7.5);

        assertEquals(7.5, dis2.getNota(), 0.001);

        dis2.setDesempenho(98);
        dis2.registrarNota(5.0);

        assertEquals(8.0, dis2.getNota(), 0.001);

        dis2.setDesempenho(99);
        dis2.registrarNota(8.5);

        assertEquals(10.0, dis2.getNota(), 0.001);
    }
}