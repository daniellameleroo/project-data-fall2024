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

        Cliente cliente = new Cliente(nombre, email, telefono);

        Estadio estadio = new Estadio();
        System.out.print("\nSecciones \n(1)Field Level \n(2)Main Level \n(3)Grandstand Level \nEscriba el número de la sección deseada: ");
        Integer seccion = scanner.nextInt();

        if(seccion == 1){
            System.out.println("Asientos disponibles en Field Level:");
            estadio.getAvaiableSeatsField().forEach(System.out::println);
        }
        else if(seccion == 2){
            System.out.println("Asientos disponibles en Main Level:");
            estadio.getAvaiableSeatsMain().forEach(System.out::println);
        }
        else if(seccion == 3){
            System.out.println("Asientos disponibles en Grandstand Level:");
            estadio.getAvaiableSeatsGrandstand().forEach(System.out::println);
        }
        else{
            System.out.println("Sección no existe");
        }


        scanner.close();

    }
}

