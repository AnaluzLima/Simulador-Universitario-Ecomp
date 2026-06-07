package PBL.testesDeUnidade.testes_fase2;

import PBL.model.model.minigames.*;
import PBL.model.repository.MinigameRepository;
import PBL.model.service.MinigameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MinigameTest {

    private MinigameRepository repo;
    private MinigameService service;

    @BeforeEach
    public void inicializa() {
        repo = new MinigameRepository();
        service = new MinigameService();
    }

    //testando regras gerais
    @Test
    public void test_Calcular_Dificuldade() {
        Minigame minigame = repo.buscarMinigamePorArea("Texto");

        //desempenho <= 50 -> Difícil
        assertEquals("Difícil", minigame.calcularDificuldade(30));
        assertEquals("Difícil", minigame.calcularDificuldade(50));

        //desempenho > 50 e <= 80 -> Médio
        assertEquals("Médio", minigame.calcularDificuldade(51));
        assertEquals("Médio", minigame.calcularDificuldade(80));

        //desempenho > 80 -> Fácil
        assertEquals("Fácil", minigame.calcularDificuldade(81));
        assertEquals("Fácil", minigame.calcularDificuldade(100));
    }

    //testando o minigame de texto
    @Test
    public void test_MinigameTexto_Digitacao_Nula_Ou_Vazia() {
        MinigameTexto texto = (MinigameTexto) repo.buscarMinigamePorArea("Texto");
        texto.setTextoAtual("UEFS");

        texto.avaliarDigitacao(null);
        assertEquals(0, texto.getPontuacao());

        texto.avaliarDigitacao("");
        assertEquals(0, texto.getPontuacao());
    }
    @Test
    public void test_MinigameTexto_Acerto_Parcial() {
        MinigameTexto texto = (MinigameTexto) repo.buscarMinigamePorArea("Texto");
        //palavra de 10 letras
        texto.setTextoAtual("engenharia");

        //jogador digitou 5 letras certas e o resto errado/faltou
        texto.avaliarDigitacao("engenXXXXX");

        //5/10 = 50% = Nota 5
        assertEquals(5, texto.getPontuacao());
    }
    @Test
    public void test_MinigameTexto_Acerto_Total() {
        MinigameTexto texto = (MinigameTexto) repo.buscarMinigamePorArea("Texto");
        texto.setTextoAtual("algoritmos");

        texto.avaliarDigitacao("algoritmos");
        assertEquals(10, texto.getPontuacao());
    }

    //testando o minigame de matemática
    @Test
    public void test_MinigameMatematica_Formatacao_Resposta() {
        MinigameMatematica matematica = (MinigameMatematica) repo.buscarMinigamePorArea("Matemática");
        matematica.setRodada("15 + 27", "42");

        //resposta certa mas cheia de espaços e letras maiúsculas misturadas
        matematica.avaliarResposta("   4 2 ", 5);
        assertEquals(10, matematica.getPontuacao()); //deve acertar e ganhar 10
    }
    @Test
    public void test_MinigameMatematica_Limites_De_Tempo() {
        MinigameMatematica matematica = (MinigameMatematica) repo.buscarMinigamePorArea("Matemática");
        matematica.setRodada("10 + 10", "20");

        //rápido
        matematica.avaliarResposta("20", 5);
        assertEquals(10, matematica.getPontuacao());

        //médio
        matematica.avaliarResposta("20", 10);
        assertEquals(8, matematica.getPontuacao());

        //lento
        matematica.avaliarResposta("20", 15);
        assertEquals(7, matematica.getPontuacao());

        //errou
        matematica.avaliarResposta("15", 5);
        assertEquals(0, matematica.getPontuacao());
    }

    //testando o minigame de software
    @Test
    public void test_MinigameSoftware_Avaliacao() {
        MinigameSoftware software = (MinigameSoftware) repo.buscarMinigamePorArea("Software");
        software.setRodada("Pergunta", Arrays.asList("A", "B", "C"), 1); //correta é a B

        //errou o índice
        software.avaliarResposta(0, 10);
        assertEquals(0, software.getPontuacao());

        //acertou rápido
        software.avaliarResposta(1, 5);
        assertEquals(10, software.getPontuacao());

        //acertou médio
        software.avaliarResposta(1, 10);
        assertEquals(8, software.getPontuacao());

        //acertou lento
        software.avaliarResposta(1, 15);
        assertEquals(7, software.getPontuacao());
    }

    //testando o minigame de hardware
    @Test
    public void test_MinigameHardware_Conexao_Correta_E_Invertida() {
        MinigameHardware hardware = (MinigameHardware) repo.buscarMinigamePorArea("Hardware");
        hardware.setRodada("Ligue o circuito", "Pino 5V", "Resistor");

        //ligou na ordem exata ditada pelo gabarito
        hardware.avaliarConexao("Pino 5V", "Resistor", 10);
        assertEquals(10, hardware.getPontuacao());

        //ligou o destino na origem
        hardware.avaliarConexao("Resistor", "Pino 5V", 10);
        assertEquals(10, hardware.getPontuacao());
    }

    @Test
    public void test_MinigameHardware_Conexao_Incorreta_Ou_Nula() {
        MinigameHardware hardware = (MinigameHardware) repo.buscarMinigamePorArea("Hardware");
        hardware.setRodada("Ligue o circuito", "Pino 5V", "Resistor");

        //ligou fios completamente diferentes
        hardware.avaliarConexao("GND", "LED", 10);
        assertEquals(0, hardware.getPontuacao());

        //ligou o fio de origem, mas soltou a outra ponta no nada
        hardware.avaliarConexao("Pino 5V", "Nada", 10);
        assertEquals(0, hardware.getPontuacao());

        //não tentou ligar nada
        hardware.avaliarConexao(null, null, 10);
        assertEquals(0, hardware.getPontuacao());
    }

    //teste do repository e do service
    @Test
    public void test_Repository_Criacao_De_Minigames() {
        assertInstanceOf(MinigameTexto.class, repo.buscarMinigamePorArea("Texto"));
        assertInstanceOf(MinigameMatematica.class, repo.buscarMinigamePorArea("Matemática"));
        assertInstanceOf(MinigameSoftware.class, repo.buscarMinigamePorArea("Software"));
        assertInstanceOf(MinigameHardware.class, repo.buscarMinigamePorArea("Hardware"));
        assertNull(repo.buscarMinigamePorArea("humanas")); //não existe
    }
    @Test
    public void test_Service_Prepara_Rodada_Texto() {
        MinigameTexto minigame = (MinigameTexto) repo.buscarMinigamePorArea("Texto");

        //simula jogador com desempenho alto
        service.prepararMinigameTexto(minigame, 90);

        //o Service deve ter colocado um texto aleatório do repositório no minigame
        assertNotNull(minigame.getTextoAtual());
        System.out.println(minigame.getTextoAtual());
        assertFalse(minigame.getTextoAtual().isEmpty());
    }

    @Test
    public void test_Service_Prepara_Rodada_Software() {
        MinigameSoftware minigame = (MinigameSoftware) repo.buscarMinigamePorArea("Software");

        service.prepararMinigameSoftware(minigame, 60); //dificuldade média

        assertNotNull(minigame.getPerguntaCodigo());
        System.out.println(minigame.getPerguntaCodigo());
        assertNotNull(minigame.getAlternativas());
        assertFalse(minigame.getAlternativas().isEmpty());
    }

    @Test
    public void test_Service_Prepara_Rodada_Hardware() {
        MinigameHardware minigame = (MinigameHardware) repo.buscarMinigamePorArea("Hardware");
        service.prepararMinigameHardware(minigame, 5); //difícil

        assertNotNull(minigame.getInstrucao());
        System.out.println(minigame.getInstrucao());
        assertNotNull(minigame.getPinoOrigemGabarito());
        assertNotNull(minigame.getPinoDestinoGabarito());
    }
}