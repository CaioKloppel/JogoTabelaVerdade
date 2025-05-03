package jogo.metodos;

import tabelaVerdade.Conectivo;
import tabelaVerdade.Variavel;
import util.Input;

import java.util.ArrayList;

abstract class MetodosBaseJogo {

    String getResposta(){
        String resposta = Input.getInstance().scanNextLine().toUpperCase();
        while (!(resposta.equals("V") || resposta.equals("F"))){
            System.out.print("Aceita somente respostas 'V' ou 'F': ");
            resposta = Input.getInstance().scanNextLine().toUpperCase();
        }
        return resposta;
    }

    void setVariaveis(ArrayList<Variavel> listaVariaveis, int numVariaveis){
        for (int j = 0; j < numVariaveis; j++){
            char nome_variavel = (char) ('A' + j);
            Variavel variavel = new Variavel(nome_variavel);
            variavel.Sortear();
            listaVariaveis.add(variavel);
        }
    }

    void setConectivos(ArrayList<String> listaConectivos, int numVariaveis, int nivel){
        if (nivel == 1){
            for (int x = 0; x < numVariaveis - 1; x++) {
                Conectivo conectivo = new Conectivo();
                conectivo.setConectivoNivel1();
                listaConectivos.add(conectivo.getConectivo());
            }
        }else{
            for (int x = 0; x < numVariaveis - 1; x++) {
                Conectivo conectivo = new Conectivo();
                conectivo.setConectivoNivel2();
                listaConectivos.add(conectivo.getConectivo());
            }
        }
    }

    void printEnunciado(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaConectivos, ArrayList<String> respostasUsuario, int numVariaveis, int rodada){
        System.out.printf("Nivel 1; Rodada %d: ", rodada + 1);
        for (int i = 0; i < numVariaveis; i++){
            System.out.printf("%c = %s | ", listaVariaveis.get(i).getNome(), listaVariaveis.get(i).getResult());
        }
        for (int j = 0; j < numVariaveis; j++){
            if (j == numVariaveis - 1){
                System.out.printf("%s%c: ", listaVariaveis.get(j).getCondicao(), listaVariaveis.get(j).getNome());
                String resposta = getResposta();
                respostasUsuario.add(resposta);
            }else{
                System.out.printf("%s%c %s ", listaVariaveis.get(j).getCondicao(), listaVariaveis.get(j).getNome(), listaConectivos.get(j));
            }
        }
        System.out.println();
    }

    void inverter(ArrayList<Variavel> listaVariaveis){
        for(Variavel variavel : listaVariaveis){
            if (variavel.getCondicao().equals("~")){
                if (variavel.getResult().equals("V")){
                    variavel.setResult("F");
                }else{
                    variavel.setResult("V");
                }
            }
        }
    }

    void result(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaCondicoes, int index, String v_f){
        listaVariaveis.get(index).setResult(v_f);
        listaVariaveis.remove(index + 1);
        listaCondicoes.remove(index);
    }

    String resposta(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaCondicoes) {
        while (listaVariaveis.size() != 1){
            if (listaCondicoes.contains("AND")){
                int index = listaCondicoes.indexOf("AND");
                if (listaVariaveis.get(index).getResult().equals("V") && listaVariaveis.get(index + 1).getResult().equals("V")){
                    result(listaVariaveis, listaCondicoes,index,"V");
                } else{
                    result(listaVariaveis, listaCondicoes,index,"F");
                }
            } else if (listaCondicoes.contains("OR")){
                int index = listaCondicoes.indexOf("OR");
                if (listaVariaveis.get(index).getResult().equals("V") || listaVariaveis.get(index + 1).getResult().equals("V")){
                    result(listaVariaveis, listaCondicoes,index,"V");
                } else{
                    result(listaVariaveis, listaCondicoes,index,"F");
                }
            } else if (listaCondicoes.contains("->")){
                int index = listaCondicoes.indexOf("->");
                if (listaVariaveis.get(index).getResult().equals("F") || listaVariaveis.get(index + 1).getResult().equals("V")){
                    result(listaVariaveis, listaCondicoes,index,"V");
                } else{
                    result(listaVariaveis, listaCondicoes,index,"F");
                }
            } else if (listaCondicoes.contains("<->")){
                int index = listaCondicoes.indexOf("<->");
                if (listaVariaveis.get(index).getResult().equals(listaVariaveis.get(index + 1).getResult())){
                    result(listaVariaveis, listaCondicoes,index,"V");
                } else{
                    result(listaVariaveis, listaCondicoes,index,"F");
                }
            } else if (listaCondicoes.contains("↑")){
                int index = listaCondicoes.indexOf("↑");
                if (listaVariaveis.get(index).getResult().equals("V") && listaVariaveis.get(index + 1).getResult().equals("V")){
                    result(listaVariaveis, listaCondicoes,index,"F");
                } else{
                    result(listaVariaveis, listaCondicoes,index,"V");
                }
            } else if (listaCondicoes.contains("↓")) {
                int index = listaCondicoes.indexOf("↓");
                if (listaVariaveis.get(index).getResult().equals("V") || listaVariaveis.get(index + 1).getResult().equals("V")){
                    result(listaVariaveis, listaCondicoes,index,"F");
                } else{
                    result(listaVariaveis, listaCondicoes,index,"V");
                }
            } else if (listaCondicoes.contains("⊕")){
                int index = listaCondicoes.indexOf("⊕");
                if (listaVariaveis.get(index).getResult().equals(listaVariaveis.get(index + 1).getResult())){
                    result(listaVariaveis, listaCondicoes,index,"F");
                } else{
                    result(listaVariaveis, listaCondicoes,index,"V");
                }
            }
        }
        return listaVariaveis.getFirst().getResult();
    }

    int acertos(ArrayList<String> respostasUsuario, ArrayList<String> respostasCertas){
        int acertos = 0;
        for (int i = 0; i < respostasCertas.size(); i++){
            if (respostasCertas.get(i).equals(respostasUsuario.get(i))){
                acertos++;
            }
        }
        return acertos;
    }
}
