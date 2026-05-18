package PBL.model.service;

import PBL.exception.TempoException;
import PBL.model.model.Jogador;
import PBL.model.model.personagens.Colega;
import PBL.model.model.personagens.NPC;
import PBL.model.repository.NpcRepository;

import java.util.List;
import java.util.Random;

/**Essa classe tem como objetivo administrar o sistema social e de dialogos do jogo*/

public class InteracaoService {

    private final NpcRepository colegaR;
    private final Random random;

    public InteracaoService(NpcRepository colegaR){
        this.colegaR = colegaR;
        this.random = new Random();
    }

    public String conversarComNPC(Jogador jogador, NPC npc) throws TempoException {
        npc.interagir(jogador);
        String mensagem = "Você passou um tempo interagindo com " + npc.getNome();

        //se o NPC for especificamente um colega, anexa uma fala aleatória do repository
        if (npc instanceof Colega) {
            List<String> falas = colegaR.carregarFalasColegas();

            int i = random.nextInt(falas.size());
            String falaSorteada = falas.get(i);
            mensagem += "\n" + npc.getNome() + ": " + falaSorteada;
        }

        return mensagem;

    }
    //passa a lógica de rejeição/aceitação do contato para o próprio NPC
    public String tentarPegarContato(Jogador jogador, NPC npc) throws TempoException {
        return npc.pedirContato(jogador);
    }
}