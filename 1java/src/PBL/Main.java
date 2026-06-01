package PBL;

import PBL.controller.gerenciadores.GerenciadorDeAudio;
import PBL.controller.gerenciadores.GerenciadorDeTelas;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class Main extends Application {

    @Override
    public void start(Stage palcoPrincipal) throws Exception {
        GerenciadorDeAudio.getInstance().tocarMusica(GerenciadorDeAudio.MUSICA_MENU);

        Font.loadFont(getClass().getResourceAsStream("/resources/fontes/PressStart2P.ttf"), 16);

        GerenciadorDeTelas g = GerenciadorDeTelas.getInstance();
        g.setStage(palcoPrincipal);
        g.adicionarCena(GerenciadorDeTelas.MENU, "/PBL/view/fxmls/menu/Menu.fxml");
        g.trocarPara(GerenciadorDeTelas.MENU);

        palcoPrincipal.setTitle("CompLife");
        palcoPrincipal.setResizable(false);
        palcoPrincipal.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}