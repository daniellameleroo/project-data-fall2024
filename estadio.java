import java.util.*;

public class estadio {
    private Set<asiento> avaiableSeats; //Se hace un set para los asientos disponibles
    private HashMap<cliente, List<asiento>> reservations;
    private LinkedList<String> transactionHistory;
    private Stack<String> undoStack;
    private Queue<cliente> waitingList;


    public estadio(){
        avaiableSeats = new HashSet<>();
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
    private void initializeSeats(){
        for (int i = 1; i <=500; i++){
            avaiableSeats.add(new asiento("Field Level", i / 10 + 1, i));

        }
        for(int i = 1; i <= 1000; i++){
            avaiableSeats.add(new asiento( "Main Level", i/ 20 + 1, i));

        }
        for(int i = 1; i <=2000; i++){
            avaiableSeats.add(new asiento("Grandstand Level", i / 30 + 1, i));
        }
    }
    
    private void initializeReservations(){
      
    }
    private void initializeTransactionHistory(){

    }
    private void initializeUndoStack(){

    }
    private void initializeWaitingList(){

    }
}
