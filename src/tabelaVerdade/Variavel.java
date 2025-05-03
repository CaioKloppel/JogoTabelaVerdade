package tabelaVerdade;

import java.util.Random;

public class Variavel extends Tabela {
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
        return result;
    }

    public String getCondicao() { return condicao; }

    public void setResult(String result) {
        this.result = result;
    }

    public void Sortear(){
        int indiceResult = random.nextInt(getVerdadeiroFalso().length);
        int indiceCondicao = random.nextInt(getVerdadeiroFalso().length);
        result = getVerdadeiroFalso()[indiceResult];
        condicao = getYesNot()[indiceCondicao];
    }
}