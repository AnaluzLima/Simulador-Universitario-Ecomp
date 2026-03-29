package PBL.model.locais;
import PBL.model.tasks.*;
import PBL.model.Jogador;
import PBL.model.academico.Disciplina;


import java.util.ArrayList;
import java.util.List;

public class Mapa {
    private List<Local> campus;
    private Local spawn;

    public Mapa(boolean spawnF6) {
        this.campus = new ArrayList<>();
        carregarMapa(spawnF6);
    }

    private void carregarMapa(boolean spawnF6){
        Local ponto1 = new Local("Ponto de Ônibus M5-M6");
        Local ponto2 = new Local("Ponto de Ônibus M3-M4");
        Local ponto3 = new Local("Ponto de Ônibus M1-M2");

        Local pracaPorDoSol = new Local("Praça do Pôr do Sol");
        Local pracaBorogodo = new Local("Praça do Borogodó");
        Local quadra = new Local("Quadra");
        Local portaoLateral = new Local("Portão Lateral");

        Local biblioteca = new Local("Biblioteca");
        Local bandejao = new Local("Bandejão");
        Local colegiado = new Local("Colegiado");
        Local feirinha = new Local("Feirinha");
        Local leds = new Local("LEDS");
        Local cantina1 = new Local("Cantina do Módulo 1");
        Local cantina3 = new Local("Cantina do Módulo 3");
        Local cantina5 = new Local("Cantina do Módulo 5");
        Local cantina7 = new Local("Cantina do Módulo 7");

        Local pav = new Local("Pavilhão");

        Local modulo1 = new Local("Módulo 1");
        Local modulo2 = new Local("Módulo 2");
        Local modulo3 = new Local("Módulo 3");
        Local modulo4 = new Local("Módulo 4");
        Local modulo5 = new Local("Módulo 5");
        Local modulo6 = new Local("Módulo 6");
        Local modulo7 = new Local("Módulo 7");

        Local pats1 = new Local("Salas de Aula do Módulo 1");
        Local pats2 = new Local("Salas de Aula do Módulo 2");
        Local pats3 = new Local("Salas de Aula do Módulo 3");
        Local pats4 = new Local("Salas de Aula do Módulo 4");
        Local pats5 = new Local("Salas de Aula do Módulo 5");
        Local pats6 = new Local("Salas de Aula do Módulo 6");
        Local pats7 = new Local("Salas de Aula do Módulo 7");

        if(spawnF6) {
            this.spawn = portaoLateral;
            portaoLateral.addAtividade(new CasaDormir()); portaoLateral.addAtividade(new CasaEstudar());
        } else {
            this.spawn = ponto1;
            ponto1.addAtividade(new CasaDormir()); ponto1.addAtividade(new CasaEstudar());
            ponto2.addAtividade(new CasaDormir()); ponto2.addAtividade(new CasaEstudar());
            ponto3.addAtividade(new CasaDormir()); ponto3.addAtividade(new CasaEstudar());
        }

        ponto1.conectar(modulo7); ponto1.conectar(modulo6); ponto1.conectar(modulo5); ponto1.conectar(biblioteca);
        ponto2.conectar(modulo4); ponto2.conectar(modulo3); ponto2.conectar(bandejao);
        ponto3.conectar(modulo2); ponto3.conectar(modulo1); ponto3.conectar(feirinha);

        modulo1.conectar(modulo2); modulo1.conectar(cantina1); modulo1.conectar(pats1);
        modulo2.conectar(modulo3); modulo2.conectar(feirinha); modulo2.conectar(pats2);
        modulo3.conectar(modulo4); modulo3.conectar(cantina3); modulo3.conectar(pracaBorogodo); modulo3.conectar(leds); modulo3.conectar(pats3);
        modulo4.conectar(modulo5); modulo4.conectar(biblioteca); modulo4.conectar(pats4);
        modulo5.conectar(modulo6); modulo5.conectar(cantina5); modulo5.conectar(biblioteca); modulo5.conectar(pav); modulo5.conectar(pats5);
        modulo6.conectar(modulo7); modulo6.conectar(pats6);
        modulo7.conectar(cantina7); modulo7.conectar(pracaPorDoSol); modulo7.conectar(pats7);

        feirinha.conectar(bandejao);
        colegiado.conectar(quadra); colegiado.conectar(pav); colegiado.conectar(pats3);
        portaoLateral.conectar(bandejao); portaoLateral.conectar(biblioteca); portaoLateral.conectar(modulo3);

        this.campus.addAll(java.util.Arrays.asList(
                ponto1, ponto2, ponto3, pracaPorDoSol, pracaBorogodo, quadra, biblioteca, bandejao, feirinha, leds,
                portaoLateral, colegiado,
                cantina1, cantina3, cantina5, cantina7,
                modulo1, modulo2, modulo3, modulo4, modulo5, modulo6, modulo7, pav,
                pats1, pats2, pats3, pats4, pats5, pats6, pats7
        ));

        //------------------------------------------------------------------------------------------------------------
        bandejao.addAtividade(new Bandejar());
        cantina1.addAtividade(new Cantinar()); cantina3.addAtividade(new Cantinar()); cantina5.addAtividade(new Cantinar()); cantina7.addAtividade(new Cantinar());
        portaoLateral.addAtividade(new darRole(false));
        biblioteca.addAtividade(new EstudarBiblioteca(false));
        colegiado.addAtividade(new FalarComMaeli());
        pav.addAtividade(new AssistirAula());
        pracaBorogodo.addAtividade(new Relaxar()); pracaPorDoSol.addAtividade(new Relaxar());
        feirinha.addAtividade(new ComprasFeirinha());
        quadra.addAtividade(new PraticarEsporte());

    }

    public Local getLocalNome(String nome) {
        for (Local l : this.campus) {
            if (l.getNome().equals(nome)) {
                return l;
            }
        }
        return null; //se não achar o local
    }

    public void distribuirAulas(Jogador jogador) {
        for (Disciplina materia : jogador.getHistorico().getCursando()) {

            Local sala = getLocalNome(materia.getNomeLocal());

            if (sala != null) {
                AssistirAula aula = new AssistirAula();
                aula.setMateria(materia);

                sala.addAtividade(aula);
            }
        }
    }

}
