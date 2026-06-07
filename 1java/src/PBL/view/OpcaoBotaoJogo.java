package PBL.view;

public class OpcaoBotaoJogo {
    private final String nome;
    private final Runnable acao; //armazena o metodo para ser executado depois

    public OpcaoBotaoJogo(String nome, Runnable acao) {
        this.nome = nome;
        this.acao = acao;
    }

    public String getNome() { return nome; }
    public Runnable getAcao() { return acao; }
}
