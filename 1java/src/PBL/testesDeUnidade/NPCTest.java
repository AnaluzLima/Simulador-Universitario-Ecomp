package PBL.testesDeUnidade;

import PBL.exception.TempoException;
import PBL.fase_1.model.Aparencia;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.personagens.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NPCTest {

    private Jogador jogador;
    private Colega colega;
    private Funcionario funcionario;
    private Professor professor;
    private Animal cachorroBravo;
    private Animal gatoFofo;

    @BeforeEach
    public void inicializa() {
        jogador = new Jogador("Luz", new Aparencia("Padrão"));
        colega = new Colega("Scooby");
        funcionario = new Funcionario("Maeli");
        professor = new Professor("Bianca", 7);

        cachorroBravo = new Animal("Velinho do 5", 1.0); //100% de chance de ataque
        gatoFofo = new Animal("Fabio Junior", 0.0); //0% de chance de ataque

        jogador.getMotivacao().setValor(50);
    }

    @Test
    public void test_Modificar_Relacao() {

        //tenta passar do limite máximo (10)
        colega.modificarRelacao(15);
        assertEquals(10, colega.getRelacao());

        //tenta passar do limite mínimo (0)
        colega.modificarRelacao(-20);
        assertEquals(0, colega.getRelacao());
    }

    @Test
    public void test_Pedir_Contato_Recusado() throws TempoException {
        //Relação inicial é 0. O limite para ter 'intimidade' para pedir o contato é 3.

        String resposta = colega.pedirContato(jogador);

        //custa 1 tempo, o jogador perde 5 de motivação e recebe a recusa
        assertEquals(99, jogador.getTempo());
        assertEquals(45, jogador.getMotivacao().getValor());
        assertEquals("Scooby: Ah... eu não costumo passar meu número assim, desculpa.", resposta);

        //celular deve continuar vazio
        assertEquals(0, jogador.getCelular().getContatos().size());
    }

    @Test
    public void test_Pedir_Contato_Aceito() throws TempoException {
        //aumenta a relação para o mínimo exigido (3)
        colega.setRelacao(3);

        String resposta = colega.pedirContato(jogador);

        //Custa 1 tempo e retorna sucesso
        assertEquals(99, jogador.getTempo());
        assertEquals("Scooby: Claro!", resposta);

        //o npc deve estar no celular
        assertTrue(jogador.getCelular().getContatos().contains("Scooby"));
    }

    @Test
    public void test_Pedir_Contato_Repetido() throws TempoException {
        //tem relação e já está no celular
        colega.setRelacao(5);
        jogador.getCelular().adicionarContato(colega);

        String resposta = colega.pedirContato(jogador);

        assertEquals("Scooby: Ué, você já não tem meu número?!", resposta);
    }

    @Test
    public void test_Pedir_Contato_Sem_Tempo() {
        //define tempo = 0
        jogador.setTempo(0);

        //cai na exceção TempoException
        assertThrows(TempoException.class, () -> {colega.pedirContato(jogador);});
    }

    //Colega
    @Test
    public void test_Interagir_Colega() throws TempoException {

        colega.interagir(jogador);

        //-3 Tempo, +10 Motivação, +1 Relação
        assertEquals(97, jogador.getTempo());
        assertEquals(60, jogador.getMotivacao().getValor()); //50+10
        assertEquals(1, colega.getRelacao());
    }

    //Funcionario
    @Test
    public void test_Interagir_Funcionario() throws TempoException {
        funcionario.interagir(jogador);

        //-1 Tempo, +5 Motivação, +1 Relação
        assertEquals(99, jogador.getTempo());
        assertEquals(55, jogador.getMotivacao().getValor()); //50+5
        assertEquals(1, funcionario.getRelacao());
    }

    //Professor
    @Test
    public void test_Interagir_Professor() throws TempoException {
        professor.interagir(jogador);

        //-3 Tempo, +5 Conhecimento, -15 Energia (rigor 7 * 2), +1 Relação
        assertEquals(97, jogador.getTempo());
        assertEquals(5, jogador.getConhecimento().getValor());
        assertEquals(86, jogador.getEnergia().getValor());
        assertEquals(1, professor.getRelacao());
    }

    //Animal
    @Test
    public void test_Interagir_Animal_Ataque() throws TempoException {
        cachorroBravo.interagir(jogador);

        //-1 Tempo, -30 Saúde, -5 Energia, -2 Relação
        assertEquals(99, jogador.getTempo());
        assertEquals(70, jogador.getSaude().getValor());
        assertEquals(95, jogador.getEnergia().getValor());
        assertEquals(0, cachorroBravo.getRelacao()); // Trava no 0
    }
    @Test
    public void test_Interagir_Animal_Carinho() throws TempoException {
        gatoFofo.interagir(jogador);

        //-1 Tempo, +10 Motivação, +5 Energia, +1 Relação
        assertEquals(99, jogador.getTempo());
        assertEquals(60, jogador.getMotivacao().getValor()); //50+10
        assertEquals(100, jogador.getEnergia().getValor()); // Trava no 100
        assertEquals(1, gatoFofo.getRelacao());
    }
}