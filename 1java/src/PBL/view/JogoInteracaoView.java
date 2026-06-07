package PBL.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class JogoInteracaoView {

    private final List<Node> botoesPonto = new ArrayList<>();

    //o metodo recebe uma ação que dirá o que o botão faz ao ser clicado
    public void criarPontoInteracao(Pane paneMundo, double x, double y, Runnable aoClicar) {

        Button btnPonto = new Button();
        btnPonto.setLayoutX(x);
        btnPonto.setLayoutY(y);

        try {
            ImageView imgPrincipal = new ImageView(new Image(getClass().getResourceAsStream("/resources/botoes/btnInteresse.png")));
            imgPrincipal.setFitWidth(25);
            imgPrincipal.setFitHeight(25);

            //brilhinho no botão
            DropShadow brilho = new DropShadow();
            brilho.setColor(Color.web("#FFF2B2"));
            brilho.setRadius(5);
            brilho.setSpread(0.6);

            imgPrincipal.setEffect(brilho); //aplica o efeito

            btnPonto.setGraphic(imgPrincipal);

            //efeito de crescer ao passar o mouse
            btnPonto.setOnMouseEntered(e -> btnPonto.setScaleX(1.1));
            btnPonto.setOnMouseEntered(e -> btnPonto.setScaleY(1.1));
            btnPonto.setOnMouseExited(e -> btnPonto.setScaleX(1.0));
            btnPonto.setOnMouseExited(e -> btnPonto.setScaleY(1.0));

            btnPonto.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-cursor: hand;");
        }
        catch (Exception e) {
            btnPonto.setText("?");
        }

        btnPonto.setOnAction(e -> {
            //consome o evento para que o clique não vaze para trás
            e.consume();
            aoClicar.run(); //executa
        });

        paneMundo.getChildren().add(btnPonto);
        botoesPonto.add(btnPonto);
    }

    public void limparBotoes(Pane paneMundo) {
        paneMundo.getChildren().removeAll(botoesPonto);
        botoesPonto.clear();
    }
}