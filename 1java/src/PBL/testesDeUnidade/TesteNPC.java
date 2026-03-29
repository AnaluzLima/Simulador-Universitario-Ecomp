package PBL.testesDeUnidade;

import PBL.model.Jogador;
import PBL.model.Atributo;
import PBL.model.personagens.Animal;
import PBL.model.personagens.Colega;
import PBL.model.personagens.Professor;
import PBL.model.locais.Local;
import PBL.model.academico.Disciplina;
import org.junit.*;
import static org.junit.Assert.*;

public class TesteNPC {
    private Jogador jogador;
    private Colega colega;
    private Professor professor;

    @Before
    public void setUp() {
        jogador = new Jogador("Luz");
        jogador.consequencia("Motivação", -30); //70

        colega = new Colega("Colega");
        professor = new Professor("Professor", 5);
    }

    private int getValorAtributo(String nome) {
        for (Atributo a : jogador.getStatus()) {
            if (a.getNome().equals(nome)) {
                return a.getValor();
            }
        }
        return -1;
    }

    @Test
    public void testInicializacao() {
        assertEquals("Colega", colega.getNome());
        assertEquals(0, colega.getRelacao());

        Local praca = new Local("Praça do Borogodó");
        colega.setLocalizacao(praca);

        assertNotNull(colega.getLocalizacao());
        assertEquals("Praça do Borogodó", colega.getLocalizacao().getNome());
    }

    @Test
    public void testLimitesRelacao() {
        colega.modificarRelacao(15);
        assertEquals(10, colega.getRelacao());

        colega.modificarRelacao(-20);
        assertEquals(0, colega.getRelacao());
    }

    @Test
    public void testInteragirColega() {
        colega.interagir(jogador);

        assertEquals(97, jogador.getTempo());
        assertEquals(80, getValorAtributo("Motivação"));
        assertEquals(1, colega.getRelacao());

        jogador.modificarTempo(-95);
        assertEquals(2, jogador.getTempo());

        colega.interagir(jogador);

        assertEquals(2, jogador.getTempo());
        assertEquals(80, getValorAtributo("Motivação"));
        assertEquals(1, colega.getRelacao());
    }

    @Test
    public void testInteragirProfessor() {
        professor.interagir(jogador);

        assertEquals(97, jogador.getTempo());
        assertEquals(5, getValorAtributo("Conhecimento"));
        assertEquals(90, getValorAtributo("Energia"));
        assertEquals(1, professor.getRelacao());

        jogador.modificarTempo(-95);
        assertEquals(2, jogador.getTempo());

        professor.interagir(jogador);
        assertEquals(2, jogador.getTempo());
        assertEquals(5, getValorAtributo("Conhecimento"));
        assertEquals(90, getValorAtributo("Energia"));
        assertEquals(1, professor.getRelacao());
    }

    @Test
    public void testAdicionarMateriaProfessor() {
        Disciplina algoritmos = new Disciplina("Algoritmos 2", 60, 0, "M5");

        professor.addMateria(algoritmos);
        assertTrue(true);

    }

    @Test
    public void testAnimalInicializacao() {
        Animal doguinho = new Animal("Scooby", "Cachorro", 0.03);

        assertEquals("Bidu", doguinho.getNome());
        assertEquals(0, doguinho.getRelacao());
    }

    @Test
    public void testAnimalCarinho() {
        Animal doguinho = new Animal("Scooby", "Cachorro", 0.03);

        jogador.modificarTempo(-100);
        assertEquals(0, jogador.getTempo());

        doguinho.interagir(jogador);
        assertEquals(0, jogador.getTempo());

        jogador.modificarTempo(51);
        doguinho.interagir(jogador);
        assertEquals(50, jogador.getTempo());
    }

}