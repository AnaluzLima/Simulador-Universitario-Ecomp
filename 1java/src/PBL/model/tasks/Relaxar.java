package PBL.model.tasks;
import PBL.model.model.Jogador;

public class Relaxar extends Atividade{
    public Relaxar(){
        super("Relaxar na Praça","...");
    }

    @Override
    public boolean executar(Jogador jogador) {
        if (jogador.getTempo() >= 3) {
            jogador.modificarTempo(-3);

            jogador.consequencia("Motivação", 5);
            jogador.consequencia("Saúde", 3);
            jogador.consequencia("Energia", 8);

            return true;
        }
        return false;
    }
}
