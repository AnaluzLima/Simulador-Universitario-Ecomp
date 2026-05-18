package PBL.fase_1.model.minigames;

import java.util.List;

//vai mostrar 3 opções e perguntar "qual bloco de código faz *alguma coisa*?"
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

    public void avaliarResposta(int indice, double tempo) {
        if (indice != this.indiceCorreto) {
            setPontuacao(0); //errou a questão
            return;
        }

        //acertou
        if (tempo <= 15.0) {
            setPontuacao(10);
        } else if (tempo <= 30.0) {
            setPontuacao(7);
        } else {
            setPontuacao(5);
        }
    }
}