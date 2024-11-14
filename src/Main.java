package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Número de teléfono: ");
        String telefono = scanner.nextLine();

        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("Telefono: " + telefono);

        scanner.close();

    }
}
