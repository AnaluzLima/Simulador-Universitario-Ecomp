package PBL.model.model;

import PBL.exception.MatriculaException;

import java.util.*;

/**Essa classe tem como objetivo ser o que vai definir o semestre do jogador*/

public class Historico {
    private int cargaHorariaTotal; //total de horas concluidas pelo jogador
    private static final int CARGA_HORARIA_FINAL = 2970; //carga horaria fixa maxima do curso
    private int cargaHorariaSemestre; //carga horaria do semestre que o jogador está cursando
    private Set<Disciplina> cursando;
    private Set<Disciplina> aprovadas;
    private Set<Disciplina> pendentes;
    private boolean semestreGreve;

    //para poder calcular o score
    private double somaNotasGeral;
    private int totalDisciplinasCursadas;
    private double score;

    public Historico(){
        this.pendentes = new HashSet<>();
        this.cursando = new HashSet<>();
        this.aprovadas = new HashSet<>();
        this.cargaHorariaTotal = 0;
        this.cargaHorariaSemestre = 0;
        this.semestreGreve = false;
        this.somaNotasGeral = 0;
        this.totalDisciplinasCursadas = 0;
        this.score = 0;

    }

    public double getScore() {
        return this.score;
    }

    public boolean isSemestreGreve() {
        return this.semestreGreve;
    }

    public void setSemestreGreve(boolean estado) {
        this.semestreGreve = estado;
    }

    public Set<Disciplina> getCursando(){
        return this.cursando;
    }
    public Set<Disciplina> getAprovadas(){
        return this.aprovadas;
    }
    public Set<Disciplina> getPendentes(){
        return this.pendentes;
    }

    public void adicionarPendente(Disciplina d) {
        this.pendentes.add(d);
    }

    public int getCargaHorariaTotal(){
        return cargaHorariaTotal;
    }
    public void setCargaHorariaTotal(int cargaHorariaTotal){
        this.cargaHorariaTotal = cargaHorariaTotal;
    }

    public int getCargaHorariaSemestre(){
        return cargaHorariaSemestre;
    }
    public void setCargaHorariaSemestre(int cargaHorariaSemestre){
        this.cargaHorariaSemestre = cargaHorariaSemestre;
    }

    public int getCargaHorariaFinal(){
        return CARGA_HORARIA_FINAL;
    }

    public void matricular(List<Disciplina> materiasSelecionadas) throws MatriculaException {
        //recebe a lista de materias que o jogador selecionou para cursar e confere se ele pode
        int horas = this.cargaHorariaSemestre;

        for (Disciplina d : materiasSelecionadas) {
            horas += d.getCargaHoraria();

            //caso não possa, cai num except
            if (!podeCursar(d)) {
                throw new MatriculaException("Você não tem os pré-requisitos para cursar " + d.getNome() + ".");
            }

            //confere se ele também selecionou o pré requisito da matéria que quer cursar
            for (Disciplina co : d.getCoRequisitos()) {
                if (!this.aprovadas.contains(co) && !materiasSelecionadas.contains(co)) {
                    throw new MatriculaException("Para cursar " + d.getNome() + ", você precisa selecionar também " + co.getNome() + " (Co-requisito).");
                }
            }
        }

        //a carga horaria maxima do semestre é de 600 horas.
        if (horas > 600) {
            throw new MatriculaException("Carga horária máxima do semestre excedida! O limite é de 600h.");
        }

        //se chegou até aqui, tudo certo, já pode matricular o jogador nas matérias selecionadas
        for (Disciplina d : materiasSelecionadas) {
            this.cursando.add(d);
            this.pendentes.remove(d);
            this.cargaHorariaSemestre += d.getCargaHoraria();
        }
    }

    public boolean podeCursar(Disciplina materia) {
        //para cursar uma matéria, o jogador não pode já estar cursando ela, nem ter sido aprovado nela.
        if (this.aprovadas.contains(materia) || this.cursando.contains(materia)) {
            return false;
        }

        //a soma da carga horaria total cumprida pelo jogador tem que ser >= a exigida pela materia
        if (this.cargaHorariaTotal < (materia.getPreRequisitoTempo() / 100.0 * CARGA_HORARIA_FINAL)) {
            return false;
        }

        //a materia não deve ter pre requisitos não concluidos
        for (Disciplina pre : materia.getPreRequisitos()) {
            if (!this.aprovadas.contains(pre)) {
                return false;
            }
        }

        return true;
    }

    public void fimSemestre(){
        //pega todas as materias que o jogador está cursando, verifica a nota, e o aprova ou reprova
        for (Disciplina d : this.cursando) {
            this.somaNotasGeral += d.getNota();
            this.totalDisciplinasCursadas++;

            if (d.getNota() >= 7) {
                this.aprovadas.add(d); //adiciona a matéria em aprovadas
                this.cargaHorariaTotal += d.getCargaHoraria(); //soma a carga horaria da matéria a carga horaria total
            }
            else {
                //se o jogador não foi aprovado, todos os atributos da disciplina são resetados
                d.setNota(0);
                d.setDesempenho(0);
                d.setProvaFeita(false);
                d.setMilagreAcademico(false);
                this.pendentes.add(d);
            }
        }

        //calcula a média para atualizar o score
        if(this.totalDisciplinasCursadas > 0){
            this.score = this.somaNotasGeral / this.totalDisciplinasCursadas;
        }

        this.cursando.clear(); //limpa a grade de materias sendo cursadas
        this.cargaHorariaSemestre = 0;
        this.semestreGreve = false;
    }

    public List<Disciplina> getSemanaProva() { //decide quais provas estarão preesentes na semana de provas
        //esse metodo foi criado pois caso voce faça uma prova surpresa de uma matéria, não vai precisar fazer a prova de novo depois
        List<Disciplina> provasPendentes = new ArrayList<>();
        for (Disciplina d : this.cursando) {
            if (!d.isProvaFeita()) {
                provasPendentes.add(d);
            }
        }
        return new ArrayList<>(provasPendentes);
    }

    public boolean isFormado() {
        return this.cursando.isEmpty() && this.pendentes.isEmpty();
    }

    public void adicionarDisciplinasCursando(List<Disciplina> disciplinas) {
        this.cursando.addAll(disciplinas);
    }

}

