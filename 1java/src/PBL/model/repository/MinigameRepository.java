package PBL.model.repository;

import PBL.model.model.minigames.*;
import PBL.model.model.minigames.Minigame;

import java.util.Arrays;
import java.util.List;

/**Esse repositorio representa todas as questões dos minigames, dividindo cada metodo em um minigame com
 * questões facies, medias e dificeis*/

public class MinigameRepository {

    public Minigame buscarMinigamePorArea(String area) {
        if (area.equalsIgnoreCase("Texto")) {
            return new MinigameTexto();
        } else if (area.equalsIgnoreCase("Matemática")) {
            return new MinigameMatematica();
        } else if (area.equalsIgnoreCase("Software")) {
            return new MinigameSoftware();
        } else if (area.equalsIgnoreCase("Hardware")) {
            return new MinigameHardware();
        }
        return null;
    }

    public List<String> textos(String dificuldade){

        if(dificuldade.equals("Fácil")){
            List<String> textosFaceis = Arrays.asList("\"Falar é fácil. Mostre-me o código.\" — Linus Torvalds",
                    "\"O software está devorando o mundo.\" — Marc Andreessen",
                    "\"Pesquisa é o que estou fazendo quando não sei o que estou fazendo.\" — Wernher von Braun",
                    "\"A educação é a arma mais poderosa que você pode usar para mudar o mundo.\" — Nelson Mandela.",
                    "\"Os perigos não vêm de computadores que pensam como humanos, mas de humanos que pensam como computadores.\" — Sydney J. Harris",
                    "\"Se for possível cortar uma palavra, corte-a sempre.\" — George Orwell.",
                    "\"A imaginação é mais importante que o conhecimento.\" — Albert Einstein.",
                    "\"A técnica é apenas um meio, o homem é o fim.\" — Erich Fromm",
                    "\"A simplicidade é o pré-requisito para a confiabilidade.\" — Edsger W. Dijkstra",
                    "\"Permaneçam famintos. Permaneçam tolos.\" — Steve Jobs.");

            return textosFaceis;
        }
        else if(dificuldade.equals("Médio")){
            List<String> textosMedios = Arrays.asList("\"Seus clientes mais insatisfeitos são sua maior fonte de aprendizado.\" — Bill Gates",
                    "\"Se você não tem vergonha da primeira versão do seu produto, você o lançou tarde demais.\" — Reid Hoffman",
                    "\"Qualquer máquina construída com o propósito de tomar decisões, se não possuir o poder de aprender, será completamente desprovida de inteligência.\" — Norbert Wiener",
                    "\"Se enxerguei mais longe, foi por estar de pé sobre ombros de gigantes.\" — Isaac Newton.",
                    "\"Nós só podemos ver um pouco mais à frente, mas podemos ver muito que precisa ser feito.\" — Alan Turing",
                    "\"O primeiro princípio é que você não deve se enganar — e você é a pessoa mais fácil de enganar.\" — Richard Feynman",
                    "\"Os analfabetos do século XXI não serão aqueles que não sabem ler e escrever, mas aqueles que não conseguem aprender, desaprender e reaprender.\" — Alvin Toffler",
                    "\"Nada na vida deve ser temido, apenas compreendido. Agora é a hora de compreender mais, para que possamos temer menos.\" — Marie Curie.",
                    "\"A Web como eu a imaginei, nós ainda não a vimos. O futuro ainda é muito maior do que o passado.\" — Tim Berners-Lee",
                    "\"Nossas posturas, nossa suposta auto-importância, a ilusão de que temos alguma posição privilegiada no Universo, são desafiadas por este ponto de luz pálida.\" — Carl Sagan");

            return textosMedios;
        }
        else{
            List<String> textosDificil = Arrays.asList("\"Nós sempre superestimamos a mudança que ocorrerá nos próximos dois anos e subestimamos a mudança que ocorrerá nos próximos dez. Não se deixe ser embalado pela inação.\" — Bill Gates.",
                    "\"As ações dos profissionais de computação mudam o mundo. Para agir com responsabilidade, eles devem refletir sobre os impactos mais amplos de seu trabalho, apoiando consistentemente o bem público. O Código de Ética e Conduta Profissional da ACM expressa a consciência da profissão.\" — Association for Computing Machinery",
                    "\"Você não pode conectar os pontos olhando para frente; você só pode conectá-los olhando para trás. Então você tem que confiar que os pontos se conectarão de alguma forma no seu futuro. Você tem que confiar em algo — seu instinto, destino, vida, carma, o que quer que seja.\" — Steve Jobs.",
                    "\"A Máquina Analítica tece padrões algébricos assim como o tear de Jacquard tece flores e folhas. Pode ser descrita como a expressão material de qualquer função indefinida de qualquer grau de generalidade e complexidade.\" — Ada Lovelace",
                    "\"Uma tese é um trabalho original de pesquisa com o qual o candidato demonstra ser um estudioso capaz de fazer avançar a disciplina à qual se dedica. Não se trata apenas de compilar informações, mas de oferecer uma nova perspectiva sobre o tema estudado.\" — Umberto Eco",
                    "\"A ciência não é um sistema de enunciados certos ou bem estabelecidos, nem é um sistema que avança continuamente em direção a um estado final. Nossa ciência não é conhecimento: ela nunca pode pretender ter atingido a verdade absoluta.\" — Karl Popper",
                    "\"Se você não trabalha em problemas importantes, não é provável que você faça um trabalho importante. É perfeitamente óbvio. Grandes cientistas pensaram cuidadosamente sobre uma série de problemas importantes em seu campo, e mantêm os olhos abertos para como atacá-los.\" — Richard Hamming",
                    "\"O capitalismo de vigilância reivindica unilateralmente a experiência humana como matéria-prima gratuita para tradução em dados comportamentais. Embora alguns desses dados sejam aplicados à melhoria do produto ou serviço, o restante é declarado como um excedente comportamental.\" — Shoshana Zuboff",
                    "\"Não há saber mais ou saber menos: há saberes diferentes. A educação não transforma o mundo. Educação muda as pessoas. Pessoas transformam o mundo.\" — Paulo Freire");

            return textosDificil;
        }
    }


    public static class Equacao {
        private String pergunta;
        private String resposta;

        public Equacao(String pergunta, String resposta) {
            this.pergunta = pergunta;
            this.resposta = resposta;
        }
        public String getPergunta() {
            return pergunta;
        }
        public String getResposta() {
            return resposta;
        }
    }

    public List<Equacao> equacoes(String dificuldade){

        if(dificuldade.equals("Fácil")){
            List<Equacao> contasFaceis = Arrays.asList(
                    new Equacao("15 + 27", "42"),
                    new Equacao("8 * 7", "56"),
                    new Equacao("144 / 12", "12"),
                    new Equacao("10 + 5 * 2", "20"),
                    new Equacao("Raiz quadrada de 64", "8")
            );
            return contasFaceis;
        }
        else if(dificuldade.equals("Médio")){
            List<Equacao> contasMedias = Arrays.asList(
                    new Equacao("Derivada de f(x) = 2x² + 3x", "4x+3"),
                    new Equacao("Derivada de f(x) = 5x", "5"),
                    new Equacao("Derivada de f(x) = sen(x)", "cos(x)"),
                    new Equacao("45 - 18 * 2", "9"),
                    new Equacao("Log de 100 na base 10", "2")
            );
            return contasMedias;
        }
        else{
            List<Equacao> contasDificeis = Arrays.asList(
                    new Equacao("Integral de f(x) = 2x", "x^2"),
                    new Equacao("Integral de f(x) = cos(x)", "sen(x)"),
                    new Equacao("Derivada de f(x) = e^x", "e^x"),
                    new Equacao("Integral de f(x) = 1/x", "ln(x)"),
                    new Equacao("Derivada de f(x) = ln(x)", "1/x")
            );
            return contasDificeis;
        }
    }

    public static class QuizSoftware {
        public String pergunta;
        public List<String> alternativas;
        public int indiceCorreto;

        public QuizSoftware(String p, List<String> a, int i) {
            this.pergunta = p; this.alternativas = a; this.indiceCorreto = i;
        }
    }

    public List<QuizSoftware> quizzesSoftware(String dificuldade) {
        if (dificuldade.equals("Fácil")) {
            return Arrays.asList(
                new QuizSoftware("Qual a saída em C de: printf(\"%d\", 5+2);", Arrays.asList("52", "7", "Erro", "0"), 1),
                new QuizSoftware("Em Java, como declarar um inteiro?", Arrays.asList("int x;", "integer x;", "num x;", "x = int;"), 0),
                new QuizSoftware("Qual símbolo representa comentário de linha em Java e C?", Arrays.asList("[]", "//", "#", "--"), 1),
                new QuizSoftware("Em Python, qual função exibe texto na tela?", Arrays.asList("echo()", "display()", "print()", "show()"), 2),
                new QuizSoftware("Qual estrutura repete um bloco de código enquanto uma condição for verdadeira?", Arrays.asList("if", "switch", "while", "return"), 2),
                new QuizSoftware("Em Java, qual palavra-chave cria um objeto?", Arrays.asList("create", "new", "make", "build"), 1),
                new QuizSoftware("Qual operador é usado para comparar igualdade em C?", Arrays.asList("=", "!=", "==", ":="), 2),
                new QuizSoftware("Qual tipo armazena valores verdadeiro/falso em Java?", Arrays.asList("bit", "bool", "boolean", "flag"), 2),
                new QuizSoftware("Qual caractere é usado para encerrar uma instrução em Java e C?", Arrays.asList(".", ";", ":", ","), 1),
                new QuizSoftware("O que é uma variável?", Arrays.asList("Um tipo de loop", "Um espaço na memória para armazenar dados", "Uma função matemática", "Um comando de saída"), 1)
            );
        }
        else if (dificuldade.equals("Médio")) {
            return Arrays.asList(
                new QuizSoftware("Em Java, qual interface garante elementos únicos em uma coleção?", Arrays.asList("List", "Queue", "Set", "Map"), 2),
                new QuizSoftware("Qual a diferença entre i++ e ++i em C/Java?", Arrays.asList("Nenhuma", "i++ incrementa depois da leitura, ++i incrementa antes", "i++ soma 2, ++i soma 1", "Erro de sintaxe"), 1),
                new QuizSoftware("O que é um ponteiro em C?", Arrays.asList("Uma variável que armazena um endereço de memória", "Uma estrutura de repetição", "Um tipo de dado booleano", "Um erro de compilação"), 0),
                new QuizSoftware("Em Java, o que a palavra-chave 'static' indica em um método?", Arrays.asList("Que a variável não pode mudar", "Que o método pertence à classe e não à instância", "Que a classe é privada", "Que o objeto é imutável"), 1),
                new QuizSoftware("Qual é a complexidade de tempo do pior caso do algoritmo Bubble Sort?", Arrays.asList("O(1)", "O(n)", "O(n log n)", "O(n²)"), 3),
                new QuizSoftware("O que é recursão?", Arrays.asList("Um loop infinito", "Uma função que chama a si mesma", "Um tipo de array", "Um operador lógico"), 1),
                new QuizSoftware("O que faz o operador % em programação?", Arrays.asList("Divide dois números", "Retorna o resto da divisão", "Calcula porcentagem", "Multiplica por 100"), 1),
                new QuizSoftware("O que é uma exceção em programação?", Arrays.asList("Um tipo de variável global", "Um erro em tempo de execução tratável", "Uma função sem retorno", "Um loop especial"), 1),
                new QuizSoftware("Qual estrutura de dados segue o princípio LIFO?", Arrays.asList("Fila", "Lista", "Pilha", "Árvore"), 2),
                new QuizSoftware("Em Java, qual modificador impede que um método seja sobrescrito?", Arrays.asList("static", "private", "final", "abstract"), 2)
            );
        }
        else {
            return Arrays.asList(
                new QuizSoftware("Qual é a complexidade de busca em uma lista ordenada com busca binária?", Arrays.asList("O(n)", "O(n²)", "O(log n)", "O(1)"), 2),
                new QuizSoftware("O que geralmente causa um 'StackOverflowError'?", Arrays.asList("Loop 'while' infinito sem alocação de memória", "Chamadas recursivas muito profundas sem atingir o caso base", "Acessar um índice fora do array", "Dividir um número por zero"), 1),
                new QuizSoftware("Em C, como alocar dinamicamente um array de 10 inteiros corretamente?", Arrays.asList("malloc(int, 10);", "int arr = new int[10];", "int* arr = alloc(10);", "int* arr = malloc(10 * sizeof(int));"), 3),
                new QuizSoftware("Qual é a saída de: System.out.println(1 + 2 + \"3\" + 4 + 5);", Arrays.asList("12345", "15345", "3345", "Erro de compilação"), 2),
                new QuizSoftware("Qual algoritmo de ordenação tem melhor caso O(n) e pior caso O(n²)?", Arrays.asList("Merge Sort", "Heap Sort", "Insertion Sort", "Quick Sort"), 2),
                new QuizSoftware("Em programação funcional, o que é uma função pura?", Arrays.asList("Uma função sem parâmetros", "Uma função que sempre retorna o mesmo resultado para os mesmos inputs e sem efeitos colaterais", "Uma função declarada com 'pure'", "Uma função estática privada"), 1),
                new QuizSoftware("Qual estrutura de dados oferece busca, inserção e remoção em O(1) médio?", Arrays.asList("Árvore AVL", "Lista encadeada", "Hash Table", "Heap binário"), 2),
                new QuizSoftware("Em Java, qual é a diferença entre '==' e '.equals()' para Strings?", Arrays.asList("Não há diferença", "'==' compara referências; '.equals()' compara conteúdo", "'==' compara conteúdo; '.equals()' compara referências", "'.equals()' só funciona com tipos primitivos"), 1)
            );
        }
    }

    public static class CircuitoHardware {
        public String instrucao;
        public String pinoOrigem;
        public String pinoDestino;

        public CircuitoHardware(String i, String origem, String destino) {
            this.instrucao = i;
            this.pinoOrigem = origem;
            this.pinoDestino = destino;
        }
    }

    public List<CircuitoHardware> circuitosHardware(String dificuldade) {
        if (dificuldade.equals("Fácil")) {
            return Arrays.asList(
                    new CircuitoHardware("Para o LED acender, ligue o polo positivo da fonte ao Resistor.", "Pino 5V", "Terminal 1 do Resistor"),
                    new CircuitoHardware("O anodo do LED está ligado, mas falta fechar o circuito. Ligue o Catodo ao terra.", "Catodo do LED", "GND (Terra)"),
                    new CircuitoHardware("Ligue o botão à fonte de alimentação para ele poder acender o LED.", "Terminal do Botão", "Pino 5V")
            );
        }
        else if (dificuldade.equals("Médio")) {
            return Arrays.asList(
                    new CircuitoHardware("Temos um CI 7408 (Porta AND). Ligue a Saída lógica ao resistor para o LED acender.", "Pino 3 (Saída AND)", "Resistor do LED"),
                    new CircuitoHardware("O CI 7404 (Porta NOT) não está alimentado. Ligue a alimentação do chip.", "Pino 14 (VCC)", "Trilha Positiva (5V)"),
                    new CircuitoHardware("O fio da Chave B soltou da Porta OR (CI 7432). Reconecte-o.", "Saída da Chave B", "Pino 2 (Entrada OR)")
            );
        }
        else { //difícil
            return Arrays.asList(
                    new CircuitoHardware("Ligue a saída do oscilador 555 à entrada do Flip-Flop D (7474) para ele piscar.", "Pino 3 (Saída do 555)", "Pino de Clock (Flip-Flop)"),
                    new CircuitoHardware("Ative o Pino de Seleção do MUX 2x1 (74157) colocando-o em nível ALTO.", "Pino de Seleção (S)", "Pino 5V"),
                    new CircuitoHardware("Ligue a saída de transbordo (Carry Out) do Somador (7483) ao LED.", "Carry Out (C4)", "Resistor do LED")
            );
        }
    }
}