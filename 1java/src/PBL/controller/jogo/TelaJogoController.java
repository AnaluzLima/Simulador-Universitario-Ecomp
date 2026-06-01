package PBL.controller.jogo;

import PBL.controller.GameplayController;
import PBL.controller.SaveController;
import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.exception.JogoException;
import PBL.view.TelaJogoView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TelaJogoController {

    @FXML private Pane paneMundo;
    @FXML private StackPane containerJogador;
    @FXML private ImageView imgMapa;

    @FXML private Pane overlayPause;
    @FXML private VBox menuPause;

    private boolean jogoPausado = false;
    private boolean atributosAbertos = false;

    @FXML private ImageView imgFundoHud;
    @FXML private Label labelMoedas;
    @FXML private Label labelTempo;
    @FXML private VBox painelAtributos;
    @FXML private Pane painelCelular;
    @FXML private Pane controlesHud;

    private GameplayController gameplay;
    private final TelaJogoView view = new TelaJogoView();

    @FXML
    public void initialize() {
        this.gameplay = new GameplayController(); //pega os dados da Sessão

        //pede a Gameplay o mapa certo
        String caminhoMapa = gameplay.getCaminhoImagemLocalAtual();

        try {
            imgMapa.setImage(new Image(getClass().getResourceAsStream(caminhoMapa)));
        } catch (NullPointerException e) {
            System.err.println("Imagem não encontrada neste caminho " + caminhoMapa);
        }

        double tamanhoDinamico = gameplay.getTamanhoJogadorLocalAtual();

        //pede a Gameplay a posição de nascimento do mapa
        containerJogador.setPrefSize(tamanhoDinamico, tamanhoDinamico);
        containerJogador.setLayoutX(gameplay.getPosicaoXLocalAtual());
        containerJogador.setLayoutY(gameplay.getPosicaoYLocalAtual());

        //mostra a qtd de moedas e pontos de tempo
        int moedas = gameplay.getDinheiroAtual();
        int tempo = gameplay.getTempoAtual();
        view.atualizarHUD(labelMoedas, labelTempo, moedas, tempo);

        //pede a view para desenhar o jogador
        view.inicializarCamadasJogador(containerJogador, tamanhoDinamico);
        view.iniciarAnimacaoIdle();

        configurarRastreamentoDeMouse();
        configurarTeclasAtalho();
    }

    private void configurarRastreamentoDeMouse() {
        paneMundo.setOnMouseMoved(event -> {
            if (jogoPausado) return;
            view.direcaoSprite(event.getX(), event.getY(), containerJogador);
        });
    }

    private void configurarTeclasAtalho() {
        Platform.runLater(() -> {paneMundo.getScene().setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ESCAPE) {
                    alternarPause();
                }});
        });
    }

    private void alternarPause() {
        jogoPausado = !jogoPausado;
        overlayPause.setVisible(jogoPausado);
        menuPause.setVisible(jogoPausado);

        if (jogoPausado) {
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);
            view.pausarAnimacao();
            imgFundoHud.setVisible(false);
            controlesHud.setVisible(false);
        }
        else {
            view.retomarAnimacao();
            imgFundoHud.setVisible(true);
            controlesHud.setVisible(true);
        }
    }

    @FXML
    public void clicarContinuar(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        alternarPause();
    }

    @FXML
    public void clicarConfig(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        try {
            GerenciadorDeTelas.getInstance().adicionarCena(GerenciadorDeTelas.CONFIGURACOES, "/PBL/view/fxmls/menu/Configuracoes.fxml");
            GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.CONFIGURACOES);
        } catch (IOException e) {
            System.err.println("Erro ao carregar configurações: " + e.getMessage());
        }
    }

    @FXML
    public void clicarSalvar(ActionEvent event) {
        try {
            SaveController saveCtrl = new SaveController();
            saveCtrl.salvarJogoDaSessao();
            System.out.println("jogo salvo");
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);
        } catch (JogoException e) {
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_NEGAR);
            System.err.println("jogo não salvo");
        }
    }

    @FXML
    public void clicarSair(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_SAIR);

        GerenciadorDeTelas.getInstance().removerCena(GerenciadorDeTelas.JOGO);
        SessaoJogo.getInstancia().setJogoAtivo(null);

        GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.MENU);
        GerenciadorDeAudio.getInstance().tocarMusica(GerenciadorDeAudio.MUSICA_MENU);
    }

    @FXML
    public void clicarAtributos(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);

        //alterna entre aberto e fechado
        atributosAbertos = !atributosAbertos;

        if (atributosAbertos) {
            view.renderizarAtributos(painelAtributos, gameplay.getAtributosJogador());
            painelAtributos.setVisible(true);
            fecharCelular(null); //garante que o celular feche se abrir os atributos
            view.atualizarFundoHUD(imgFundoHud, "atributos");
        } else {
            painelAtributos.setVisible(false);
            view.atualizarFundoHUD(imgFundoHud, "normal");
        }
    }

    @FXML
    public void clicarCelular(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);

        painelCelular.setVisible(true);
        painelAtributos.setVisible(false); //fecha atributos se abrir o celular
        atributosAbertos = false;

        view.atualizarFundoHUD(imgFundoHud, "celular");
    }

    @FXML
    public void fecharCelular(ActionEvent event) {
        if (painelCelular.isVisible()) {
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
            painelCelular.setVisible(false);
            view.atualizarFundoHUD(imgFundoHud, "normal");
        }
    }
}