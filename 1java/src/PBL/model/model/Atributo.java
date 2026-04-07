package PBL.model.model;

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

    public String getNome() {
        return this.nome;
    }
    public int getValor() {
        return this.valor;
    }
    public void setValor(int val){
        this.valor = val;
    }
    public int getvMaximo(){
        return this.vMaximo;
    }
    public int getvMinimo(){
        return this.vMinimo;
    }
}
