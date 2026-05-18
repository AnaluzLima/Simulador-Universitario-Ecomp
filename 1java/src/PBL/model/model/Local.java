package PBL.model.model;

import PBL.model.model.personagens.NPC;
import PBL.model.model.tasks.AssistirAula;
import PBL.model.model.tasks.Atividade;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**Essa classe tem como objetivo criar um local que o jogador poderá visitar. Ele também armazena as conexoes com outros locais*/

public class Local {
    private final String nome;
    private transient Set<Local> conexoes; //lista de conexoes com outros lugares
    private transient List<Atividade> atvLocais; //atividades que podem ser feitas no local
    private transient List<NPC> npcsLocal; //npcs que estão no local

    public Local(String nome){
        this.nome = nome;
        this.conexoes = new HashSet<>();
        this.atvLocais = new ArrayList<>();
        this.npcsLocal = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public List<Local> getConexoes() {
        return new ArrayList<>(conexoes);
    }
    public void conectar(Local vizinho) { //conecta os vizinhos
        if (!this.getConexoes().contains(vizinho)) {
            this.conexoes.add(vizinho);
            vizinho.conectar(this); //se da para ir de um lugar para outro, da para voltar
        }
    }

    public List<NPC> getNpcsLocal(){
        return new ArrayList<>(npcsLocal);
    }
    public void addNPC(NPC npc) {
        this.npcsLocal.add(npc);
        npc.setLocalizacao(this); //adiciona o npc no local e define a localizacao dele
    }

    public List<Atividade> getAtvLocais(){
        return new ArrayList<>(atvLocais);
    }
    public void addAtividade(Atividade atividade) {
        this.atvLocais.add(atividade);
    }
    public void removerAtividadesDeAula() {
        this.atvLocais.removeIf(atividade -> atividade instanceof AssistirAula);
    }
}