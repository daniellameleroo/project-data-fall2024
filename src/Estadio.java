package src;
import java.util.*;

public class Estadio {
    private Set<Asiento> avaiableSeatsField;
    private Set<Asiento> avaiableSeatsMain;
    private Set<Asiento> avaiableSeatsGrandstand;
    private HashMap<Cliente, List<Asiento>> reservations;
    private LinkedList<String> transactionHistory;
    private Stack<String> undoStack;
    private Queue<Cliente> waitingList;

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
        waitingList = new LinkedList<>();

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

    // Getters para los sets
    public Set<Asiento> getAvaiableSeatsField() {
        return avaiableSeatsField;
    }

    public Set<Asiento> getAvaiableSeatsMain() {
        return avaiableSeatsMain;
    }

    public Set<Asiento> getAvaiableSeatsGrandstand() {
        return avaiableSeatsGrandstand;
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
        waitingList.clear();

    }
  
    
}
