package PBL.model.model.minigames;

/**Essa classe que herda de minigames cria a lógica para o funcionamento das provas de hardware
 * Toda prova terá sua instrução (enunciado), o ponto de origem do fio que o jogador deverá conectar e o
 * ponto de destino*/

public class MinigameHardware extends Minigame {
    private String instrucao;
    private String pinoOrigemGabarito;
    private String pinoDestinoGabarito;
    private EstrategiaAvaliacao<String[], String[]> estrategia;

    public MinigameHardware(EstrategiaAvaliacao<String[], String[]> estrategia) {
        super("Conexão de Circuitos", "Hardware");
        this.estrategia = estrategia;
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

    public void avaliarConexao(String origemJogador, String destinoJogador, double tempo) {
        String[] repostaJogador = {origemJogador, destinoJogador};
        String[] gabarito = {this.pinoOrigemGabarito, this.pinoDestinoGabarito};

        int notaFinal = estrategia.calcularPontuacao(repostaJogador, gabarito, tempo);
        setPontuacao(notaFinal);
    }
}