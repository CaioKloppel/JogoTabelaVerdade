package jogo;

import tabelaVerdade.Conectivo;
import tabelaVerdade.Variavel;
import util.Input;

import java.util.ArrayList;

class MetodosBaseJogo {

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
            listaVariaveis.add(variavel);
        }
    }

    void setConectivos(ArrayList<String> listaConectivos, int numVariaveis, int nivel){
        if (nivel == 1){
            for (int x = 0; x < numVariaveis - 1; x++) {
                Conectivo conectivo = new Conectivo(nivel);
                listaConectivos.add(conectivo.getConectivo());
            }
        }else{
            for (int x = 0; x < numVariaveis - 1; x++) {
                Conectivo conectivo = new Conectivo(nivel);
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

    void setResultOnList(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaconectivos, int index, String v_f){
        listaVariaveis.get(index).setResult(v_f);
        listaVariaveis.remove(index + 1);
        listaconectivos.remove(index);
    }

    String resposta(ArrayList<Variavel> listaVariaveis, ArrayList<String> listaconectivos) {
        while (listaVariaveis.size() > 1) {
            int index;
            String conectivo;

            int idxAnd  = listaconectivos.indexOf("AND");
            int idxNand = listaconectivos.indexOf("↑");
            if (idxAnd != -1 || idxNand != -1) {
                if (idxNand != -1 && (idxAnd == -1 || idxNand < idxAnd)) {
                    index = idxNand;
                    conectivo = "↑";
                } else {
                    index = idxAnd;
                    conectivo = "AND";
                }
            } else {
                int idxOr  = listaconectivos.indexOf("OR");
                int idxNor = listaconectivos.indexOf("↓");
                if (idxOr != -1 || idxNor != -1) {
                    if (idxNor != -1 && (idxOr == -1 || idxNor < idxOr)) {
                        index = idxNor;
                        conectivo = "↓";
                    } else {
                        index = idxOr;
                        conectivo = "OR";
                    }
                } else if (listaconectivos.contains("->")) {
                    index = listaconectivos.indexOf("->");
                    conectivo = "->";
                } else {
                    int idxBic = listaconectivos.indexOf("<->");
                    int idxXor = listaconectivos.indexOf("⊕");
                    if (idxXor != -1 && (idxBic == -1 || idxXor < idxBic)) {
                        index = idxXor;
                        conectivo = "⊕";
                    } else {
                        index = idxBic;
                        conectivo = "<->";
                    }
                }
            }

            String out = getResult(listaVariaveis, index, conectivo);

            setResultOnList(listaVariaveis, listaconectivos, index, out);
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

    private String getResult(ArrayList<Variavel> listaVariaveis, int index, String conectivo) {
        String L = listaVariaveis.get(index).getResult();
        String R = listaVariaveis.get(index + 1).getResult();
        return switch (conectivo) {
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
