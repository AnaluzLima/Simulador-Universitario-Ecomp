package PBL.controller.menu;

import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.view.MenuView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MenuController {
    @FXML private ImageView imagemFundo;
    @FXML private StackPane stackPrincipal;
    private final MenuView view = new MenuView();

    @FXML
    public void initialize() {
        //roda automaticamente quando o FXML é carregado
        Platform.runLater(() -> view.configurarParallax(stackPrincipal, imagemFundo));
    }

    @FXML
    public void clicarNovoJogo() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        try {
            GerenciadorDeTelas g = GerenciadorDeTelas.getInstance();
            g.adicionarCena(GerenciadorDeTelas.NOVO_JOGO, "/PBL/view/fxmls/menu/NovoJogo.fxml");
            g.trocarPara(GerenciadorDeTelas.NOVO_JOGO);
        } catch (IOException e) {
            System.err.println("Erro ao carregar tela: " + e.getMessage());
        }
    }

    @FXML
    public void clicarCarregar() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        try {
            GerenciadorDeTelas g = GerenciadorDeTelas.getInstance();
            g.adicionarCena(GerenciadorDeTelas.CARREGAR, "/PBL/view/fxmls/menu/CarregarJogo.fxml");
            g.trocarPara(GerenciadorDeTelas.CARREGAR);
        } catch (IOException e) {
            System.err.println("Erro ao carregar tela: " + e.getMessage());
        }
    }

    @FXML
    public void clicarConfig() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        try {
            GerenciadorDeTelas g = GerenciadorDeTelas.getInstance();
            g.adicionarCena(GerenciadorDeTelas.CONFIGURACOES, "/PBL/view/fxmls/menu/Configuracoes.fxml");
            g.trocarPara(GerenciadorDeTelas.CONFIGURACOES);
        } catch (IOException e) {
            System.err.println("Erro ao carregar tela: " + e.getMessage());
        }
    }

    @FXML
    public void clicarSair() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_SAIR);
        Platform.exit(); //encerra o jogo de forma limpa
    }
}