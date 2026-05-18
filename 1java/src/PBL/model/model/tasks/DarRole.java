package PBL.model.model.tasks;

import PBL.exception.DinheiroException;
import PBL.exception.JogoException;
import PBL.exception.TempoException;
import PBL.model.model.Jogador;
import PBL.model.model.personagens.Colega;

public class DarRole extends Atividade{
    private Colega acompanhante;

    //ao ir para um rolê, voce escolhe se leva um colega ou não

    public DarRole(Colega acompanhante){
        super("Sair para se divertir", "...");
        this.acompanhante = acompanhante;
    }
    //Overriding
    public DarRole(){
        super("Sair para se divertir", "...");
        this.acompanhante = null;
    }

    public Colega getAcompanhante(){
        return acompanhante;
    }

    @Override
    public void executar(Jogador jogador) throws JogoException {
        if (jogador.getDinheiro() < 30) {
            throw new DinheiroException("Você não tem dinheiro para ir ao rolê!");
        }
        if (jogador.getTempo() < (acompanhante != null ? 10 : 5)) {
            throw new TempoException("Você está sem tempo livre!");
        }

        if(acompanhante != null){ //se o jogador ta levando um colega
            jogador.modificarTempo(-10); //gasta 10 pontos de tempo
            jogador.getMotivacao().modificar(40); //recebe 40 de motivação
            jogador.getCelular().modificarAmizade(acompanhante.getNome(), 2); //aumenta a relação com o colega em 2
        }
        else { //se não
            jogador.modificarTempo(-5); //gasta 5 pontos de tempo
            jogador.getMotivacao().modificar(20); //recebe 20 de motivação
        }

        jogador.getSaude().modificar(10); //recebe 10 de saude
        jogador.getEnergia().modificar(15); //recebe 15 de energia
        jogador.modificarDinheiro(-30); //gasta 30 reais
    }
}