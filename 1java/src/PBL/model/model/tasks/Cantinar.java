package PBL.model.model.tasks;

import PBL.exception.DinheiroException;
import PBL.exception.JogoException;
import PBL.exception.TempoException;
import PBL.model.model.Jogador;

public class Cantinar extends Atividade{

    public Cantinar() {
        super("Comprar comida na Cantina", "...");
    }

    //comprar comida na cantina

    @Override
    public void executar(Jogador jogador) throws JogoException {
        if (jogador.getDinheiro() < 12) {
            throw new DinheiroException("Saldo Insuficiente!");
        }
        if (jogador.getTempo() < 2){
            throw new TempoException("Você está sem tempo");
        }
        jogador.modificarDinheiro(-12); //gasta 12 conto
        jogador.modificarTempo(-2); //gasta 2 pontos de tempo

        jogador.getEnergia().modificar(50); //aumenta 50 de energia
        jogador.getSaude().modificar(15); //aumenta 15 de saude
        jogador.getMotivacao().modificar(10); //aumenta 10 de motivação

    }
}
