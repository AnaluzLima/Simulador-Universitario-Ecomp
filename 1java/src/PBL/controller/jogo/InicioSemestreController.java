package PBL.controller.jogo;

import PBL.controller.gerenciadores.GerenciadorDeTelas;
import PBL.view.InicioSemestreView;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class InicioSemestreController {

    @FXML private StackPane stackPrincipal;
    @FXML private Label labelSemestre;

    private final InicioSemestreView view = new InicioSemestreView();

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

    public void setSemestre(int numero) {
        view.atualizarTextSemestre(labelSemestre, numero);
    }
}