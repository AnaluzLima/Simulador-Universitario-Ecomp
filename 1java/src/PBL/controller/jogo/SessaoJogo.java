package PBL.controller.jogo;

import PBL.model.model.Jogo;

public class SessaoJogo {
    private static SessaoJogo instancia;
    private Jogo jogoAtivo;

    private SessaoJogo() {}

    public static SessaoJogo getInstancia() {
        if (instancia == null) {
            instancia = new SessaoJogo();
        }
        return instancia;
    }

    public void setJogoAtivo(Jogo jogo) {
        this.jogoAtivo = jogo;
    }

    public Jogo getJogoAtivo() {
        return this.jogoAtivo;
    }
}