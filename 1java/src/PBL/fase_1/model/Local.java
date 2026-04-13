package PBL.fase_1.model;

import PBL.fase_1.model.personagens.NPC;
import PBL.fase_1.model.tasks.AssistirAula;
import PBL.fase_1.model.tasks.Atividade;
import java.util.ArrayList;
import java.util.List;

/**Essa classe tem como objetivo criar um local que o jogador poderá visitar. Ele também armazena as conexoes com outros locais*/

public class Local {
    private final String nome;
    private List<Local> conexoes; //lista de conexoes com outros lugares
    private List<Atividade> atvLocais; //atividades que podem ser feitas no local
    private List<NPC> npcsLocal; //npcs que estão no local

    public Local(String nome){
        this.nome = nome;
        this.conexoes = new ArrayList<>();
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