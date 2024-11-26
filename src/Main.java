package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
/** 
* Pedimos información al cliente utlizando la clase Scanner
*/
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Número de teléfono: ");
        String telefono = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, email, telefono);

// Mostrar asientos disponibles al cliente
        Estadio estadio = new Estadio();
        System.out.print("\nSecciones \n(1)Field Level \n(2)Main Level \n(3)Grandstand Level \nEscriba el número de la sección deseada: ");
        Integer seccion = scanner.nextInt();
        scanner.nextLine();

        if (seccion == 1) {
            System.out.println("Asientos disponibles en Field Level:");
            for (Asiento asiento : estadio.getAvailableSeatsField()) {
                System.out.println(asiento);
            }
        } else if (seccion == 2) {
            System.out.println("Asientos disponibles en Main Level:");
            for (Asiento asiento : estadio.getAvailableSeatsMain()) {
                System.out.println(asiento);
            }
        } else if (seccion == 3) {
            System.out.println("Asientos disponibles en Grandstand Level:");
            for (Asiento asiento : estadio.getAvailableSeatsGrandstand()) {
                System.out.println(asiento);
            }
        } else {
            System.out.println("Sección no existe");
        }

// Si no hay asientos disponibles, dar opción a lista de espera
        if(estadio.getAvailableSeatsField().isEmpty()){
            System.out.println("Quiere entar a la lista de espera? (si/no) ");
            String espera = scanner.nextLine();
            if(espera.equals("si")){
                estadio.addToWaitingList(estadio.getFieldList(), cliente);
            }
        }
        if(estadio.getAvailableSeatsMain().isEmpty()){
            System.out.println("Quiere entar a la lista de espera? (si/no) ");
            String espera = scanner.nextLine();
            if(espera.equals("si")){
                estadio.addToWaitingList(estadio.getMainList(), cliente);
            }
        }
        if(estadio.getAvailableSeatsGrandstand().isEmpty()){
            System.out.println("Quiere entar a la lista de espera? (si/no) ");
            String espera = scanner.nextLine();
            if(espera.equals("si")){
                estadio.addToWaitingList(estadio.getGrandstandList(), cliente);
            }
        }

// Permitir al cliente seleccionar asientos
        System.out.println("Seleccione la fila (row): ");
        int selectedRow = scanner.nextInt();
        System.out.println("Seleccione el número de asiento (seat): ");
        int selectedSeat = scanner.nextInt();
// Reservar asientos
        boolean successful = estadio.reserveSeat(cliente, seccion, selectedRow, selectedSeat);

        if (successful) {
            System.out.println("Reservación completada con éxito.");
        } else {
            System.out.println("No se pudo completar la reservación.");
        }
    

        scanner.close();

    }
}

