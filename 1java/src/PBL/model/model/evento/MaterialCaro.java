package PBL.model.model.evento;

import PBL.exception.DinheiroException;
import PBL.model.model.Jogador;

public class MaterialCaro extends EventoAleatorio{
    public MaterialCaro(){
        super("Material Caro", "...", 0.10);
    }

    @Override
    public void eventoConsequencia(Jogador jogador) throws DinheiroException {
        //se, ao assistir uma aula, o jogador descobre aleatoriamente que terá que gastar dinheiro
        try{
            jogador.modificarDinheiro(-50); //é obrigado a gastar 50 reais
            jogador.getMotivacao().modificar(-15); //perde 15 de motivação
        }
        catch (DinheiroException e){ //se ele não tiver como pagar, perde 35 de motivação
            jogador.getMotivacao().modificar(-35);
        }
    }
}
