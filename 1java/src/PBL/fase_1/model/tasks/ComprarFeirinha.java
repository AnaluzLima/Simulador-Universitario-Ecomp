package PBL.fase_1.model.tasks;

import PBL.exception.DinheiroException;
import PBL.exception.JogoException;
import PBL.exception.TempoException;
import PBL.fase_1.model.Jogador;

public class ComprarFeirinha extends Atividade{
    private String item;
    private int preco;

    public ComprarFeirinha(String item, int preco) {
        super("Comprar " + item, "...");
        this.item = item;
        this.preco = preco;
    }

    //Comprar na feirinha fará com que o jogador possa comprar novos acessórios para sua skin

    @Override
    public void executar(Jogador jogador) throws JogoException {
        if (jogador.getDinheiro() < this.preco) {
            throw new DinheiroException("Saldo Insuficiente!");
        }
        if (jogador.getTempo() < 2) {
            throw new TempoException("Você está sem tempo!");
        }
        if (jogador.getSkin().getAcessoriosDesbloqueados().contains(this.item)) {
            throw new JogoException("Você já comprou este item!");
        }

        jogador.modificarDinheiro(-this.preco); //desconta o preço do item
        jogador.modificarTempo(-2); //gasta 2 pontos de tempo

        jogador.getSkin().addAcessorio(this.item); //adiciona o acessorio na lista de acessorios do jogador
        jogador.getSkin().setAcessorioAtual(this.item); //veste o novo item

        jogador.getEnergia().modificar(10); //aumenta 10 de energia
        jogador.getMotivacao().modificar(15); //aumenta 15 de motivação
    }
}