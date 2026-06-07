package PBL.controller.gerenciadores;

import PBL.view.TransicaoSemestreView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeTelas {
    private static GerenciadorDeTelas instance;

    private String telaAtual;
    private String telaAnterior; //foi necessario fazer a tela anterior para as configurações conhecerem o caminho de volta

    //palco para todas as cenas
    private Stage stage;
    //map de cenas
    private final Map<String, Scene> cenas = new HashMap<>();

    //telas
    public static final String MENU = "menu";
    public static final String NOVO_JOGO = "novoJogo";
    public static final String CARREGAR = "carregar";
    public static final String CONFIGURACOES = "configuracoes";
    public static final String JOGO = "jogo";
    public static final String ESCOLHER_SPAWN = "escolherSpawn";
    public static final String INICIO_SEMESTRE = "inicioSemestre";

    //singleton
    public static GerenciadorDeTelas getInstance() {
        if (instance == null) {
            instance = new GerenciadorDeTelas();
        }
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //carrega um fxml e guarda a scene no map com o nome
    public void adicionarCena(String nome, String caminhoFxml) throws IOException {
        if (!this.cenas.containsKey(nome)) {
            Parent root = FXMLLoader.load(getClass().getResource(caminhoFxml)); //instancia cada componente e cria uma árvore
            this.cenas.put(nome, new Scene(root, 1280, 720));
        }
    }

    public void trocarPara(String nome) {
        Scene cena = this.cenas.get(nome);

        if (cena == null) {
            System.err.println("Tela '" + nome + "' não foi carregada no mapa.");
            return;
        }

        this.telaAnterior = this.telaAtual;
        this.telaAtual = nome;

        this.stage.setScene(cena);
    }

    public void removerCena(String nome) {
        this.cenas.remove(nome);
    }

    public void trocarParaInicioSemestre(int numeroSemestre) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("PBL/view/fxmls/jogo/InicioSemestre.fxml"));

        //sempre recarrega para atualizar o semestre
        Parent root = loader.load();

        //acessa o controller para colocar o número do semestre
        TransicaoSemestreView ctrl = loader.getController();
        ctrl.setSemestre(numeroSemestre);

        this.cenas.put(GerenciadorDeTelas.INICIO_SEMESTRE, new Scene(root, 1280, 720));
        this.stage.setScene(cenas.get(GerenciadorDeTelas.INICIO_SEMESTRE));
    }

    public String getTelaAnterior() {
        return this.telaAnterior;
    }
}