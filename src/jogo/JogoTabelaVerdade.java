package jogo;

import tabelaVerdade.Variavel;
import util.Funcoes;
import util.Input;

import java.util.ArrayList;

public class JogoTabelaVerdade {
    private ArrayList<Variavel> listaVariaveis;
    private ArrayList<String> listaConectivos, respostasUsuario, respostasCorretas;
    private int numVariaveis, numRodadas, acertos, nivel;

    public void setGame(int nivel){
        this.nivel = nivel;
        if (nivel == 1) {
            listaVariaveis = new ArrayList<>();
            listaConectivos = new ArrayList<>();
            respostasUsuario = new ArrayList<>();
            respostasCorretas = new ArrayList<>();
        }
        numVariaveis = MetodosPrincipaisJogo.getNumVariaveis(nivel);

        String pergunta = "Quantas rodadas você gostaria de jogar no nível " + nivel + ": ";

        numRodadas = Funcoes.getInt(pergunta);
    }

    public void startGame(){
        acertos = MetodosPrincipaisJogo.jogo(listaVariaveis, listaConectivos, respostasUsuario, respostasCorretas, numVariaveis, numRodadas, nivel);

        MetodosPrincipaisJogo.printResultados(respostasUsuario, respostasCorretas, acertos);

        if (nivel == 2){
            Input.getInstance().closeScan();
        }
    }

    public void checkNivel2(){
        MetodosPrincipaisJogo.check(acertos, numRodadas);

        respostasUsuario.clear();
        respostasCorretas.clear();
        listaVariaveis.clear();
        listaConectivos.clear();
    }
}
