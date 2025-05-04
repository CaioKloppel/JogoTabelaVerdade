package jogo;

import tabelaVerdade.Variavel;
import util.Funcoes;
import util.Input;

import java.util.ArrayList;

class MetodosPrincipaisJogo {

    static public int jogo(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaConectivos, ArrayList<String> respostasUsuario, ArrayList<String> respostasCorretas, int numVariaveis, int numRodadas, int nivel){
        for (int i = 0; i < numRodadas; i++){
            MetodosBaseJogo.setVariaveis(listaVariaveis, numVariaveis);
            MetodosBaseJogo.setConectivos(listaConectivos, numVariaveis, nivel);
            MetodosBaseJogo.printEnunciado(listaVariaveis, listaConectivos, respostasUsuario, numVariaveis, i);

            MetodosBaseJogo.inverter(listaVariaveis);
            respostasCorretas.add(MetodosBaseJogo.resposta(listaVariaveis, listaConectivos));

            listaVariaveis.clear();
            listaConectivos.clear();
        }

        return MetodosBaseJogo.acertos(respostasUsuario, respostasCorretas);
    }

    static public int getNumVariaveis(int nivel){
        int numVariaveis = 0;
        while (numVariaveis < 1) {
            numVariaveis = Funcoes.getInt("Com quantas variáveis você gostaria de jogar no nível " + nivel + ": ");
            if (numVariaveis < 1){
                System.out.println("Aceita somente números inteiros maiores do que 0");
            }
        }
        return numVariaveis;
    }

    static public void printResultados(ArrayList<String> respostasUsuario, ArrayList<String> respostasCorretas, int acertos){
        System.out.printf("Você teve %d acerto(s)!!\nSuas respostas: %s\nRespostas corretas: %s\n", acertos, String.join(" | ", respostasUsuario), String.join(" | ", respostasCorretas));
    }

    static public void check(int acertos, int numRodadas){
        if (acertos < numRodadas / 2.0){
            System.out.println("Você não atingiu o mínimo de 50% e não passou para o próximo nível.");
            Input.getInstance().closeScan();
            System.exit(0);
        }
        else {
            System.out.println("Parabéns!! Você atingiu o mínimo de 50% e passou para o próximo nível.");
        }
    }

}
