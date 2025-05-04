package tabelaVerdade;

abstract class AtributosTabelaVerdade {
    private final String[] conectivos = {"AND", "OR", "->", "<->"};
    private final String[] conectivosFase2 = {"AND", "OR", "->", "<->", "↑", "↓", "⊕"};
    private final String[] verdadeiroFalso = {"V", "F"};
    private final String[] yesNot = {"", "~"};

    String[] getConectivos(int nivel) {
        if (nivel == 1){
            return conectivos;
        } else {
            return conectivosFase2;
        }
    }

    String[] getVerdadeiroFalso() {
        return verdadeiroFalso;
    }

    String[] getYesNot() {
        return yesNot;
    }
}