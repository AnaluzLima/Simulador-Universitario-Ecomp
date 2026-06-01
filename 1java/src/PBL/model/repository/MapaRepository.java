package PBL.model.repository;

import PBL.model.model.Local;
import PBL.model.model.Mapa;
import PBL.model.model.personagens.Animal;
import PBL.model.model.personagens.Colega;
import PBL.model.model.personagens.Professor;
import PBL.model.model.tasks.*;

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

        //ainda sofrerá atualizações
        locais.put("PontoOnibus", new Local("Ponto de Ônibus", 350.0, 420.0, 200.0, "/resources/Locais/PontoOnibusLocal.png"));

        locais.put("PorDoSol", new Local("Praça do Pôr do Sol", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Borogodo", new Local("Praça do Borogodó", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Quadra", new Local("Quadra", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Portao", new Local("Portão Lateral", 530.0, 500.0, 220.0, "/resources/Locais/PortaoLateralLocal.png"));
        locais.put("EntradaBiblioteca", new Local("Entrada da Biblioteca", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Biblioteca", new Local("Biblioteca", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Bandejao", new Local("Bandejão", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Colegiado", new Local("Colegiado", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Feirinha", new Local("Feirinha", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("LEDS", new Local("LEDS", 0.0, 0.0, 150.0, "/resources/Locais/.png"));

        locais.put("Cantina3", new Local("Cantina do Módulo 3", 0.0, 0.0, 150.0, "/resources/Locais/.png"));

        locais.put("Modulo1", new Local("Módulo 1", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Modulo2", new Local("Módulo 2", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Modulo3", new Local("Módulo 3", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Modulo4", new Local("Módulo 4", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Modulo5", new Local("Módulo 5", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Modulo6", new Local("Módulo 6", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("Modulo7", new Local("Módulo 7", 0.0, 0.0, 150.0, "/resources/Locais/.png"));

        locais.put("PAT1", new Local("Salas de Aula do Módulo 1", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("PAT2", new Local("Salas de Aula do Módulo 2", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("PAT3", new Local("Salas de Aula do Módulo 3", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("PAT4", new Local("Salas de Aula do Módulo 4", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("PAT5", new Local("Salas de Aula do Módulo 5", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("PAT6", new Local("Salas de Aula do Módulo 6", 0.0, 0.0, 150.0, "/resources/Locais/.png"));
        locais.put("PAT7", new Local("Salas de Aula do Módulo 7", 0.0, 0.0, 150.0, "/resources/Locais/.png"));

    }

    private void configurarSpawn(Mapa mapa, boolean spawnF6) { //define onde o jogador vai 'nascer'
        if(spawnF6) { //se for no feira 6, ele deve ir para casa dormir ou estudar pelo portão lateral
            Local portao = locais.get("Portao");
            mapa.setSpawn(portao);
            portao.addAtividade(new CasaDormir());
            portao.addAtividade(new CasaEstudar());
        }

        else { //se for em outro lugar, ele deve pegar o ônibus
            Local ponto = locais.get("PontoOnibus");
            mapa.setSpawn(ponto);
            ponto.addAtividade(new CasaDormir());
            ponto.addAtividade(new CasaEstudar());
        }
    }

    private void conectarLocais() { //conecta os locais interligados
        locais.get("PontoOnibus").conectar(locais.get("Modulo7"));
        locais.get("PontoOnibus").conectar(locais.get("Modulo6"));
        locais.get("PontoOnibus").conectar(locais.get("Modulo5"));
        locais.get("PontoOnibus").conectar(locais.get("Biblioteca"));

        locais.get("PontoOnibus").conectar(locais.get("Modulo4"));
        locais.get("PontoOnibus").conectar(locais.get("Cantina3"));

        locais.get("PontoOnibus").conectar(locais.get("Modulo2"));
        locais.get("PontoOnibus").conectar(locais.get("Modulo1"));
        locais.get("PontoOnibus").conectar(locais.get("Feirinha"));

        locais.get("Modulo1").conectar(locais.get("Modulo2"));
        locais.get("Modulo1").conectar(locais.get("PAT1"));

        locais.get("Modulo2").conectar(locais.get("Cantina3"));
        locais.get("Modulo2").conectar(locais.get("Feirinha"));
        locais.get("Modulo2").conectar(locais.get("PAT2"));
        locais.get("PAT2").conectar(locais.get("Quadra"));



        locais.get("Cantina3").conectar(locais.get("Modulo4"));
        locais.get("Cantina3").conectar(locais.get("Modulo3"));

        locais.get("Modulo3").conectar(locais.get("Borogodo"));
        locais.get("Modulo3").conectar(locais.get("LEDS"));
        locais.get("Modulo3").conectar(locais.get("PAT3"));
        locais.get("Modulo3").conectar(locais.get("Quadra"));


        locais.get("Modulo4").conectar(locais.get("Modulo5"));
        locais.get("Modulo4").conectar(locais.get("Biblioteca"));
        locais.get("Modulo4").conectar(locais.get("PAT4"));

        locais.get("Modulo5").conectar(locais.get("Modulo6"));
        locais.get("Modulo5").conectar(locais.get("Biblioteca"));
        locais.get("Modulo5").conectar(locais.get("PAT5"));

        locais.get("Modulo6").conectar(locais.get("Modulo7"));
        locais.get("Modulo6").conectar(locais.get("PAT6"));

        locais.get("Modulo7").conectar(locais.get("PorDoSol"));
        locais.get("Modulo7").conectar(locais.get("PAT7"));

        locais.get("Feirinha").conectar(locais.get("Bandejao"));
        locais.get("Colegiado").conectar(locais.get("PAT3"));

        locais.get("Portao").conectar(locais.get("Bandejao"));
        locais.get("Portao").conectar(locais.get("Biblioteca"));
        locais.get("Portao").conectar(locais.get("Modulo3"));
    }

    private void adicionarAtividades() { //adiciona as atividades fixas nos locais
        locais.get("Bandejao").addAtividade(new Bandejar());
        locais.get("Modulo1").addAtividade(new Cantinar());
        locais.get("Cantina3").addAtividade(new Cantinar());
        locais.get("Modulo5").addAtividade(new Cantinar());
        locais.get("Modulo7").addAtividade(new Cantinar());

        locais.get("Portao").addAtividade(new DarRole());
        locais.get("Biblioteca").addAtividade(new EstudarBiblioteca());

        locais.get("Colegiado").addAtividade(new FalarComMaeli());
        locais.get("Borogodo").addAtividade(new Relaxar());
        locais.get("PorDoSol").addAtividade(new Relaxar());

        //sofrerá modificações no futuro
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("blushed", 10));
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("flor", 30));
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("folhas", 35));
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("luva", 20));
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("oculos", 70));
        locais.get("Feirinha").addAtividade(new ComprarFeirinha("camisaEcomp", 100));

        locais.get("Quadra").addAtividade(new PraticarEsporte());
    }

    private void distribuirNPCs(NpcRepository npcR) {
        //espalha os NPCs pelo mapa
        List<Professor> professores = npcR.carregarProfessores();

        if (professores.size() >= 3) {
            locais.get("Colegiado").addNPC(professores.get(0)); //Ana Lúcia
            locais.get("LEDS").addNPC(professores.get(1)); //Wild
            locais.get("PAT5").addNPC(professores.get(2)); //Michele
        }

        List<Colega> colegas = npcR.carregarColegas();
        locais.get("LEDS").addNPC(colegas.get(0));


        List<Animal> animais = npcR.carregarAnimais();
        locais.get("Cantina3").addNPC(animais.get(0)); //Scooby
        locais.get("PAT5").addNPC(animais.get(1)); //Velhote
        locais.get("Modulo7").addNPC(animais.get(2)); //Fábio Junior
        locais.get("Modulo4").addNPC(animais.get(3)); // Rajadinho
    }
}
