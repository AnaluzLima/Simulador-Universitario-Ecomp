package PBL.testesDeUnidade.testes_fase1;

import PBL.exception.DesmaioException;
import PBL.exception.JogoException;
import PBL.model.model.Aparencia;
import PBL.model.model.Disciplina;
import PBL.model.model.Jogador;
import PBL.model.model.tasks.*;
import PBL.model.service.AtividadeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**Ao testar um service, o teste se torna mais um teste de integração do que de unidade*/

public class AtividadeServiceTest {

    private Jogador jogador;
    private AtividadeService atividadeService;
    private Disciplina disciplina;

    @BeforeEach
    public void inicializa() {
        jogador = new Jogador("Luz", new Aparencia("Padrão"));
        disciplina = new Disciplina("Cálculo", null, 60, 0, null);
        atividadeService = new AtividadeService();

    }

    @Test
    public void test_Registrar_Atividade_No_Celular() throws JogoException {
        Relaxar relaxar = new Relaxar();

        // testando executar através do Service
        atividadeService.realizarAtividade(jogador, relaxar);

        //o Service deve ter injetado o nome da atividade no celular
        assertEquals(1, jogador.getCelular().getHistoricoAtividades().size());
        assertEquals("Relaxar na Praça", jogador.getCelular().getHistoricoAtividades().get(0).getTexto());
    }

    @Test
    public void test_Desmaiar_Por_Exaustao_Quando_Energia_Zerar() {
        //EstudarBiblioteca gasta 15 de energia.
        //deixando jogador com exatamente 15 de energia para zerar.

        jogador.getEnergia().setValor(15);

        EstudarBiblioteca estudar = new EstudarBiblioteca();
        estudar.setMateria(disciplina);

        //avisa sobre o desmaio
        DesmaioException erro = assertThrows(DesmaioException.class, () -> {atividadeService.realizarAtividade(jogador, estudar);});

        //verifica a mensagem e os atributos
        assertEquals("Sua visão escureceu... Você desmaiou de exaustão e foi levado ao Hospital.", erro.getMessage());
        assertEquals(20, jogador.getEnergia().getValor());
        assertEquals(80, jogador.getSaude().getValor());
    }

    @Test
    public void test_Desmaiar_Por_Doenca_Quando_Saude_Zerar() {
        //CasaEstudar gasta 10 de saúde.
        //deixando jogador com exatamente 10 de saúde

        jogador.getSaude().setValor(10);

        CasaEstudar estudar = new CasaEstudar();
        estudar.setMateria(disciplina);

        //avisa sobre o desmaio
        DesmaioException erro = assertThrows(DesmaioException.class, () -> {atividadeService.realizarAtividade(jogador, estudar);});

        // Verifica a mensagem e os atributos
        assertEquals("Você não aguentou e apagou... A equipe médica te atendeu.", erro.getMessage());
        assertEquals(20, jogador.getSaude().getValor());
        assertEquals(20, jogador.getEnergia().getValor());
    }

}