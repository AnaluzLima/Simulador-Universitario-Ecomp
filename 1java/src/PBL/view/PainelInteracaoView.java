package PBL.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;

public class PainelInteracaoView {

    public void renderizarListas(VBox vboxNavegacao, VBox vboxAtividades,
                                 List<OpcaoBotaoJogo> rotas, List<OpcaoBotaoJogo> atividades) {

        vboxNavegacao.getChildren().clear();
        vboxAtividades.getChildren().clear();

        //popula as rotas
        if (rotas.isEmpty()) {
            vboxNavegacao.getChildren().add(criarTextoVazio("Nenhum local próximo."));
        } else {
            for (OpcaoBotaoJogo rota : rotas) {
                vboxNavegacao.getChildren().add(criarBotaoEstilizado(rota.getNome(), rota.getAcao()));
            }
        }

        //popula as atividades
        if (atividades.isEmpty()) {
            vboxAtividades.getChildren().add(criarTextoVazio("Nada para fazer aqui."));
        } else {
            for (OpcaoBotaoJogo atv : atividades) {
                vboxAtividades.getChildren().add(criarBotaoEstilizado(atv.getNome(), atv.getAcao()));
            }
        }
    }

    //botão do sidepane
    private Button criarBotaoEstilizado(String texto, Runnable acao) {
        Button btn = new Button(texto);

        btn.setPrefSize(250, 45);
        btn.setMaxSize(250, 45);

        try {
            ImageView imgFundo = new ImageView(new Image(getClass().getResourceAsStream("/resources/botoes/btnGeral.png")));

            imgFundo.setFitWidth(250);
            imgFundo.setFitHeight(45);
            imgFundo.setPreserveRatio(false);

            btn.setGraphic(imgFundo);

            btn.setContentDisplay(ContentDisplay.CENTER);
        }
        catch (Exception e) {
            System.err.println("Aviso: Imagem btnGeral.png não encontrada.");
            btn.getStyleClass().add("botao-retro"); //fallback do css
        }

        //estilo do texto do botão
        btn.setStyle("-fx-background-color: transparent; -fx-padding: 0; -fx-text-fill: #372600; -fx-font-family: 'Press Start 2P'; -fx-font-size: 10px; -fx-cursor: hand;");

        //ação do clique
        btn.setOnAction(e -> {
            e.consume(); //impede que o clique vaze
            acao.run();
        });

        //efeito de escurecer ao passar o mouse por cima
        btn.setOnMouseEntered(e -> btn.setOpacity(0.8));
        btn.setOnMouseExited(e -> btn.setOpacity(1.0));

        return btn;
    }

    private Label criarTextoVazio(String mensagem) {
        Label lblVazio = new Label(mensagem);
        lblVazio.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 9px; -fx-text-fill: #372600;");
        lblVazio.setPadding(new Insets(10, 0, 0, 10));
        return lblVazio;
    }
}