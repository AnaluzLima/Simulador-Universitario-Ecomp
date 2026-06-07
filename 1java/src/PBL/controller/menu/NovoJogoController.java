package PBL.controller.menu;

import PBL.controller.SaveController;
import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.exception.JogoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class NovoJogoController {

    @FXML private TextField campoNomePartida;
    @FXML private TextField campoNomeJogador;

    private SaveController persistencia;

    @FXML
    public void initialize() {
        try {
            persistencia = new SaveController();
        } catch (JogoException e) {
            System.err.println("Erro ao inicializar: " + e.getMessage());
        }
    }

    @FXML
    public void clicarProximo() throws JogoException {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        String nomePartida = campoNomePartida.getText().trim();
        String nomeJogador = campoNomeJogador.getText().trim();

        if (nomePartida.isEmpty() || nomeJogador.isEmpty()) {
            System.out.println("Preencha todos os campos!");
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_NEGAR);
            return;
        }
        if (persistencia.saveExiste(nomePartida)) {
            System.out.println("Erro: Já existe um save com o nome '" + nomePartida + "'!");
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_NEGAR);
            return;
        }

        SessaoNovoJogo.getInstancia().salvar(nomeJogador, nomePartida);

        try {
            GerenciadorDeTelas g = GerenciadorDeTelas.getInstance();
            g.adicionarCena(GerenciadorDeTelas.ESCOLHER_SPAWN, "/PBL/view/fxmls/menu/Residencia.fxml");
            g.trocarPara(GerenciadorDeTelas.ESCOLHER_SPAWN);
        } catch (IOException e) {
            System.err.println("Erro ao carregar tela: " + e.getMessage());
        }
    }

    @FXML
    public void clicarFechar() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
        GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.MENU);
    }
}

