package PBL.model;

import PBL.model.academico.Disciplina;
import PBL.model.academico.Historico;
import PBL.model.locais.Local;

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

    public Jogador(String nome) {
        this.nome = nome;
        this.dinheiro = 50;
        this.score = 0;
        this.tempo = 100;
        this.semestre = 1;
        this.cansado = false;

        this.status = new ArrayList<>();
        this.historico = new Historico();

        status.add(new Atributo("Energia", 100, 0, 100));
        status.add(new Atributo("Saúde", 100, 0, 100));
        status.add(new Atributo("Motivação", 100, 0, 100));
        status.add(new Atributo("Conhecimento", 0, 0, 100));
        status.add(new Atributo("Desempenho Acadêmico", 0, 0, 100));
        //a porcentagem do desempenho acadêmico será a média das porcentagens de desempenho por materia
    }

    public boolean modificarDinheiro(double valor) {
        if (valor < 0 && this.dinheiro < valor*(-1)) {
            return false;
        } else {
            this.dinheiro += valor;
            return true;
        }
    }

    public boolean modificarTempo(int valor) {
        if (valor < 0 && this.tempo < valor*(-1)) {
            return false;
        } else {
            this.tempo += valor;
            return true;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public int getDinheiro() {
        return this.dinheiro;
    }

    public int getTempo() {
        return this.tempo;
    }

    public double getScore() {
        return this.score;
    }

    public int getSemestre() {
        return this.semestre;
    }

    public Historico getHistorico(){
        return this.historico;
    }

    public void consequencia(String nome, int modificador) {
        for (Atributo atributo : status) {
            if (atributo.getNome().equals(nome)) {
                atributo.modificar(modificador);
            }
        }
    }

    public void atualiDesempenho() {
        List<Disciplina> materias = this.historico.getCursando();

        if (materias.isEmpty()) {
            return;
        }

        int soma = 0;

        for (Disciplina d : materias) {
            soma += d.getDesempenho();
        }

        int media = soma / materias.size();

        for (Atributo atributo : status) {
            if (atributo.getNome().equals("Desempenho Acadêmico")) {
                atributo.setValor(media);
            }
        }
    }

    public void avancaSemestre(){
        this.historico.fimSemestre();
        this.semestre ++;
    }

    public boolean isCansado(){
        return this.cansado;
    }

    public void setCansado(boolean estudouDeMatrugada){
        this.cansado = estudouDeMatrugada;
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