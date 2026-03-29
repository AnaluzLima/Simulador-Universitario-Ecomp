package PBL.testesDeUnidade;

import PBL.model.Atributo;
import org.junit.*;
import static org.junit.Assert.*;

public class TesteAtributo {
    private Atributo atributo;

    @Before
    public void setUp() {
        atributo = new Atributo("Energia", 50, 0, 100);
    }

    @Test
    public void testInicializacao() {
        assertEquals("Energia", atributo.getNome());
        assertEquals(50, atributo.getValor());
    }

    @Test
    public void testModificacaoNormal() {
        atributo.modificar(20);
        assertEquals(70, atributo.getValor());

        atributo.modificar(-30);
        assertEquals(40, atributo.getValor());

        atributo.modificar(150);
        assertEquals(100, atributo.getValor());

        atributo.modificar(-120);
        assertEquals(0, atributo.getValor());
    }

    @Test
    public void testSetValor() {
        atributo.setValor(85);
        assertEquals(85, atributo.getValor());
    }
}