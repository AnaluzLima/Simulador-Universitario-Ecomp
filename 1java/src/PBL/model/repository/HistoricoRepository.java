package PBL.model.repository;

import PBL.model.model.Disciplina;
import PBL.model.model.Historico;
import PBL.model.model.Mapa;

import java.util.Set;

public class HistoricoRepository { //todas as disciplinas do curso
    public void carregarHistorico(Historico historico, Mapa mapa, MinigameRepository miniRepo) {
        //1º
        Disciplina exa614 = new Disciplina("Tópicos de Formação Humanística", miniRepo.buscarMinigamePorArea("Texto"), 60, 0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa854 = new Disciplina("MI Algoritmos",miniRepo.buscarMinigamePorArea("Software"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa861 = new Disciplina("Introdução à Engenharia de Computação", miniRepo.buscarMinigamePorArea("Texto"), 30,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa801 = new Disciplina("Algoritmos e Programação I", miniRepo.buscarMinigamePorArea("Software"),60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa543 = new Disciplina("Introdução ao Cálculo", miniRepo.buscarMinigamePorArea("Matemática"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina tec497 = new Disciplina("Introdução à Eletrônica", miniRepo.buscarMinigamePorArea("Hardware"), 30,0, mapa.buscarLocalPorNome("LEDS"));
        Disciplina tec503 = new Disciplina("Produção de Textos Técnicos e Acadêmicos", miniRepo.buscarMinigamePorArea("Texto"), 60,0, mapa.buscarLocalPorNome("Módulo 3"));
        exa854.addCoRequisito(exa801); exa801.addCoRequisito(exa854);

        historico.getCursando().addAll(java.util.Arrays.asList(exa614, exa854, exa861, exa801, exa543, tec497, tec503));

        //2º
        Disciplina exa704 = new Disciplina("Cálculo Diferencial e Integral I E", miniRepo.buscarMinigamePorArea("Matemática"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 2"));
        Disciplina exa806 = new Disciplina("Estrutura de Dados",miniRepo.buscarMinigamePorArea("Software"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina tec221 = new Disciplina("Tópicos de Formação Complementar",miniRepo.buscarMinigamePorArea("Texto"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec401 = new Disciplina("Circuitos Digitais",miniRepo.buscarMinigamePorArea("Hardware"), 30,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec498 = new Disciplina("MI Projeto de Circuitos Digitais",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("LEDS"));
        exa704.addPreRequisito(exa543); exa806.addPreRequisito(exa854); exa806.addPreRequisito(exa801); tec401.addPreRequisito(tec497);
        tec401.addCoRequisito(tec498); tec498.addPreRequisito(tec497); tec498.addPreRequisito(tec503); tec498.addCoRequisito(tec401);

        //3º
        Disciplina exa417 = new Disciplina("Química Geral e de Materiais",miniRepo.buscarMinigamePorArea("Matemática"), 45,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa702 = new Disciplina("Álgebra Vetorial e Geometria Analítica",miniRepo.buscarMinigamePorArea("Matemática"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 4"));
        Disciplina exa705 = new Disciplina("Cálculo Diferencial e Integral II E", miniRepo.buscarMinigamePorArea("Matemática"),60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa807 = new Disciplina("Estruturas Discretas", miniRepo.buscarMinigamePorArea("Matemática"),60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa863 = new Disciplina("MI Programação",miniRepo.buscarMinigamePorArea("Software"), 30,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa805 = new Disciplina("Algoritmos e Programação II", miniRepo.buscarMinigamePorArea("Software"),60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        exa705.addPreRequisito(exa704); exa807.addPreRequisito(exa806); exa807.addCoRequisito(exa863); exa807.addCoRequisito(exa805);
        exa863.addPreRequisito(exa806); exa863.addPreRequisito(tec503); exa863.addCoRequisito(exa805); exa863.addCoRequisito(exa807);
        exa805.addPreRequisito(exa806); exa805.addCoRequisito(exa863); exa805.addCoRequisito(exa807);

        //4º
        Disciplina exa706 = new Disciplina("Equações Diferenciais I E",miniRepo.buscarMinigamePorArea("Matemática"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina fis110 = new Disciplina("Física I",miniRepo.buscarMinigamePorArea("Matemática"), 90,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 4"));
        Disciplina tec402 = new Disciplina("Arquitetura de Computadores",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec408 = new Disciplina("Sistemas Operacionais",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec499 = new Disciplina("MI Sistemas Digitais",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("LEDS"));
        exa706.addPreRequisito(exa705); fis110.addPreRequisito(exa704); tec402.addPreRequisito(tec401); tec402.addPreRequisito(tec498);
        tec402.addCoRequisito(tec499); tec408.addPreRequisito(exa863); tec408.addPreRequisito(exa805); tec499.addPreRequisito(tec401);
        tec499.addPreRequisito(tec498); tec499.addCoRequisito(tec402);

        //5º
        Disciplina exa703 = new Disciplina("Álgebra Linear I E",miniRepo.buscarMinigamePorArea("Matemática"), 60, 0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina fis310 = new Disciplina("Física III",miniRepo.buscarMinigamePorArea("Matemática"), 90, 0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 4"));
        Disciplina tec409 = new Disciplina("Redes de Computadores",miniRepo.buscarMinigamePorArea("Hardware"), 60, 0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec502 = new Disciplina("MI Concorrência e Conectividade",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina exa709 = new Disciplina("Probabilidade e Estatística I-E",miniRepo.buscarMinigamePorArea("Matemática"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        fis310.addPreRequisito(exa702); fis310.addPreRequisito(exa705); tec409.addPreRequisito(tec408); tec409.addCoRequisito(tec502);
        tec502.addPreRequisito(tec408); tec502.addCoRequisito(tec409); exa709.addPreRequisito(exa704);

        //6º
        Disciplina exa810 = new Disciplina("Banco de Dados",miniRepo.buscarMinigamePorArea("Software"), 30, 0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa613 = new Disciplina("MI Engenharia de Software",miniRepo.buscarMinigamePorArea("Software"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa616 = new Disciplina("Empreendedorismo I",miniRepo.buscarMinigamePorArea("Texto"), 60,60, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina exa809 = new Disciplina("Engenharia de Software",miniRepo.buscarMinigamePorArea("Software"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina tec217 = new Disciplina("Métodos Computacionais",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec218 = new Disciplina("Circuitos Elétricos",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        exa810.addPreRequisito(exa863); exa810.addPreRequisito(exa805); exa810.addCoRequisito(exa613); exa810.addCoRequisito(exa809);
        exa613.addPreRequisito(exa863); exa613.addPreRequisito(exa805); exa613.addCoRequisito(exa809); exa613.addCoRequisito(exa810);
        exa809.addPreRequisito(exa863); exa809.addPreRequisito(exa805); exa809.addCoRequisito(exa613); exa809.addCoRequisito(exa810);
        tec217.addPreRequisito(exa703); tec217.addPreRequisito(exa706);
        tec218.addPreRequisito(tec497); tec218.addPreRequisito(exa706); tec218.addPreRequisito(fis310);

        //7º
        Disciplina tec412 = new Disciplina("Sinais e Sistemas",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec506 = new Disciplina("MI Projetos de Circuitos Eletrônicos",miniRepo.buscarMinigamePorArea("Hardware"), 30,0, mapa.buscarLocalPorNome("LEDS"));
        Disciplina tec219 = new Disciplina("Eletrônica Geral",miniRepo.buscarMinigamePorArea("Hardware"), 30,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        tec412.addPreRequisito(tec217); tec506.addPreRequisito(exa706); tec506.addPreRequisito(fis310); tec506.addPreRequisito(tec497);
        tec506.addPreRequisito(tec218); tec506.addCoRequisito(tec219); tec219.addPreRequisito(exa706); tec219.addPreRequisito(fis310);
        tec219.addPreRequisito(tec497); tec219.addPreRequisito(tec218); tec219.addCoRequisito(tec506);

        //8º
        Disciplina exa615 = new Disciplina("Ética em Computação",miniRepo.buscarMinigamePorArea("Texto"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 4"));
        Disciplina tec430 = new Disciplina("Processamento Digital de Sinais",miniRepo.buscarMinigamePorArea("Hardware"), 30,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec513 = new Disciplina("MI Processamento Digital de Sinais",miniRepo.buscarMinigamePorArea("Hardware"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec517 = new Disciplina("Metodologia da Pesquisa e Desenvolvimento em Engenharia de Computação",miniRepo.buscarMinigamePorArea("Texto"), 60,70, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina tec501 = new Disciplina("Eletrônica para Processamento Digital de Sinais",miniRepo.buscarMinigamePorArea("Hardware"), 30,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        tec430.addPreRequisito(tec401); tec430.addPreRequisito(tec412); tec430.addCoRequisito(tec513); tec430.addCoRequisito(tec501);
        tec513.addPreRequisito(tec401); tec513.addPreRequisito(tec412); tec513.addCoRequisito(tec430); tec513.addCoRequisito(tec501);
        tec501.addPreRequisito(tec219); tec501.addCoRequisito(tec513); tec501.addCoRequisito(tec430);

        //9º
        Disciplina exa544 = new Disciplina("Linguagens Formais e Compiladores",miniRepo.buscarMinigamePorArea("Software"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina exa813 = new Disciplina("Análise e Projeto de Algoritmos",miniRepo.buscarMinigamePorArea("Software"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        Disciplina tec518 = new Disciplina("Trabalho de Conclusão de Curso I",miniRepo.buscarMinigamePorArea("Texto"), 60,0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina exa622 = new Disciplina("Extensão em Computação I",miniRepo.buscarMinigamePorArea("Software"), 90,20, mapa.buscarLocalPorNome("Salas de Aula do Módulo 5"));
        exa544.addPreRequisito(exa863); exa544.addPreRequisito(exa805); exa813.addPreRequisito(exa705); exa813.addPreRequisito(exa807);
        tec518.addPreRequisito(tec517);

        //10°
        Disciplina exa612 = new Disciplina("Estágio",miniRepo.buscarMinigamePorArea("Software"), 255, 40, mapa.buscarLocalPorNome("Portão Lateral"));
        Disciplina tec222 = new Disciplina("Trabalho de Conclusão de Curso II",miniRepo.buscarMinigamePorArea("Texto"), 60, 0, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        Disciplina exa224 = new Disciplina("Extensão em Computação II",miniRepo.buscarMinigamePorArea("Hardware"), 90, 20, mapa.buscarLocalPorNome("Salas de Aula do Módulo 3"));
        tec222.addPreRequisito(tec518);

        historico.getPendentes().addAll(java.util.Arrays.asList(
                exa704, exa806, tec221, tec401, tec498,
                exa417, exa702, exa705, exa807, exa863, exa805,
                exa706, fis110, tec402, tec408, tec499,
                exa703, fis310, tec409, tec502, exa709,
                exa810, exa613, exa616, exa809, tec217, tec218,
                tec412, tec506, tec219,
                exa615, tec430, tec513, tec517, tec501,
                exa544, exa813, tec518, exa622,
                exa612, tec222, exa224));

        historico.getAprovadas().clear();
    }

    public void reconstruirDisciplinas(Historico historico, Mapa mapa, MinigameRepository miniRepo) {
        Historico grade = new Historico();
        this.carregarHistorico(grade, mapa, miniRepo); //preenche a grade com todas as matérias originais do jogo.

        //pega as listas de matérias que vieram quebradas do save e manda para a função ArrumarLista
        //usando a 'grade' como manual
        ArrumarLista(historico.getCursando(), grade, mapa);
        ArrumarLista(historico.getPendentes(), grade, mapa);
        ArrumarLista(historico.getAprovadas(), grade, mapa);
    }

    private void ArrumarLista(Set<Disciplina> listaDoJogador, Historico grade, Mapa mapa) {
        for (Disciplina materiaSalva : listaDoJogador) {
            //vai na grade original e procura a matéria perfeita que tem o mesmo nome da matéria do save
            Disciplina materiaCorreta = buscarPorNome(materiaSalva.getNome(), grade);

            //se ele encontrou a matéria
            if (materiaCorreta != null) {
                //pega os requisitos e o minigame da matéria perfeita e coloca na matéria do jogador.
                materiaSalva.setPreRequisitos(materiaCorreta.getPreRequisitos());
                materiaSalva.setCoRequisitos(materiaCorreta.getCoRequisitos());
                materiaSalva.setMinigame(materiaCorreta.getMinigame());

                //olha o nome da sala na matéria perfeita e depois pede pro Mapa do jogo atual buscar o Local real
                //e conecta o jogador a esse Local real
                materiaSalva.setSala(mapa.buscarLocalPorNome(materiaCorreta.getSala().getNome()));
            }
        }
    }

    private Disciplina buscarPorNome(String nome, Historico grade) {
        //vasculha as matérias que estão no "cursando"
        for (Disciplina d : grade.getCursando()) {
            if (d.getNome().equals(nome)) return d; //achou? da return
        }
        //se não achou, olha nas pendentes
        for (Disciplina d : grade.getPendentes()) {
            if (d.getNome().equals(nome)) return d;
        }
        //se não achou, olha nas aprovadas
        for (Disciplina d : grade.getAprovadas()) {
            if (d.getNome().equals(nome)) return d;
        }
        //se não achou, retorna null
        return null;
    }
}

