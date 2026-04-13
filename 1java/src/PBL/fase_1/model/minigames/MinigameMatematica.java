package PBL.fase_1.model.minigames;

public class MinigameMatematica extends Minigame {

    public MinigameMatematica() {
        super("Calculador", "Matemática");
    }

    @Override
    public void jogar(int desempenhoAtual) {
        String dificuldade = calcularDificuldade(desempenhoAtual);

    }
}