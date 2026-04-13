package PBL.fase_1.repository;

import PBL.fase_1.model.Local;
import PBL.fase_1.model.Mapa;
import PBL.fase_1.model.personagens.Animal;
import PBL.fase_1.model.personagens.Colega;
import PBL.fase_1.model.personagens.Professor;
import PBL.fase_1.model.tasks.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**Essa classe tem como objetivo carregar toda a estrutura que forma o mapa do jogo, desde instanciar e conectar os locais
 * até distribuir os NPCs e as atividades*/

public class MapaRepository {

    private Map<String, Local> locais;

    public MapaRepository() {
        this.locais = new HashMap<>();
    }

    public void carregarMapa(Mapa mapa, boolean spawnF6, NpcRepository npcR){
        this.instanciarLocais();
        this.configurarSpawn(mapa, spawnF6);
        this.conectarLocais();
        this.adicionarAtividades();
        this.distribuirNPCs(npcR);
        mapa.getCampus().addAll(this.locais.values());
    }

    public void instanciarLocais() { //cria todos os locais
        locais.put("Ponto 1", new Local("Ponto de Ônibus M5-M6"));
        locais.put("Ponto 2", new Local("Ponto de Ônibus M3-M4"));
        locais.put("Ponto 3", new Local("Ponto de Ônibus M1-M2"));

        locais.put("Por do Sol", new Local("Praça do Pôr do Sol"));
        locais.put("Borogodo", new Local("Praça do Borogodó"));
        locais.put("Quadra", new Local("Quadra"));
        locais.put("Portao", new Local("Portão Lateral"));
        locais.put("Biblioteca", new Local("Biblioteca"));
        locais.put("Bandejao", new Local("Bandejão"));
        locais.put("Colegiado", new Local("Colegiado"));
        locais.put("Feirinha", new Local("Feirinha"));
        locais.put("LEDS", new Local("LEDS"));
        locais.put("Pavilhao", new Local("Pavilhão"));

        locais.put("Cantina 1", new Local("Cantina do Módulo 1"));
        locais.put("Cantina 3", new Local("Cantina do Módulo 3"));
        locais.put("Cantina 5", new Local("Cantina do Módulo 5"));
        locais.put("Cantina 7", new Local("Cantina do Módulo 7"));

        for (int i = 1; i <= 7; i++) {
            locais.put("Modulo " + i, new Local("Módulo " + i));
            locais.put("PATS " + i, new Local("Salas de Aula do Módulo " + i));
        }
    }

    private void configurarSpawn(Mapa mapa, boolean spawnF6) { //define onde o jogador vai 'nascer'
        if(spawnF6) { //se for no feira 6, ele deve ir para casa dormir ou estudar pelo portão lateral
            Local portao = locais.get("Portao");
            mapa.setSpawn(portao);
            portao.addAtividade(new CasaDormir());
            portao.addAtividade(new CasaEstudar());
        }

        else { //se for em outro lugar, ele deve pegar o ônibus
            Local ponto1 = locais.get("Ponto 1");
            mapa.setSpawn(ponto1);
            ponto1.addAtividade(new CasaDormir());
            ponto1.addAtividade(new CasaEstudar());

            locais.get("Ponto 2").addAtividade(new CasaDormir());
            locais.get("Ponto 2").addAtividade(new CasaEstudar());
            locais.get("Ponto 3").addAtividade(new CasaDormir());
            locais.get("Ponto 3").addAtividade(new CasaEstudar());
        }
    }

    private void conectarLocais() { //conecta os locais interligados
        locais.get("Ponto 1").conectar(locais.get("Modulo 7"));
        locais.get("Ponto 1").conectar(locais.get("Modulo 6"));
        locais.get("Ponto 1").conectar(locais.get("Modulo 5"));
        locais.get("Ponto 1").conectar(locais.get("Biblioteca"));

        locais.get("Ponto 2").conectar(locais.get("Modulo 4"));
        locais.get("Ponto 2").conectar(locais.get("Modulo 3"));
        locais.get("Ponto 2").conectar(locais.get("Bandejao"));

        locais.get("Ponto 3").conectar(locais.get("Modulo 2"));
        locais.get("Ponto 3").conectar(locais.get("Modulo 1"));
        locais.get("Ponto 3").conectar(locais.get("Feirinha"));

        locais.get("Modulo 1").conectar(locais.get("Modulo 2"));
        locais.get("Modulo 1").conectar(locais.get("Cantina 1"));
        locais.get("Modulo 1").conectar(locais.get("PATS 1"));

        locais.get("Modulo 2").conectar(locais.get("Modulo 3"));
        locais.get("Modulo 2").conectar(locais.get("Feirinha"));
        locais.get("Modulo 2").conectar(locais.get("PATS 2"));

        locais.get("Modulo 3").conectar(locais.get("Modulo 4"));
        locais.get("Modulo 3").conectar(locais.get("Cantina 3"));
        locais.get("Modulo 3").conectar(locais.get("Borogodo"));
        locais.get("Modulo 3").conectar(locais.get("LEDS"));
        locais.get("Modulo 3").conectar(locais.get("PATS 3"));

        locais.get("Modulo 4").conectar(locais.get("Modulo 5"));
        locais.get("Modulo 4").conectar(locais.get("Biblioteca"));
        locais.get("Modulo 4").conectar(locais.get("PATS 4"));

        locais.get("Modulo 5").conectar(locais.get("Modulo 6"));
        locais.get("Modulo 5").conectar(locais.get("Cantina 5"));
        locais.get("Modulo 5").conectar(locais.get("Biblioteca"));
        locais.get("Modulo 5").conectar(locais.get("Pavilhao"));
        locais.get("Modulo 5").conectar(locais.get("PATS 5"));

        locais.get("Modulo 6").conectar(locais.get("Modulo 7"));
        locais.get("Modulo 6").conectar(locais.get("PATS 6"));

        locais.get("Modulo 7").conectar(locais.get("Cantina 7"));
        locais.get("Modulo 7").conectar(locais.get("Por do Sol"));
        locais.get("Modulo 7").conectar(locais.get("PATS 7"));

        locais.get("Feirinha").conectar(locais.get("Bandejao"));
        locais.get("Colegiado").conectar(locais.get("Quadra"));
        locais.get("Colegiado").conectar(locais.get("Pavilhao"));
        locais.get("Colegiado").conectar(locais.get("PATS 3"));

        locais.get("Portao").conectar(locais.get("Bandejao"));
        locais.get("Portao").conectar(locais.get("Biblioteca"));
        locais.get("Portao").conectar(locais.get("Modulo 3"));
    }

    private void adicionarAtividades() { //adiciona as atividades fixas nos locais
        locais.get("Bandejao").addAtividade(new Bandejar());
        locais.get("Cantina 1").addAtividade(new Cantinar());
        locais.get("Cantina 3").addAtividade(new Cantinar());
        locais.get("Cantina 5").addAtividade(new Cantinar());
        locais.get("Cantina 7").addAtividade(new Cantinar());

        locais.get("Portao").addAtividade(new DarRole());
        locais.get("Biblioteca").addAtividade(new EstudarBiblioteca());

        locais.get("Colegiado").addAtividade(new FalarComMaeli());
        locais.get("Borogodo").addAtividade(new Relaxar());
        locais.get("Por do Sol").addAtividade(new Relaxar());

        //sofrerá modificações no futuro
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("Pulseira", 10));
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("Planta", 30));
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("Conjunto", 70));

        locais.get("Quadra").addAtividade(new PraticarEsporte());
    }

    private void distribuirNPCs(NpcRepository npcR) {
        //espalha os NPCs pelo mapa
        List<Professor> professores = npcR.carregarProfessores();

        if (professores.size() >= 3) {
            locais.get("Colegiado").addNPC(professores.get(0)); //Ana Lúcia
            locais.get("PATS 3").addNPC(professores.get(1)); //Wild
            locais.get("PAT 5").addNPC(professores.get(2)); //Michele
        }

        List<Colega> colegas = npcR.carregarColegas();
        locais.get("LEDS").addNPC(colegas.get(0));


        List<Animal> animais = npcR.carregarAnimais();
        locais.get("Modulo 5").addNPC(animais.get(1)); //Fábio Junior
        locais.get("Modulo 3").addNPC(animais.get(0)); //Scooby
        locais.get("Modulo 2").addNPC(animais.get(2)); //Nariz
        locais.get("Modulo 7").addNPC(animais.get(5)); //Raposinha
        locais.get("Portao").addNPC(animais.get(3)); //Pitufina
        locais.get("Modulo 3").addNPC(animais.get(4)); //Mico
    }
}
