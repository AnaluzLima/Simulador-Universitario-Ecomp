package PBL.controller.menu;

public class SessaoNovoJogo {

    private static SessaoNovoJogo instancia;

    private String nomeJogador;
    private String nomePartida;
    private boolean spawnF6;

    private SessaoNovoJogo() {}

    public static SessaoNovoJogo getInstancia() {
        if (instancia == null) {
            instancia = new SessaoNovoJogo();
        }
        return instancia;
    }

    //chamado pelo NovoJogoController ao clicar Próximo
    public void salvar(String nomeJogador, String nomePartida) {
        this.nomeJogador = nomeJogador;
        this.nomePartida = nomePartida;
    }

    //chamado pelo ResidenciaController ao escolher o spawn
    public String getNomeJogador(){
        return nomeJogador;
    }
    public String getNomePartida(){
        return nomePartida;
    }
    public boolean isSpawnF6(){
        return spawnF6;
    }

    public void setSpawnF6(boolean spawnF6) { this.spawnF6 = spawnF6; }

    //limpa os dados após o jogo ser criado
    public void limpar() {
        this.nomeJogador = null;
        this.nomePartida = null;
        this.spawnF6 = false;
    }
}
