package PBL.testesDeUnidade.testes_fase1;

import PBL.model.model.Aparencia;
import PBL.model.model.Jogador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import PBL.exception.DinheiroException;
import PBL.exception.TempoException;

public class JogadorTest {

    private Jogador jogador;
    private Aparencia skin;

    @BeforeEach
    public void inicializa() {

        //Jogador inicial: 50 reais, 100 de tempo, semestre 1

        skin = new Aparencia("Padrão");
        jogador = new Jogador("Mauricio", skin);
    }

    @Test
    public void test_Descontar_Dinheiro_com_Saldo() throws DinheiroException {
        //Gasta 20 reais
        jogador.modificarDinheiro(-20);

        //50 - 20 = 30
        assertEquals(30, jogador.getDinheiro());
    }

    @Test
    public void test_Adicionar_Dinheiro() throws DinheiroException {
        //Ganha 50 reais
        jogador.modificarDinheiro(50);

        //50 + 50 = 100
        assertEquals(100, jogador.getDinheiro());
    }

    @Test
    public void test_Bloquear_Gastar_Mais_Dinheiro_Do_Que_Possui() {
        //Gastar 60 quando tem 50 deve lançar a DinheiroException

        //verifica se, ao usar 'jogador.modificarDinheiro', o tipo de erro é o DinheiroException
        assertThrows(DinheiroException.class, () -> {jogador.modificarDinheiro(-60);});

        //Saldo intacto após a falha
        assertEquals(50, jogador.getDinheiro());
    }

    @Test
    public void test_Descontar_Tempo_com_Saldo() throws TempoException {
        //Gasta 30 pontos de tempo
        jogador.modificarTempo(-30);

        //100 - 30 = 70
        assertEquals(70, jogador.getTempo());
    }

    @Test
    public void test_Bloquar_Gastar_Mais_Tempo_Do_Que_Possui() {
        //Gastar 110 quando se tem 100 deve lançar TempoException

        //verifica se, ao usar 'jogador.modificarTempo', o tipo de erro é o TempoException
        assertThrows(TempoException.class, () -> {jogador.modificarTempo(-110);});

        //Tempo intactacto após a falha
        assertEquals(100, jogador.getTempo());
    }

    @Test
    public void test_Avancar_Semestre() {
        //semestre += 1
        jogador.avancarSemestre();

        //Semestre inicial é 1, então deve ir para 2
        assertEquals(2, jogador.getSemestre());
    }
}