package PBL.view;

import javafx.animation.FadeTransition;
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

    //pra não ficar recarregando a imagem o tempo todo
    private static final Map<String, Image> cacheImagens = new HashMap<>();

    private String direcaoAtual = "frente";
    private int frameIdle = 0;
    private Timeline animacaoIdle;

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
        if (animacaoIdle != null) {
            animacaoIdle.stop();
        }

        atualizarSprites("idle", direcaoAtual, frameIdle);

        //a Timeline calcula a troca de frame a cada meio segundo
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
        if (!novaDirecao.equals(this.direcaoAtual)) {
            this.direcaoAtual = novaDirecao;
            atualizarSprites("idle", this.direcaoAtual, this.frameIdle);
        }
    }

    public void atualizarSprites(String acao, String direcaoAtual, int frameIdle) {
        String frame = direcaoAtual + frameIdle;
        String caminhoBase = "/resources/skins/jogador/" + acao + "/" + frame + ".png";
        atualizarImagemCamada("base", caminhoBase);
    }

    //le a imagem do cache ao invés do disco
    private void atualizarImagemCamada(String nomeCamada, String caminhoRecurso) {
        try {
            //se a imagem aidna nao ta na memória, pega do disco
            if (!cacheImagens.containsKey(caminhoRecurso)) {
                Image novaImagem = new Image(getClass().getResourceAsStream(caminhoRecurso));
                cacheImagens.put(caminhoRecurso, novaImagem);
            }

            //pega a imagem direto da memória ram
            Image imagemEmCache = cacheImagens.get(caminhoRecurso);
            camadasVisuais.get(nomeCamada).setImage(imagemEmCache);

        }
        catch (Exception e) {
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
            if (!cacheImagens.containsKey(caminho)) {
                Image novaImgHUD = new Image(getClass().getResourceAsStream(caminho));
                cacheImagens.put(caminho, novaImgHUD);
            }
            imgFundoHud.setImage(cacheImagens.get(caminho));
        }
        catch (Exception e) {
            System.err.println("Imagem do HUD não encontrada: " + caminho);
        }
    }

    public void atualizarHUD(Label labelMoedas, Label labelTempo, int moedas, int tempo) {
        labelMoedas.setText(String.valueOf(moedas));
        labelTempo.setText(String.valueOf(tempo));
    }

    public void renderizarAtributos(VBox painelAtributos, Map<String, Integer> atributos) {
        painelAtributos.getChildren().clear();
        painelAtributos.setPadding(new Insets(15, 0, 0, 15));
        painelAtributos.setSpacing(12);

        for (Map.Entry<String, Integer> entry : atributos.entrySet()) {
            String nomeAtributo = entry.getKey();
            int valor = entry.getValue();

            VBox bloco = new VBox();
            bloco.setSpacing(4);

            Label lblNome = new Label(nomeAtributo);
            lblNome.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 9px; -fx-text-fill: #372600;");

            HBox linhaBarra = new HBox();
            linhaBarra.setSpacing(10);
            linhaBarra.setAlignment(Pos.CENTER_LEFT);

            Pane bordaBarra = new Pane();
            bordaBarra.setPrefSize(100, 12);
            bordaBarra.setStyle("-fx-border-color: #372600; -fx-border-width: 2px; -fx-background-color: transparent;");

            Pane preenchimento = new Pane();
            double larguraPreenchimento = 96.0 * (valor / 100.0);
            preenchimento.setPrefSize(larguraPreenchimento, 8);
            preenchimento.setLayoutX(2);
            preenchimento.setLayoutY(2);
            preenchimento.setStyle("-fx-background-color: #372600;");

            bordaBarra.getChildren().add(preenchimento);

            Label lblPorcentagem = new Label(valor + "%");
            lblPorcentagem.setStyle("-fx-font-family: 'Press Start 2P'; -fx-font-size: 9px; -fx-text-fill: #372600;");

            linhaBarra.getChildren().addAll(bordaBarra, lblPorcentagem);
            bloco.getChildren().addAll(lblNome, linhaBarra);
            painelAtributos.getChildren().add(bloco);
        }
    }

    public void preencherPopupAtividade(Label titulo, Label desc, Label consq, String strTitulo, String strDesc, String strConsq) {
        titulo.setText(strTitulo);
        desc.setText(strDesc);

        if (strConsq != null && !strConsq.isEmpty()) {
            String[] partes = strConsq.split("\\|");
            StringBuilder textoFinal = new StringBuilder();

            for (String parte : partes) {
                textoFinal.append("").append(parte.trim()).append("\n");
            }
            consq.setText(textoFinal.toString());
        } else {
            consq.setText("Nenhum efeito conhecido.");
        }
    }

    public void animarTransicaoSala(Pane overlayTransicao, Runnable acaoNoEscuro) {
        overlayTransicao.setOpacity(0.0);
        overlayTransicao.setVisible(true);
        overlayTransicao.toFront();

        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), overlayTransicao);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeIn.setOnFinished(e -> {
            acaoNoEscuro.run();

            javafx.animation.PauseTransition tempoDeCarregamento = new javafx.animation.PauseTransition(Duration.millis(150));

            tempoDeCarregamento.setOnFinished(event -> {
                FadeTransition fadeOut = new FadeTransition(Duration.millis(300), overlayTransicao);
                fadeOut.setFromValue(1.0);
                fadeOut.setToValue(0.0);
                fadeOut.setOnFinished(ev -> overlayTransicao.setVisible(false));
                fadeOut.play();
            });

            tempoDeCarregamento.play();
        });

        fadeIn.play();
    }
}