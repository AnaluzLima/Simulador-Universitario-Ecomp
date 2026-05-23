package PBL.model.model.minigames;

/**Essa classe abstrata cria o molde para os demais minigames
 * tem como atributo o nome do minigame, a area dele e a pontuação do jogador nele*/

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

    //calcula a dificuldade do minigame com base no desempenho do jogador na matéria
    public String calcularDificuldade(int desempenho) {
        if (desempenho <= 50) { //se o desempenho em determinada disciplina foi <= 50
            return "Difícil"; //prova dificil
        } else if (desempenho <= 80) { //se foi entre 50 e 80
            return "Médio"; //prova média
        } else { //se não
            return "Fácil"; //prova fácil
        }
    }

}