package tabelaVerdade;

import java.util.Random;

public class Conectivo extends Tabela {
    private final Random random = new Random();
    private final int nivel;

    public Conectivo(int nivel){
        this.nivel = nivel;
    }

    public String getConectivo() {
        String conectivo;
        if (nivel == 1){
            int indice = random.nextInt(getConectivos().length);
            conectivo = getConectivos()[indice];
        } else {
            int indice = random.nextInt(getConectivosFase2().length);
            conectivo = getConectivosFase2()[indice];
        }
        return conectivo;
    }
}
