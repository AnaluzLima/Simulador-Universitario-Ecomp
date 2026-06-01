package PBL.testesDeUnidade.testes_fase2;

import PBL.exception.JogoException;
import PBL.model.model.*;
import PBL.model.repository.JogoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class JogoRepositoryTest {

    private JogoRepository jogoRepo;
    private Jogo jogoTeste;
    private final String SLOT_TESTE = "SavePrincipal";
    private final String CAMINHO_ARQUIVO = "saves.json";

    @BeforeEach
    public void inicializa() throws JogoException {
        jogoRepo = new JogoRepository();
        Jogador jogador = new Jogador("cobaia", new Aparencia("padrão"), true);
        Mapa mapa = new Mapa(true);
        jogoTeste = new Jogo(SLOT_TESTE, jogador);
    }

    @BeforeEach
    public void limparArquivoDeSave() {
        //apaga o arquivo antes e depois de testar
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }

    @Test
    public void testCriacaoDoArquivoJsonFisico() throws JogoException {
        //salva o jogo no repositório
        jogoRepo.salvar(jogoTeste);

        File arquivoJson = new File(CAMINHO_ARQUIVO);

        //verifica se o arquivo foi criado no computador
        assertTrue(arquivoJson.exists());

        //verifica se o arquivo não está vazio
        assertTrue(arquivoJson.length() > 0);
    }

    @Test
    public void test_Salvar_E_Carregar_Jogo() throws JogoException {
        //modifica o estado do jogador e do jogo
        Jogador jogador = jogoTeste.getJogador();
        jogador.setDinheiro(1500);
        jogador.setSemestre(4);
        jogador.getEnergia().setValor(30);
        jogador.getSaude().setValor(85);
        jogador.setCansado(true);

        jogoTeste.setFinalizado(true); //simulando que o jogo foi zerado

        //salva essas alterações no JSON
        jogoRepo.salvar(jogoTeste);

        //um repository novo é criado para simular que o jogador fechou e abriu o programa
        JogoRepository repoSecundario = new JogoRepository();
        Jogo jogoCarregado = repoSecundario.carregar(SLOT_TESTE);
        Jogador jogadorCarregado = jogoCarregado.getJogador();

        //validações para garantir que TUDO foi salvo e lido corretamente
        assertNotNull(jogoCarregado);
        assertEquals("cobaia", jogadorCarregado.getNome());
        assertEquals(1500, jogadorCarregado.getDinheiro());
        assertEquals(4, jogadorCarregado.getSemestre());
        assertEquals(30, jogadorCarregado.getEnergia().getValor());
        assertEquals(85, jogadorCarregado.getSaude().getValor());
        assertTrue(jogadorCarregado.isCansado());
        assertTrue(jogoCarregado.isFinalizado());
    }

    @Test
    public void test_Multiplos_Saves() throws JogoException {
        //cria e salva o jogo 1
        Jogador jogador1 = new Jogador("Luz", new Aparencia("padrão"), false);
        jogador1.setDinheiro(5);
        Jogo jogo1 = new Jogo("Save_Luz", jogador1);
        jogoRepo.salvar(jogo1);

        //cria e salva o jogo 2
        Jogador jogador2 = new Jogador("Bianca", new Aparencia("padrão"), true);
        jogador2.setDinheiro(9999);
        Jogo jogo2 = new Jogo("Save_Bianca", jogador2);
        jogoRepo.salvar(jogo2);

        //simula reiniciar o jogo
        JogoRepository repoLeitura = new JogoRepository();

        Jogo load1 = repoLeitura.carregar("Save_Luz");
        Jogo load2 = repoLeitura.carregar("Save_Bianca");

        //verifica se ambos existem
        assertNotNull(load1);
        assertNotNull(load2);

        assertEquals("Luz", load1.getJogador().getNome());
        assertEquals(5, load1.getJogador().getDinheiro());

        assertEquals("Bianca", load2.getJogador().getNome());
        assertEquals(9999, load2.getJogador().getDinheiro());
    }

    @Test
    public void test_Deletar_Jogo_Existente() throws JogoException {
        //salva e garante que existe
        jogoRepo.salvar(jogoTeste);
        assertTrue(jogoRepo.existeSave(SLOT_TESTE));

        //deleta e garante que sumiu
        jogoRepo.deletar(SLOT_TESTE);
        assertFalse(jogoRepo.existeSave(SLOT_TESTE));
        assertNull(jogoRepo.carregar(SLOT_TESTE));
    }

    @Test
    public void test_Deletar_Slot_Inexistente_Lanca_Excecao() {
        //tentar deletar um save que não existe
        JogoException excecao = assertThrows(JogoException.class, () -> jogoRepo.deletar("fantasma"));
        assertEquals("Não é possível apagar um slot vazio", excecao.getMessage());
    }

    @Test
    public void test_Simulacao_De_Partida_1() throws JogoException {

        Jogador jogador = jogoTeste.getJogador();
        jogador.setDinheiro(1500);
        jogador.setSemestre(4);
        jogador.getEnergia().setValor(30);
        jogador.getSaude().setValor(85);
        jogador.setCansado(true);

        //simulando interações sociais
        jogador.getCelular().modificarAmizade("Scooby", 8);
        jogador.getCelular().modificarAmizade("Professor Computação", 5);

        //simulando a vida acadêmica
        Disciplina programacao = new Disciplina("Programação", null, 60, 0, null);
        programacao.registrarNota(9.5); //simula que fez a prova e tirou 9.5
        jogador.getHistorico().adicionarPendente(programacao);
        jogador.getHistorico().matricular(Arrays.asList(programacao));

        //simulando customização
        jogador.getSkin().desbloquearAcessorio("oculos");
        jogador.getSkin().toggleAcessorio("oculos");

        jogoTeste.setFinalizado(false);

        //salvando o estado
        jogoRepo.salvar(jogoTeste);

        //carregando um nomo repositorio
        JogoRepository repoSecundario = new JogoRepository();
        Jogo jogoCarregado = repoSecundario.carregar(SLOT_TESTE);
        Jogador jogadorCarregado = jogoCarregado.getJogador();

        //lista de validações
        assertNotNull(jogoCarregado);

        assertEquals("cobaia", jogadorCarregado.getNome());
        assertEquals(1500, jogadorCarregado.getDinheiro());
        assertEquals(4, jogadorCarregado.getSemestre());
        assertEquals(30, jogadorCarregado.getEnergia().getValor());
        assertEquals(85, jogadorCarregado.getSaude().getValor());
        assertTrue(jogadorCarregado.isCansado());

        //vValidando as amizades no celular
        assertEquals(8, jogadorCarregado.getCelular().getNivelAmizade("Scooby"));
        assertEquals(5, jogadorCarregado.getCelular().getNivelAmizade("Professor Computação"));

        //validando a aparência
        assertEquals("oculos", jogadorCarregado.getSkin().getAcessoriosEquipados().get(1));
        assertTrue(jogadorCarregado.getSkin().getAcessoriosDesbloqueados().contains("oculos"));

        //validando o histórico
        assertFalse(jogadorCarregado.getHistorico().getCursando().isEmpty());
        Disciplina materiaCarregada = jogadorCarregado.getHistorico().getCursando().iterator().next();
        assertEquals("Programação", materiaCarregada.getNome());
        assertEquals(9.5, materiaCarregada.getNota());
    }

    @Test
    public void test_Simulacao_De_Partida_2() throws JogoException {

        Jogador jogador = jogoTeste.getJogador();
        jogador.setDinheiro(150);
        jogador.setSemestre(1);
        jogador.getEnergia().setValor(80);
        jogador.getSaude().setValor(100);
        jogador.getMotivacao().setValor(95);
        jogador.setCansado(false);

        //simulando a vida acadêmica
        Disciplina algoritmos = new Disciplina("Algoritmos I", null, 60, 0, null);
        Disciplina calculo = new Disciplina("Intro ao Cálculo", null, 60, 0, null);
        Disciplina introducao = new Disciplina("Intro à ECOMP", null, 30, 0, null);

        //o jogo adiciona as pendentes primeiro, e depois o aluno se matricula
        jogador.getHistorico().adicionarPendente(algoritmos);
        jogador.getHistorico().adicionarPendente(calculo);
        jogador.getHistorico().adicionarPendente(introducao);

        jogador.getHistorico().matricular(Arrays.asList(algoritmos, calculo, introducao));

        //simulando que ele já fez a primeira prova de Algoritmos e foi bem
        jogador.getHistorico().getCursando().get(0).registrarNota(8.5);

        //simulando interações sociais
        jogador.getCelular().modificarAmizade("veterano", 4);
        jogador.getCelular().modificarAmizade("colega", 8);

        // 4. Simulando customização
        jogador.getSkin().desbloquearAcessorio("oculos");
        jogador.getSkin().toggleAcessorio("oculos");

        jogoTeste.setFinalizado(false);

        //salvando o estado do jogo
        jogoRepo.salvar(jogoTeste);

        // carregando em um novo repositório para provar que a persistência funcionou
        JogoRepository repoSecundario = new JogoRepository();
        Jogo jogoCarregado = repoSecundario.carregar(SLOT_TESTE);
        Jogador jogadorCarregado = jogoCarregado.getJogador();

        //testes
        assertNotNull(jogoCarregado);

        //validando os status do calouro
        assertEquals("cobaia", jogadorCarregado.getNome());
        assertEquals(1, jogadorCarregado.getSemestre());
        assertEquals(150, jogadorCarregado.getDinheiro());
        assertEquals(80, jogadorCarregado.getEnergia().getValor());

        //validando as amizades salvas
        assertEquals(4, jogadorCarregado.getCelular().getNivelAmizade("veterano"));
        assertEquals(8, jogadorCarregado.getCelular().getNivelAmizade("colega"));

        //validando a aparência
        assertTrue(jogadorCarregado.getSkin().getAcessoriosEquipados().contains("oculos"));

        //validando o histórico e a grade curricular
        assertEquals(3, jogadorCarregado.getHistorico().getCursando().size());

        Disciplina materiaSalva = jogadorCarregado.getHistorico().getCursando().get(0);
        assertEquals("Algoritmos I", materiaSalva.getNome());
        assertEquals(8.5, materiaSalva.getNota());
    }
}