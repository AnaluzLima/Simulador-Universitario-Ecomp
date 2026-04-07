package PBL.model.model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;

    private double score;
    private int tempo, semestre, dinheiro;
    private boolean cansado, fazendoProva;

    private List<Atributo> status;
    private Local localizacao;
    private Historico historico;

    private Celular celular;
    private Aparencia skin;

    public Jogador(String nome, Aparencia skin) {
        this.nome = nome;
        this.dinheiro = 50;
        this.score = 0;
        this.tempo = 100;
        this.semestre = 1;
        this.cansado = false;

        this.status = new ArrayList<>();
        this.historico = new Historico();

        celular = new Celular();
        this.skin = skin;

        status.add(new Atributo("Energia", 100, 0, 100));
        status.add(new Atributo("Saúde", 100, 0, 100));
        status.add(new Atributo("Motivação", 100, 0, 100));
        status.add(new Atributo("Conhecimento", 0, 0, 100));
        status.add(new Atributo("Desempenho Acadêmico", 0, 0, 100));
        //a porcentagem do desempenho acadêmico será a média das porcentagens de desempenho por materia
    }

    public String getNome() {
        return this.nome;
    }

    public int getDinheiro() {
        return this.dinheiro;
    }

    public void setDinheiro(int valor){
        this.dinheiro = valor;
    }

    public int getTempo() {
        return this.tempo;
    }

    public void setTempo(int valor){
        this.tempo = valor;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double nota){
        this.score = nota;
    }

    public int getSemestre() {
        return this.semestre;
    }

    public void setSemestre(int n){
        this.semestre = n;
    }

    public Historico getHistorico(){
        return this.historico;
    }

    public boolean isCansado(){
        return this.cansado;
    }

    public void setCansado(boolean estudouMatrugada){
        this.cansado = estudouMatrugada;
    }

    public boolean isFazendoProva(){
        return this.fazendoProva;
    }

    public void setFazendoProva(boolean prova){
        this.fazendoProva = prova;
    }

    public List<Atributo> getStatus(){
        return this.status;
    }

}