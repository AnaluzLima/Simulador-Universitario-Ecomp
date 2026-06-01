package PBL.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class TelaJogoView {

    private final Map<String, ImageView> camadasVisuais = new HashMap<>();

    private String direcaoAtual = "frente";
    private int frameIdle = 0;
    private Timeline animacaoIdle;

    //monta as 8 camadas sobrepostas dentro do StackPane do jogador
    public void inicializarCamadasJogador(StackPane containerJogador, double tamanhoDinamico) {
        String[] ordemDeRenderizacao = {"base", "camisa", "oculos", "luva", "folhas", "flor", "blushed", "damage"};

        for (String nomeCamada : ordemDeRenderizacao) {
            ImageView imgLayer = new ImageView();
            imgLayer.setFitWidth(tamanhoDinamico);
            imgLayer.setFitHeight(tamanhoDinamico);
            imgLayer.setPreserveRatio(true);

            camadasVisuais.put(nomeCamada, imgLayer);
            containerJogador.getChildren().add(imgLayer);
        }
    }

    public void iniciarAnimacaoIdle() {
        animacaoIdle = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            frameIdle = (frameIdle == 0) ? 1 : 0;
            atualizarSprites("idle", direcaoAtual, frameIdle);
        }));
        animacaoIdle.setCycleCount(Timeline.INDEFINITE);
        animacaoIdle.play();
    }

    public void pausarAnimacao() {
        if (animacaoIdle != null) animacaoIdle.pause();
    }
    public void retomarAnimacao() {
        if (animacaoIdle != null) animacaoIdle.play();
    }

    //calcula para qual lado o jogador deve virar com base no mouse
    public void direcaoSprite(double mouseX, double mouseY, StackPane containerJogador) {
        double jogadorCentroX = containerJogador.getLayoutX() + (containerJogador.getWidth() / 2);
        double jogadorCentroY = containerJogador.getLayoutY() + (containerJogador.getHeight() / 2);

        double dx = mouseX - jogadorCentroX;
        double dy = mouseY - jogadorCentroY;

        String novaDirecao;
        if (Math.abs(dx) > Math.abs(dy)) {
            novaDirecao = (dx > 0) ? "direita" : "esquerda";
        }
        else {
            novaDirecao = (dy > 0) ? "frente" : "costas";
        }
        //se a direção do mouse mudou atualiza a variável
        if (!novaDirecao.equals(this.direcaoAtual)) {
            this.direcaoAtual = novaDirecao;
            atualizarSprites("idle", this.direcaoAtual, this.frameIdle);
        }
    }

    //atualiza as imagens de todas as camadas
    public void atualizarSprites(String acao, String direcaoAtual, int frameIdle) {
        String frame = direcaoAtual + frameIdle;

        String caminhoBase = "/resources/skins/jogador/" + acao + "/" + frame + ".png";
        atualizarImagemCamada("base", caminhoBase);
    }

    private void atualizarImagemCamada(String nomeCamada, String caminhoRecurso) {
        try {
            Image imagem = new Image(getClass().getResourceAsStream(caminhoRecurso));
            camadasVisuais.get(nomeCamada).setImage(imagem);
        } catch (Exception e) {
            //ignora
        }
    }

    public void atualizarFundoHUD(ImageView imgFundoHud, String estado) {
        String caminho;
        if (estado.equals("atributos")) {
            caminho = "/resources/HUD/HUD_atributos.png";
        } else if (estado.equals("celular")) {
            caminho = "/resources/HUD/HUD_celular.png";
        } else {
            caminho = "/resources/HUD/HUD.png";
        }

        try {
            imgFundoHud.setImage(new Image(getClass().getResourceAsStream(caminho)));
        } catch (Exception e) {
            System.err.println("Imagem do HUD não encontrada: " + caminho);
        }
    }
    public void atualizarHUD(Label labelMoedas, Label labelTempo, int moedas, int tempo) {
        //converte os int inteiros para String e joga nas Labels
        labelMoedas.setText(String.valueOf(moedas));
        labelTempo.setText(String.valueOf(tempo));
    }

    public void renderizarAtributos(VBox painelAtributos, Map<String, Integer> atributos) {
        //prepara o painel principal
        painelAtributos.getChildren().clear();
        painelAtributos.setPadding(new Insets(15, 0, 0, 15));
        painelAtributos.setSpacing(12); //espaço entre um atributo e outro

        for (Map.Entry<String, Integer> entry : atributos.entrySet()) {
            String nomeAtributo = entry.getKey();
            int valor = entry.getValue();

            //vBox que empilha o Nome em cima da barra
            VBox bloco = new VBox();
            bloco.setSpacing(4);

            //label do nome
            Label lblNome = new Label(nomeAtributo);
            lblNome.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 9px; -fx-text-fill: #372600;");

            //hBox que alinha a barra e a porcentagem
            HBox linhaBarra = new HBox();
            linhaBarra.setSpacing(10);
            linhaBarra.setAlignment(Pos.CENTER_LEFT);

            //fundo transparente com a borda
            Pane bordaBarra = new Pane();
            bordaBarra.setPrefSize(100, 12);
            bordaBarra.setStyle("-fx-border-color: #372600; -fx-border-width: 2px; -fx-background-color: transparent;");

            //preenchimento da barra
            Pane preenchimento = new Pane();

            //calcula o tamanho da barra
            //a largura máxima é 96px (100px - 4px das duas bordas)
            double larguraPreenchimento = 96.0 * (valor / 100.0);
            preenchimento.setPrefSize(larguraPreenchimento, 8);

            //move 2 pixels para dentro para não ficar por cima da borda
            preenchimento.setLayoutX(2);
            preenchimento.setLayoutY(2);
            preenchimento.setStyle("-fx-background-color: #372600;");

            //coloca o preenchimento
            bordaBarra.getChildren().add(preenchimento);

            //label da porcentagem
            Label lblPorcentagem = new Label(valor + "%");
            lblPorcentagem.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 9px; -fx-text-fill: #372600;");

            //junta tudo
            linhaBarra.getChildren().addAll(bordaBarra, lblPorcentagem);
            bloco.getChildren().addAll(lblNome, linhaBarra);
            painelAtributos.getChildren().add(bloco);
        }
    }

}