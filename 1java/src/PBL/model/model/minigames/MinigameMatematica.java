package PBL.model.model.minigames;

public class MinigameMatematica extends Minigame {
    private String equacaoAtual;
    private String resposta;

    public MinigameMatematica() {
        super("Cálculo", "Matemática");
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

        if (!respostaFormatada.equals(this.resposta)) {
            setPontuacao(0); //errou
            return;
        }

        if (seg <= 10.0) {
            setPontuacao(10);
        } else if (seg <= 20.0) {
            setPontuacao(7);
        } else {
            setPontuacao(5);
        }
    }
}