package PBL.fase_1.model;

/**Essa classe é uma uma classe anêmica utilizada apenas para armazenar e enviar informações essenciais do jogo*/

public class Jogo {
    private int slot;
    private Jogador jogador;
    private transient Mapa mapa;
    private boolean finalizado;

    public Jogo(int slot, Jogador jogador, Mapa mapa) {
        this.slot = slot;
        this.jogador = jogador;
        this.mapa = mapa;
        this.finalizado = false;
    }

    public int getSlot(){
        return this.slot;
    }
    public void setSlot(int slot){
        this.slot = slot;
    }

    public Jogador getJogador(){
        return this.jogador;
    }
    public void setJogador(Jogador jogador){
        this.jogador = jogador;
    }

    public Mapa getMapa(){
        return this.mapa;
    }
    public void setMapa(Mapa mapa){
        this.mapa = mapa;
    }

    public boolean isFinalizado(){
        return this.finalizado;
    }
    public void setFinalizado(boolean fim){
        this.finalizado = fim;
    }
}