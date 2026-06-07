package PBL.controller.gerenciadores;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class GerenciadorDeAudio {

    private static GerenciadorDeAudio instance;

    //musicas
    public static final String MUSICA_MENU = "/resources/audio/MusicaMenu.mp3";
    public static final String MUSICA_JOGO = "/resources/audio/MusicaJogo.mp3";

    //efeitos sonoros
    public static final String SFX_CLIQUE  = "/resources/audio/Clicar.mp3";
    public static final String SFX_SAIR = "/resources/audio/Exit.mp3";
    public static final String SFX_FECHAR = "/resources/audio/CloseMenu.mp3";
    public static final String SFX_POPUP = "/resources/audio/PopUpOpen.mp3";
    public static final String SFX_NEGAR = "/resources/audio/Negar.mp3";
    public static final String SFX_INICIAR = "/resources/audio/Iniciar.mp3";

    private MediaPlayer musicaAtual;
    private double volumeMusica = 0.3;  //50%
    private double volumeSfx    = 0.8;  //80%

    private GerenciadorDeAudio() {}

    public static GerenciadorDeAudio getInstance() {
        if (instance == null) {
            instance = new GerenciadorDeAudio();
        }
        return instance;
    }

    public void tocarMusica(String caminhoAudio) {
        //se já está tocando a mesma música, não reinicia
        if (musicaAtual != null) {
            musicaAtual.stop();
            musicaAtual.dispose(); //destroi a thread nativa de áudio devolvendo a memoria para o computador.
        }

        Media media = new Media(getClass().getResource(caminhoAudio).toExternalForm());
        musicaAtual = new MediaPlayer(media);
        musicaAtual.setVolume(volumeMusica);
        musicaAtual.setCycleCount(MediaPlayer.INDEFINITE); //loop infinito
        musicaAtual.play();
    }

    public void pararMusica() {
        if (musicaAtual != null) {
            musicaAtual.stop();
        }
    }

    public void pausarMusica(){
        if (musicaAtual != null) {
            musicaAtual.pause();
        }
    }

    public void continuarMusica(){
        if (musicaAtual != null) {
            musicaAtual.play();
        }
    }

    public void setVolumeMusica(double volume) {
        this.volumeMusica = volume;
        if (musicaAtual != null) {
            musicaAtual.setVolume(volume);
        }
    }

    public void tocarSfx(String caminhoAudio) {
        //cada sfx cria seu próprio MediaPlayer e se descarta sozinho
        Media media = new Media(getClass().getResource(caminhoAudio).toExternalForm());
        MediaPlayer sfx = new MediaPlayer(media);
        sfx.setVolume(volumeSfx);
        sfx.play();

        //libera memória automaticamente quando o som terminar
        sfx.setOnEndOfMedia(sfx::dispose);
    }

    public void setVolumeSfx(double volume) {
        this.volumeSfx = volume;
    }
    public double getVolumeSfx() {
        return this.volumeSfx;
    }
    public double getVolumeMusica() {
        return this.volumeMusica;
    }
}