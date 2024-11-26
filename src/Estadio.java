package src;
import java.util.*;

public class Estadio {
    private Set<Asiento> avaiableSeatsField;
    private Set<Asiento> avaiableSeatsMain;
    private Set<Asiento> avaiableSeatsGrandstand;
    private HashMap<Cliente, List<Asiento>> reservations;
    private LinkedList<String> transactionHistory;
    private Stack<String> undoStack;
    private Queue<Cliente> fieldList;
    private Queue<Cliente> mainList;
    private Queue<Cliente> grandstandList;

    public Estadio() {
        Comparator<Asiento> asientoComparator = Comparator
                .comparingInt(Asiento::getRow)
                .thenComparingInt(Asiento::getSeatNumber);

        avaiableSeatsField = new TreeSet<>(asientoComparator);
        avaiableSeatsMain = new TreeSet<>(asientoComparator);
        avaiableSeatsGrandstand = new TreeSet<>(asientoComparator);

        reservations = new HashMap<>();
        transactionHistory = new LinkedList<>();
        undoStack = new Stack<>();
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
            avaiableSeatsField.add(new Asiento("Field Level", i / 10 + 1, i, 300.0, false));
        }
        for (int i = 1; i <= 1000; i++) {
            avaiableSeatsMain.add(new Asiento("Main Level", i / 20 + 1, i, 120.0, false));
        }
        for (int i = 1; i <= 2000; i++) {
            avaiableSeatsGrandstand.add(new Asiento("Grandstand Level", i / 30 + 1, i, 45.0, false));
        }
    }

    // Getters
    public List<Asiento> getAvailableSeatsField() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : avaiableSeatsField) {
            if (!asiento.getReservado()) {
                availableSeats.add(asiento);
            }
        }
        return availableSeats;
    }
    public List<Asiento> getAvailableSeatsMain() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : avaiableSeatsMain) {
            if (!asiento.getReservado()) {
                availableSeats.add(asiento);
            }
        }
        return availableSeats;
    }
    public List<Asiento> getAvailableSeatsGrandstand() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : avaiableSeatsGrandstand) {
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

    public boolean reserveSeat(Cliente cliente, int seccion, int row, int seatNumber) {
        Set<Asiento> selectedSection = null;
        // Buscar sección seleccionada
        if (seccion == 1) {
            selectedSection = avaiableSeatsField;
        } else if (seccion == 2) {
            selectedSection = avaiableSeatsMain;
        } else if (seccion == 3) {
            selectedSection = avaiableSeatsGrandstand;
        } else {
            System.out.println("Sección no válida.");
            return false;
        }
        // Buscar asiento y reservar asiento
        for (Asiento asiento : selectedSection) {
            if (asiento.getRow() == row && asiento.getSeatNumber() == seatNumber) {
                if (!asiento.getReservado()) {
                    asiento.setReservado(true);
                    if (!reservations.containsKey(cliente)) {
                        reservations.put(cliente, new ArrayList<>());
                    }
                    reservations.get(cliente).add(asiento);
                    System.out.println("Asiento reservado: " + asiento);
                    return true;
                } else {
                    System.out.println("El asiento ya está reservado.");
                    return false;
                }
            }
        }
        System.out.println("El asiento no existe en la sección seleccionada.");
        return false;
    }

    public void addToWaitingList(Queue<Cliente> list, Cliente cliente){
        list.add(cliente);
    }
    
  
}
