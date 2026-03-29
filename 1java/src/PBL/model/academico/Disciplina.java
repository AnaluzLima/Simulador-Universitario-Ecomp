package PBL.model.academico;

import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private String nomeLocal;
    private double nota;
    private int desempenho;
    private int cargaHoraria;
    private boolean provaFeita;

    private List<Disciplina> preRequisitos;
    private List<Disciplina> coRequisitos;

    private int preRequisitoTempo;


    public Disciplina(String nome, int cargaHoraria, int preRequisitoTempo, String nomeLocal) {
        this.nome = nome;
        this.nota = 0.0;
        this.desempenho = 0;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitoTempo = preRequisitoTempo;
        this.nomeLocal = nomeLocal;

        this.preRequisitos = new ArrayList<>();
        this.coRequisitos = new ArrayList<>();
        this.provaFeita = false;

    }

    public String getNome(){
        return this.nome;
    }

    public String getNomeLocal(){
        return this.nomeLocal;
    }

    public double getNota(){
        return this.nota;
    }

    public int getDesempenho(){
        return this.desempenho;
    }

    public boolean isProvaFeita() {
        return this.provaFeita;
    }

    public void setProvaFeita(boolean provaFeita) {
        this.provaFeita = provaFeita;
    }

    public int getPreRequisitoTempo(){
        return this.preRequisitoTempo;
    }

    public int getCargaHoraria(){
        return this.cargaHoraria;
    }

    public void registrarNota(double pontuacaoMinigame){
        double notaFinal = pontuacaoMinigame;

        if(this.getDesempenho() > 95){
            if (notaFinal < 10){
                notaFinal += 3;
                if(notaFinal > 10) {
                    notaFinal = 10;
                }
            }
        }
        this.setNota(notaFinal);
    }

    public void setNota(double nota){
        this.nota = nota;
    }

    public void setDesempenho(int desempenho){
        this.desempenho = desempenho;
    }

    public void modificarDesempenho(int desempenho){
        this.desempenho += desempenho;

        if (this.desempenho > 100) {
            this.desempenho = 100;
        } else if (this.desempenho < 0) {
            this.desempenho = 0;
        }
    }

    public void addPreRequisito(Disciplina d) {
        this.preRequisitos.add(d);
    }

    public void addCoRequisito(Disciplina d) {
        this.coRequisitos.add(d);
    }

    public List<Disciplina> getPreRequisitos() {
        return this.preRequisitos;
    }
    public List<Disciplina> getCoRequisitos() {
        return this.coRequisitos;
    }

}


