package PBL.testesDeUnidade;

import PBL.model.locais.Mapa;
import PBL.model.locais.Local;
import PBL.model.Jogador;
import PBL.model.tasks.Atividade;
import PBL.model.tasks.AssistirAula;
import PBL.model.academico.Disciplina;
import PBL.model.tasks.CasaDormir;
import PBL.model.tasks.CasaEstudar;
import org.junit.*;
import static org.junit.Assert.*;

public class TesteMapa {

    private Mapa mapaF6;
    private Mapa mapaPonto;
    private Jogador jogador;

    @Before
    public void setUp() {
        mapaF6 = new Mapa(true); //spawna no Feira VI
        mapaPonto = new Mapa(false); //spawna no ponto de ônibus
        jogador = new Jogador("Luz");
    }

    @Test
    public void testSpawnFeiraVI() {
        Local spawn = mapaF6.getLocalNome("Portão Lateral");
        assertNotNull(spawn);

        boolean veriCasaDormir=false, veriCasaEstudar=false;

        for (Atividade a : spawn.getAtvLocais()) {
            if(a instanceof CasaDormir){
                veriCasaDormir = true;
            }
            if (a instanceof CasaEstudar){
                veriCasaEstudar = true;
            }
        }
        assertTrue(veriCasaDormir && veriCasaEstudar);
    }

    @Test
    public void testSpawnOnibus() {
        Local ponto1 = mapaPonto.getLocalNome("Ponto de Ônibus M5-M6");
        assertNotNull(ponto1);

        boolean veriCasaDormir=false, veriCasaEstudar=false;

        for (Atividade a : ponto1.getAtvLocais()) {
            if(a instanceof CasaDormir){
                veriCasaDormir = true;
            }
            if (a instanceof CasaEstudar){
                veriCasaEstudar = true;
            }
        }
        assertTrue(veriCasaDormir && veriCasaEstudar);
    }

    @Test
    public void testLocalExistente() {
        Local bandejao = mapaF6.getLocalNome("Bandejão");
        assertNotNull(bandejao);
        assertEquals("Bandejão", bandejao.getNome());
    }

    @Test
    public void testLocalInexistente() {
        Local localFalso = mapaF6.getLocalNome("Modulo 8");
        assertNull(localFalso); //return null
    }

    @Test
    public void testDistribuirAulas() {
        Disciplina materia = jogador.getHistorico().getCursando().get(0);
        String nomeLocalDaMateria = materia.getNomeLocal();

        Local salaDeAula = mapaF6.getLocalNome(nomeLocalDaMateria);
        assertNotNull(salaDeAula);

        int qtdAtividadesInicial = salaDeAula.getAtvLocais().size();

        mapaF6.distribuirAulas(jogador);

        assertTrue(salaDeAula.getAtvLocais().size() > qtdAtividadesInicial);

        boolean temAula = false;
        for (Atividade a : salaDeAula.getAtvLocais()) {
            if (a instanceof AssistirAula) {
                temAula = true;
                break;
            }
        }
        assertTrue(temAula);
    }
}