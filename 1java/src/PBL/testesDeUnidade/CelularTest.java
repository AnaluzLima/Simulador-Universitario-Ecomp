package PBL.testesDeUnidade;

import PBL.fase_1.model.Celular;
import PBL.fase_1.model.Local;
import PBL.fase_1.model.personagens.Colega;
import PBL.fase_1.model.personagens.NPC;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CelularTest {

    private Celular celular;
    private NPC colega;
    private Local praca;

    @BeforeEach
    public void inicializa() {
        celular = new Celular();

        colega = new Colega("Scooby");
        praca = new Local("Praça do Borogodó");
    }


    @Test
    public void test_Adicionar_Contato_Inexistente() {
        //retorna true se conseguiu adicionar e false se ele ja tinha o colega adicionado
        boolean adicionou = celular.adicionarContato(colega);

        //verifica se adicionou
        assertTrue(adicionou);
        assertTrue(celular.getContatos().contains("Scooby"));
        //verifica se tem apenas 1 contato
        assertEquals(1, celular.getContatos().size());
    }

    @Test
    public void test_Nao_Deve_Adicionar_Contato_Duplicado() {
        //adiciona a primeira vez
        celular.adicionarContato(colega);

        //tenta adicionar de novo o mesmo NPC
        NPC clone = new Colega("Scooby");
        boolean adicionouDeNovo = celular.adicionarContato(clone);

        //Deve barrar a adição
        assertFalse(adicionouDeNovo);

        //a lista deve continuar com tamanho 1
        assertEquals(1, celular.getContatos().size());
    }

    @Test
    public void test_Registrar_Atividade_Armazena_Corretamente_No_Historico() {
        //No celular, será possivel todas as suas ações anteriores
        celular.registrarAtividade("Estudou na Biblioteca");
        celular.registrarAtividade("Foi ao Bandejão");

        assertEquals(2, celular.getHistoricoAtividades().size());
        assertEquals("Estudou na Biblioteca", celular.getHistoricoAtividades().get(0));
        assertEquals("Foi ao Bandejão", celular.getHistoricoAtividades().get(1));
    }

    @Test
    public void test_Perguntar_Localizacao_NPC_Quando_NPC_Tem_Local() {
        //coloca Scooby na praça
        colega.setLocalizacao(praca);

        //pega a mensagem do colega
        String resposta = celular.perguntarLocalizacao(colega);

        //verifica se a string ta igual
        assertEquals("Estou no(a) Praça do Borogodó agora.", resposta);
    }

    @Test
    public void test_Perguntar_Localizacao_NPC_Quando_Nao_Esta_No_Mapa() {
        //colega acabou de ser instanciado e a localizacao dele é null

        //pega a mensagem do colega
        String resposta = celular.perguntarLocalizacao(colega);

        //verifica se a string ta igual
        assertEquals("Não estou na UEFS...", resposta);
    }

    @Test
    public void test_Encapsulamento_De_Contatos() {

        celular.adicionarContato(colega);

        //um código pega o getter e tenta adicionar um contato forçado
        List<String> lista = celular.getContatos();
        lista.add("Um ser aleatório");

        //a lista original não deve ter sido afetada
        assertFalse(celular.getContatos().contains("Um ser aleatório"));
        assertEquals(1, celular.getContatos().size());
    }

    @Test
    public void test_Encapsulamento_De_Atividades() {
        //Mesma verificação para as atividades
        celular.registrarAtividade("Atividade Oficial");

        List<String> lista = celular.getHistoricoAtividades();
        lista.add("Atividade Falsa");

        assertFalse(celular.getHistoricoAtividades().contains("Atividade Falsa"));
        assertEquals(1, celular.getHistoricoAtividades().size());
    }
}