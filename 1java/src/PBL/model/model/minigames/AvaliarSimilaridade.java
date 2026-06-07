package PBL.model.model.minigames;

//strategy 2: avalia por similaridade de texto
public class AvaliarSimilaridade implements EstrategiaAvaliacao<String, String> {

    @Override
    public int calcularPontuacao(String respostaJogador, String gabarito, double tempo) {
        //se ele não digitou nada, tira zero
        if (respostaJogador == null || respostaJogador.isEmpty()) {
            return 0;
        }

        int acertos = 0;
        //compara letra por letra até onde o jogador conseguiu digitar
        int limite = Math.min(respostaJogador.length(), gabarito.length());

        //compara as letras e vai somando os acertos
        for (int i = 0; i < limite; i++) {
            if (respostaJogador.charAt(i) == gabarito.charAt(i)) {
                acertos++;
            }
        }

        //calcula a porcentagem de acerto
        double porcentagem = (double) acertos / gabarito.length();

        //define a nota com base na aproximação da porcentagem
        return (int) Math.round(porcentagem * 10.0);
    }
}