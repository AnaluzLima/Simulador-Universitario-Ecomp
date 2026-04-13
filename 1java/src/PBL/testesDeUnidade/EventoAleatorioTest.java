package PBL.testesDeUnidade;

import PBL.exception.DinheiroException;
import PBL.exception.TempoException;
import PBL.fase_1.model.Aparencia;
import PBL.fase_1.model.Disciplina;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.evento.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventoAleatorioTest {

    private Jogador jogador;
    private FilaGigante filaGigante;
    private MaterialCaro materialCaro;
    private Disciplina disciplina;

    @BeforeEach
    public void inicializa() {
        jogador = new Jogador("Luz", new Aparencia("Padrão"));
        filaGigante = new FilaGigante();
        materialCaro = new MaterialCaro();
        disciplina = new Disciplina("Cálculo", null, 60, 0, null);

    }

    @Test
    public void test_Metodos_Herdados_Da_Classe_Abstrata() {

        assertEquals("Fila Gigante", filaGigante.getNome());
        assertEquals("...", filaGigante.getDescricao());
        assertEquals(0.30, filaGigante.getProbabilidade());
    }

    //Fila Gigante
    @Test
    public void test_Consequencia_Fila_Gigante_Com_Tempo_Suficiente() throws TempoException {
        filaGigante.eventoConsequencia(jogador);

        //Tempo -5, Energia -10, Motivacao -15
        assertEquals(95, jogador.getTempo());
        assertEquals(90, jogador.getEnergia().getValor());
        assertEquals(85, jogador.getMotivacao().getValor());
    }
    @Test
    public void test_Consequencia_Fila_Gigante_Sem_Tempo() {
        //deixa o jogador com apenas 2 de tempo
        jogador.setTempo(2);

        //verifica se cai na exceção corretamente
        assertThrows(TempoException.class, () -> {filaGigante.eventoConsequencia(jogador);});
    }

    //Material Caro
    @Test
    public void test_Consequencia_Com_Dinheiro_Suficiente() throws DinheiroException {
        //Jogador nasce com 50 conto e 100 de motivação

        materialCaro.eventoConsequencia(jogador);

        //Gastou os R$50 e perdeu 15 de motivação
        assertEquals(0, jogador.getDinheiro());
        assertEquals(85, jogador.getMotivacao().getValor());
    }
    @Test
    public void test_Consequencia_Sem_Dinheiro() throws DinheiroException {
        //jogador liso
        jogador.setDinheiro(0);

        //o evento tenta tirar 50, DinheiroException é chamado internamente e o catch é acionado
        materialCaro.eventoConsequencia(jogador);

        //o dinheiro continua 0, mas a motivação cai em 35
        assertEquals(0, jogador.getDinheiro());
        assertEquals(65, jogador.getMotivacao().getValor());
    }

    //Milagre Acadêmico
    @Test
    public void test_Consequencia_Ativa_Milagre() {
        MilagreAcademico milagre = new MilagreAcademico(disciplina);
        //define a motivacao como 50
        jogador.getMotivacao().setValor(50);

        //aplica o evento
        milagre.eventoConsequencia(jogador);

        //verifica se a motivacao foi corretamente aplicada
        assertEquals(60, jogador.getMotivacao().getValor());

        //verifica se a condição de milagre academico foi ativada para a materia
        assertTrue(disciplina.isMilagreAcademico());
    }

    //Greve
    @Test
    public void test_Consequencia_Greve_No_Historico() {
        Greve greve = new Greve();

        //aplica a consequencia
        greve.eventoConsequencia(jogador);

        //verifica se o semestre entrou em greve
        assertTrue(jogador.getHistorico().isSemestreGreve());
    }

    //Passar Mal
    @Test
    public void test_Consequencia_Passar_Mal() {
        PassarMal passarMal = new PassarMal();

        //aplica a consequencia
        passarMal.eventoConsequencia(jogador);

        assertEquals(90, jogador.getEnergia().getValor()); //jogador perde 10 de energia
        assertEquals(90, jogador.getMotivacao().getValor()); //10 de motivação
        assertEquals(60, jogador.getSaude().getValor()); //e 40 de saúde
    }

    //Prova Surpresa
    @Test
    public void test_Consequencia_Prova_Surpresa() {
        ProvaSurpresa prova = new ProvaSurpresa(disciplina);

        //incializa com o jogador não fazendo a prova
        assertFalse(jogador.isFazendoProva());
        assertFalse(disciplina.isProvaSurpresaAtiva());

        //aplica a consequencia
        prova.eventoConsequencia(jogador);

        //jogador fazendo prova
        assertTrue(jogador.isFazendoProva());
        assertTrue(disciplina.isProvaSurpresaAtiva());
    }
}