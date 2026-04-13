package PBL.fase_1.model.minigames;

//ESSAS CLASSES (Minigame e suas subclasses) NÃO FORAM CONCLUIDAS

public abstract class Minigame {
    private final String nome;
    private final String area; //texto, matemática, software ou hardware
    private int pontuacao;

    public Minigame(String nome, String area) {
        this.nome = nome;
        this.area = area;
    }

    public String getNome() {
        return nome;
    }

    public String getArea() {
        return area;
    }

    public int getPontuacao(){
        return pontuacao;
    }
    public void setPontuacao(int nota){
        this.pontuacao = nota;
    }

    public String calcularDificuldade(int desempenho) {
        if (desempenho <= 50) { //se o desempenho em determinada disciplina foi <= 50
            return "Difícil"; //prova dificil
        } else if (desempenho <= 80) { //se foi entre 50 e 80
            return "Médio"; //prova média
        } else { //se não
            return "Fácil"; //prova fácil
        }
    }

    public abstract void jogar(int desempenhoAtual);
}