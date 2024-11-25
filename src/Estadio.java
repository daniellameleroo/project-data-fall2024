package src;
import java.util.*;

public class Estadio {
    private Set<Asiento> avaiableSeats;
    private HashMap<Cliente, List<Asiento>> reservations;
    private LinkedList<String> transactionHistory;
    private Stack<String> undoStack;
    private Queue<Cliente> waitingList;
    


    public Estadio(){
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
            avaiableSeats.add(new Asiento("Field Level", i / 10 + 1, i, 300.0));

        }
        for(int i = 1; i <= 1000; i++){
            avaiableSeats.add(new Asiento( "Main Level", i/ 20 + 1, i, 120.0));

        }
        for(int i = 1; i <=2000; i++){
            avaiableSeats.add(new Asiento("Grandstand Level", i / 30 + 1, i, 45.0));
        }
        
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
