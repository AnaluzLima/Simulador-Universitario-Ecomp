package PBL.fase_1.model;

import PBL.fase_1.model.minigames.Minigame;

import java.util.ArrayList;
import java.util.List;

/**Essa classe tem como objetivo criar uma disciplina que o jogador terá que cursar.
 * ela terá seus requisitos, sua nota, carga horaria, local que será ministrada, etc.*/

public class Disciplina {
    private final String nome;
    private final Minigame minigame; //tipo de minigame: "Texto", "Matemática", "Hardware" ou "Sotfware"
    private final Local sala;
    private double nota;
    private int desempenho;
    private final int cargaHoraria;
    private boolean provaFeita;
    private boolean milagreAcademico;
    private boolean provaSurpresaAtiva;

    private List<Disciplina> preRequisitos;
    private List<Disciplina> coRequisitos;

    private final int preRequisitoTempo;


    public Disciplina(String nome, Minigame minigame, int cargaHoraria, int preRequisitoTempo, Local sala) {
        this.nome = nome;
        this.minigame = minigame;
        this.nota = 0.0;
        this.desempenho = 0;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitoTempo = preRequisitoTempo;
        this.sala = sala;

        this.preRequisitos = new ArrayList<>();
        this.coRequisitos = new ArrayList<>();
        this.provaFeita = false;
        this.milagreAcademico = false;
        this.provaSurpresaAtiva = false;

    }

    public void registrarNota(double pontuacao){
        double notaFinal = pontuacao;

        if(this.milagreAcademico){ //se a disciplina está no estado de milagre acadêmico, ele tem um bônus na nota
            notaFinal += 3;
            if (notaFinal > 10){
                notaFinal = 10;
            }
        }
        this.nota = notaFinal;
        this.provaFeita = true;
        this.provaSurpresaAtiva = false;
    }

    public void modificarDesempenho(int desempenho){
        if (this.desempenho + desempenho > 100) { //assim como os atributos, as diciplinas tratam seu desempenho em %
            this.desempenho = 100;
        }
        else if (this.desempenho + desempenho < 0) {
            this.desempenho = 0;
        }
        else{
            this.desempenho += desempenho;
        }
    }

    public String getNome(){
        return this.nome;
    }
    public Minigame getMinigame(){
        return this.minigame;
    }

    public Local getSala(){
        return this.sala;
    }

    public double getNota(){
        return this.nota;
    }
    public void setNota(double nota){
        this.nota = nota;
    }

    public int getDesempenho(){
        return this.desempenho;
    }
    public void setDesempenho(int desempenho){
        this.desempenho = desempenho;
    }

    public boolean isProvaFeita() {
        return this.provaFeita;
    }
    public void setProvaFeita(boolean provaFeita) {
        this.provaFeita = provaFeita;
    }

    public boolean isMilagreAcademico(){
        return this.milagreAcademico;
    }
    public void setMilagreAcademico(boolean milagre){
        this.milagreAcademico = milagre;
    }

    public int getPreRequisitoTempo(){
        return this.preRequisitoTempo;
    }

    public int getCargaHoraria(){
        return this.cargaHoraria;
    }

    public void addPreRequisito(Disciplina d) {
        this.preRequisitos.add(d);
    }

    public void addCoRequisito(Disciplina d) {
        this.coRequisitos.add(d);
    }

    public List<Disciplina> getPreRequisitos() {
        return new ArrayList<>(preRequisitos);
    }
    public List<Disciplina> getCoRequisitos() {
        return new ArrayList<>(coRequisitos);
    }

    public boolean isProvaSurpresaAtiva() {
        return this.provaSurpresaAtiva;
    }
    public void setProvaSurpresaAtiva(boolean status) {
        this.provaSurpresaAtiva = status;
    }

}


