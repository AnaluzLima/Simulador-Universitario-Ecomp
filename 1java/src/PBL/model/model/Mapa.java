package PBL.model.model;


import PBL.model.model.tasks.AssistirAula;

import java.util.HashSet;
import java.util.Set;

/**Essa classe tem como objetivo ser o conjunto de locais (o mapa)
 * É ela que armazena onde o jogador vai 'nascer', saber quais são os lugares que faezem parte do mapa
 * e distribuir as aulas nos locais certos*/

public class Mapa {
    private Set<Local> campus;
    private Local spawn;

    public Mapa(boolean spawnF6) {
        this.campus = new HashSet<>();
    }

    public Set<Local> getCampus() {
        return this.campus;
    }

    public void setCampus(Set<Local> campus) {
        this.campus = campus;
    }

    public Local getSpawn() {
        return this.spawn;
    }

    public void setSpawn(Local spawn) {
        this.spawn = spawn;
    }

    public Local buscarLocalPorNome(String nome) {
        for (Local l : this.campus) {
            if (l.getNome().equalsIgnoreCase(nome)) {
                return l;
            }
        }
        return null;
    }

    public void distribuirAulas(Jogador jogador) {
        for (Local l: this.campus){ //se esse metodo foi chamado, significa que um novo semeste foi inciado, então os
                                    //locais que tinham a atividade de assistir aula antes, agora não terão mais.
            l.removerAtividadesDeAula();
        }

        for (Disciplina materia : jogador.getHistorico().getCursando()) { //pega as materias que o jogador ta cursando agora
            Local sala = this.buscarLocalPorNome(materia.getSala().getNome()); //e distribui as aulas
            if (sala != null) {
                AssistirAula aula = new AssistirAula();
                aula.setMateria(materia);
                sala.addAtividade(aula);
            }
        }
    }

}
