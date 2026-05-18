package PBL.model.model.minigames;

//a ideia é ter a imagem de um protoboard e o jogador vai ter que ligar os componentes da forma correta

public class MinigameHardware extends Minigame {
    private String instrucao;
    private String pinoOrigemGabarito;
    private String pinoDestinoGabarito;

    public MinigameHardware() {
        super("Conexão de Circuitos", "Hardware");
    }

    public void setRodada(String instrucao, String origem, String destino) {
        this.instrucao = instrucao;
        this.pinoOrigemGabarito = origem;
        this.pinoDestinoGabarito = destino;
    }

    public String getInstrucao() {
        return instrucao;
    }

    public String getPinoOrigemGabarito() {
        return pinoOrigemGabarito;
    }

    public String getPinoDestinoGabarito() {
        return pinoDestinoGabarito;
    }

    //recebe as duas pontas do fio que o jogador conectou na tela
    public void avaliarConexao(String origemJogador, String destinoJogador, double tempo) {

        if (origemJogador == null || destinoJogador == null) {
            setPontuacao(0); // Não conectou nada
            return;
        }

        //verifica se ligou na ordem exata do gabarito
        boolean ligacaoDireta = origemJogador.equals(this.pinoOrigemGabarito) && destinoJogador.equals(this.pinoDestinoGabarito);

        //verifica se ligou de trás pra frente
        boolean ligacaoInvertida = origemJogador.equals(this.pinoDestinoGabarito) && destinoJogador.equals(this.pinoOrigemGabarito);

        if (ligacaoDireta || ligacaoInvertida) {
            setPontuacao(10);
        } else {
            setPontuacao(0);  // Errou o fio
        }
    }
}