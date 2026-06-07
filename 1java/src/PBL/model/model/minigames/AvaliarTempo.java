package PBL.model.model.minigames;

//strategy 1: avaliação baseada no tempo

public class AvaliarTempo<T> implements EstrategiaAvaliacao<T, T> {
    @Override
    public int calcularPontuacao(T respostaJogador, T gabarito, double tempo) {
        //se errou a resposta, é zero
        if (!respostaJogador.equals(gabarito)) {
            return 0;
        }

        //se acertou aplica a regra de tempo
        if (tempo <= 5.0) {
            return 10;
        } else if (tempo <= 12.0) {
            return 8;
        } else {
            return 7;
        }
    }
}