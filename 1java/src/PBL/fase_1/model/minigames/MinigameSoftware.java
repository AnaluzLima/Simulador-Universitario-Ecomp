package PBL.fase_1.model.minigames;

public class MinigameSoftware extends Minigame {

    public MinigameSoftware() {
        super("Quiz de Lógica", "Software");
    }

    @Override
    public void jogar(int desempenhoAtual) {
        String dificuldade = calcularDificuldade(desempenhoAtual);

    }
}