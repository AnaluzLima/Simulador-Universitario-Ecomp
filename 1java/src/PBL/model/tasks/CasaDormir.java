package PBL.model.tasks;
import PBL.model.Jogador;

public class CasaDormir extends Atividade{
    public CasaDormir(){
        super("Ir para Casa e descansar", "...");
    }

    @Override
    public boolean executar(Jogador jogador) {
        if (jogador.getTempo() >= 2) {
            jogador.modificarTempo(-2);

            if (jogador.isCansado()) {
                jogador.consequencia("Energia", 40); //mimiu mal
                jogador.setCansado(false);  //reset
            } else {
                jogador.consequencia("Energia", 70); //mimiu bem
            }
            return true;
        }
        return false;
    }
}
