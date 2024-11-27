package src;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(true){
            //Pedimos información al cliente utlizando la clase Scanner
            System.out.print("\nIngrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Número de teléfono: ");
            String telefono = scanner.nextLine();

            Cliente cliente = new Cliente(nombre, email, telefono);

            Estadio estadio = new Estadio();

            while(true){
                //Menu principal
                System.out.println("\nSeleccione número:\n(1) Reservar Asientos\n(2) Cancelar reservación\n(3) Historial de transacciones\n(4) Mis reservaciones\n(5) Deshacer última acción\n(6) Terminar transacción");
                Integer choice = scanner.nextInt();
                scanner.nextLine();

                //Reservar asientos
                if(choice == 1){
                    //Mostar secciones disponibles y el costo
                    System.out.println("\n(1) Field Level\n  Costo: $300\n  Asientos Disponibles: " + estadio.getAvailableSeatsField().size() + "\n(2) Main Level\n  Costo: $120\n  Asientos Disponibles: " + estadio.getAvailableSeatsMain().size() + "\n(3) Grandstand Level\n  Costo: $45\n  Asientos Disponibles: " + estadio.getAvailableSeatsGrandstand().size());
                    //Si no hay asientos disponibles, dar opción a lista de espera
                    if(estadio.getAvailableSeatsField().isEmpty()){
                        System.out.println("Desea entrar a la lista de espera para Field level? (si/no) ");
                        String espera = scanner.nextLine();
                        if(espera.equals("si")){
                            estadio.addToWaitingList(estadio.getFieldList(), cliente);
                        }
                    }
                    if(estadio.getAvailableSeatsMain().isEmpty()){
                        System.out.println("Desea entrar a la lista de espera para Main Level? (si/no) ");
                        String espera = scanner.nextLine();
                        if(espera.equals("si")){
                            estadio.addToWaitingList(estadio.getMainList(), cliente);
                        }
                    }
                    if(estadio.getAvailableSeatsGrandstand().isEmpty()){
                        System.out.println("Desea entrar a la lista de espera para Grandstand Level? (si/no) ");
                        String espera = scanner.nextLine();
                        if(espera.equals("si")){
                            estadio.addToWaitingList(estadio.getGrandstandList(), cliente);
                        }
                    }
                    //Verificar que la cantidad de asientos está disponible
                    System.out.println("\nSeleccione número de sección deseada: ");
                    Integer seccion = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Cuántos asientos desea? ");
                    Integer numAsientos = scanner.nextInt();
                    scanner.nextLine();
                    //Reservar los asientos
                    double totalPrice = 0;
                    if(seccion == 1 && numAsientos <= estadio.getAvailableSeatsField().size()){
                        totalPrice = numAsientos * 300;
                        System.out.println("Costo total: " + totalPrice);
                        for(int i = 0; i < numAsientos; i++){
                            estadio.reserveSeat(cliente, seccion);
                        }
                    }
                    else if(seccion == 2 && numAsientos <= estadio.getAvailableSeatsMain().size()){
                        totalPrice = numAsientos * 120;
                        System.out.println("Costo total: " + totalPrice);
                        for(int i = 0; i < numAsientos; i++){
                            estadio.reserveSeat(cliente, seccion);
                        }
                    }
                    else if(seccion == 3 && numAsientos <= estadio.getAvailableSeatsGrandstand().size()){
                        totalPrice = numAsientos * 45;
                        System.out.println("Costo total: " + totalPrice);
                        for(int i = 0; i < numAsientos; i++){
                            estadio.reserveSeat(cliente, seccion);
                        }
                    }
                    else{
                        System.out.println("Cantidad de asientos no disponible.");
                    }
                    System.out.println("Asientos reservados con éxito!");
                }
                // Cancelar reservación
                else if(choice == 2){
                    // logica para cancelar reservacion
                }
                // Mostrar historial de transacciones
                else if(choice == 3){
                    System.out.println("\nHistorial de transacciones:");
                for (String transaction : estadio.transactionHistory) {
                    System.out.println(transaction);
                }
                }
                // Mostrar los asientos reservados por el cliente
                else if(choice == 4){
                    List<Asiento> clientReservations = estadio.getReservations().get(cliente);

                    if (clientReservations == null || clientReservations.isEmpty()) {
                        System.out.println("\nNo tiene reservaciones actualmente.");
                    } else {
                        System.out.println("\nSus reservaciones:");
                        for (Asiento asiento : clientReservations) {
                            System.out.println(" " + asiento);
                        }
                    }
                }
                // Deshacer última acción
                else if(choice == 5){
                    List<Asiento> clientReservations = estadio.getReservations().get(cliente);
                    estadio.undoLastAction(clientReservations);
                }
                // Terminar la transacción
                else if(choice == 6){
                    break;
                }
                else{
                    throw new IllegalArgumentException("Opción no es válida.");
                }
            }
            System.out.print("\nCerrar programa? ");
            String cerrar = scanner.nextLine();
            if(cerrar.equals("si")){
                break;
            }
        }
       scanner.close();
    }
}

