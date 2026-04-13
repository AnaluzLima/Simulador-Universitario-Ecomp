package PBL.fase_1.model;

import PBL.exception.DinheiroException;
import PBL.exception.TempoException;

import java.util.List;

/**Essa classe representa o jogador e define tudo que é essencial para sua existência.
 * Ela é responsável por alterar ses atributos e informações necessárias para o andamento do jogo*/

public class Jogador {
    private final String nome;

    private int tempo, semestre, dinheiro;
    private boolean cansado, fazendoProva;

    private Atributo energia; private Atributo saude; private Atributo motivacao;
    private Atributo conhecimento; private Atributo desempenhoAcademico;

    private Local localizacao;
    private Historico historico;

    private Celular celular;
    private Aparencia skin;

    public Jogador(String nome, Aparencia skin) {
        this.nome = nome;
        this.dinheiro = 50;
        this.tempo = 100;
        this.semestre = 1;
        this.cansado = false;

        this.historico = new Historico();

        celular = new Celular();
        this.skin = skin;

        this.energia = new Atributo("Energia", 100);
        this.saude = new Atributo("Saúde", 100);
        this.motivacao = new Atributo("Motivação", 100);
        this.conhecimento = new Atributo("Conhecimento", 0);
        this.desempenhoAcademico = new Atributo("Desempenho Acadêmico", 0);
        //a porcentagem do desempenho acadêmico será a média das porcentagens de desempenho por materia
    }

    public String getNome() {
        return this.nome;
    }

    public Local getLocalizacao(){
        return localizacao;
    }
    public void setLocalizacao(Local newLocal){
        this.localizacao = newLocal;
    }

    public int getDinheiro() {
        return this.dinheiro;
    }
    public void setDinheiro(int valor){
        this.dinheiro = valor;
    }

    public int getTempo() {
        return this.tempo;
    }
    public void setTempo(int valor){
        this.tempo = valor;
    }

    public int getSemestre() {
        return this.semestre;
    }
    public void setSemestre(int n){
        this.semestre = n;
    }
    public void avancarSemestre() {
        this.semestre += 1;
    }

    public Historico getHistorico(){
        return this.historico;
    }

    public boolean isCansado(){
        return this.cansado;
    }
    public void setCansado(boolean estudouMatrugada){
        this.cansado = estudouMatrugada;
    }

    public boolean isFazendoProva(){
        return this.fazendoProva;
    }
    public void setFazendoProva(boolean prova){
        this.fazendoProva = prova;
    }

    public Aparencia getSkin(){
        return skin;
    }
    public void setSkin(Aparencia newSkin){
        this.skin = newSkin;
    }

    public Celular getCelular(){
        return celular;
    }

    public void modificarDinheiro(int valor) throws DinheiroException{  //modifica o dinheiro somando ou subtraindo
        if (valor < 0 && this.dinheiro < valor*(-1)) {                  //respeitando o valor mínimo
            throw new DinheiroException("Saldo Insuficiente!");
        }
        else {
            this.dinheiro += valor;
        }
    }

    public void modificarTempo(int valor) throws TempoException{ //mesma coisa para os pontos de tempo
        if (valor < 0 && this.tempo < valor*(-1)) {
            throw new TempoException("Você está sem tempo livre para fazer isso agora.");
        } else {
            this.tempo += valor;
        }
    }

    public void atualizarDesempenho() { //o atributo de desempenho academico é a media do desempenho nas matérias
        List<Disciplina> materias = this.getHistorico().getCursando(); //pega as materias que o jogador está cursado

        if (materias.isEmpty()) {
            return;
        }

        int soma = 0;
        for (Disciplina d : materias) {
            soma += d.getDesempenho();
        }

        this.desempenhoAcademico.setValor(soma / materias.size()); //calcula a média e atualiza o atributo

    }

    public Atributo getSaude(){
        return this.saude;
    }
    public Atributo getMotivacao(){
        return this.motivacao;
    }
    public Atributo getConhecimento(){
        return this.conhecimento;
    }
    public Atributo getEnergia(){
        return this.energia;
    }
    public Atributo getDesempenhoAcademico(){
        return this.desempenhoAcademico;
    }

}