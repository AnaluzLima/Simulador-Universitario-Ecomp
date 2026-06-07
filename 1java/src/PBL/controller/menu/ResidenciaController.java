package PBL.controller.menu;

import PBL.controller.SaveController;
import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.controller.jogo.SessaoJogo;
import PBL.exception.JogoException;
import PBL.model.model.Jogo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ResidenciaController {

    private SaveController persistencia;

    @FXML
    public void initialize() {
        try {
            persistencia = new SaveController();
        } catch (JogoException e) {
            System.err.println("Erro ao inicializar PersistenciaController: " + e.getMessage());
        }
    }

    @FXML
    public void clicarLadoUniversidade() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_INICIAR);
        iniciarJogo(true);
    }

    @FXML
    public void clicarOutroBairro() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_INICIAR);
        iniciarJogo(false);
    }

    @FXML
    public void clicarFechar() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
        GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.NOVO_JOGO);
    }

    private void iniciarJogo(boolean spawnF6) {
        SessaoNovoJogo sessao = SessaoNovoJogo.getInstancia();
        String nomeJogador = sessao.getNomeJogador();
        String nomePartida = sessao.getNomePartida();

        try {
            Jogo jogoIniciado = persistencia.iniciarNovoJogo(nomeJogador, nomePartida, spawnF6);

            //dados temporários
            sessao.limpar();

            System.out.println("Jogo '" + jogoIniciado.getSlot() + "' iniciado com sucesso!");
            System.out.println("Jogador: " + jogoIniciado.getJogador().getNome());
            System.out.println("Spawn F6: " + spawnF6);

            //salva o jogo ativo na sessão
            SessaoJogo.getInstancia().setJogoAtivo(jogoIniciado);

            //carrega a tela do Jogo na memória
            GerenciadorDeTelas.getInstance().adicionarCena(GerenciadorDeTelas.JOGO, "/PBL/view/fxmls/jogo/Jogo.fxml");

            GerenciadorDeTelas.getInstance().removerCena(GerenciadorDeTelas.CARREGAR);
            GerenciadorDeAudio.getInstance().pararMusica();

            //inicia a transição
            GerenciadorDeTelas.getInstance().trocarParaInicioSemestre(jogoIniciado.getJogador().getSemestre());
            GerenciadorDeAudio.getInstance().tocarMusica(GerenciadorDeAudio.MUSICA_JOGO);
        }
        catch (JogoException | IOException e) {
            System.err.println("Erro ao iniciar jogo: " + e.getMessage());
        }
    }
}