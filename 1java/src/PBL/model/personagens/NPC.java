package PBL.model.personagens;

import PBL.model.Jogador;
import PBL.model.locais.Local;

public abstract class NPC {
    private String nome;
    private Local localizacao;
    private int relacao;

    public NPC(String nome){
        this.nome = nome;
        this.relacao = 0;
    }

    public abstract void interagir(Jogador jogador);

    public void setLocalizacao(Local local){
        this.localizacao = local;
    }

    public void modificarRelacao(int modificar){
        this.relacao += modificar;
        if (this.relacao <0){
            this.relacao = 0;
        }
        else if(this.relacao > 10){
            this.relacao = 10;
        }
    }

    public int getRelacao(){
        return this.relacao;
    }

    public String getNome(){
        return this.nome;
    }

    public Local getLocalizacao(){
        return this.localizacao;
    }

}
