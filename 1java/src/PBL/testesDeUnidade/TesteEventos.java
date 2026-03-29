package PBL.testesDeUnidade;

import PBL.model.Jogador;
import PBL.model.Atributo;
import PBL.model.evento.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TesteEventos {
    private Jogador jogador;

    @Before
    public void setUp() {
        jogador = new Jogador("Luz");
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
    public void testAtivarEvento() {
        EventoAleatorio filaSempre = new FilaGigante() {
            @Override
            public double gerarAleatorio() {
                return 0.10;
            }
        };
        assertTrue(filaSempre.tentarAtivar());
    }

    @Test
    public void testFalharEvento() {
        EventoAleatorio filaNunca = new FilaGigante() {
            @Override
            public double gerarAleatorio() {
                return 0.99;
            }
        };
        assertFalse(filaNunca.tentarAtivar());
    }

    @Test
    public void testFilaGiganteConsequencia() {
        EventoAleatorio fila = new FilaGigante();
        fila.eventoConsequencia(jogador);

        assertEquals(95, jogador.getTempo());
        assertEquals(90, getValorAtributo("Motivação"));
    }

    @Test
    public void testGreveConsequencia() {
        EventoAleatorio greve = new Greve();

        assertFalse(jogador.getHistorico().isSemestreGreve());
        greve.eventoConsequencia(jogador);
        assertTrue(jogador.getHistorico().isSemestreGreve());
    }

    @Test
    public void testMaterialCaro() {
        EventoAleatorio material = new MaterialCaro();

        material.eventoConsequencia(jogador);
        assertEquals(0.0, jogador.getDinheiro(), 0.001);
        assertEquals(85, getValorAtributo("Motivação"));

        jogador.modificarDinheiro(10);

        material.eventoConsequencia(jogador);
        assertEquals(10.0, jogador.getDinheiro(), 0.001);
        assertEquals(50, getValorAtributo("Motivação"));
    }

    @Test
    public void testPassarMalConsequencia() {
        EventoAleatorio passarMal = new PassarMal();
        passarMal.eventoConsequencia(jogador);

        assertEquals(90, getValorAtributo("Motivação"));
        assertEquals(90, getValorAtributo("Energia"));
        assertEquals(60, getValorAtributo("Saúde"));
    }

    @Test
    public void testProvaSurpresaConsequencia() {
        EventoAleatorio prova = new ProvaSurpresa();

        assertFalse(jogador.isFazendoProva());
        prova.eventoConsequencia(jogador);
        assertTrue(jogador.isFazendoProva());
    }

}