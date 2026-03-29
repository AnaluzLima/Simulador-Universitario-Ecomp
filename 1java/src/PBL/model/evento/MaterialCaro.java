package PBL.model.evento;

import PBL.model.Jogador;

public class MaterialCaro extends EventoAleatorio{
    public MaterialCaro(){
        super("Material Caro", "...", 0.10);
    }

    @Override
    public void eventoConsequencia(Jogador jogador) {
        boolean modDin = jogador.modificarDinheiro(-50);
        if(modDin){
            jogador.consequencia("Motivação", -15);
        }
        else{
            jogador.consequencia("Motivação", -35);
        }
    }
}
