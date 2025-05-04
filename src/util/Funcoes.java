package util;

import java.util.InputMismatchException;
import java.util.Random;

public class Funcoes {
    private static final Random random = new Random();

    public static int getInt(String pergunta)
    {
        int numeroInt = 0;
        boolean entrada_valida = false;
        while (!entrada_valida){
            System.out.print(pergunta);
            try {
                numeroInt = Input.getInstance().scanNextInt();
                Input.getInstance().scanNextLine();
                entrada_valida = true;
            } catch (InputMismatchException e){
                System.out.println("Entrada inválida, aceita apenas números inteiros!");
                Input.getInstance().scanNextLine();
            }
        }

        return numeroInt;
    }
}
