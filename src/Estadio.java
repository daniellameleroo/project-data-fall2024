package src;
import java.util.*;

public class Estadio {
    private Set<Asiento> availableSeatsField;
    private Set<Asiento> availableSeatsMain;
    private Set<Asiento> availableSeatsGrandstand;
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

        availableSeatsField = new TreeSet<>(asientoComparator);
        availableSeatsMain = new TreeSet<>(asientoComparator);
        availableSeatsGrandstand = new TreeSet<>(asientoComparator);

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
            availableSeatsField.add(new Asiento("Field Level", i / 10 + 1, i, 300.0, false));
        }
        for (int i = 1; i <= 1000; i++) {
            availableSeatsMain.add(new Asiento("Main Level", i / 20 + 1, i, 120.0, false));
        }
        for (int i = 1; i <= 2000; i++) {
            availableSeatsGrandstand.add(new Asiento("Grandstand Level", i / 30 + 1, i, 45.0, false));
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

    public boolean reserveSeat(Cliente cliente, int seccion, int row, int seatNumber) {
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
