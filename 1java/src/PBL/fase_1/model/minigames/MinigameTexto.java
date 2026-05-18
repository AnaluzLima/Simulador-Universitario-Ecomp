package PBL.fase_1.model.minigames;

public class MinigameTexto extends Minigame {
    private String textoAtual;

    public MinigameTexto() {
        super("TypeRacer Acadêmico", "Texto");
    }

    public void setTextoAtual(String texto) {
        this.textoAtual = texto;
    }

    public String getTextoAtual() {
        return this.textoAtual;
    }

    public void avaliarDigitacao(String textoDigitado, double seg) {
        if (textoDigitado == null || textoDigitado.isEmpty()) {
            setPontuacao(0);
            return;
        }

        int acertos = 0;
        //compara letra por letra até onde o jogador conseguiu digitar
        int limite = Math.min(textoDigitado.length(), this.textoAtual.length());

        for (int i = 0; i < limite; i++) {
            if (textoDigitado.charAt(i) == this.textoAtual.charAt(i)) {
                acertos++;
            }
        }

        //calcula a porcentagem de acerto
        double porcentagem = (double) acertos / this.textoAtual.length();

        int notaFinal = (int) Math.round(porcentagem * 10.0);
        setPontuacao(notaFinal);
    }
}