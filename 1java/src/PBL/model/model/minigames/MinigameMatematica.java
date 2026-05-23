package PBL.model.model.minigames;

/**Essa classe que herda de minigames cria a lógica para o funcionamento das provas de matemática
 * Toda prova terá a equação que o jogador deve responder e a resposta correta. Quanto mais rápido ele responder,
 * maior a nota*/

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

    //Recebe a resposta digitada pelo jogador e quanto tempo ele demorou para enviar.
    public void avaliarResposta(String respostaDigitada, double seg) {
        //formata a resposta do jogador. Se ele digitou letras maiusculas ou espaços aleatórios, será ignorado, o que torna
        //a avaliação mais justa
        String respostaFormatada = respostaDigitada != null ? respostaDigitada.trim().toLowerCase().replace(" ", "") : "";

        //se ele errou a resposta completamente, é zero
        if (!respostaFormatada.equals(this.resposta)) {
            setPontuacao(0); //errou
            return;
        }

        if (seg <= 5) { //se ele respondeu em até 5s tira 10
            setPontuacao(10);
        } else if (seg <= 12) { //se respondeu em até 12s tira 8
            setPontuacao(8);
        } else { //se não, tira 7
            setPontuacao(7);
        }
    }
}