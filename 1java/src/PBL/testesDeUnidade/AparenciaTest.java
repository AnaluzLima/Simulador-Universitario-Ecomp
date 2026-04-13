package PBL.testesDeUnidade;

import PBL.fase_1.model.Aparencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AparenciaTest {

    private Aparencia skin;

    @BeforeEach
    public void inicializa() {
        //jogador escolhe o sprite base "descolado" ao criar o Novo Jogo
        skin = new Aparencia("descolado");
    }

    @Test
    public void test_Inicializacao_Padrao_Do_Personagem() {
        //verifica se as configurações inciais estão corretas
        assertEquals("descolado", skin.getSkinBase());
        assertEquals("padrão", skin.getAcessorioAtual());

        //o jogador já deve nascer com o acessório padrão desbloqueado
        assertEquals(1, skin.getAcessoriosDesbloqueados().size());
        assertTrue(skin.getAcessoriosDesbloqueados().contains("padrão"));
    }

    @Test
    public void test_Adicionar_Acessorio_Novo() {
        skin.addAcessorio("Óculos Escuros");

        //a lista deve crescer para 2 (Padrão + Óculos)
        assertEquals(2, skin.getAcessoriosDesbloqueados().size());
        assertTrue(skin.getAcessoriosDesbloqueados().contains("Óculos Escuros"));
    }

    @Test
    public void test_Adicionar_Acessorio_Duplicado() {
        skin.addAcessorio("Chapéu");

        //tenta adicionar o chapéu de novo
        skin.addAcessorio("Chapéu");

        //a lista não pode ter crescido para 3
        assertEquals(2, skin.getAcessoriosDesbloqueados().size());
    }

    @Test
    public void test_Mudar_Acessorio_Atual() {
        skin.addAcessorio("Mochila");

        //o jogador abre o menu e equipa a mochila
        skin.setAcessorioAtual("Mochila");

        assertEquals("Mochila", skin.getAcessorioAtual());
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
}