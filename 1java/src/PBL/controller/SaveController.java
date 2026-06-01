package PBL.controller;

import PBL.exception.JogoException;
import PBL.model.model.Aparencia;
import PBL.model.model.Jogador;
import PBL.model.model.Jogo;
import PBL.model.model.Mapa;
import PBL.model.repository.*;
import PBL.model.service.JogoService;

import java.util.List;

public class SaveController {
    private final JogoService jogoService;
    private final HistoricoRepository historicoRepo;
    private final MinigameRepository minigameRepo;
    private final MapaRepository mapaRepo;
    private final NpcRepository npcRepo;

    private Mapa mapa;
    private Jogo jogoAtual;


    public SaveController() throws JogoException {
        //inicializa todas os repositórios necessários
        JogoRepository jogoRepo = new JogoRepository();
        this.historicoRepo = new HistoricoRepository();
        this.minigameRepo = new MinigameRepository();
        this.mapaRepo = new MapaRepository();
        this.npcRepo = new NpcRepository();

        //injeta as dependencias no Service
        this.jogoService = new JogoService(jogoRepo, historicoRepo, minigameRepo);
    }

    //chamado quando o jogador clica no botão Novo Jogo
    public Jogo iniciarNovoJogo(String nomeJogador, String slot, boolean spawnFeira6) throws JogoException {

        //cria o personagem do zero
        Aparencia skinInicial = new Aparencia("padrão");
        Jogador novoJogador = new Jogador(nomeJogador, skinInicial, spawnFeira6);

        //gera o mundo (Mapa e NPCs) do zero
        this.mapa = new Mapa(spawnFeira6);
        npcRepo.carregarNPCs();
        mapaRepo.carregarMapa(this.mapa, spawnFeira6, npcRepo);

        //gera a grade curricular completa do curso para o jogador
        historicoRepo.carregarHistorico(novoJogador.getHistorico(), this.mapa, minigameRepo);

        //monta o pacote final do Jogo e coloca o jogador no spawn
        novoJogador.setLocalizacao(this.mapa.getSpawn());
        Jogo novoJogo = new Jogo(slot, novoJogador);

        //salva o progresso inicial e distribui as primeiras aulas
        jogoService.salvarJogo(novoJogo);
        mapa.distribuirAulas(novoJogador);

        this.jogoAtual = novoJogo;
        return novoJogo;
    }

    //chamado quando o jogador clica no botão "Carregar" e escolhe um slot.
    public Jogo carregarJogoExistente(String slot) throws JogoException {
        //Lê o arquivo só para saber o spawn do jogador
        Jogo rascunho = jogoService.carregarRascunho(slot);
        boolean spawnF6 = rascunho.getJogador().isSpawnF6();

        //como os locais e NPCs não são salvos, recria-se o mundo base antes de dar o load
        this.mapa = new Mapa(spawnF6);
        npcRepo.carregarNPCs();
        mapaRepo.carregarMapa(this.mapa, true, npcRepo);

        //pega os status do jogador e coloca nesse mundo base
        Jogo jogoCarregado = jogoService.carregarJogo(slot, this.mapa);
        this.jogoAtual = jogoCarregado;
        return jogoCarregado;
    }

    //chamado quando o jogador clica no ícone de lixeira em um slot de save.
    public void apagarSave(String slot) throws JogoException {
        jogoService.apagarJogo(slot);
    }

    public boolean saveExiste(String slot) throws JogoException {
        JogoRepository repo = new JogoRepository();
        return repo.existeSave(slot);
    }
    public List<String> listarSalvesDisponiveis() throws JogoException {
        JogoRepository repo = new JogoRepository();
        return repo.listarSlots();
    }

    public Mapa getMapa() {
        return this.mapa;
    }

    public void salvarJogoAtual() throws JogoException {
        jogoService.salvarJogo(this.jogoAtual);
    }
    //essa classe será instanciado na tela de jogo apenas quando o jogador clicar no botão de salvar
    //entao esse metodo pega o jogo diretamente da sessso salve
    public void salvarJogoDaSessao() throws JogoException {
        Jogo jogo = PBL.controller.jogo.SessaoJogo.getInstancia().getJogoAtivo();
        jogoService.salvarJogo(jogo);
    }
}
