package PBL.model.model;

/**Essa classe é uma uma classe anêmica utilizada apenas para armazenar e enviar informações essenciais do jogo*/

public class Jogo {
    private String slot;
    private Jogador jogador;
    private transient Mapa mapa;
    private boolean finalizado;

    public Jogo(String slot, Jogador jogador, Mapa mapa) {
        this.slot = slot;
        this.jogador = jogador;
        this.mapa = mapa;
        this.finalizado = false;
    }

    public String getSlot(){
        return this.slot;
    }
    public void setSlot(String slot){
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