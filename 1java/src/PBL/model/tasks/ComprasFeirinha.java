package PBL.model.tasks;
import PBL.model.model.Jogador;


public class ComprasFeirinha extends Atividade{
    public ComprasFeirinha() {
        super("Comprar comida na Feirinha", "...");
    }

    @Override
    public boolean executar(Jogador jogador) {
        if(jogador.getTempo() >= 2 && jogador.getDinheiro() >= 10){
            jogador.modificarDinheiro(-10);
            jogador.modificarTempo(-2);

            jogador.consequencia("Energia", 55);
            jogador.consequencia("Saúde", 15);
            jogador.consequencia("Motivação", 15);

            return true;
        }
        return false;

    }
}
