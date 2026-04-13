package PBL.fase_1.model;

import java.util.ArrayList;
import java.util.List;

/**Essa classe tem como objetivo armazenar as skins do jogador
 * A ideia é que no menu inicial, ao criar um novo jogo, o jogador possa escolher qual será seu personagem.
 * Ao longo do jogo, ele poderá completar missões para ganhar novos acessórios para seu personagem, ou comprar com dionheiro.*/

public class Aparencia {
    private final String skinBase; //sprite base do jogador
    private String acessorioAtual; //padrão, com mochila, sem mochila, etc
    private List<String> acessoriosDesbloqueados;

    public Aparencia(String skinBase) {
        this.skinBase = skinBase;
        this.acessorioAtual = "padrão";
        this.acessoriosDesbloqueados = new ArrayList<>();
        this.acessoriosDesbloqueados.add("padrão");
    }

    public String getSkinBase(){
        return this.skinBase;
    }

    public String getAcessorioAtual(){
        return this.acessorioAtual;
    }
    public void setAcessorioAtual(String acessorioAtual){
        this.acessorioAtual = acessorioAtual;
    }

    public void addAcessorio(String acessorio){
        if (!this.acessoriosDesbloqueados.contains(acessorio)) {
            this.acessoriosDesbloqueados.add(acessorio);
        }
    }

    public List<String> getAcessoriosDesbloqueados(){
        return new ArrayList<>(acessoriosDesbloqueados);
    }

    //futuramente adicionar um metodo que pega o arquivo da pasta de sprites
}