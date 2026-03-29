package PBL.model.academico;

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

        carregarHistorico();
    }

    private void carregarHistorico(){

        //1º
        Disciplina exa614 = new Disciplina("Tópicos de Formação Humanística", 60, 0, "Salas de Aula do Módulo 5");
        Disciplina exa854 = new Disciplina("MI Algoritmos", 60,0, "Salas de Aula do Módulo 5");
        Disciplina exa861 = new Disciplina("Introdução à Engenharia de Computação", 30,0, "Pavilhão");
        Disciplina exa801 = new Disciplina("Algoritmos e Programação I", 60,0, "Pavilhão");
        Disciplina exa543 = new Disciplina("Introdução ao Cálculo", 60,0, "Salas de Aula do Módulo 5");
        Disciplina tec497 = new Disciplina("Introdução à Eletrônica", 30,0, "LEDS");
        Disciplina tec503 = new Disciplina("Produção de Textos Técnicos e Acadêmicos", 60,0, "Salas de Aula do Módulo 3");
        exa854.addCoRequisito(exa801); exa801.addCoRequisito(exa854);

        cursando.addAll(java.util.Arrays.asList(exa614, exa854, exa861, exa801, exa543, tec497, tec503));

        //2º
        Disciplina exa704 = new Disciplina("Cálculo Diferencial e Integral I E", 60,0, "Pavilhão");
        Disciplina exa806 = new Disciplina("Estrutura de Dados", 60,0, "Salas de Aula do Módulo 5");
        Disciplina tec221 = new Disciplina("Tópicos de Formação Complementar", 60,0, "Salas de Aula do Módulo 3");
        Disciplina tec401 = new Disciplina("Circuitos Digitais", 30,0, "Salas de Aula do Módulo 3");
        Disciplina tec498 = new Disciplina("MI Projeto de Circuitos Digitais", 60,0, "LEDS");
        exa704.addPreRequisito(exa543); exa806.addPreRequisito(exa854); exa806.addPreRequisito(exa801); tec401.addPreRequisito(tec401);
        tec401.addCoRequisito(tec498); tec498.addPreRequisito(tec497); tec498.addPreRequisito(tec503); tec498.addCoRequisito(tec401);

        //3º
        Disciplina exa417 = new Disciplina("Química Geral e de Materiais", 45,0, "Salas de Aula do Módulo 5");
        Disciplina exa702 = new Disciplina("Álgebra Vetorial e Geometria Analítica", 60,0, "Salas de Aula do Módulo 4");
        Disciplina exa705 = new Disciplina("Cálculo Diferencial e Integral II E", 60,0, "Salas de Aula do Módulo 5");
        Disciplina exa807 = new Disciplina("Estruturas Discretas", 60,0, "Salas de Aula do Módulo 5");
        Disciplina exa863 = new Disciplina("MI Programação", 30,0, "Salas de Aula do Módulo 5");
        Disciplina exa805 = new Disciplina("Algoritmos e Programação II", 60,0, "Salas de Aula do Módulo 5");
        exa705.addPreRequisito(exa704); exa807.addPreRequisito(exa806); exa807.addCoRequisito(exa863); exa807.addCoRequisito(exa805);
        exa863.addPreRequisito(exa806); exa863.addPreRequisito(tec503); exa863.addCoRequisito(exa805); exa863.addCoRequisito(exa807);
        exa805.addPreRequisito(exa806); exa805.addCoRequisito(exa863); exa805.addCoRequisito(exa807);

        //4º
        Disciplina exa706 = new Disciplina("Equações Diferenciais I E", 60,0, "Salas de Aula do Módulo 5");
        Disciplina fis110 = new Disciplina("Física I", 90,0, "Salas de Aula do Módulo 4");
        Disciplina tec402 = new Disciplina("Arquitetura de Computadores", 60,0, "Salas de Aula do Módulo 3");
        Disciplina tec408 = new Disciplina("Sistemas Operacionais", 60,0, "Salas de Aula do Módulo 3");
        Disciplina tec499 = new Disciplina("MI Sistemas Digitais", 60,0, "LEDS");
        exa706.addPreRequisito(exa705); fis110.addPreRequisito(exa704); tec402.addPreRequisito(tec401); tec402.addPreRequisito(tec498);
        tec402.addCoRequisito(tec499); tec408.addPreRequisito(exa863); tec408.addPreRequisito(exa805); tec499.addPreRequisito(tec401);
        tec499.addPreRequisito(tec498); tec499.addCoRequisito(tec402);

        //5º
        Disciplina exa703 = new Disciplina("Álgebra Linear I E", 60, 0, "Salas de Aula do Módulo 5");
        Disciplina fis310 = new Disciplina("Física III", 90, 0, "Pavilhão");
        Disciplina tec409 = new Disciplina("Redes de Computadores", 60, 0, "Salas de Aula do Módulo 3");
        Disciplina tec502 = new Disciplina("MI Concorrência e Conectividade", 60,0, "Salas de Aula do Módulo 3");
        Disciplina exa709 = new Disciplina("Probabilidade e Estatística I-E", 60,0, "Salas de Aula do Módulo 5");
        fis310.addPreRequisito(exa702); fis310.addPreRequisito(exa705); tec409.addPreRequisito(tec408); tec409.addCoRequisito(tec502);
        tec502.addPreRequisito(tec408); tec502.addCoRequisito(tec409); exa709.addPreRequisito(exa704);

        //6º
        Disciplina exa810 = new Disciplina("Banco de Dados", 30, 0, "Pavilhão");
        Disciplina exa613 = new Disciplina("MI Engenharia de Software", 60,0, "Pavilhão");
        Disciplina exa616 = new Disciplina("Empreendedorismo I", 60,60, "Salas de Aula do Módulo 3");
        Disciplina exa809 = new Disciplina("Engenharia de Software", 60,0, "Salas de Aula do Módulo 5");
        Disciplina tec217 = new Disciplina("Métodos Computacionais", 60,0, "Salas de Aula do Módulo 3");
        Disciplina tec218 = new Disciplina("Circuitos Elétricos", 60,0, "Salas de Aula do Módulo 3");
        exa810.addPreRequisito(exa863); exa810.addPreRequisito(exa805); exa810.addCoRequisito(exa613); exa810.addCoRequisito(exa809);
        exa613.addPreRequisito(exa863); exa613.addPreRequisito(exa805); exa613.addCoRequisito(exa809); exa613.addCoRequisito(exa810);
        exa809.addPreRequisito(exa863); exa809.addPreRequisito(exa805); exa809.addCoRequisito(exa613); exa809.addCoRequisito(exa810);
        tec217.addPreRequisito(exa703); tec217.addPreRequisito(exa706);
        tec218.addPreRequisito(tec497); tec218.addPreRequisito(exa706); tec218.addPreRequisito(fis310);

        //7º
        Disciplina tec412 = new Disciplina("Sinais e Sistemas", 60,0, "Salas de Aula do Módulo 3");
        Disciplina tec506 = new Disciplina("MI Projetos de Circuitos Eletrônicos", 30,0, "LEDS");
        Disciplina tec219 = new Disciplina("Eletrônica Geral", 30,0, "Salas de Aula do Módulo 3");
        tec412.addPreRequisito(tec217); tec506.addPreRequisito(exa706); tec506.addPreRequisito(fis310); tec506.addPreRequisito(tec497);
        tec506.addPreRequisito(tec218); tec506.addCoRequisito(tec219); tec219.addPreRequisito(exa706); tec219.addPreRequisito(fis310);
        tec219.addPreRequisito(tec497); tec219.addPreRequisito(tec218); tec219.addCoRequisito(tec506);

        //8º
        Disciplina exa615 = new Disciplina("Ética em Computação", 60,0, "Salas de Aula do Módulo 4");
        Disciplina tec430 = new Disciplina("Processamento Digital de Sinais", 30,0, "Salas de Aula do Módulo 3");
        Disciplina tec513 = new Disciplina("MI Processamento Digital de Sinais", 60,0, "Salas de Aula do Módulo 3");
        Disciplina tec517 = new Disciplina("Metodologia da Pesquisa e Desenvolvimento em Engenharia de Computação", 60,70, "Salas de Aula do Módulo 3");
        Disciplina tec501 = new Disciplina("Eletrônica para Processamento Digital de Sinais", 30,0, "Salas de Aula do Módulo 3");
        tec430.addPreRequisito(tec401); tec430.addPreRequisito(tec412); tec430.addCoRequisito(tec513); tec430.addCoRequisito(tec501);
        tec513.addPreRequisito(tec401); tec513.addPreRequisito(tec412); tec513.addCoRequisito(tec430); tec513.addCoRequisito(tec501);
        tec501.addPreRequisito(tec219); tec501.addCoRequisito(tec513); tec501.addCoRequisito(tec430);

        //9º
        Disciplina exa544 = new Disciplina("Linguagens Formais e Compiladores", 60,0, "Salas de Aula do Módulo 5");
        Disciplina exa813 = new Disciplina("Análise e Projeto de Algoritmos", 60,0, "Salas de Aula do Módulo 5");
        Disciplina tec518 = new Disciplina("Trabalho de Conclusão de Curso I", 60,0, "Salas de Aula do Módulo 3");
        Disciplina exa622 = new Disciplina("Extensão em Computação I", 90,20, "Salas de Aula do Módulo 5");
        exa544.addPreRequisito(exa863); exa544.addPreRequisito(exa805); exa813.addPreRequisito(exa705); exa813.addPreRequisito(exa807);
        tec518.addPreRequisito(tec517);

        //10°
        Disciplina exa612 = new Disciplina("Estágio", 255, 40, "");
        Disciplina tec222 = new Disciplina("Trabalho de Conclusão de Curso II", 60, 0, "Salas de Aula do Módulo 3");
        Disciplina exa224 = new Disciplina("Extensão em Computação II", 90, 20, "Salas de Aula do Módulo 3");
        tec222.addPreRequisito(tec518);

        pendentes.addAll(java.util.Arrays.asList(
                exa704, exa806, tec221, tec401, tec498,
                exa417, exa702, exa705, exa807, exa863, exa805,
                exa706, fis110, tec402, tec408, tec499,
                exa703, fis310, tec409, tec502, exa709,
                exa810, exa613, exa616, exa809, tec217, tec218,
                tec412, tec506, tec219,
                exa615, tec430, tec513, tec517, tec501,
                exa544, exa813, tec518, exa622,
                exa612, tec222, exa224));

        aprovadas.clear();
    }

    public boolean matricular(Disciplina materia){
        boolean ap, curs;
        ap = aprovadas.contains(materia);
        curs = cursando.contains(materia);

        if (ap || curs || (this.cargaHorariaTotal < materia.getPreRequisitoTempo()/100*CARGA_HORARIA_FINAL) ||
                (this.cargaHorariaSemestre + materia.getCargaHoraria()) > 600){
            return false;
        }
        else{
            for(Disciplina pre : materia.getPreRequisitos()){
                if (!(aprovadas.contains(pre))){
                    return false;
                }
            }
            for(Disciplina co : materia.getCoRequisitos()){
                if (!(aprovadas.contains(co)) && !(cursando.contains(co))){
                    return false;
                }
            }
        }

        cursando.add(materia);
        this.cargaHorariaSemestre += materia.getCargaHoraria();
        pendentes.remove(materia);
        return true;
    }

    public void fimSemestre(){
        for(Disciplina d : cursando){
            if (d.getNota() >= 7){
                aprovadas.add(d);
                this.cargaHorariaTotal += d.getCargaHoraria();
            }
            else{
                d.setNota(0);
                d.setDesempenho(0);
                d.setProvaFeita(false);
                pendentes.add(d);
            }
        }
        cursando.clear();
        this.cargaHorariaSemestre = 0;
        this.semestreGreve = false;
    }

    public List<Disciplina> getSemanaProva() {
        List<Disciplina> provasPendentes = new ArrayList<>();

        for (Disciplina d : this.cursando) {
            if (!d.isProvaFeita()) {
                provasPendentes.add(d);
            }
        }

        return provasPendentes;
    }

    public boolean formado(){
        if(cursando.isEmpty() && pendentes.isEmpty()){
            return true;
        }
        return false;
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
    public int getCargaHorariaTotal(){
        return this.cargaHorariaTotal;
    }
    public int getCargaHorariaSemestre(){
        return this.cargaHorariaSemestre;
    }
}
