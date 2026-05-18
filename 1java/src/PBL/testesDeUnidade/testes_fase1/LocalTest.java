package PBL.testesDeUnidade.testes_fase1;

import PBL.fase_1.model.Local;
import PBL.fase_1.model.personagens.Colega;
import PBL.fase_1.model.personagens.NPC;
import PBL.fase_1.model.tasks.AssistirAula;
import PBL.fase_1.model.tasks.CasaDormir;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocalTest {

    private Local pavilhao;
    private Local biblioteca;

    @BeforeEach
    public void inicializa() {
        pavilhao = new Local("Pavilhão");
        biblioteca = new Local("Biblioteca");
    }

    @Test
    public void test_Conexao_Bidirecional() {
        //Conecta apenas de um lado
        pavilhao.conectar(biblioteca);

        //A conexão deve ter ido e voltado automaticamente
        assertTrue(pavilhao.getConexoes().contains(biblioteca));
        assertTrue(biblioteca.getConexoes().contains(pavilhao));
    }

    @Test
    public void test_Nao_Deve_Permitir_Conexoes_Duplicadas() {
        //Tenta conectar 3 vezes a mesma sala
        pavilhao.conectar(biblioteca);
        pavilhao.conectar(biblioteca);
        biblioteca.conectar(pavilhao); //Tenta fazer a volta

        //só deve existir 1 conexão na lista
        assertEquals(1, pavilhao.getConexoes().size());
        assertEquals(1, biblioteca.getConexoes().size());
    }

    @Test
    public void test_Adicionar_NPC_Atualiza_Localizacao() {
        NPC colega = new Colega("Luz");

        //adiciona o npc no pavilhao
        pavilhao.addNPC(colega);

        //o Local guardou o npc e o npc sabe onde está
        assertTrue(pavilhao.getNpcsLocal().contains(colega));
        assertEquals(pavilhao, colega.getLocalizacao());
    }

    @Test
    public void test_Remover_Atividades_Aula_Nao_Afeta_Outras() {
        //adicionando atividades no pavilhão
        pavilhao.addAtividade(new CasaDormir());
        pavilhao.addAtividade(new AssistirAula());
        pavilhao.addAtividade(new AssistirAula());

        assertEquals(3, pavilhao.getAtvLocais().size());

        //roda a limpeza do semestre
        pavilhao.removerAtividadesDeAula();

        //as duas aulas sumiram, mas vc ainda pode dormir lá
        assertEquals(1, pavilhao.getAtvLocais().size());

        assertInstanceOf(CasaDormir.class, pavilhao.getAtvLocais().get(0));
    }

    @Test
    public void test_Encapsulamento_Conexoes() {
        //pega a lista pelo getter e tenta injetar um local falso
        List<Local> lista = pavilhao.getConexoes(); //tecnicamente, aqui estaria passando o endereço de conexões do pav
        // mas o return do get devolve uma cópia da lista, e não seu endereço

        lista.add(new Local("Sala"));

        //a lista original de conexões do Pavilhão deve continuar vazia (0)
        assertEquals(0, pavilhao.getConexoes().size());
    }
}