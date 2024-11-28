package src;
import java.util.*;

/** 
 * Clase Estadio 
 * Esta clase representa un sistema de reservación de asientos para un estadio 
 * Incluye funcionalidades para mostrar asientos disponibles, realizar reservación, manejar cancelaciones, lista de espera y acciones de deshacer.
 */
public class Estadio {
    // Conjunto de sets de asientos disponibles para cada sección del estadio
    private Set<Asiento> availableSeatsField; 
    private Set<Asiento> availableSeatsMain;
    private Set<Asiento> availableSeatsGrandstand;

    //Mapa que almacena las reservas realizadas por los clientes
    private HashMap<Cliente, List<Asiento>> reservations;

    //Stack para guardar la última acción realizada
    private Stack<String> undoStack;

    //Stack para almacenar los asientos cancelados recientemente
    private Stack<Asiento> canceledSeats;

    //Filas de espera para cada sección(waiting list)
    private Queue<Cliente> fieldList;
    private Queue<Cliente> mainList;
    private Queue<Cliente> grandstandList;

    //Lista que almacena el historial de transacciones
    LinkedList<String> transactionHistory;
/**
 * Creamos un constructor de la clase Estadio que inicializa los asientos, reservaciones, historial de transacciones, filas y listas de espera. 
 */
    public Estadio() {
        //Definimos un comparador(comparator) para organizar las asientos por filas y por número de asiento
        Comparator<Asiento> asientoComparator = Comparator
                .comparingInt(Asiento::getRow) //Comparamos los asientos por fila 
                .thenComparingInt(Asiento::getSeatNumber); // Si las filas son las mismas, compararlo por número de asiento

        //Inicializamos los TreeSets para almacenar las sillas disponibles en secciones diferentes del estadio, organizado por el comparator.
        availableSeatsField = new TreeSet<>(asientoComparator); // Asientos en la sección de Field Level
        availableSeatsMain = new TreeSet<>(asientoComparator); //Asientos en la sección del Main Level
        availableSeatsGrandstand = new TreeSet<>(asientoComparator); //Asientos en la sección del GrandStand Level

        //Inicializamos un HashMap para almacenar las resevaciones y los clientes en sus asientos reservadas 
        reservations = new HashMap<>();

        //Inicializamos un LinkedList para realizar un seguimiento del historial de transacciones
        transactionHistory = new LinkedList<>();

        //Inicializar un Stack para mantener un registro de acciones para la funcionalidad de deshacer(undo)
        undoStack = new Stack<>();

        //Inicializar un Stack para guardar registro de los asientos cancelados para posibles operaciones de deshacer
        canceledSeats = new Stack<>();

        //Inicializar LinkedLists para representar la lista de espera de cada sección
        fieldList = new LinkedList<>(); //Lista de espera para la sección Field level
        mainList = new LinkedList<>(); //Lista de espera para la sección Main Level
        grandstandList = new LinkedList<>(); //Lista de espera para la sección Grandstand Level

        // Métodos auxiliares para completar e inicializar las estructuras de datos
        initializeSeats(); // Completa los datos de asientos para el estadio
        initializeReservations(); // Configura cualquier reserva preexistente
        initializeTransactionHistory(); //Completa el historial de transacciones con datos iniciales
        initializeUndoStack(); //Prepara la lista de deshacer para realizar el seguimiento de acciones
        initializeWaitingList(); // Completa la lista de espera, si es necesario.
    }
/**
 * Inicializa los asientos disponibles para las tres secciones del estadio
 * Con su precio y capacidad 
 * En reservado es falso por que así le dice al programa que estan disponibles. 
 */
    private void initializeSeats() { // Sección de Fieldlevel con su capacidad de asientos y su precio usando double. 
        for (int i = 1; i <= 500; i++) {
            availableSeatsField.add(new Asiento(1, i / 10 + 1, i, 300.0, false));
        }
        for (int i = 1; i <= 1000; i++) { //Sección de MainLevel con su capacidad de asientos y su precio
            availableSeatsMain.add(new Asiento(2, i / 20 + 1, i, 120.0, false));
        }
        for (int i = 1; i <= 2000; i++) { // Sección de Grandstand level con sus capacidad y precio.
            availableSeatsGrandstand.add(new Asiento(3, i / 30 + 1, i, 45.0, false));
        }
    }
/**
 * Hacemos los getters con las listas de los asientos disponibles en el campo
 * @return Lista de asientos disponibles Fieldlevel
 */
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
    /**
     * Lista de asientos disponibles en la sección de Main Level
     * @return Lista de asientos disponibles en Main Level
     */
    public List<Asiento> getAvailableSeatsMain() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : availableSeatsMain) {
            if (!asiento.getReservado()) {
                availableSeats.add(asiento);
            }
        }
        return availableSeats;
    }
    /**
     * Lista de asientos disponibles en la sección de Grandstand level
     * @return Lista de asientos disponibles en Grandstand Level
     */
    public List<Asiento> getAvailableSeatsGrandstand() {
        List<Asiento> availableSeats = new ArrayList<>();
        for (Asiento asiento : availableSeatsGrandstand) {
            if (!asiento.getReservado()) {
                availableSeats.add(asiento);
            }
        }
        return availableSeats;
    }
    /**
     * Recupera la lista de espera de la sección Field Level
     * @return Un Queue de clientes esperando para asientos para la sección Field Level
     */
    public Queue<Cliente> getFieldList(){
        return fieldList;
    }
    /**
     * Recupera la lista de espera de la sección Main Level
     * @return Un Queue de clientes esperando para asientos en la sección Main Level
     */
    public Queue<Cliente> getMainList(){
        return mainList;
    }
    /**
     * Recupera la lista de espera de la sección Grandstand Level
     * @return Un Queue de clientes esperando para asientos en la sección Grandstand Level
     */
    public Queue<Cliente> getGrandstandList(){
        return grandstandList;
    }
    /**
     * Recupera las reservaciones hechas en el estadio
     * @return Un Map donde el Key es el cliente (Cliente) y el value es la lista de asientos reservados(Asiento).
     */
    public Map<Cliente, List<Asiento>> getReservations(){
        return reservations;
    }
/**
 * Inicializamos la reservación a clear eliminando cualquier entrada previa
 */
    private void initializeReservations(){
      reservations.clear();
    }
    /**
     * Inicializamos el historial de transacción a clear eliminando cualquier entrada previa
     */
    private void initializeTransactionHistory(){
        transactionHistory.clear();
    }
    /**
     * Inicializamos el stack de deshacer, eliminando cualquier entrada previa
     */
    private void initializeUndoStack(){
        undoStack.clear();
    }
    /**
     * Inicializamos la lista de espera de todas las secciones, eliminando cualquier entrada previa
     */
    private void initializeWaitingList(){
        fieldList.clear();
        mainList.clear();
        grandstandList.clear();
    }
/**
 * Reservamos un asiento para un cliente con la sección de preferencia
 * @param cliente Cliente que realiza la reserva
 * @param seccion Sección que desean( 1: Fieldlevel 2: MainLevel 3: Grandstand Level)
 * @return true si la reserva fue exitosa y falso si fue lo contrario(reservado el asiento o cualquier caso contrario)
 */

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
                // Agregar al historial de transacción
                String transaction = "Cliente " + cliente.getNombre() + " reservó asiento: " + asiento;
                transactionHistory.add(transaction);
                // Agregar al stack de deshacer la última acción
                undoStack.push("RESERVA:" + asiento);

                System.out.println("Asiento reservado: " + asiento);
                return true;
            } 
        }
        System.out.println("No se pudo hacer la reservación");
        return false;
    }
/**
 * Cancelar una reserva para un cliente en la sección específica o donde tiene su reservación
 * @param clientReservations Lista de reservación del cliente
 * @param seccion Sección de la reserva donde se va a cancelar la reservación.
 */
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
                //Registramos la cancelación
                String transaction = "Canceló reserva: " + asiento;
                transactionHistory.add(transaction);
                
                clientReservations.remove(asiento);
                // Agregar al stack de deshacer la última acción
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
    /**
     * Agregamos a un cliente a la lista de espera de una sección 
     * @param list Lista de espera
     * @param cliente Cliente al que vamos a agregar
     */

    public void addToWaitingList(Queue<Cliente> list, Cliente cliente){
        list.add(cliente);
        System.out.println("Está en lista de espera.");
    }
    /**
     * Método para deshacer la última acción realizada(reserva o cancelación)
     * @param clientReservations Lista de reservación del cliente que puede ser modificada al deshacer.
     */
    public void undoLastAction(List<Asiento> clientReservations){
        //Verificar si hay acciones para deshacer(si el stack esta vacio o tiene algún dato)
        if (undoStack.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }
        //Obtenemos la útlima acción realizada
        String lastAction = undoStack.pop();
        String[] actionParts = lastAction.split(":");

        //Determinar si fue una reserva o cancelación
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
                // Restaurar el último asiento cancelado
                Asiento lastCanceled = canceledSeats.pop();
                lastCanceled.setReservado(true);
                clientReservations.add(lastCanceled);
                System.out.println("Se ha deshecho la última cancelación: " + lastCanceled);
            } else {
                System.out.println("No hay cancelaciones para deshacer.");
            }
        }
    }
/**
 * Método para mostrar todas las reservaciones realizadas
 */
    public void showAllReservations() {
        //Verificar si hay reservas registradas
        if (reservations.isEmpty()) {
            System.out.println("No hay reservas realizadas.");
            return;
        }
        System.out.println("Reservas realizadas:");
        //Usamos un iterator para iterar por cada cliente y sus respectivas reservaciones
        for (Map.Entry<Cliente, List<Asiento>> entry : reservations.entrySet()) {
            Cliente cliente = entry.getKey();
            List<Asiento> asientos = entry.getValue();
        //Mostramos información del cliente y sus asientos reservados
            System.out.println("Cliente: " + cliente.getNombre());
            System.out.println("Reservas:");
            for (Asiento asiento : asientos) {
                System.out.println(" " + asiento); //Imprimimos la información del asiento.
            }
        }
    }

  
}
