package PBL.testesDeUnidade.testes_fase1;

import PBL.exception.MatriculaException;
import PBL.fase_1.model.Disciplina;
import PBL.fase_1.model.Historico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricoTest {

    private Historico historico;
    private Disciplina prog1;
    private Disciplina prog2;
    private Disciplina miAlgoritmos;
    private Disciplina fisica;

    @BeforeEach
    public void inicializa() {
        historico = new Historico();

        prog1 = new Disciplina("Programação I", null, 60, 0, null);
        prog2 = new Disciplina("Programação II", null, 60, 0, null);
        miAlgoritmos = new Disciplina("MI Algoritmos", null, 60, 0, null);

        //Matéria com 600h para forçar o teste de limite do semestre
        fisica = new Disciplina("Física", null, 600, 0, null);

        prog2.addPreRequisito(prog1); //Prog 2 precisa de Prog 1 concluída
        prog1.addCoRequisito(miAlgoritmos); //Prog 1 exige MI Algoritmos no mesmo semestre

        historico.adicionarPendente(prog1);
        historico.adicionarPendente(prog2);
        historico.adicionarPendente(miAlgoritmos);
    }

    @Test
    public void test_Cursar_Sem_PreRequisito_False() {
        //tenta cursar Prog2 sem ter cursado Prog1
        assertFalse(historico.podeCursar(prog2));
    }

    @Test
    public void test_Cursar_Com_PreRequisito_True() throws MatriculaException {
        //Para passar em Prog1, o aluno tem que se matricular nela e no co-requisito
        historico.matricular(Arrays.asList(prog1, miAlgoritmos));

        //nota de aprovação para as duas
        prog1.registrarNota(10.0);
        miAlgoritmos.registrarNota(10.0);

        //Histórico processar a aprovação
        historico.fimSemestre();

        //verifica se foi para a lista de aprovados
        assertTrue(historico.getAprovadas().contains(prog1));
        assertTrue(historico.getAprovadas().contains(miAlgoritmos));

        //Prog2 deve estar liberada para matrícula
        assertTrue(historico.podeCursar(prog2));
    }

    @Test
    public void test_Matricular_Sem_CoRequisito() {
        //Tenta matricular apenas em Prog1, esquecendo o MI Algoritmos (Co-requisito)

        //verifica se, ao usar 'historico.matricular()', o tipo de erro é o MatriculaException
        assertThrows(MatriculaException.class, () -> {historico.matricular(Arrays.asList(prog1));});
    }

    @Test
    public void test_Matricular_Materias_Com_CoRequisito_Sucesso() throws MatriculaException {
        //Aluno seleciona Prog1 e MI Algoritmos juntas na mesma matrícula
        historico.matricular(Arrays.asList(prog1, miAlgoritmos));

        //Valida se as duas entraram no semestre
        assertTrue(historico.getCursando().contains(prog1));
        assertTrue(historico.getCursando().contains(miAlgoritmos));

        // 60h + 60h = 120h no semestre
        assertEquals(120, historico.getCargaHorariaSemestre());
    }

    @Test
    public void test_Matricular_Exceder_Limite_Horas() {
        historico.adicionarPendente(fisica);

        //Tenta matricular em 660 horas de vez (600 + 60)
        assertThrows(MatriculaException.class, () -> {historico.matricular(Arrays.asList(fisica, miAlgoritmos));});
    }

    @Test
    public void test_Fim_Semestre_Aprovacao_e_Reprovacao() throws MatriculaException {
        //Aluno se matricula de forma válida
        historico.matricular(Arrays.asList(prog1, miAlgoritmos));

        //Prog1: Passou
        prog1.registrarNota(8.5);

        //MI Algoritmos: Usou milagre mas a nota final ficou 6.0 (Reprovou)
        miAlgoritmos.setMilagreAcademico(true);
        miAlgoritmos.registrarNota(3); //3 + 3 = 6

        //Acaba o semestre
        historico.fimSemestre();

        //Prog 1 foi para as aprovadas e rendeu horas concluidas
        assertTrue(historico.getAprovadas().contains(prog1));
        assertEquals(60, historico.getCargaHorariaTotal());

        //MI Algoritmos voltou para as pendentes
        assertTrue(historico.getPendentes().contains(miAlgoritmos));

        //Os status de MI Algoritmos foram limpos
        assertFalse(miAlgoritmos.isMilagreAcademico());
        assertEquals(0.0, miAlgoritmos.getNota());

        //O semestre atual foi limpo
        assertTrue(historico.getCursando().isEmpty());
        assertEquals(0, historico.getCargaHorariaSemestre());
    }
}