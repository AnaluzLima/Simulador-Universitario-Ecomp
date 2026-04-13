package PBL.testesDeUnidade;

import PBL.exception.MatriculaException;
import PBL.fase_1.model.*;
import PBL.fase_1.model.tasks.AssistirAula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapaTest {

    private Mapa mapa;
    private Local pavilhao;
    private Local leds;
    private Jogador jogador;

    @BeforeEach
    public void inicializa() {
        mapa = new Mapa(true);
        pavilhao = new Local("Pavilhão");
        leds = new Local("LEDS");

        mapa.setCampus(Arrays.asList(pavilhao, leds));

        Aparencia skin = new Aparencia("Padrão");
        jogador = new Jogador("Luz", skin);
    }

    @Test
    public void test_Buscar_Local_Inexistente() {
        Local encontrado = mapa.buscarLocalPorNome("Módulo 8");
        assertNull(encontrado);
    }

    @Test
    public void test_Distribuir_Aulas_Na_Sala_Certa() throws MatriculaException {
        //disciplina em que a aula é no Pavilhão
        Disciplina algoritmos = new Disciplina("Algoritmos I", null, 60, 0, pavilhao);

        //matricula o jogador na disciplina
        jogador.getHistorico().adicionarPendente(algoritmos);
        jogador.getHistorico().matricular(Arrays.asList(algoritmos));

        //O mapa tem que ler o histórico e colocar a atividade de "Assistir Aula" no Pavilhão
        mapa.distribuirAulas(jogador);

        //Pavilhão ganhou uma atividade de aula. O LEDS continuou vazio.
        assertEquals(1, pavilhao.getAtvLocais().size());
        assertEquals(0, leds.getAtvLocais().size());

        //confere se a atividade criada foi preenchida com a matéria certa
        AssistirAula atividadeAula = (AssistirAula) pavilhao.getAtvLocais().get(0);
        assertTrue(atividadeAula.getNome().contains("Algoritmos I"));
    }

    @Test
    public void test_Distribuir_Aulas_Limpa_O_Semestre_Anterior() {
        //injetamos uma aula do semestre passado no LEDS
        leds.addAtividade(new AssistirAula());
        assertEquals(1, leds.getAtvLocais().size());

        //distribuímos aulas para o Jogador que está com ZERO matérias agora
        mapa.distribuirAulas(jogador);

        //O mapa varre tudo e apaga a aula do LEDS. Deve estar com 0.
        assertEquals(0, leds.getAtvLocais().size());
    }
}