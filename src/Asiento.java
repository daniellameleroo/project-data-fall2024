package src;


/**
 * Se crea la Clase Asiento para representar un asiento de la Clase Estadio con
 * su sección, fila y su número de asiento
 */
public class Asiento {
    private int section; // Representa las secciones: Field Level, Main Level y Grandstand Level 
    private int row; //Representa las filas 
    private int seatNumber; //Representa el número de asiento
    private double price;
    private boolean reservado;

    /**
     * Creamos un constructor para crear el asiento 
     * @param section La sección del asiento
     * @param row La fila deel asiento
     * @param seatNumber El número del asiento
     * @param price El precio de cada asiento
     * @param reservado Estado del asiento
     */

    public Asiento(int section, int row, int seatNumber, double price, boolean reservado) {
        if (section <= 0) throw new IllegalArgumentException("La sección debe ser un número positivo.");
        if (row <= 0) throw new IllegalArgumentException("La fila debe ser un número positivo.");
        if (seatNumber <= 0) throw new IllegalArgumentException("El número de asiento debe ser un número positivo.");
        if (price < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.section = section;
        this.row = row;
        this.seatNumber = seatNumber;
        this.price = price;
        this.reservado = reservado;
    }
/** 
 * Este string me permite coger la sección 
 * 
 * @return el nombre de la sección
 */
    public int getSection() {
        return section;

    }
/**
 * Este integer me permite devolver el número de la fila 
 * @return número de fila
 */
    public int getRow() {
        return row;

    }
/**
 * Este integer me permite devolver el número del asiento
 * @return número de asiento
 */
    public int getSeatNumber() {
        return seatNumber;
    }
    /**
     * Este double me devuelve el precio del asiento
     * @return precio del asiento
     */
    public double getPrice(){
        return price;
    }
/** 
 * Este boolean muestra el estado del asiento 
 */
public boolean getReservado() {
    return reservado;
}
public void setReservado(boolean reservado){
    if(this.reservado&&reservado){
        throw new IllegalArgumentException("El asiento ya esta reservado: " + toString());
    }
    if(!this.reservado&&!reservado){
        throw new IllegalArgumentException("El asiento no estaba reservado: " + toString());
    }
    this.reservado=reservado;
}
/**
 * Al iagual que en Cliente hicimos un string que nos devuelva la representación del Asiento 
 */
    @Override
    public String toString() {
        return "Section: " + section + ", Row: " + row + ", Seat: " + seatNumber + ", Price: $"+ price;
    }
/**
 * Hicimos esta parte para verificar si hay dos asientos basado en la sección, fila y número de asiento
 * @param obj Lo usamos para comparar
 * @return True si hay dos asientos iguales y False que no hay duplicados
 */
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Asiento asiento = (Asiento) obj;

        return row==asiento.row &&
        seatNumber == asiento.seatNumber &&
        section == asiento.section;
    }
/**
 * Hacemos un Hash Code para el cliente basado en su section, fila y número de asiento asi salva su información. Hash Code me deja identificar los objetos rapidamente y optimiza el trabajo cuando se guarda datos o cuando se sacan datos.
 * Si salen dos equals como en el booleano de arriba, se ponen en el mismo código de hash. 
 * Escogimos que se multiplique con 31 por que es un número primario Odd que permite la distribucción mas organizada y minimiza las coliciones. Es una practica común y pensamos que distribuía mejor. Ayuda a combinar la sección, fila y # de asiento en un valor númerico. 
 */
    @Override
    public int hashCode(){
        int result = Integer.hashCode(section);
        result = 31* result + row;
        result = 31 * result + seatNumber;
        return result;
    }
}