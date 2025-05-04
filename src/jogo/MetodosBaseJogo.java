package jogo;

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

    void setResultOnList(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaCondicoes, int index, String v_f){
        listaVariaveis.get(index).setResult(v_f);
        listaVariaveis.remove(index + 1);
        listaCondicoes.remove(index);
    }

    String resposta(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaCondicoes) {
        while (listaVariaveis.size() > 1) {
            int index;
            String condicao;

            int idxAnd  = listaCondicoes.indexOf("AND");
            int idxNand = listaCondicoes.indexOf("↑");
            if (idxAnd != -1 || idxNand != -1) {
                if (idxNand != -1 && (idxAnd == -1 || idxNand < idxAnd)) {
                    index = idxNand;
                    condicao    = "↑";
                } else {
                    index = idxAnd;
                    condicao    = "AND";
                }
            } else {
                int idxOr  = listaCondicoes.indexOf("OR");
                int idxNor = listaCondicoes.indexOf("↓");
                if (idxOr != -1 || idxNor != -1) {
                    if (idxNor != -1 && (idxOr == -1 || idxNor < idxOr)) {
                        index = idxNor;
                        condicao    = "↓";
                    } else {
                        index = idxOr;
                        condicao    = "OR";
                    }
                } else if (listaCondicoes.contains("->")) {
                    index = listaCondicoes.indexOf("->");
                    condicao    = "->";
                } else {
                    int idxBic = listaCondicoes.indexOf("<->");
                    int idxXor = listaCondicoes.indexOf("⊕");
                    if (idxXor != -1 && (idxBic == -1 || idxXor < idxBic)) {
                        index = idxXor;
                        condicao    = "⊕";
                    } else {
                        index = idxBic;
                        condicao    = "<->";
                    }
                }
            }

            String out = getResult(listaVariaveis, index, condicao);

            setResultOnList(listaVariaveis, listaCondicoes, index, out);
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

    private String getResult(ArrayList<Variavel> listaVariaveis, int index, String condicao) {
        String L = listaVariaveis.get(index).getResult();
        String R = listaVariaveis.get(index + 1).getResult();
        return switch (condicao) {
            case "AND" -> (L.equals("V") && R.equals("V")) ? "V" : "F";
            case "↑" -> (L.equals("V") && R.equals("V")) ? "F" : "V";
            case "OR" -> (L.equals("V") || R.equals("V")) ? "V" : "F";
            case "↓" -> (L.equals("V") || R.equals("V")) ? "F" : "V";
            case "->" -> (L.equals("F") || R.equals("V")) ? "V" : "F";
            case "<->" -> (L.equals(R)) ? "V" : "F";
            default -> (L.equals(R)) ? "F" : "V";
        };
    }
}
