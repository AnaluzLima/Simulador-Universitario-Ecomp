# Simulador Universitário (ECOMP)

## Sobre o Projeto
Simulador de vida universitária focado na jornada de um estudante de Engenharia da Computação na Universidade Estadual de Feira de Santana (UEFS). O sistema permite gerenciar status de sobrevivência (tempo, energia, dinheiro, conhecimento, saúde e motivação) e progresso acadêmico ao longo dos semestres. 

O projeto foi desenvolvido aplicando a POO e o padrão arquitetural MVC.

## Principais Funcionalidades
* **Gerenciamento de Personagem:** Controle de atributos dinâmicos como motivação, energia, saúde, conhecimento, dinheiro e tempo.
* **Sistema Acadêmico:** Histórico escolar, sistema de notas, pré-requisitos, co-requisitos e transição de semestres.
* **Navegação:** Mapa do campus construído utilizando estrutura de dados em grafos (de forma medíocre), permitindo locomoção entre módulos, praças e pontos de ônibus.
* **Atividades Dinâmicas:** Sistema polimórfico onde cada local do mapa oferece interações específicas (estudar, ir ao bandejão, assistir aulas, etc).
* **Eventos de Tempo:** Ações consomem pontos de tempo e afetam o estado geral do jogador.

## Tecnologias Utilizadas
* **Linguagem:** Java
* **Testes de Unidade:** JUnit
* **Interface Gráfica:** JavaFX (em implementação)
* **Arquitetura:** MVC (Model-View-Controller)

## Como Executar
1. Clone este repositório em sua máquina local.
2. Abra o projeto em sua IDE de preferência (recomendado: IntelliJ IDEA).
3. Certifique-se de configurar o SDK do Java correspondente.
4. Execute a classe principal do jogo.
