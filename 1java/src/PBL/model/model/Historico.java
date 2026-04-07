package PBL.model.model;

import java.util.ArrayList;
import java.util.List;

public class Historico {
    private int cargaHorariaTotal;
    private static final int CARGA_HORARIA_FINAL = 2970;
    private int cargaHorariaSemestre;
    private List<Disciplina> cursando;
    private List<Disciplina> aprovadas;
    private List<Disciplina> pendentes;
    private boolean semestreGreve;

    public Historico(){
        this.pendentes = new ArrayList<>();
        this.cursando = new ArrayList<>();
        this.aprovadas = new ArrayList<>();
        this.cargaHorariaTotal = 0;
        this.cargaHorariaSemestre = 360;
        this.semestreGreve = false;

    }


    public boolean isSemestreGreve() {
        return this.semestreGreve;
    }

    public void setSemestreGreve(boolean estado) {
        this.semestreGreve = estado;
    }

    public List<Disciplina> getCursando(){
        return this.cursando;
    }
    public List<Disciplina> getAprovadas(){
        return this.aprovadas;
    }
    public List<Disciplina> getPendentes(){
        return this.pendentes;
    }

    public int getCargaHorariaTotal() { return cargaHorariaTotal; }
    public void setCargaHorariaTotal(int cargaHorariaTotal) { this.cargaHorariaTotal = cargaHorariaTotal; }

    public int getCargaHorariaSemestre() { return cargaHorariaSemestre; }
    public void setCargaHorariaSemestre(int cargaHorariaSemestre) { this.cargaHorariaSemestre = cargaHorariaSemestre; }

    public int getCargaHorariaFinal(){
        return CARGA_HORARIA_FINAL;
    }
}
