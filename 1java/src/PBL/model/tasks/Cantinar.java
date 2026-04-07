package PBL.model.tasks;

import PBL.model.model.Jogador;

public class Cantinar extends Atividade{
    public Cantinar() {
        super("Comprar comida na Cantina", "...");
    }

    @Override
    public boolean executar(Jogador jogador) {
        if(jogador.getTempo() >= 1 && jogador.getDinheiro() >= 12){
            jogador.modificarDinheiro(-12);
            jogador.modificarTempo(-1);

            jogador.consequencia("Energia", 50);
            jogador.consequencia("Saúde", 15);
            jogador.consequencia("Motivação", 10);

            return true;
        }
        return false;

    }
}
