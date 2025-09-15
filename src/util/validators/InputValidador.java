package util.validators;

import java.util.Scanner;

public class InputValidador {

    public static int lerInteiroValido(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("\nEntrada Inválida. Por favor entre com um número inteiro válido.");
            sc.next(); // descarta entrada inválida
            System.out.print(message);
        }
        return sc.nextInt();
    }
}