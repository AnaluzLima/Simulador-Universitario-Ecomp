package PBL.model.evento;
import PBL.model.model.Jogador;

public class FilaGigante extends EventoAleatorio{
    public FilaGigante() {
        super("Fila Gigante", "...", 0.30);
    }

    @Override
    public void eventoConsequencia(Jogador jogador) {
        jogador.modificarTempo(-5);
        jogador.consequencia("Motivação", -10);
    }
}
