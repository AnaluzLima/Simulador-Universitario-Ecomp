package PBL.fase_1.service;

import PBL.fase_1.model.minigames.MinigameHardware;
import PBL.fase_1.model.minigames.MinigameMatematica;
import PBL.fase_1.model.minigames.MinigameSoftware;
import PBL.fase_1.model.minigames.MinigameTexto;
import PBL.fase_1.repository.MinigameRepository;
import java.util.List;
import java.util.Random;

public class MinigameService {

    private MinigameRepository repo = new MinigameRepository();
    private Random random = new Random();

    public void prepararMinigameTexto(MinigameTexto minigame, int desempenhoAtual) {

        String dificuldade = minigame.calcularDificuldade(desempenhoAtual);

        List<String> textosPossiveis = repo.textos(dificuldade);

        String textoSorteado = textosPossiveis.get(random.nextInt(textosPossiveis.size()));
        minigame.setTextoAtual(textoSorteado);
    }

    public void prepararMinigameMatematica(MinigameMatematica minigame, int desempenhoAtual) {
        String dificuldade = minigame.calcularDificuldade(desempenhoAtual);

        List<MinigameRepository.Equacao> contas = repo.equacoes(dificuldade);

        MinigameRepository.Equacao contaSorteada = contas.get(random.nextInt(contas.size()));

        minigame.setRodada(contaSorteada.getPergunta(), contaSorteada.getResposta());
    }

    public void prepararMinigameSoftware(MinigameSoftware minigame, int desempenhoAtual) {
        String dificuldade = minigame.calcularDificuldade(desempenhoAtual);
        List<MinigameRepository.QuizSoftware> perguntas = repo.quizzesSoftware(dificuldade);

        MinigameRepository.QuizSoftware sorteada = perguntas.get(random.nextInt(perguntas.size()));
        minigame.setRodada(sorteada.pergunta, sorteada.alternativas, sorteada.indiceCorreto);
    }

    public void prepararMinigameHardware(MinigameHardware minigame, int desempenhoAtual) {
        String dificuldade = minigame.calcularDificuldade(desempenhoAtual);
        List<MinigameRepository.CircuitoHardware> circuitos = repo.circuitosHardware(dificuldade);

        MinigameRepository.CircuitoHardware sorteado = circuitos.get(random.nextInt(circuitos.size()));
        minigame.setRodada(sorteado.instrucao, sorteado.pinoOrigem, sorteado.pinoDestino); // Atualizado
    }
}