package PBL.fase_1.model.personagens;

import PBL.exception.TempoException;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.Local;

/**Classe abstrata é o molde para os demais tipos de NPC*/

public abstract class NPC {
    private String nome;
    private Local localizacao;
    private int relacao;

    public NPC(String nome){
        this.nome = nome;
        this.relacao = 0;
    }

    public abstract void interagir(Jogador jogador) throws TempoException;


    //a ideia é que, para cada NPC, a relação seja algo mais específico
    //Para o professor: de 0 a 2: nenhum apreço;  de 2 a 4: mínima;  4 a 8: moderada;  de 8 a 9: alto; 10: aluno favorito
    //Para o colega: de 0 a 2: estranho;  de 2 a 4: conhecido;  4 a 8: amigo; de 8 a 10: melhor amigo
    //Para o animal: de 0 a 2: estranho;  de 2 a 4: conhecido;  4 a 8: adora; de 8 a 10: humano favorito

    //A relação pe medida de 1 a 10
    public void modificarRelacao(int modificar){
        this.relacao += modificar;
        if (this.relacao < 0){
            this.relacao = 0;
        }
        else if(this.relacao > 10){
            this.relacao = 10;
        }
    }

    public String pedirContato(Jogador jogador) throws TempoException{
        jogador.modificarTempo(-1);

        //um jogador pode pedir o contato de um NPC para salvar no celular
        //para isso, ele precisa ao menos conhecer um pouco o personagem, por isso é exigido uma relação nivel 3
        if (this.relacao >= 3) {
            boolean adicionou = jogador.getCelular().adicionarContato(this);

            if (adicionou) {
                return this.nome + ": Claro!";
            } else {
                return this.nome + ": Ué, você já não tem meu número?!";
            }
        } else {
            jogador.getMotivacao().modificar(-5);
            return this.nome + ": Ah... eu não costumo passar meu número assim, desculpa.";
        }
    }

    public int getRelacao(){
        return this.relacao;
    }
    public void setRelacao(int valor){
        this.relacao = valor;
    }
    public String getNome(){
        return this.nome;
    }
    public Local getLocalizacao(){
        return this.localizacao;
    }
    public void setLocalizacao(Local local){
        this.localizacao = local;
    }


}