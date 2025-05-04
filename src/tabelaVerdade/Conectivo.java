package tabelaVerdade;

import java.util.Random;

public class Conectivo extends AtributosTabelaVerdade {
    private final Random random = new Random();
    private final int nivel;

    public Conectivo(int nivel){
        this.nivel = nivel;
    }

    public String getConectivo() {
        String conectivo;
        int indice = random.nextInt(getConectivos(nivel).length);
        conectivo = getConectivos(nivel)[indice];
        return conectivo;
    }
}
