package PBL.controller.menu;

import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.view.ConfiguracoesView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class ConfiguracoesController {
    @FXML private Slider sliderSom;
    @FXML private Slider sliderMusica;

    private final ConfiguracoesView view = new ConfiguracoesView();

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            GerenciadorDeAudio audio = GerenciadorDeAudio.getInstance();

            //inicializa sliders com os valores atuais
            view.inicializarSliders(sliderSom, sliderMusica, audio.getVolumeSfx(), audio.getVolumeMusica());

            //quando o slider de sound mudar atualiza o GerenciadorDeAudio
            sliderSom.valueProperty().addListener((obs, antigo, novo) -> audio.setVolumeSfx(novo.doubleValue()));

            //quando o slider de music mudar atualiza o GerenciadorDeAudio
            sliderMusica.valueProperty().addListener((obs, antigo, novo) -> audio.setVolumeMusica(novo.doubleValue()));
        });
    }

    @FXML
    public void clicarFechar() {
        GerenciadorDeAudio.getInstance().tocarSfx(GerenciadorDeAudio.SFX_FECHAR);

        //pega a variável de controle
        String telaParaVoltar = GerenciadorDeTelas.getInstance().getTelaAnterior();

        if (telaParaVoltar != null) {
            GerenciadorDeTelas.getInstance().trocarPara(telaParaVoltar);
        }
        else {
            //se for nulo volta pro menu
            GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.MENU);
        }
    }
}