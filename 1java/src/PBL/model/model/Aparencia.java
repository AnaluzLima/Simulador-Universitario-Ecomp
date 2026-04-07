package PBL.model.model;

import java.util.ArrayList;
import java.util.List;

public class Aparencia {
    private String skinBase; //sprite
    private String acessorioAtual; //padrão, com mochila, sem mochila, etc
    private List<String> acessoriosDesbloqueados;

    public Aparencia(String skinBase) {
        this.skinBase = skinBase;
        this.acessorioAtual = "padrao";
        this.acessoriosDesbloqueados = new ArrayList<>();
        this.acessoriosDesbloqueados.add("padrao");
    }

    public String getSkinBase(){
        return this.skinBase;
    }
    public void setskinBase(String skinBase){
        this.skinBase = skinBase;
    }

    public String getAcessorioAtual(){
        return this.acessorioAtual;
    }
    public void setAcessorioAtual(String acessorioAtual){
        this.acessorioAtual = acessorioAtual;
    }

    public void addAcessorio(String acessorio) {
        if (!this.acessoriosDesbloqueados.contains(acessorio)) {
            this.acessoriosDesbloqueados.add(acessorio);
        }
    }

    public List<String> getAcessoriosDesbloqueados() { return this.acessoriosDesbloqueados; }

    //futuramente adicionar um metodo que pega o arquivo da pasta se sprites
}