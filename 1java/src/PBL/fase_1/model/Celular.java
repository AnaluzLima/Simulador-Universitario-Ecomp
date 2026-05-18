package PBL.fase_1.model;

import PBL.fase_1.model.personagens.*;

import java.util.*;

/**Essa classe tem como objetivo armazenar os contatos dos NPCs que o jogador salvar para conseguir encontra-los com facilidade depois,
 * ver o histórico de atividades realizadas pelo jogador e o seu score.
 * A parte de visualização do histórico será feita no controller*/

public class Celular {
    private Set<String> contatos; //lista de contatos do jogador
    private List<Registro> historicoAtividades; //historico das atividades feitas
    private Map<String, Integer> nivelAmizade;

    public Celular() {
        this.contatos = new HashSet<>();
        this.historicoAtividades = new ArrayList<>();
        this.nivelAmizade = new HashMap<>();
    }

    public List<String> getContatos(){
        return new ArrayList<String>(contatos);
    }

    public List<Registro> getHistoricoAtividades(){
        return new ArrayList<>(historicoAtividades);
    }

    public boolean adicionarContato(NPC npc) {
        return this.contatos.add(npc.getNome());
    }

    public String perguntarLocalizacao(NPC npc) {
        if (npc.getLocalizacao() != null) { //se o npc está na uefs:
            return "Estou no(a) " + npc.getLocalizacao().getNome() + " agora.";
        }
        else { //se ele não está:
            return "Não estou na UEFS...";
        }
    }

    public static class Registro { //Classe interna que será usada apenas pelo Celular para registrar as atividade do jogador
        private String texto;
        private int tempoGasto;

        public Registro(String texto, int tempo) {
            this.texto = texto;
            this.tempoGasto = tempo;
        }

        public String getTexto() {
            return texto;
        }
        public int getTempoGasto() {
            return tempoGasto;
        }
    }

    public void adicionarRegistro(String texto, int tempoGasto) {
        this.historicoAtividades.add(new Registro(texto, tempoGasto));
    }
    public void adicionarRegistro(String texto) {
        this.historicoAtividades.add(new Registro(texto, 0));
    }

    public int getNivelAmizade(String nomeNpc) {
        return this.nivelAmizade.getOrDefault(nomeNpc, 0); // Se não conhecer, é 0
    }

    public void modificarAmizade(String nomeNpc, int valor) {
        int amizadeAtual = getNivelAmizade(nomeNpc);
        int novaAmizade = amizadeAtual + valor;

        // Trava entre 0 e 10
        if (novaAmizade < 0) novaAmizade = 0;
        if (novaAmizade > 10) novaAmizade = 10;

        this.nivelAmizade.put(nomeNpc, novaAmizade);
    }
}