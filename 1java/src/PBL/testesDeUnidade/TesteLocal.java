package PBL.testesDeUnidade;

import PBL.model.locais.Local;
import PBL.model.tasks.Relaxar;
import org.junit.*;
import static org.junit.Assert.*;

public class TesteLocal {

    private Local praca;
    private Local biblioteca;

    @Before
    public void setUp() {
        praca = new Local("Praça do Borogodó");
        biblioteca = new Local("Biblioteca");
    }

    @Test
    public void testInicializacao() {
        assertEquals("Praça do Borogodó", praca.getNome());
        assertTrue(praca.getConexoes().isEmpty());
        assertTrue(praca.getAtvLocais().isEmpty());
    }

    @Test
    public void testConexao() {
        praca.conectar(biblioteca);

        assertTrue(praca.getConexoes().contains(biblioteca));
        assertEquals(1, praca.getConexoes().size());

        assertTrue(biblioteca.getConexoes().contains(praca));
        assertEquals(1, biblioteca.getConexoes().size());
    }

    @Test
    public void testConexaoDuplicada() {
        praca.conectar(biblioteca);
        praca.conectar(biblioteca);

        assertEquals(1, praca.getConexoes().size());
        assertEquals(1, biblioteca.getConexoes().size());
    }

    @Test
    public void testAdicionarAtividade() {
        Relaxar relaxar = new Relaxar();
        praca.addAtividade(relaxar);

        assertFalse(praca.getAtvLocais().isEmpty());
        assertEquals(1, praca.getAtvLocais().size());
        assertEquals("Relaxar na Praça", praca.getAtvLocais().get(0).getNome());
    }
}