package PBL.testesDeUnidade.testes_fase2;

import PBL.exception.JogoException;
import PBL.fase_1.model.Aparencia;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.Jogo;
import PBL.fase_1.model.Mapa;
import PBL.fase_1.repository.JogoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JogoRepositoryTest {

    private JogoRepository jogoRepo;
    private Jogo jogoTeste;
    private final int SLOT_TESTE = 999;

    @BeforeEach
    public void inicializa() throws JogoException {
        jogoRepo = new JogoRepository();
        Jogador jogador = new Jogador("Cobaia", new Aparencia("padrão"));
        Mapa mapaTransient = new Mapa(true);
        jogoTeste = new Jogo(SLOT_TESTE, jogador, mapaTransient);
    }

    @AfterEach
    public void limpaSave() throws JogoException {
        //limpa o arquivo JSON
        if (jogoRepo.existeSave(SLOT_TESTE)) {
            jogoRepo.deletar(SLOT_TESTE);
        }
    }

    @Test
    public void test_Salvar_E_Carregar_Jogo() throws JogoException {
        //altera um dado do jogador para testar se a persistência mantém o valor
        jogoTeste.getJogador().setDinheiro(1500);
        jogoRepo.salvar(jogoTeste);

        //um repo novo foi criado para simular que o jogador fechou e abriu o programa
        JogoRepository repoSecundario = new JogoRepository();
        Jogo jogoCarregado = repoSecundario.carregar(SLOT_TESTE);

        assertNotNull(jogoCarregado);
        assertEquals("Cobaia", jogoCarregado.getJogador().getNome());
        assertEquals(1500, jogoCarregado.getJogador().getDinheiro());

        //o Gson deve ignorar o transient e deixou null
        assertNull(jogoCarregado.getMapa());
    }

    @Test
    public void test_Deletar_Jogo_Existente() throws JogoException {
        jogoRepo.salvar(jogoTeste);
        assertTrue(jogoRepo.existeSave(SLOT_TESTE));

        jogoRepo.deletar(SLOT_TESTE);
        assertFalse(jogoRepo.existeSave(SLOT_TESTE));
        assertNull(jogoRepo.carregar(SLOT_TESTE));
    }

    @Test
    public void test_Deletar_Slot_Inexistente_Lanca_Excecao() {
        assertThrows(JogoException.class, () -> jogoRepo.deletar(666));
    }
}