package PBL.model.model.minigames;

import java.util.List;

/**Essa classe que herda de minigames cria a lógica para o funcionamento das provas de software
 * Toda prova terá o enunciado, as alternativas e a resposta correta. Quanto mais rápido ele responder,
 * maior a nota*/

public class MinigameSoftware extends Minigame {
    private String perguntaCodigo;
    private List<String> alternativas;
    private int indiceCorreto;

    public MinigameSoftware() {
        super("Quiz de Programação", "Software");
    }

    public void setRodada(String perguntaCodigo, List<String> alternativas, int indiceCorreto) {
        this.perguntaCodigo = perguntaCodigo;
        this.alternativas = alternativas;
        this.indiceCorreto = indiceCorreto;
    }

    public String getPerguntaCodigo() {
        return perguntaCodigo;
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    //Recebe o indice da alternativa que ele marcou e em quanto tempo ele respondeu
    public void avaliarResposta(int indice, double tempo) {
        //se ele errou a resposta completamente, é zero
        if (indice != this.indiceCorreto) {
            setPontuacao(0);
            return;
        }

        //acertou
        if (tempo <= 5) { //se ele respondeu em até 5s tira 10
            setPontuacao(10);
        } else if (tempo <= 12) { //se respondeu em até 12s tira 8
            setPontuacao(8);
        } else { //se não, tira 7
            setPontuacao(7);
        }
    }
}