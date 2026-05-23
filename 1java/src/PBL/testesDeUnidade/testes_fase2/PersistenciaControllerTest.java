package PBL.testesDeUnidade.testes_fase2;

import PBL.exception.JogoException;
import PBL.controller.PersistenciaController;
import PBL.model.model.Jogo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersistenciaControllerTest {

    private PersistenciaController controller;
    private final String SLOT = "teste";

    @BeforeEach
    public void inicializa() throws JogoException {
        controller = new PersistenciaController();
    }

    @AfterEach
    public void limpaSave() {
        try {
            controller.apagarSave(SLOT);
        } catch (JogoException e) {
            //ignora se o slot já não existir
        }
    }

    //teste de integração
    @Test
    public void test_Fluxo_Completo_Criar_E_Carregar_Jogo() throws JogoException {
        //o jogador clica em novo jogo
        Jogo jogoCriado = controller.iniciarNovoJogo("Luz", SLOT, false);

        //verifica se o jogo nasceu certinho
        assertNotNull(jogoCriado);
        assertEquals("Luz", jogoCriado.getJogador().getNome());
        assertNotNull(jogoCriado.getMapa());
        assertEquals("Ponto de Ônibus", jogoCriado.getJogador().getLocalizacao().getNome());

        //simula o jogador movendo o personagem para a Biblioteca e salvando o jogo
        jogoCriado.getJogador().setLocalizacao(jogoCriado.getMapa().buscarLocalPorNome("Biblioteca"));

        //simula uma amizade sendo feita
        jogoCriado.getJogador().getCelular().modificarAmizade("Scooby", 8);

        //o jogador fecha o jogo, abre de novo e clica em CARREGAR
        Jogo jogoCarregado = controller.carregarJogoExistente(SLOT);

        //verifica se ta tudo certo
        assertNotNull(jogoCarregado);
        assertEquals("Luz", jogoCarregado.getJogador().getNome());

        //garante que o Controller recriou o transient Mapa e injetou no Jogo
        assertNotNull(jogoCarregado.getMapa());

        //garante que o jogador foi reconectado ao objeto Local correto
        assertEquals("Biblioteca", jogoCarregado.getJogador().getLocalizacao().getNome());
        assertFalse(jogoCarregado.getJogador().getLocalizacao().getConexoes().isEmpty());

        //garante que a vida social foi mantida
        assertEquals(8, jogoCarregado.getJogador().getCelular().getNivelAmizade("Scooby"));

        //garante que o histórico não sofre de NullPointer nas disciplinas
        assertNotNull(jogoCarregado.getJogador().getHistorico().getCursando().iterator().next().getMinigame());
    }
    //a persistência é testada mais afundo na classe JogoRepositoryTest
}
