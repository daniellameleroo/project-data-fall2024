package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean cerrar = false;

        while(!cerrar){
            //Pedimos información al cliente utlizando la clase Scanner
            System.out.print("\nIngrese su nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Número de teléfono: ");
            String telefono = scanner.nextLine();

            Cliente cliente = new Cliente(nombre, email, telefono);

            System.out.print("Cuántos asientos desea reservar? ");
            Integer numAsientos = scanner.nextInt();
            scanner.nextLine();

            Estadio estadio = new Estadio();
            Double totalPrice = 0.0;
            while(numAsientos > 0){
                // Mostrar asientos disponibles al cliente
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
                    else{
                        continue;
                    }
                }
                if(estadio.getAvailableSeatsMain().isEmpty()){
                    System.out.println("Quiere entar a la lista de espera? (si/no) ");
                    String espera = scanner.nextLine();
                    if(espera.equals("si")){
                        estadio.addToWaitingList(estadio.getMainList(), cliente);
                    }
                    else{
                        continue;
                    }
                }
                if(estadio.getAvailableSeatsGrandstand().isEmpty()){
                    System.out.println("Quiere entar a la lista de espera? (si/no) ");
                    String espera = scanner.nextLine();
                    if(espera.equals("si")){
                        estadio.addToWaitingList(estadio.getGrandstandList(), cliente);
                    }
                    else{
                        continue;
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
                    if(seccion == 1){
                        totalPrice += 300.0;
                    }else if(seccion == 2){
                        totalPrice += 120.0;
                    }
                    else if(seccion == 3){
                        totalPrice += 45.0;
                    }
                    numAsientos -= 1;
                }
            }

            System.out.println("Costo total: " + totalPrice + "\nTransacción completada.\n");

            scanner.nextLine();
            System.out.println("Cerrar programa? (si/no) ");
            String terminar = scanner.nextLine();
            if(terminar.equals("si")){
                cerrar = true;
            }
            
        }

        scanner.close();
    }
}

