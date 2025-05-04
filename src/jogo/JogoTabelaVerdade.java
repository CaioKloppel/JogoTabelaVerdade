package jogo;

import tabelaVerdade.Variavel;
import util.Funcoes;
import util.Input;

import java.util.ArrayList;

public class JogoTabelaVerdade {
    private final MetodosPrincipaisJogo metodosJogo = new MetodosPrincipaisJogo();
    ArrayList<Variavel> listaVariaveis;
    ArrayList<String> listaConectivos, respostasUsuario, respostasCorretas;
    int numVariaveis, numRodadas, acertos, nivel;
    String pergunta;

    public void setGame(int nivel){
        this.nivel = nivel;
        if (nivel == 1) {
            listaVariaveis = new ArrayList<>();
            listaConectivos = new ArrayList<>();
            respostasUsuario = new ArrayList<>();
            respostasCorretas = new ArrayList<>();
        }
        numVariaveis = metodosJogo.getNumVariaveis(nivel);

        pergunta = "Quantas rodadas você gostaria de jogar no nível " + nivel + ": ";

        numRodadas = Funcoes.getInt(pergunta);
    }

    public void startGame(){
        acertos = metodosJogo.jogo(listaVariaveis, listaConectivos, respostasUsuario, respostasCorretas, numVariaveis, numRodadas, nivel);

        metodosJogo.printResultados(respostasUsuario, respostasCorretas, acertos);

        if (nivel == 2){
            Input.getInstance().closeScan();
        }
    }

    public void checkNivel2(){
        metodosJogo.check(acertos, numRodadas);

        respostasUsuario.clear();
        respostasCorretas.clear();
        listaVariaveis.clear();
        listaConectivos.clear();
    }
}
