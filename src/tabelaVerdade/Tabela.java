package tabelaVerdade;

abstract class Tabela {
    private final String[] conectivos = {"AND", "OR", "->", "<->"};
    private final String[] conectivosFase2 = {"AND", "OR", "->", "<->", "↑", "↓", "⊕"};
    private final String[] verdadeiroFalso = {"V", "F"};
    private final String[] yesNot = {"", "~"};

    String[] getConectivos() {
        return conectivos;
    }

    String[] getConectivosFase2() {
        return conectivosFase2;
    }

    String[] getVerdadeiroFalso() {
        return verdadeiroFalso;
    }

    String[] getYesNot() {
        return yesNot;
    }
}