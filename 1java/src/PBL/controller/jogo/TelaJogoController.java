package PBL.controller.jogo;

import PBL.controller.GameplayController;
import PBL.controller.SaveController;
import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.exception.JogoException;
import PBL.view.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @FXML private Pane painelAcoesLateral;
    @FXML private VBox vboxNavegacao;
    @FXML private VBox vboxAtividades;

    @FXML private Pane overlayPopupAtividade;
    @FXML private StackPane popupAtividade;
    @FXML private Label labelTituloAtividade;
    @FXML private Label labelDescAtividade;
    @FXML private Label labelConsequencias;

    @FXML private Pane overlayTransicao;


    private GameplayController gameplay;
    private final TelaJogoView view = new TelaJogoView();
    private final JogoInteracaoView menuInteracao = new JogoInteracaoView();
    private final PainelInteracaoView painelView = new PainelInteracaoView();
    private String atividadePendente = null;

    @FXML
    public void initialize() {
        this.gameplay = new GameplayController(); //pega os dados da Sessão

        carregarCenarioAtual();

        //mostra a qtd de moedas e pontos de tempo
        int moedas = gameplay.getDinheiroAtual();
        int tempo = gameplay.getTempoAtual();
        view.atualizarHUD(labelMoedas, labelTempo, moedas, tempo);
        configurarRastreamentoDeMouse();
        configurarTeclasAtalho();
    }

    private void configurarRastreamentoDeMouse() {
        paneMundo.setOnMouseMoved(event -> {
            if (jogoPausado) return;
            view.direcaoSprite(event.getX(), event.getY(), containerJogador);
        });
        paneMundo.setOnMouseClicked(event -> {
            if (painelAcoesLateral.isVisible()) {
                GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
                painelAcoesLateral.setVisible(false);
            }
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
            GerenciadorDeAudio.getInstance().pausarMusica();
            imgFundoHud.setVisible(false);
            controlesHud.setVisible(false);
            painelAcoesLateral.setVisible(false);
        }
        else {
            view.retomarAnimacao();
            GerenciadorDeAudio.getInstance().continuarMusica();
            imgFundoHud.setVisible(true);
            controlesHud.setVisible(true);
        }
    }

    private void clicarSidebar(List<OpcaoBotaoJogo> rotas, List<OpcaoBotaoJogo> atividades) {
        if(painelAcoesLateral.isVisible()){
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
            painelAcoesLateral.setVisible(false);
        }
        else{
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);
            painelView.renderizarListas(vboxNavegacao, vboxAtividades, rotas, atividades);
            painelAcoesLateral.setVisible(true);
            fecharCelular();
            painelAtributos.setVisible(false);
        }
    }

    private void carregarCenarioAtual() {
        String nomeLocalAtual = gameplay.getNomeLocalAtual();
        var visual = LayoutCenarios.getVisualCenario(nomeLocalAtual);

        try {
            imgMapa.setImage(new Image(getClass().getResourceAsStream(visual.caminhoImagem)));
        } catch (NullPointerException e) {
            System.err.println("Imagem não encontrada neste caminho " + visual.caminhoImagem);
        }

        containerJogador.setPrefSize(visual.tamanhoJogador, visual.tamanhoJogador);
        containerJogador.setLayoutX(visual.spawnX);
        containerJogador.setLayoutY(visual.spawnY);
        containerJogador.getChildren().clear();

        view.inicializarCamadasJogador(containerJogador, visual.tamanhoJogador);
        view.iniciarAnimacaoIdle();

        menuInteracao.limparBotoes(paneMundo);
        painelAcoesLateral.setVisible(false);

        Map<String, LayoutCenarios.DirecaoCenario> direcoesLocal = LayoutCenarios.getDirecoesLocal(nomeLocalAtual);

        // O Facade agora retorna apenas listas de Strings (os nomes)
        List<String> nomesConexoes = gameplay.getNomesConexoesLocalAtual();
        List<String> nomesAtividades = gameplay.getNomesAtividadesLocalAtual();

        for (LayoutCenarios.DirecaoCenario dir : direcoesLocal.values()) {
            menuInteracao.criarPontoInteracao(paneMundo, dir.x, dir.y, () -> {

                List<OpcaoBotaoJogo> opcRotas = new ArrayList<>();
                List<OpcaoBotaoJogo> opcAtvs = new ArrayList<>();

                for (String nomeDestino : nomesConexoes) {
                    if (dir.itensNestaDirecao.contains(nomeDestino)) {
                        opcRotas.add(new OpcaoBotaoJogo(nomeDestino, () -> viajarComTransicao(nomeDestino)));
                    }
                }

                for (String nomeAtv : nomesAtividades) {
                    if (dir.itensNestaDirecao.contains(nomeAtv)) {
                        opcAtvs.add(new OpcaoBotaoJogo(nomeAtv, () -> prepararAtividade(nomeAtv)));
                    }
                }
                clicarSidebar(opcRotas, opcAtvs);
            });
        }
    }

    //pause
    @FXML
    public void clicarContinuar() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        alternarPause();
    }
    @FXML
    public void clicarConfig() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);
        try {
            GerenciadorDeTelas.getInstance().adicionarCena(GerenciadorDeTelas.CONFIGURACOES, "/PBL/view/fxmls/menu/Configuracoes.fxml");
            GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.CONFIGURACOES);
        } catch (IOException e) {
            System.err.println("Erro ao carregar configurações: " + e.getMessage());
        }
    }
    @FXML
    public void clicarSalvar() {
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
    public void clicarSair() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_SAIR);

        GerenciadorDeTelas.getInstance().removerCena(GerenciadorDeTelas.JOGO);
        SessaoJogo.getInstancia().setJogoAtivo(null);

        GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.MENU);
        GerenciadorDeAudio.getInstance().tocarMusica(GerenciadorDeAudio.MUSICA_MENU);
    }

    //hud
    @FXML
    public void clicarAtributos() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);

        //alterna entre aberto e fechado
        atributosAbertos = !atributosAbertos;

        if (atributosAbertos) {
            view.renderizarAtributos(painelAtributos, gameplay.getAtributosJogador());
            painelAtributos.setVisible(true);
            fecharCelular();
            painelAcoesLateral.setVisible(false);
            view.atualizarFundoHUD(imgFundoHud, "atributos");
        } else {
            painelAtributos.setVisible(false);
            view.atualizarFundoHUD(imgFundoHud, "normal");
        }
    }
    @FXML
    public void clicarCelular() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);

        painelCelular.setVisible(true);
        painelAtributos.setVisible(false); //fecha atributos se abrir o celular
        atributosAbertos = false;
        painelAcoesLateral.setVisible(false);

        view.atualizarFundoHUD(imgFundoHud, "celular");
    }
    @FXML
    public void fecharCelular() {
        if (painelCelular.isVisible()) {
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
            painelCelular.setVisible(false);
            view.atualizarFundoHUD(imgFundoHud, "normal");
        }
    }


    private void prepararAtividade(String nomeAtividade) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);

        this.atividadePendente = nomeAtividade;

        // Pede os detalhes textuais ao Facade (retorna um array: [0] Titulo, [1] Descricao, [2] Consequencia)
        String[] detalhes = gameplay.getDetalhesAtividade(nomeAtividade);

        view.preencherPopupAtividade(labelTituloAtividade, labelDescAtividade, labelConsequencias,
                detalhes[0], detalhes[1], detalhes[2]);

        painelAcoesLateral.setVisible(false);

        imgFundoHud.setVisible(false);
        controlesHud.setVisible(false);
        overlayPopupAtividade.setVisible(true);
        popupAtividade.setVisible(true);
    }
    @FXML
    public void clicarFecharPopupAtividade() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
        overlayPopupAtividade.setVisible(false);
        popupAtividade.setVisible(false);
        imgFundoHud.setVisible(true);
        controlesHud.setVisible(true);
        this.atividadePendente = null; //limpa a memória
    }
    @FXML
    public void clicarConfirmarAtividade() {
        if (this.atividadePendente != null) {
            executarAtividade(this.atividadePendente);
            clicarFecharPopupAtividade();

            imgFundoHud.setVisible(true);
            controlesHud.setVisible(true);
        }
    }
    private void viajarComTransicao(String nomeDestino) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CLIQUE);

        view.animarTransicaoSala(overlayTransicao, () -> {
            gameplay.viajarPara(nomeDestino);
            carregarCenarioAtual();
        });
    }
    private void executarAtividade(String nomeAtividade) {
        try {
            // O Facade cuida de achar a atividade no Model e executá-la
            gameplay.executarAtividade(nomeAtividade);

            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);
            view.atualizarHUD(labelMoedas, labelTempo, gameplay.getDinheiroAtual(), gameplay.getTempoAtual());

            if (atributosAbertos) {
                view.renderizarAtributos(painelAtributos, gameplay.getAtributosJogador());
            }
        }
        catch (JogoException e) {
            GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_NEGAR);
            System.err.println("Erro na Atividade: " + e.getMessage());
        }
    }
}