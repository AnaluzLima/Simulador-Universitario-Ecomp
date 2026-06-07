package PBL.model.model.minigames;


//Padrão Comportamental: Strategy
public interface EstrategiaAvaliacao<R, G> {
    //R = tipo da resposta
    //G = tipo do gabarito

    //metodo que as estratégias concretas vão implementar
    int calcularPontuacao(R respostaJogador, G gabarito, double tempo);

}
