package src;
import java.util.*;

<<<<<<< HEAD:Estadio.java
public class Estadio {
    private Set<Asiento> avaiableSeats; //Se hace un set para los asientos disponibles
    private HashMap<Cliente, List<Asiento>> reservations;
=======


public class Estadio {
    private Set<Asiento> avaiableSeats;
    private HashMap<Cliente, List<Asiento>> reservations;
>>>>>>> 8097d41 (new file main):src/Estadio.java
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
            avaiableSeats.add(new Asiento("Field Level", i / 10 + 1, i));

        }
        for(int i = 1; i <= 1000; i++){
            avaiableSeats.add(new Asiento( "Main Level", i/ 20 + 1, i));

        }
        for(int i = 1; i <=2000; i++){
            avaiableSeats.add(new Asiento("Grandstand Level", i / 30 + 1, i));
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
