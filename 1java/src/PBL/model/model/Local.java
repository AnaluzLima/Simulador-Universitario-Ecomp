package PBL.model.model;

import PBL.model.tasks.Atividade;
import java.util.ArrayList;
import java.util.List;

public class Local {
    private String nome;
    protected List<Local> conexoes;
    private List<Atividade> atvLocais;

    public Local(String nome){
        this.nome = nome;
        this.conexoes = new ArrayList<>();
        this.atvLocais = new ArrayList<>();
    }

    public void addAtividade(Atividade atividade) {
        this.atvLocais.add(atividade);
    }
    public String getNome() {
        return this.nome;
    }
    public List<Local> getConexoes() {
        return this.conexoes;
    }
    public List<Atividade> getAtvLocais(){
        return this.atvLocais;
    }
}