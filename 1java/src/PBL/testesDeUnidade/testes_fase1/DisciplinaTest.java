package PBL.testesDeUnidade.testes_fase1;

import PBL.fase_1.model.Disciplina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisciplinaTest {

    private Disciplina disciplina;

    @BeforeEach
    public void inicializa() {
        //Null para minigame e local, pq o foco é a disciplina
        disciplina = new Disciplina("Algoritmos e Programação II", null, 60, 0, null);
    }

    @Test
    public void test_Registrar_Nota_Simples() {
        disciplina.registrarNota(7.5);

        // Verifica se a nota foi = 7.5
        assertEquals(7.5, disciplina.getNota());

        // Verifica se o metodo .registrarNota() tornou a variavel booleana ProvaFeita = True
        assertTrue(disciplina.isProvaFeita());
    }

    @Test
    public void test_Registrar_Nota_Com_Milagre_Academico() {
        //Milagre Academico On
        disciplina.setMilagreAcademico(true);

        //Aluno tirou 5.0, mas usou o milagre (+3.0)
        disciplina.registrarNota(5.0);

        //5 + 3 = 8
        assertEquals(8.0, disciplina.getNota());
    }

    @Test
    public void test_Trava_De_Nota_Maxima_Com_Milagre() {
        //Milagre Academico On
        disciplina.setMilagreAcademico(true);

        //Aluno tirou 8.5 e com o milagre iria para 11.5
        disciplina.registrarNota(8.5);

        //A nota final não pode passar de 10.0
        assertEquals(10.0, disciplina.getNota());
    }

    @Test
    public void test_Modificar_Desempenho_Dentro_Dos_Limites() {
        // 0 + 40 = 40
        disciplina.modificarDesempenho(40);
        assertEquals(40, disciplina.getDesempenho());

        // 40 - 15 = 25
        disciplina.modificarDesempenho(-15);
        assertEquals(25, disciplina.getDesempenho());
    }

    @Test
    public void test_Trava_Limite_Maximo_Desempenho() {
        disciplina.modificarDesempenho(150);

        //Trava em 100
        assertEquals(100, disciplina.getDesempenho());
    }

    @Test
    public void test_Trava_Limite_Minimo_Desempenho() {
        disciplina.modificarDesempenho(-50);

        //Trava em 0
        assertEquals(0, disciplina.getDesempenho());
    }
}