package PBL.model.repository;

import PBL.model.model.personagens.Animal;
import PBL.model.model.personagens.Colega;
import PBL.model.model.personagens.Funcionario;
import PBL.model.model.personagens.Professor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NpcRepository {
    private List<Animal> animais;
    private List<Professor> professores;
    private List<Colega> colegas;
    private List<Funcionario> funcionarios;

    public NpcRepository(){
        this.animais = new ArrayList<>();
        this.professores = new ArrayList<>();
        this.colegas = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
    }


    public void carregarNPCs(){
        carregarAnimais();
        carregarProfessores();
        carregarColegas();
        carregarFuncionarios();
    }

    public List<Animal> carregarAnimais() {
        //sofrerá modificações no futuro, adicionando mais animais
        this.animais.add(new Animal("Scooby", 0.05));
        this.animais.add(new Animal("Fábio Junior", 0.60));
        this.animais.add(new Animal("Nariz", 0.10));
        this.animais.add(new Animal("Pitufina", 0.50));
        this.animais.add(new Animal("Mico", 0.80));
        this.animais.add(new Animal("Rapozinha", 0.10));

        return this.animais;
    }
    public List<Professor> carregarProfessores() {
        //sofrerá modificações no futuro, adicionando mais professores

        this.professores.add(new Professor("Ana Lúcia", 3));
        this.professores.add(new Professor("Wild", 6));
        this.professores.add(new Professor("Michele", 8));
        this.professores.add(new Professor("Bianca", 5));
        this.professores.add(new Professor("João", 4));

        return this.professores;
    }
    public List<Funcionario> carregarFuncionarios(){
        //sofrerá modificações no futuro, adicionando mais funcionarios
        this.funcionarios.add(new Funcionario("Maeli"));

        return this.funcionarios;
    }

    public List<Colega> carregarColegas() {

        //sofrerá modificações no futuro, adicionando mais colegas
        this.colegas.add(new Colega("Luz"));

        return this.colegas;
    }

    public List<String> carregarFalasColegas() { //lista de falas aleatórias dos colegas
        return Arrays.asList(
                "Você viu a fila do Bandejão hoje? Tava enorme",
                "Tô vivendo à base de café esses dias...",
                "Greve? Alguém falou em greve?",
                "Vou dormir na biblioteca hoje, certeza.",
                "É... tem que ver com os cara.",
                "Vamos no Módulo 8 hoje?",
                "O Sagres caiu... de novo.",
                "Se a gente sair agora, ainda da tempo de pega o ônibus...",
                "Será que dá tempo de mudar de curso ainda?",
                "Tô considerando seriamente abrir um quiosque na praia.",
                "A gente fazendo engenharia só para virar Uber no final... pelo menos o diploma vai ficar bonito na parede.",
                "Alguém tem o link do Drive com as provas dos semestres passados?",
                "Tô com três trabalhos atrasados e um sonho.",
                "Você sabe onde fica o colegiado? Vou lá trancar o curso.",
                "Se eu dormir 4 horas hoje, já é lucro.",
                "Acho que vou virar artista de rua, tá mais fácil que Cálculo 2.",
                "O ar-condicionado do LEDS tá no modo congelar.",
                "O Wi-Fi da faculdade é movido a manivela, não é possível.",
                "O café do Módulo 5 hoje tá parecendo chá de tão fraco...",
                "Alguém tem o PDF do Guidorizzi pra me passar?",
                "A fila do RU tá dando a volta já, desisto.",
                "O semestre mal começou e eu já tô pedindo férias.",
                "A aula de hoje é teórica ou a gente vai pro laboratório?",
                "A gente finge que entende a aula e o professor finge que explica...",
                "Vou ter que varar a noite pra terminar esse relatório.",
                "Se eu formar em 7 anos, ainda assim é milagre.",
                "Se meu código não rodar agora, eu vou pro bar.",
                "Vou trancar o curso, não tem jeito",
                "Tchê tchê",
                "Alguém tem um cabo HDMI ou adaptador pra emprestar?",
                "Sou trouxa por estar fazendo o trabalho em grupo sozinho de novo?",
                "Se eu tirar 5 nessa prova, eu saio daqui comemorando.",
                "Tô quase trancando pra não perder o réu primário.",
                "Cê viu Scooby?",
                "O edital da monitoria saiu.",
                "A aula de hoje é do 'aqui é tchai aqui ó tomi'.",
                "E ele ainda teve a audâcia de dizer que se ele consegue em uma semana, nós conseguimos em um mês... O CARA TEM DOUTORADO!",
                "Vou ter que pedir revisão de prova, esse 1,9 foi maldade pura.",
                "Será que tem sala vazia?",
                "Da tempo de cochilar antes da aula hein.",
                "Preciso de uma tomada.",
                "COMO ASSIM EU NÃO VOU ENTENDER? SE O CARA ESCREVEU VELHO, EU SÓ VOU ENTENDER O QUE ELE ESCREVEU, IMAGINA QUE ELE TEVE QUE TIRAR DO ZERO AQUILO TUDO, EU SÓ TENHO QUE APLICAAAAAR",
                "O cara me dá 10 listas de 50 exercícios e diz: 'É pra fixar o conteúdo'. Fixar o que? Minha depressão?",
                "Não, porque o não eu já tenho, eu tô aqui buscando é a humilhação!",
                "Eu sou o mestre do 'vai dar tudo certo' enquanto tudo ao meu redor tá pegando fogo.",
                "O professor: 'A prova tá fácil'. A prova: Calcule a massa do sol usando uma régua e um clipe de papel.",
                "Tô aqui só pelo diploma, porque a sanidade mental eu deixei na matrícula.",
                "Eu não estudo mais, eu só abro o livro e fico esperando o conhecimento entrar por osmose.",
                "Se Deus quiser... porque Ele sabe o que eu tô passando nessa faculdade!",
                "Olha pra minha cara, você acha que eu dormi? Eu não durmo desde o primeiro semestre!",
                "É sobre isso e tá tudo mal, mas a gente continua porque o café ainda tá fazendo efeito.",
                "Eu só queria um 5, senhor, um 5 e eu nunca mais reclamo de nada nessa vida!",
                "A minha vida acadêmica se resume a: 'Entendi, mas não compreendi'.",
                "Eu só queria que o Wi-Fi da faculdade fosse tão rápido quanto a minha vontade de ir embora.",
                "A regra é clara: se o professor não chegar em 15 minutos, a gente tá legalmente liberado.",
                "Minha vida se resume a: olhar pro quadro, olhar pro caderno e aceitar que eu não sei o que tá acontecendo.",
                "Gente, eu não sou estudante, eu sou um sobrevivente do sistema educacional brasileiro!!!!",
                "É sobre isso e não ta nada bem."

        );
    }
}
