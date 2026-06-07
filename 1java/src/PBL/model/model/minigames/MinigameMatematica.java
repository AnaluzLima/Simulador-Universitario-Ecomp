package PBL.model.model.minigames;

/**Essa classe que herda de minigames cria a lógica para o funcionamento das provas de matemática
 * Toda prova terá a equação que o jogador deve responder e a resposta correta. Quanto mais rápido ele responder,
 * maior a nota*/

public class MinigameMatematica extends Minigame {
    private String equacaoAtual;
    private String resposta;
    private EstrategiaAvaliacao<String, String> estrategia;


    public MinigameMatematica(EstrategiaAvaliacao<String, String> estrategia) {
        super("Cálculo", "Matemática");
        this.estrategia = estrategia;
    }

    public void setRodada(String equacao, String resposta) {
        this.equacaoAtual = equacao;
        this.resposta = resposta;
    }

    public String getEquacaoAtual() {
        return this.equacaoAtual;
    }

    public void avaliarResposta(String respostaDigitada, double seg) {
        String respostaFormatada = respostaDigitada != null ? respostaDigitada.trim().toLowerCase().replace(" ", "") : "";

        int notaFinal = estrategia.calcularPontuacao(respostaFormatada, this.resposta, seg);
        setPontuacao(notaFinal);
    }
}