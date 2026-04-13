package PBL.fase_1.model.minigames;

public class MinigameTexto extends Minigame {

    public MinigameTexto() {
        super("TypeRacer Acadêmico", "Texto");
    }

    @Override
    public void jogar(int desempenhoAtual) {
        String dificuldade = calcularDificuldade(desempenhoAtual);

    }
}