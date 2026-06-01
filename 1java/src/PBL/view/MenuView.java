package PBL.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MenuView {
    public void configurarParallax(StackPane stackPrincipal, ImageView imagemFundo) {
        imagemFundo.setScaleX(1.08);
        imagemFundo.setScaleY(1.08);

        //crava a resolução base da imagem para o tamanho padrão
        imagemFundo.setFitWidth(1280);
        imagemFundo.setFitHeight(720);

        stackPrincipal.setOnMouseMoved(event -> {
            double dx = (event.getX() / stackPrincipal.getWidth() - 0.5) * 2;
            double dy = (event.getY() / stackPrincipal.getHeight()  - 0.5) * 2;

            //se o mouse estiver totalmente na direita a imagem se moverá -20 pixels
            imagemFundo.setTranslateX(-dx * 20);
            imagemFundo.setTranslateY(-dy * 20);
        });
    }
}