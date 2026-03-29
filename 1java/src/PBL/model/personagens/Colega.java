package PBL.model.personagens;

import PBL.model.Jogador;

public class Colega extends NPC {
    //Nivel de Amizade;
    //de 0 a 2: estranho;  de 2 a 4: conhecido;  4 a 8: amigo; de 8 a 10: melhor amigo*/

    public Colega(String nome){
        super(nome);
    }

    @Override
    public void interagir(Jogador jogador) {
        if (jogador.getTempo() >= 3) {
            jogador.modificarTempo(-3);
            jogador.consequencia("Motivação", 10);

            this.modificarRelacao(1);
        }
    }
}