# 🧠 Jogo da Tabela Verdade

Este projeto é um jogo interativo em Java que desafia o jogador a resolver expressões lógicas utilizando operadores booleanos como `AND`, `OR`, `->`, `<->`, `↑`, `↓`, e `⊕`. O objetivo é responder corretamente se a expressão lógica apresentada é verdadeira (V) ou falsa (F).

## 🚀 Como Funciona

O jogo possui dois níveis de dificuldade:

- **Nível 1:** Usa conectivos lógicos básicos (`AND`, `OR`, `->`, `<->`).
- **Nível 2:** Introduz conectivos mais complexos (`↑`, `↓`, `⊕`).

### Etapas do jogo:

1. O jogador escolhe quantas variáveis lógicas utilizar.
2. Escolhe o número de rodadas que deseja jogar.
3. Em cada rodada, uma expressão lógica é gerada aleatoriamente.
4. O jogador deve indicar se a expressão é `V` ou `F`.
5. Ao final, o número de acertos é exibido.

Se o jogador acertar pelo menos 50% no nível 1, ele avança para o nível 2.

---

##  🤓 Exemplo de Jogo

```
Com quantas variáveis você gostaria de jogar no nível 1: 3
Quantas rodadas você gostaria de jogar no primeiro nível 1: 2
Nivel 1; Rodada 1: A = V | B = F | C = V | A AND B OR C:
Aceita somente respostas 'V' ou 'F': V
...
Você teve 1 acerto(s)!
Suas respostas: V | F
Respostas corretas: F | F
```
---
## 📖 Lógica Implementada

A lógica considera precedência entre operadores e avalia as expressões da esquerda para a direita, resolvendo os conectivos em ordem definida para evitar ambiguidade.

---
## 📂 Estrutura do Projeto

- `Main.java` — Ponto de entrada do jogo.
- `JogoTabelaVerdade.java` — Coordena as etapas do jogo.
- `MetodosPrincipaisJogo.java` — Métodos principais usados no jogo.
- `MetodosBaseJogo.java` — Métodos base auxiliares.
- `Conectivo.java` — Lida com a geração dos operadores lógicos.
- `Variavel.java` — Representa variáveis booleanas.
- `Tabela.java` — Estrutura base com os conectivos e valores possíveis.
- `Funcoes.java` — Utilitário para entrada segura de inteiros.
- `Input.java` — Singleton responsável pela leitura de entradas do jogador.

---
## 👨‍💻 Autor

Projeto desenvolvido por Caio Klöppel.

---

## 📜 Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
