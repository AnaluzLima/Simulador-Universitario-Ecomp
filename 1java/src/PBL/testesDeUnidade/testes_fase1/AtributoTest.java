package PBL.testesDeUnidade.testes_fase1;

import PBL.fase_1.model.Atributo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtributoTest {

    private Atributo atributo;

    @BeforeEach
    public void inicializa() {
        this.atributo = new Atributo("Energia", 50);
    }

    @Test
    public void test_Dentro_Dos_Limites() {
        //Soma 20 ao valor do atributo energia
        atributo.modificar(20);

        //50 + 20 = 70 ?
        assertEquals(70, atributo.getValor());
    }

    @Test
    public void test_Limite_Maximo() {
        //Testando somar um valor que passe de 100
        atributo.modificar(80);

        //50 + 80 = 130, mas o máximo é 100
        assertEquals(100, atributo.getValor());
    }

    @Test
    public void test_Limite_Minimo() {
        //Testando subtrair um valor que dê um resultado < 0
        atributo.modificar(-60);

        //50 - 60 = -10, mas o mínimo é 0
        assertEquals(0, atributo.getValor());
    }
}
