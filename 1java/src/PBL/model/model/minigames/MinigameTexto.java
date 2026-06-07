package PBL.model.model.minigames;

/**Essa classe que herda de minigames cria a lógica para o funcionamento das provas de texto
 * Toda prova terá o texto que o jogador deve digitar corretamente*/

public class MinigameTexto extends Minigame {
    private String textoAtual;
    private EstrategiaAvaliacao<String, String> estrategia;

    public MinigameTexto(EstrategiaAvaliacao<String, String> estrategia) {
        super("TypeRacer Acadêmico", "Texto");
        this.estrategia = estrategia;
    }

    public void setTextoAtual(String texto) {
        this.textoAtual = texto;
    }

    public String getTextoAtual() {
        return this.textoAtual;
    }

    public void avaliarDigitacao(String textoDigitado) {
        int notaFinal = estrategia.calcularPontuacao(textoDigitado, this.textoAtual, 0.0);
        setPontuacao(notaFinal);
    }
}