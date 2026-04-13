package PBL.fase_1.model.minigames;

public class MinigameHardware extends Minigame {

    public MinigameHardware() {
        super("Conexão de Circuitos", "Hardware");
    }

    @Override
    public void jogar(int desempenhoAtual) {
        String dificuldade = calcularDificuldade(desempenhoAtual);

    }
}