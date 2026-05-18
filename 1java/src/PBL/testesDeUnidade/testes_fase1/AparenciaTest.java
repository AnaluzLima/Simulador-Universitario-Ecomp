package PBL.testesDeUnidade.testes_fase1;

import PBL.exception.JogoException;
import PBL.model.model.Aparencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AparenciaTest {

    private Aparencia skin;

    @BeforeEach
    public void inicializa() {
        skin = new Aparencia("padrão");
    }

    @Test
    public void test_Inicializacao_Padrao_Do_Personagem() {
        //verifica se as configurações inciais estão corretas
        assertEquals("padrão", skin.getSkinBase());

        //jogador deve nascer com o acessório padrão desbloqueado e equipado
        assertEquals(1, skin.getAcessoriosDesbloqueados().size());
        assertTrue(skin.getAcessoriosDesbloqueados().contains("padrão"));
        assertTrue(skin.getAcessoriosEquipados().contains("padrão"));
    }

    @Test
    public void test_Adicionar_Acessorio_Novo() {
        skin.desbloquearAcessorio("oculos");

        //a lista deve crescer para 2 (Padrão + Óculos)
        assertEquals(2, skin.getAcessoriosDesbloqueados().size());
        assertTrue(skin.getAcessoriosDesbloqueados().contains("oculos"));
    }

    @Test
    public void test_Adicionar_Acessorio_Duplicado() {
        //HashSet garante que não haja duplicatas
        skin.desbloquearAcessorio("luva");
        skin.desbloquearAcessorio("luva");

        //a lista não pode ter crescido para 3
        assertEquals(2, skin.getAcessoriosDesbloqueados().size());
    }

    @Test
    public void test_Equipar_Acessorio() throws JogoException {
        skin.desbloquearAcessorio("oculos");

        //o jogador clica para equipar o óculos
        skin.toggleAcessorio("oculos");

        assertTrue(skin.getAcessoriosEquipados().contains("oculos"));
    }

    @Test
    public void test_Encapsulamento_De_Acessorios() {
        //tenta adicionar um item pelo Getter
        List<String> lista = skin.getAcessoriosDesbloqueados();
        lista.add("Coroa");

        //inventário oficial deve permanecer intacto
        assertFalse(skin.getAcessoriosDesbloqueados().contains("Coroa"));
        assertEquals(1, skin.getAcessoriosDesbloqueados().size());
    }

    @Test
    public void test_Ordem_Camadas_Acessorios() throws JogoException {
        skin.desbloquearAcessorio("blushed"); //camada mais alta
        skin.desbloquearAcessorio("camisa");  //camada mais baixa

        skin.toggleAcessorio("blushed");
        skin.toggleAcessorio("camisa");

        //pega a lista gerada
        List<String> equipados = skin.getAcessoriosEquipados();

        //o item "padrão" não está na lista de ORDEM_CAMADAS, o Java dá índice -1 para ele.
        //logo, a ordem do retorno deve ser: padrão (fundo), camisa (meio), blushed (topo).
        assertEquals(3, equipados.size());
        assertEquals("padrão", equipados.get(0));
        assertEquals("camisa", equipados.get(1));
        assertEquals("blushed", equipados.get(2));
    }
}