package PBL.model.model;

import PBL.exception.JogoException;

import java.util.*;

/**Essa classe tem como objetivo armazenar as skins do jogador
 * A ideia é que no menu inicial, ao criar um novo jogo, o jogador possa escolher qual será seu personagem.
 * Ao longo do jogo, ele poderá completar missões para ganhar novos acessórios para seu personagem, ou comprar com dionheiro.
 *
 * Essa classe foi modificada para a fase 2, removendo funções que não serão mais necessárias e acrescentando novas, como a Lista de acessorios equipados,
 * o estado atual de movimento do jogador e a direção.*/



public class Aparencia {
    private final String skinBase; //sprite base do jogador
    private Set<String> acessoriosDesbloqueados;
    private List<String> acessoriosEquipados;
    private String estadoAtual; // idle, walk, interact, ...
    private String direcaoAtual; // frente, costas, esquerda direita

    private static final List<String> ORDEM_CAMADAS = Arrays.asList(
            "camisa", "oculos", "luva", "folhas", "flor", "blushed"
        // Camada mais baixa                                Camada mais alta
    );

    public Aparencia(String skinBase) {
        this.acessoriosEquipados = new ArrayList<>();
        this.acessoriosDesbloqueados = new HashSet<>();

        this.skinBase = skinBase;
        this.estadoAtual = "idle";
        this.direcaoAtual = "direita";

        this.acessoriosDesbloqueados.add("padrão");
        this.acessoriosEquipados.add("padrão");
    }

    public String getSkinBase(){
        return this.skinBase;
    }

    public List<String> getAcessoriosEquipados() {
        //copia da lista
        List<String> listaOrdenada = new ArrayList<>(this.acessoriosEquipados);

        //ordenar com base no indice da lista oficial de camadas
        listaOrdenada.sort((a1, a2) -> Integer.compare(ORDEM_CAMADAS.indexOf(a1), ORDEM_CAMADAS.indexOf(a2)));

        return listaOrdenada;
    }

    public void toggleAcessorio(String acessorio) throws JogoException {
        //so pode mexer se já tiver pego o acessório ao longo do jogo
        if (!this.acessoriosDesbloqueados.contains(acessorio)) {
            throw new JogoException("Você ainda não desbloqueou este acessório!");
        }

        //se já estiver equipado o clique desequipa
        if (this.acessoriosEquipados.contains(acessorio)) {
            this.acessoriosEquipados.remove(acessorio);
        }
        //se não estiver equipado o clique equipa
        else {
            this.acessoriosEquipados.add(acessorio);
        }
    }

    public void desbloquearAcessorio(String acessorio){
        this.acessoriosDesbloqueados.add(acessorio);
    }
    public boolean isAcessorioDesbloqueado(String acessorio) {
        return this.acessoriosDesbloqueados.contains(acessorio);
    }

    public List<String> getAcessoriosDesbloqueados(){
        return new ArrayList<>(acessoriosDesbloqueados);
    }

    public String getEstadoAtual() {
        return this.estadoAtual;
    }
    public void setEstadoAtual(String estadoAtual) {
        this.estadoAtual = estadoAtual;
    }
    public String getDirecaoAtual() {
        return this.direcaoAtual;
    }
    public void setDirecaoAtual(String direcaoAtual) {
        this.direcaoAtual = direcaoAtual;
    }
}