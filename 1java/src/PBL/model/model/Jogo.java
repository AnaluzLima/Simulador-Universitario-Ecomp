package PBL.model.model;

public class Jogo {
    private int slot;
    private String data;
    private Jogador jogador;
    private Mapa mapa;

    public Jogo(int slot, Jogador jogador, Mapa mapa) {
        this.slot = slot;
        this.jogador = jogador;
        this.mapa = mapa;
        this.data = "Novo Jogo";
    }

    public int getSlot(){
        return this.slot;
    }
    public void setSlot(int slot){
        this.slot = slot;
    }

    public String getData(){
        return this.data;
    }
    public void setData(String data){
        this.data = data;
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
}