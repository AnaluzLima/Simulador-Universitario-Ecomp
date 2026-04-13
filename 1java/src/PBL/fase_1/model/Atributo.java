package PBL.fase_1.model;

/**Essa classe tem como objetivo criar os atributos do jogador, que serão objetos.
 * Ele define o valor máximo e o valor mínimo que um atributo pode alcançar, assim
 * Como foi decidido que os atributos serão em porcentagem, o maior valor é 100 e o menor é 0*/

public class Atributo {
    private final String nome;
    private int valor;

    public Atributo(String nome, int valorInicial) {
        this.nome = nome;
        this.valor = valorInicial;
    }

    public String getNome() {
        return this.nome;
    }
    public int getValor() {
        return this.valor;
    }
    public void setValor(int val){
        if (val > 100){
            this.valor = 100;
        }
        else if (val < 0){
            this.valor = 0;
        }
        else {
            this.valor = val;
        }
    }

    public void modificar(int qtd) {
        this.setValor(this.valor + qtd);
    }
}

