package PBL.view;

import PBL.controller.gerenciadores.GerenciadorDeTelas;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class TransicaoSemestreView {

    @FXML
    private StackPane stackPrincipal;
    @FXML private Label labelSemestre;

    private int semestre;

    @FXML
    public void initialize() {
        stackPrincipal.setOpacity(0);

        //fade in -> pausa -> fade out
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), stackPrincipal);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        PauseTransition pausa = new PauseTransition(Duration.seconds(1));

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), stackPrincipal);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        SequentialTransition sequencia = new SequentialTransition(fadeIn, pausa, fadeOut);

        //quando a tela preta apagar o jogo começa
        sequencia.setOnFinished(e -> GerenciadorDeTelas.getInstance().trocarPara(GerenciadorDeTelas.JOGO));
        sequencia.play();
    }

    public void atualizarTextSemestre(int numero) {
        String num = obterNum(numero);
        this.labelSemestre.setText(num + " semestre do curso de Engenharia de Computação");
    }

    private String obterNum(int numero) {
        return switch (numero) {
            case 1  -> "1°";
            case 2  -> "2°";
            case 3  -> "3°";
            case 4  -> "4°";
            case 5  -> "5°";
            case 6  -> "6°";
            case 7  -> "7°";
            case 8  -> "8°";
            case 9  -> "9°";
            case 10 -> "10°";
            default -> numero + "°";
        };
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int nSemestre) {
        this.semestre = nSemestre;
    }
}