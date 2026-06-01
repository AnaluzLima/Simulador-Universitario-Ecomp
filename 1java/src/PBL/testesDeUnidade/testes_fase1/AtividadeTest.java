package PBL.testesDeUnidade.testes_fase1;

import PBL.exception.GreveException;
import PBL.exception.JogoException;
import PBL.model.model.Aparencia;
import PBL.model.model.Disciplina;
import PBL.model.model.Jogador;
import PBL.model.model.personagens.Colega;
import PBL.model.model.tasks.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtividadeTest {

    private Jogador jogador;
    private Disciplina disciplina;
    private Colega colega;

    @BeforeEach
    public void inicializa() {
        jogador = new Jogador("Luz", new Aparencia("padrão"), false);
        disciplina = new Disciplina("Cálculo", null, 60, 0, null);
        colega = new Colega("Maeli");
    }

    //Assistir Aula
    @Test
    public void test_AssistirAula_Semestre_Greve() {
        jogador.getHistorico().setSemestreGreve(true);

        AssistirAula aula = new AssistirAula();
        aula.setMateria(disciplina);

        //lança o aviso GreveException
        assertThrows(GreveException.class, () -> {aula.executar(jogador);});
    }
    @Test
    public void test_AssistirAula_Jogador_Cansado() throws JogoException {
        jogador.setCansado(true);
        AssistirAula aula = new AssistirAula();
        aula.setMateria(disciplina);

        //jogador começa com 0 de conhecimento
        aula.executar(jogador);

        //se tá cansado, ganha só 10. Se estivesse descansado, ganharia 20.
        assertEquals(10, jogador.getConhecimento().getValor());
    }

    //Comprar na Feirinha
    @Test
    public void test_ComprarFeirinha_Item_Repetido() throws JogoException {
        //jogador já tem a "Pulseira"
        jogador.getSkin().desbloquearAcessorio("folhas");
        ComprarFeirinha comprar = new ComprarFeirinha("folhas", 10);

        JogoException erro = assertThrows(JogoException.class, () -> {comprar.executar(jogador);});
        assertEquals("Você já comprou este item!", erro.getMessage());
    }

    //Comprar na cantina
    @Test
    public void test_Cantinar_Sucesso_Recupera_Status() throws JogoException {
        jogador.getEnergia().setValor(10); //diminui a energia para 10
        Cantinar cantinar = new Cantinar();

        //executaa a atividade
        cantinar.executar(jogador);

        //custa R$12, então sobra R$38. Energia sobe 50 (10+50 = 60).
        assertEquals(38, jogador.getDinheiro());
        assertEquals(60, jogador.getEnergia().getValor());
    }

    //Ir para casa dormir
    @Test
    public void test_CasaDormir() throws JogoException {
        //jogador está cansado
        jogador.setCansado(true);
        //energia baixa
        jogador.getEnergia().setValor(10);
        CasaDormir dormir = new CasaDormir();
        //executa a atividade
        dormir.executar(jogador);

        assertFalse(jogador.isCansado()); //jogador deixa de estar cansado
        assertEquals(50, jogador.getEnergia().getValor()); //10 + 40
    }

    @Test
    public void test_CasaEstudar_Sem_Materia() {
        CasaEstudar estudar = new CasaEstudar();

        //não definiu a matéria propositalmente

        assertThrows(JogoException.class, () -> {estudar.executar(jogador);});
    }

    @Test
    public void test_EstudarBiblioteca_Com_E_Sem_Colega() throws JogoException {
        // Testando sobrecarga dos construtores
        EstudarBiblioteca estudarSozinho = new EstudarBiblioteca();
        estudarSozinho.setMateria(disciplina);

        EstudarBiblioteca estudarComColega = new EstudarBiblioteca(colega);
        estudarComColega.setMateria(disciplina);

        //Tempo inicial é 100. Sozinho custa 5. Com colega custa 10.
        estudarSozinho.executar(jogador);
        assertEquals(95, jogador.getTempo());

        estudarComColega.executar(jogador);
        assertEquals(85, jogador.getTempo()); // -5 - 10 = -15
    }

    //Dar um Rolê
    @Test
    public void test_DarRole() throws JogoException {
        DarRole roleSozinho = new DarRole();
        DarRole roleAcompanhado = new DarRole(colega);

        //aumentando dinheiro para pagar dois rolês de R$30
        jogador.setDinheiro(100);

        //zera motivação
        jogador.getMotivacao().setValor(0);

        roleSozinho.executar(jogador);
        assertEquals(20, jogador.getMotivacao().getValor()); //ganha 20 de motivação ao sair sozinho

        jogador.getMotivacao().setValor(0); //zera de novo

        roleAcompanhado.executar(jogador);
        assertEquals(40, jogador.getMotivacao().getValor()); //ganha 40 de motivação ao sair com um amigo

        assertEquals(2, jogador.getCelular().getNivelAmizade("Maeli")); //aumenta a relacao com o colega
    }

    //Praticar Esporte
    @Test
    public void test_PraticarEsporte() throws JogoException{
        jogador.getEnergia().setValor(50);
        PraticarEsporte esporte = new PraticarEsporte();
        esporte.executar(jogador);
        assertEquals(20, jogador.getEnergia().getValor());
    }

    //Ir ao Médico
    @Test
    public void test_IrMedico() throws JogoException{
        jogador.getSaude().setValor(10);
        IrMedico medico = new IrMedico();
        medico.executar(jogador);
        assertEquals(50, jogador.getSaude().getValor()); // 10 + 40
    }

    //Falar com Maeli
    @Test
    public void test_FalarComMaeli() throws JogoException{
        jogador.getConhecimento().setValor(0);
        FalarComMaeli maeli = new FalarComMaeli();
        maeli.executar(jogador);
        assertEquals(5, jogador.getConhecimento().getValor());
    }

    //Relaxar

    @Test
    public void test_Relaxar() throws JogoException{
        jogador.getSaude().setValor(50);
        Relaxar relaxar = new Relaxar();
        relaxar.executar(jogador);
        assertEquals(53, jogador.getSaude().getValor());
    }

}