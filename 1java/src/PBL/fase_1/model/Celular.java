package PBL.fase_1.model;

import PBL.fase_1.model.personagens.*;
import java.util.ArrayList;
import java.util.List;

/**Essa classe tem como objetivo armazenar os contatos dos NPCs que o jogador salvar para conseguir encontra-los com facilidade depois,
 * ver o histórico de atividades realizadas pelo jogador e o seu score.
 * A parte de visualização do histórico será feita no controller*/

public class Celular {
    private List<String> contatos; //lista de contatos do jogador
    private List<String> historicoAtividades; //historico das atividades feitas

    public Celular() {
        this.contatos = new ArrayList<>();
        this.historicoAtividades = new ArrayList<>();
    }

    public List<String> getContatos(){
        return new ArrayList<String>(contatos);
    }

    public List<String> getHistoricoAtividades(){
        return new ArrayList<>(historicoAtividades);
    }

    public boolean adicionarContato(NPC npc) {
        if (!this.contatos.contains(npc.getNome())) { //se o jogador ja não tem o contato salvo...:
            this.contatos.add(npc.getNome());
            return true;
        }
        return false;
    }

    public void registrarAtividade(String descricaoAtividade) {
        this.historicoAtividades.add(descricaoAtividade);
    }

    public String perguntarLocalizacao(NPC npc) {
        if (npc.getLocalizacao() != null) { //se o npc está na uefs:
            return "Estou no(a) " + npc.getLocalizacao().getNome() + " agora.";
        }
        else { //se ele não está:
            return "Não estou na UEFS...";
        }
    }
}