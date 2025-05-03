import jogo.JogoTabelaVerdade;

public class Main {
    private static final int PRIMEIRO_NIVEL = 1;
    private static final int SEGUNDO_NIVEL = 2;

    public static void main(String[] args) {
        JogoTabelaVerdade jogo = new JogoTabelaVerdade();
        jogo.setGame(PRIMEIRO_NIVEL);
        jogo.startGame();
        jogo.checkNivel2();
        jogo.setGame(SEGUNDO_NIVEL);
        jogo.startGame();
    }
}