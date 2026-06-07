package PBL.model.model.minigames;

import java.util.List;

/**Essa classe que herda de minigames cria a lógica para o funcionamento das provas de software
 * Toda prova terá o enunciado, as alternativas e a resposta correta. Quanto mais rápido ele responder,
 * maior a nota*/

public class MinigameSoftware extends Minigame {
    private String perguntaCodigo;
    private List<String> alternativas;
    private int indiceCorreto;

    private EstrategiaAvaliacao<Integer, Integer> estrategia;

    public MinigameSoftware(EstrategiaAvaliacao<Integer, Integer> estrategia) {
        super("Quiz de Programação", "Software");
        this.estrategia = estrategia;
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
        //delega o cálculo para a estrategia
        int notaFinal = estrategia.calcularPontuacao(indice, this.indiceCorreto, tempo);
        setPontuacao(notaFinal);
    }
}