package PBL.view;

import javafx.scene.control.Slider;

public class ConfiguracoesView {
    //inicializa os sliders com os valores atuais do GerenciadorDeAudio
    public void inicializarSliders(Slider sliderSom, Slider sliderMusica, double volumeSomAtual, double volumeMusicaAtual) {
        sliderSom.setValue(volumeSomAtual);
        sliderMusica.setValue(volumeMusicaAtual);
    }

}