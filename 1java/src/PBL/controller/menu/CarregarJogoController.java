package PBL.controller.menu;

import PBL.controller.SaveController;
import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.controller.jogo.SessaoJogo;
import PBL.exception.JogoException;
import PBL.model.model.Jogo;
import PBL.view.CarregarJogoView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class CarregarJogoController {

    @FXML private VBox listaSaves;
    @FXML private StackPane popupConfirmacao;
    @FXML private Label labelNomeSave;
    @FXML private Pane overlay;

    private String slotParaDeletar;

    private SaveController persistencia;

    private CarregarJogoView view = new CarregarJogoView();

    @FXML
    public void initialize() {
        try {
            this.persistencia = new SaveController();
        } catch (JogoException e) {
            System.err.println("Erro ao inicializar: " + e.getMessage());
            return;
        }

        this.popupConfirmacao.setVisible(false);
        carregarListaDeSaves();
    }

    private void carregarListaDeSaves() {
        this.listaSaves.getChildren().clear();

        try {
            List<String> slots = this.persistencia.listarSalvesDisponiveis();

            if (slots.isEmpty()) {
                this.listaSaves.getChildren().add(view.criarMensagemVazia());
                return;
            }

            for (String slot : slots) {
                this.listaSaves.getChildren().add(this.view.criarLinhaSave(slot, () -> carregarJogo(slot), s -> abrirPopupConfirmacao(s)));
            }
        } catch (JogoException e) {
            System.err.println("Erro ao listar saves: " + e.getMessage());
        }

    }


    private void carregarJogo(String slot) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_INICIAR);
        try {
            //le o arquivo JSON e monta o Jogo com Mapa e Jogador
            Jogo jogoCarregado = persistencia.carregarJogoExistente(slot);
            System.out.println("Jogo carregado: " + jogoCarregado.getJogador().getNome());

            //para o GameplayController conseguir achar
            SessaoJogo.getInstancia().setJogoAtivo(jogoCarregado);

            //carrega a tela do jogo
            GerenciadorDeTelas.getInstance().adicionarCena(GerenciadorDeTelas.JOGO, "/PBL/view/fxmls/jogo/Jogo.fxml");

            //limpa a tela e a música
            GerenciadorDeTelas.getInstance().removerCena(GerenciadorDeTelas.CARREGAR);
            GerenciadorDeAudio.getInstance().pararMusica();

            //transição
            GerenciadorDeTelas.getInstance().trocarParaInicioSemestre(jogoCarregado.getJogador().getSemestre());

        } catch (JogoException | IOException e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
        }
    }

    private void abrirPopupConfirmacao(String slot) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_POPUP);
        this.slotParaDeletar = slot;
        this.labelNomeSave.setText("Tem certeza que deseja excluir o save \"" + slot + "\"?");
        this.overlay.setVisible(true);
        this.popupConfirmacao.setVisible(true);
    }

    @FXML
    public void clicarConfirmarDelecao(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_CONFIRMAR);
        try {
            this.persistencia.apagarSave(slotParaDeletar);
            System.out.println("Save '" + slotParaDeletar + "' apagado.");
        } catch (JogoException e) {
            System.err.println("Erro ao apagar: " + e.getMessage());
        } finally {
            this.overlay.setVisible(false);
            this.popupConfirmacao.setVisible(false);
            this.slotParaDeletar = null;

            carregarListaDeSaves();
        }
    }

    @FXML
    public void clicarCancelarDelecao(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_NEGAR);
        this.overlay.setVisible(false);
        this.popupConfirmacao.setVisible(false);
        this.slotParaDeletar = null;
    }

    @FXML
    public void clicarFechar(ActionEvent event) {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);
        GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.MENU);
    }
}