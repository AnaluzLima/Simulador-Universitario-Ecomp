package PBL.model.model.tasks;

import PBL.exception.DinheiroException;
import PBL.exception.JogoException;
import PBL.exception.TempoException;
import PBL.model.model.Jogador;
import PBL.model.model.evento.EventoAleatorio;
import PBL.model.model.evento.FilaGigante;
import PBL.model.model.evento.PassarMal;

public class Bandejar extends Atividade {

    //Indo comer no RU

    public Bandejar() {
        super("Ir ao Bandejão", "...");
    }

    @Override
    public void executar(Jogador jogador) throws JogoException {

        if (jogador.getDinheiro() < 2) {
            throw new DinheiroException("Saldo Insuficiente!");
        }
        if (jogador.getTempo() < 3){
            throw new TempoException("Você está sem tempo");
        }

        jogador.modificarDinheiro(-2);
        jogador.modificarTempo(-3);

        //verifica se ele pegou a fila gigante
        boolean pegouFila = false;

        EventoAleatorio filaGrande = new FilaGigante();
        EventoAleatorio passarMal = new PassarMal();

        try{
            pegouFila = filaGrande.tentarAtivar(jogador);
        }
        catch (TempoException e){
            jogador.setTempo(0); //se ele não tinha tempo o suficiente, em vez de cancelar a ação, vai zerar os pontos
            pegouFila = true;
        }

        boolean passouMal = passarMal.tentarAtivar(jogador); //veifica se passou mal

        if (!passouMal){ //se não passou mal
            jogador.getEnergia().modificar(30);
            jogador.getSaude().modificar(10);
            jogador.getMotivacao().modificar(10);
        }
    }
}