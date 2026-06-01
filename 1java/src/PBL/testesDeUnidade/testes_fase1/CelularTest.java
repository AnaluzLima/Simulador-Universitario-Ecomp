package PBL.testesDeUnidade.testes_fase1;

import PBL.model.model.Celular;
import PBL.model.model.Local;
import PBL.model.model.personagens.Colega;
import PBL.model.model.personagens.NPC;
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
        praca = new Local("Praça do Borogodó", 0.0, 0.0, 150.0, "/resources/Locais/.png");
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
        //No celular, será possivel ver todas as suas ações anteriores
        celular.adicionarRegistro("Estudou na Biblioteca");
        celular.adicionarRegistro("Foi ao Bandejão");

        assertEquals(2, celular.getHistoricoAtividades().size());
        assertEquals("Estudou na Biblioteca", celular.getHistoricoAtividades().get(0).getTexto());
        assertEquals("Foi ao Bandejão", celular.getHistoricoAtividades().get(1).getTexto());
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
        celular.adicionarRegistro("Atividade Oficial");

        List<Celular.Registro> lista = celular.getHistoricoAtividades();
        lista.add(new Celular.Registro("Atividade Falsa", 0));

        assertEquals(1, celular.getHistoricoAtividades().size());
        assertNotEquals("Atividade Falsa", celular.getHistoricoAtividades().get(0).getTexto());
    }

    @Test
    public void test_Modificar_Nivel_Amizade_Limites() {
        //um npc que o jogador acabou de conhecer começa com amizade 0
        assertEquals(0, celular.getNivelAmizade("Bianca"));

        //aumenta a amizade para 5
        celular.modificarAmizade("Bianca", 5);
        assertEquals(5, celular.getNivelAmizade("Bianca"));

        //tenta subir a amizade além do limite máximo (10)
        celular.modificarAmizade("Bianca", 10);

        // 5 + 10 = 15, mas o celular deve travar em 10
        assertEquals(10, celular.getNivelAmizade("Bianca"));

        //tenta descer a amizade abaixo do limite mínimo (0)
        celular.modificarAmizade("Bianca", -20);

        // 10 - 20 = -10, mas o celular deve travar em 0
        assertEquals(0, celular.getNivelAmizade("Bianca"));
    }
}