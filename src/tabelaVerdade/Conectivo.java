package tabelaVerdade;

import java.util.Random;

public class Conectivo extends Tabela {
    Random random = new Random();
    private String conectivo;

    public String getConectivo() {return conectivo;}

    public void setConectivoNivel1(){
        int indice = random.nextInt(getConectivos().length);
        conectivo = (getConectivos()[indice]);
    }

    public void setConectivoNivel2(){
        int indice = random.nextInt(getConectivosFase2().length);
        conectivo = (getConectivosFase2()[indice]);
    }
}
