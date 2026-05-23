package PBL.model.model.minigames;

/**Essa classe que herda de minigames cria a lógica para o funcionamento das provas de texto
 * Toda prova terá o texto que o jogador deve digitar corretamente*/

public class MinigameTexto extends Minigame {
    private String textoAtual;

    public MinigameTexto() {
        super("TypeRacer Acadêmico", "Texto");
    }

    public void setTextoAtual(String texto) {
        this.textoAtual = texto;
    }

    public String getTextoAtual() {
        return this.textoAtual;
    }

    //recebe o texto que o jogador digitou
    public void avaliarDigitacao(String textoDigitado) {
        //se ele não digitou nada, tira zero
        if (textoDigitado == null || textoDigitado.isEmpty()) {
            setPontuacao(0);
            return;
        }

        int acertos = 0;
        //compara letra por letra até onde o jogador conseguiu digitar
        int limite = Math.min(textoDigitado.length(), this.textoAtual.length()); //o Math.min() pega o menor valor

        //compara as letras e vai somando os acertos
        for (int i = 0; i < limite; i++) {
            if (textoDigitado.charAt(i) == this.textoAtual.charAt(i)) {
                acertos++;
            }
        }

        //calcula a porcentagem de acerto
        double porcentagem = (double) acertos / this.textoAtual.length();

        //define a nota com base na aproximação da porcentagem
        int notaFinal = (int) Math.round(porcentagem * 10.0);
        setPontuacao(notaFinal);
    }
}