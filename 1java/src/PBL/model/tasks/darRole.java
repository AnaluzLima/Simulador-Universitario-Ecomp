package PBL.model.tasks;

import PBL.model.Jogador;

public class darRole extends Atividade{
    private boolean levarColega;


    public darRole(boolean levarColega){
        super("Sair e se distrair", "...");
        this.levarColega = levarColega;
    }

    @Override
    public boolean executar(Jogador jogador) {
        if (((jogador.getTempo() >= 5 && !levarColega) || (jogador.getTempo() >= 10 && levarColega)) && jogador.getDinheiro() >= 30) {

            if(levarColega){
                jogador.modificarTempo(-10);
                jogador.consequencia("Motivação", 40);
            }
            else{
                jogador.modificarTempo(-5);
                jogador.consequencia("Motivação", 25);
            }

            jogador.consequencia("Saúde", 10);
            jogador.consequencia("Energia", 15);
            jogador.modificarDinheiro(-30);

            return true;
        }
        return false;
    }
}
