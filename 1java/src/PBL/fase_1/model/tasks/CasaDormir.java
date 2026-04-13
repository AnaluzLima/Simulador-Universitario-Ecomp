package PBL.fase_1.model.tasks;

import PBL.exception.JogoException;
import PBL.fase_1.model.Jogador;

public class CasaDormir extends Atividade{
    public CasaDormir(){
        super("Ir para Casa e descansar", "...");
    }

    //Ir para casa dormir

    @Override
    public void executar(Jogador jogador) throws JogoException {
        jogador.modificarTempo(-2);

        if (jogador.isCansado()) { //se o jogador está cansado, ou seja, dormiu tarde
            jogador.getEnergia().modificar(40); //recupera apenas 40 de energia
            jogador.setCansado(false);  //reset
        }
        else { //se não
            jogador.getEnergia().modificar(70); //recupera 70 de energia
        }
    }
}
