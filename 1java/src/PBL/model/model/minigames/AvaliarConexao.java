package PBL.model.model.minigames;

//strategy 3: avalia se os dois pontos conectados batem, independente da ordem

public class AvaliarConexao implements EstrategiaAvaliacao<String[], String[]> {

    @Override
    public int calcularPontuacao(String[] respostaJogador, String[] gabarito, double tempo) {
        if (respostaJogador[0] == null || respostaJogador[1] == null) {
            return 0;
        }

        //verifica se ligou na ordem exata
        boolean ligacaoDireta = respostaJogador[0].equals(gabarito[0]) && respostaJogador[1].equals(gabarito[1]);

        //verifica se ligou invertido
        boolean ligacaoInvertida = respostaJogador[0].equals(gabarito[1]) && respostaJogador[1].equals(gabarito[0]);

        if (ligacaoDireta || ligacaoInvertida) {
            return 10;
        } else {
            return 0;
        }
    }
}