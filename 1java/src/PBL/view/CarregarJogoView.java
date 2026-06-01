package PBL.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

import java.util.function.Consumer;

public class CarregarJogoView {

    //o controller chama esses métodos
    public Label criarMensagemVazia() {
        Label vazio = new Label("Nenhuma partida encontrada");
        vazio.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 20; -fx-text-fill: #372600;");
        vazio.setPadding(new Insets(20));
        return vazio;
    }
    public StackPane criarLinhaSave(String slot, Runnable aoClicarNome, Consumer<String> aoClicarLixo) {
        //espaço que vai armazenar toda a linha do save
        StackPane container = new StackPane();
        container.setPrefSize(980, 75);
        container.setMaxSize(980, 75);

        //imagem da linha
        ImageView bg = new ImageView(new Image(getClass().getResourceAsStream("/resources/textBox.png")));
        bg.setFitWidth(980);
        bg.setFitHeight(75);
        bg.setPreserveRatio(false);

        Label nome = new Label(slot);
        nome.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 20; " + "-fx-text-fill: #2B1810; -fx-cursor: hand;");
        nome.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(nome, Priority.ALWAYS); //estica a HBox o máximo possível
        nome.setOnMouseClicked(e -> aoClicarNome.run()); //ao clicar no slot, carrega o jogo

        //imagem da lixeira
        ImageView imgLixeira = new ImageView(new Image(getClass().getResourceAsStream("/resources/botoes/btnLixeira.png")));
        imgLixeira.setFitWidth(30);
        imgLixeira.setPreserveRatio(true);

        //botao da lixeira que chama o popup
        Button lixeira = new Button();
        lixeira.setGraphic(imgLixeira);
        lixeira.getStyleClass().add("botao-retro");
        lixeira.setOnAction(e -> aoClicarLixo.accept(slot));

        HBox conteudo = new HBox();
        conteudo.setAlignment(Pos.CENTER_LEFT);
        conteudo.setPadding(new Insets(0, 40, 0, 40)); //não deixa o nome do slot e a lixeira encostrarem
        conteudo.getChildren().addAll(nome, lixeira);

        container.getChildren().addAll(bg, conteudo);
        return container;
    }
}