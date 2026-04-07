package PBL.model.tasks;

import PBL.model.model.Jogador;
import PBL.model.evento.*;

public class Bandejar extends Atividade{

    public Bandejar() {
        super("Ir ao Bandejão", "...");
    }

    @Override
    public boolean executar(Jogador jogador) {

        if(jogador.getTempo() >= 3 && jogador.getDinheiro() >= 2){
            jogador.modificarDinheiro(-2); //gasta 2 reais
            jogador.modificarTempo(-3); //gasta 3 pontos de tempo

            EventoAleatorio fila = new FilaGigante();
            EventoAleatorio passarMal = new PassarMal();

            boolean filaB = fila.tentarAtivar();
            boolean passarMalB = passarMal.tentarAtivar();

            if(filaB){
                fila.eventoConsequencia(jogador);
                jogador.consequencia("Energia", 30);
                jogador.consequencia("Saúde", 10);
            }
            if(passarMalB){
                passarMal.eventoConsequencia(jogador);
            }

            if (!filaB && !passarMalB){
                jogador.consequencia("Energia", 30);
                jogador.consequencia("Saúde", 10);
                jogador.consequencia("Motivação", 10);
            }
            return true;
        }
        return false;

    }
}
