package PBL.controller;

import PBL.exception.JogoException;
import PBL.fase_1.model.Aparencia;
import PBL.fase_1.model.Jogador;
import PBL.fase_1.model.Jogo;
import PBL.fase_1.model.Mapa;
import PBL.fase_1.repository.*;
import PBL.fase_1.service.JogoService;

public class PersistenciaController {
    private final JogoService jogoService;
    private final HistoricoRepository historicoRepo;
    private final MinigameRepository minigameRepo;
    private final MapaRepository mapaRepo;
    private final NpcRepository npcRepo;

    public PersistenciaController() throws JogoException {
        //inicializa todas os repositórios necessários

        JogoRepository jogoRepo = new JogoRepository();
        this.historicoRepo = new HistoricoRepository();
        this.minigameRepo = new MinigameRepository();
        this.mapaRepo = new MapaRepository();
        this.npcRepo = new NpcRepository();

        //injeta as dependências no Service
        this.jogoService = new JogoService(jogoRepo, historicoRepo, minigameRepo);
    }


    // Chamado quando o jogador clica no botão "Novo Jogo".

    public Jogo iniciarNovoJogo(String nomeJogador, int slot, boolean spawnFeira6) throws JogoException {

        //cria o personagem do zero
        Aparencia skinInicial = new Aparencia("padrão");
        Jogador novoJogador = new Jogador(nomeJogador, skinInicial);

        //gera o mundo (Mapa e NPCs) do zero
        Mapa mapa = new Mapa(spawnFeira6);
        npcRepo.carregarNPCs();
        mapaRepo.carregarMapa(mapa, spawnFeira6, npcRepo);

        //gera a grade curricular completa do curso para o jogador
        historicoRepo.carregarHistorico(novoJogador.getHistorico(), mapa, minigameRepo);

        //monta o pacote final do Jogo e coloca o jogador no spawn
        novoJogador.setLocalizacao(mapa.getSpawn());
        Jogo novoJogo = new Jogo(slot, novoJogador, mapa);

        //salva o progresso inicial e distribui as primeiras aulas
        jogoService.salvarJogo(novoJogo);
        mapa.distribuirAulas(novoJogador);

        return novoJogo;
    }

    //Chamado quando o jogador clica no botão "Carregar" e escolhe um slot.

    public Jogo carregarJogoExistente(int slot) throws JogoException {

        //como os locais e NPCs não são salvos, recria-se o mundo base antes de dar o load
        Mapa mapaBase = new Mapa(true);
        npcRepo.carregarNPCs();
        mapaRepo.carregarMapa(mapaBase, true, npcRepo);

        //o Service vai no JSON, pega os status do jogador e coloca nesse mundo base

        return jogoService.carregarJogo(slot, mapaBase);
    }

    // Chamado quando o jogador clica no ícone de "Lixeira" em um slot de save.

    public void apagarSave(int slot) throws JogoException {
        jogoService.apagarJogo(slot);
    }
}
