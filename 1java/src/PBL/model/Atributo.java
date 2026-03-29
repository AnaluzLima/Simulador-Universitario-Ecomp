package PBL.model;

public class Atributo {
    private String nome;
    private int valor;
    private int vMaximo;
    private int vMinimo;

    public Atributo(String nome, int valorInicial, int vMinimo, int vMaximo) {
        this.nome = nome;
        this.valor = valorInicial;
        this.vMinimo = vMinimo;
        this.vMaximo = vMaximo;
    }

    public void modificar(int qtd) {
        this.valor += qtd;

        if (this.valor > vMaximo) {
            this.valor = vMaximo;
        } else if (this.valor < vMinimo) {
            this.valor = vMinimo;
        }

    }

    public String getNome() {
        return this.nome;
    }
    public int getValor() {
        return this.valor;
    }
    public void setValor(int val){
        this.valor = val;
    }
}
