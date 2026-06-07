package PBL.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayoutCenarios {
    //dados de fundo e posição do jogador
    public static class DadosCenario {
        public final String caminhoImagem;
        public final double spawnX;
        public final double spawnY;
        public final double tamanhoJogador;

        public DadosCenario(String img, double x, double y, double tamanho) {
            this.caminhoImagem = img;
            this.spawnX = x;
            this.spawnY = y;
            this.tamanhoJogador = tamanho;
        }
    }

    private static final Map<String, DadosCenario> cenariosVisuais = new HashMap<>();

    static {
        //coloca as imagens do locais e define a posição/tamanho do jogador nesse cenário especifico
        cenariosVisuais.put("Ponto de Ônibus", new DadosCenario("/resources/Locais/PontoOnibusLocal.png", 350.0, 420.0, 200.0));
        cenariosVisuais.put("Portão Lateral", new DadosCenario("/resources/Locais/PortaoLateralLocal.png", 530.0, 500.0, 220.0));
        cenariosVisuais.put("Entrada da Biblioteca", new DadosCenario("/resources/Locais/EntradaBibliotecaLocal.png", 670.0, 500.0, 150.0));
        cenariosVisuais.put("Biblioteca", new DadosCenario("/resources/Locais/BibliotecaLocal.png", 570.0, 580.0, 150.0));
        cenariosVisuais.put("Cantina do M3", new DadosCenario("/resources/Locais/Cantina3Local.png", 366.0, 400.0, 150.0));
    }

    public static DadosCenario getVisualCenario(String nomeLocal) {
        return cenariosVisuais.getOrDefault(nomeLocal,
                new DadosCenario("/resources/Locais/LugarDefault.png", 640.0, 360.0, 150.0));
    }

    //direções e zonas

    public static class DirecaoCenario {
        public final double x;
        public final double y;
        public final List<String> itensNestaDirecao;

        public DirecaoCenario(double x, double y, java.util.List<String> itens) {
            this.x = x;
            this.y = y;
            this.itensNestaDirecao = itens;
        }
    }

    //mapeia o nome do local -> nome da direção -> dados da direção
    private static final Map<String, Map<String, DirecaoCenario>> direcoes = new HashMap<>();

    static {
        //PONTO DE ÔNIBUS
        Map<String, DirecaoCenario> pontoOnibus = new HashMap<>();
        pontoOnibus.put("Esquerda", new DirecaoCenario(780.0, 460.0,
                java.util.Arrays.asList("Descansar em casa", "Estudar em Casa", "Ir ao Médico", "Sair para se divertir")));

        pontoOnibus.put("Rua", new DirecaoCenario(500.0, 653.0,
                java.util.Arrays.asList("Módulo 7", "Módulo 6", "Módulo 5", "Entrada da Biblioteca",
                        "Módulo 4", "Cantina do M3", "Módulo 2", "Módulo 1", "Feirinha")));
        direcoes.put("Ponto de Ônibus", pontoOnibus);

        //PORTÃO LATERAL
        Map<String, DirecaoCenario> portao = new HashMap<>();
        portao.put("Saída", new DirecaoCenario(630.0, 450.0,
                java.util.Arrays.asList("Descansar em casa", "Estudar em Casa", "Sair para se divertir")));

        portao.put("Baixo", new DirecaoCenario(770.0, 680.0,
                java.util.Arrays.asList("Bandejão", "Entrada da Biblioteca", "Cantina do M3")));
        direcoes.put("Portão Lateral", portao);

        //ENTRADA DA BIBLIOTECA
        Map<String, DirecaoCenario> entradaBiblioteca = new HashMap<>();
        entradaBiblioteca.put("Porta", new DirecaoCenario(628.0, 530.0,
                java.util.Arrays.asList("Biblioteca")));

        entradaBiblioteca.put("Baixo", new DirecaoCenario(800.0, 680.0,
                java.util.Arrays.asList("Módulo 5", "Módulo 4", "Ponto de Ônibus")));

        entradaBiblioteca.put("Esquerda", new DirecaoCenario(520.0, 680.0,
                java.util.Arrays.asList("Portão Lateral")));

        direcoes.put("Entrada da Biblioteca", entradaBiblioteca);

        //BIBLIOTECA
        Map<String, DirecaoCenario> biblioteca = new HashMap<>();
        biblioteca.put("Escada", new DirecaoCenario(140.0, 370.0,
                java.util.Arrays.asList("Estudar na Biblioteca")));

        biblioteca.put("Sair", new DirecaoCenario(770.0, 680.0,
                java.util.Arrays.asList("Entrada da Biblioteca")));

        direcoes.put("Biblioteca", biblioteca);

        //CANTINA DO 3
        Map<String, DirecaoCenario> cantina3 = new HashMap<>();
        cantina3.put("Cantinar", new DirecaoCenario(700.0, 300.0,
                java.util.Arrays.asList("Comprar Comida")));
        cantina3.put("Cima", new DirecaoCenario(400.0, 290.0,
                java.util.Arrays.asList("Ponto de Ônibus", "Portão Lateral")));

        direcoes.put("Cantina do M3", cantina3);
    }

    public static Map<String, DirecaoCenario> getDirecoesLocal(String nomeLocal) {
        return direcoes.getOrDefault(nomeLocal, new HashMap<>());
    }
}