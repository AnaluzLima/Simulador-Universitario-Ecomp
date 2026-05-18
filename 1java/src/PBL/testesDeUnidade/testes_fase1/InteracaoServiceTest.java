package PBL.testesDeUnidade.testes_fase1;

import PBL.exception.TempoException;
import PBL.model.model.Aparencia;
import PBL.model.model.Jogador;
import PBL.model.model.personagens.Colega;
import PBL.model.model.personagens.Professor;
import PBL.model.repository.NpcRepository;
import PBL.model.service.InteracaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**Ao testar um service, o teste se torna mais um teste de integração do que de unidade*/

public class InteracaoServiceTest {

    private Jogador jogador;
    private InteracaoService interacaoService;
    private Colega colega;
    private Professor professor;

    @BeforeEach
    public void inicializa() {
        jogador = new Jogador("Luz", new Aparencia("Padrão"));

        NpcRepository colegaRepo = new NpcRepository();
        interacaoService = new InteracaoService(colegaRepo);

        colega = new Colega("Scooby");
        professor = new Professor("Wild", 8);
    }

    @Test
    public void test_Conversar_Com_Colega_Retorna_Fala_Sorteada() throws TempoException {
        //conversar com um Colega gasta 3 de tempo
        String resposta = interacaoService.conversarComNPC(jogador, colega);

        //verifica se o metodo interagir() rodou
        assertEquals(97, jogador.getTempo());

        //verifica se a mensagem veio com a fala sorteada do repositório
        // Como a fala é aleatória, o teste é feito com um print...
        System.out.println(resposta);
    }

    @Test
    public void test_Conversar_Com_Nao_Colega_Retorna_Apenas_Mensagem_Base() throws TempoException {
        //professor também gasta 3 de tempo na interação base
        String resposta = interacaoService.conversarComNPC(jogador, professor);

        assertEquals(97, jogador.getTempo());

        assertEquals("Você passou um tempo interagindo com Wild", resposta);
    }

    @Test
    public void test_Conversar_Sem_Tempo() {
        //interagir custa 3 de tempo. Vamos deixar o jogador com 2.
        jogador.setTempo(2);

        //o npc.interagir() vai lançar a exceção e o service tem que repassar ela
        assertThrows(TempoException.class, () -> {interacaoService.conversarComNPC(jogador, colega);});
    }

    @Test
    public void test_Tentar_Pegar_Contato() throws TempoException {
        //o jogador acabou de nascer, a relação com Scooby é 0.
        //pela regra do NPC, com relação < 3, ele recusa.

        //testamos se o Service aciona a mecânica corretamente
        String resposta = interacaoService.tentarPegarContato(jogador, colega);

        //custou 1 de tempo e devolveu a recusa padrão do NPC
        assertEquals(99, jogador.getTempo());
        assertEquals("Scooby: Ah... eu não costumo passar meu número assim, desculpa.", resposta);
    }
}