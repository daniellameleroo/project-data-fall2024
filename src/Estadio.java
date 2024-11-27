package src;
import java.util.*;

public class Estadio {
    private Set<Asiento> availableSeatsField;
    private Set<Asiento> availableSeatsMain;
    private Set<Asiento> availableSeatsGrandstand;
    private HashMap<Cliente, List<Asiento>> reservations;
    private Stack<String> undoStack;
    private Stack<Asiento> canceledSeats;
    private Queue<Cliente> fieldList;
    private Queue<Cliente> mainList;
    private Queue<Cliente> grandstandList;
    LinkedList<String> transactionHistory;

    public Estadio() {
        Comparator<Asiento> asientoComparator = Comparator
                .comparingInt(Asiento::getRow)
                .thenComparingInt(Asiento::getSeatNumber);

        availableSeatsField = new TreeSet<>(asientoComparator);
        availableSeatsMain = new TreeSet<>(asientoComparator);
        availableSeatsGrandstand = new TreeSet<>(asientoComparator);

        reservations = new HashMap<>();
        transactionHistory = new LinkedList<>();
        undoStack = new Stack<>();
        canceledSeats = new Stack<>();
        fieldList = new LinkedList<>();
        mainList = new LinkedList<>();
        grandstandList = new LinkedList<>();

        initializeSeats();
        initializeReservations();
        initializeTransactionHistory();
        initializeUndoStack();
        initializeWaitingList();
    }

    private void initializeSeats() {
        for (int i = 1; i <= 500; i++) {
            availableSeatsField.add(new Asiento(1, i / 10 + 1, i, 300.0, false));
        }
        for (int i = 1; i <= 1000; i++) {
            availableSeatsMain.add(new Asiento(2, i / 20 + 1, i, 120.0, false));
        }
        for (int i = 1; i <= 2000; i++) {
            availableSeatsGrandstand.add(new Asiento(3, i / 30 + 1, i, 45.0, false));
        }
    }

    // Getters
    public List<Asiento> getAvailableSeatsField() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : availableSeatsField) {
            if (!asiento.getReservado()) {
                availableSeats.add(asiento);
            }
        }
        return availableSeats;
    }
    public List<Asiento> getAvailableSeatsMain() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : availableSeatsMain) {
            if (!asiento.getReservado()) {
                availableSeats.add(asiento);
            }
        }
        return availableSeats;
    }
    public List<Asiento> getAvailableSeatsGrandstand() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : availableSeatsGrandstand) {
            if (!asiento.getReservado()) {
                availableSeats.add(asiento);
            }
        }
        return availableSeats;
    }

    public Queue<Cliente> getFieldList(){
        return fieldList;
    }
    public Queue<Cliente> getMainList(){
        return mainList;
    }
    public Queue<Cliente> getGrandstandList(){
        return grandstandList;
    }

    public Map<Cliente, List<Asiento>> getReservations(){
        return reservations;
    }


    private void initializeReservations(){
      reservations.clear();
    }
    private void initializeTransactionHistory(){
        transactionHistory.clear();
    }
    private void initializeUndoStack(){
        undoStack.clear();
    }
    private void initializeWaitingList(){
        fieldList.clear();
        mainList.clear();
        grandstandList.clear();
    }


    public boolean reserveSeat(Cliente cliente, int seccion) {
        Set<Asiento> selectedSection = null;
        // Buscar sección seleccionada
        if (seccion == 1) {
            selectedSection = availableSeatsField;
        } else if (seccion == 2) {
            selectedSection = availableSeatsMain;
        } else if (seccion == 3) {
            selectedSection = availableSeatsGrandstand;
        } else {
            System.out.println("Sección no válida.");
            return false;
        }
        // Reservar asientos
        for (Asiento asiento : selectedSection) {
            if (!asiento.getReservado()) {
                asiento.setReservado(true);
                if (!reservations.containsKey(cliente)) {
                    reservations.put(cliente, new ArrayList<>());
                }
                reservations.get(cliente).add(asiento);
                // Agregar al historial
                String transaction = "Cliente " + cliente.getNombre() + " reservó asiento: " + asiento;
                transactionHistory.add(transaction);
                // Agregar al stack de deshacer la última acción de reservación
                undoStack.push("RESERVA:" + asiento);

                System.out.println("Asiento reservado: " + asiento);
                return true;
            } 
        }
        System.out.println("No se pudo hacer la reservación");
        return false;
    }

    public void cancelReservation(List<Asiento> clientReservations, int seccion) {
        if (clientReservations.isEmpty()) {
            System.out.println("No hay reservaciones.");
            return;
        }
    
        boolean canceled = false;
        for (Asiento asiento : clientReservations) {
            if (asiento.getSection() == seccion) {
                asiento.setReservado(false);
                System.out.println("Se ha cancelado la reserva: " + asiento);
    
                String transaction = "Canceló reserva: " + asiento;
                transactionHistory.add(transaction);
                
                clientReservations.remove(asiento);
    
                undoStack.push("CANCELACION:" + asiento);
                canceledSeats.push(asiento);
                canceled = true;
                break;
            }
        }
    
        if (!canceled) {
            System.out.println("No se encontraron reservaciones en la sección " + seccion + ".");
        }
    }
    

    public void addToWaitingList(Queue<Cliente> list, Cliente cliente){
        list.add(cliente);
        System.out.println("Está en lista de espera.");
    }
    
    public void undoLastAction(List<Asiento> clientReservations){
        if (undoStack.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }
    
        String lastAction = undoStack.pop();
        String[] actionParts = lastAction.split(":");
    
        if (actionParts[0].equals("RESERVA")) {
            // Deshacer reserva
            Asiento asiento = clientReservations.getLast();
            asiento.setReservado(false);
            clientReservations.remove(asiento);
            System.out.println("Se ha deshecho la última reserva: " + asiento);
        } 
        else if (actionParts[0].equals("CANCELACION")) {
            // Deshacer cancelación
            if (!canceledSeats.isEmpty()) {
                Asiento lastCanceled = canceledSeats.pop();
                lastCanceled.setReservado(true);
                clientReservations.add(lastCanceled);
                System.out.println("Se ha deshecho la última cancelación: " + lastCanceled);
            } else {
                System.out.println("No hay cancelaciones para deshacer.");
            }
        }
    }

    public void showAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No hay reservas realizadas.");
            return;
        }
        System.out.println("Reservas realizadas:");
        for (Map.Entry<Cliente, List<Asiento>> entry : reservations.entrySet()) {
            Cliente cliente = entry.getKey();
            List<Asiento> asientos = entry.getValue();
    
            System.out.println("Cliente: " + cliente.getNombre());
            System.out.println("Reservas:");
            for (Asiento asiento : asientos) {
                System.out.println(" " + asiento);
            }
        }
    }

  
}
