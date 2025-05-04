package tabelaVerdade;

import java.util.Random;

public class Variavel extends AtributosTabelaVerdade {
    private final Random random = new Random();
    private final char nome;
    private String result;
    private String condicao;

    public Variavel(char nome){
        this.nome = nome;
    }

    public char getNome() {
        return nome;
    }

    public String getResult() {
        if (result == null){
            int indiceResult = random.nextInt(getVerdadeiroFalso().length);
            result = getVerdadeiroFalso()[indiceResult];
        }
        return result;
    }

    public String getCondicao() {
        if (condicao == null){
            int indiceCondicao = random.nextInt(getVerdadeiroFalso().length);
            condicao = getYesNot()[indiceCondicao];
        }
        return condicao;
    }

    public void setResult(String result) {
        this.result = result;
    }
}