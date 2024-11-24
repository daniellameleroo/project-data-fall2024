package src;
/*
* Representa un cliente haciendo reservación para el estadio 
 */
public class Cliente {
    private String nombre; //Nombre del cliente
    private String email; // Email del cliente
    private String telefono; //Teléfono del cliente

    /**
     * Constructor para crear a un nuevo cliente
     * @param nombre El nombre del cliente
     * @param email El email del cliente
     * @param telefono El teléfono del cliente
     */

    public Cliente(String nombre, String email, String telefono){
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
/** 
 * Coje el nombre del cliente 
 * @return el nombre del cliente
 */
    public String getNombre(){
        return nombre;
    }
/**
 * Coje el email del cliente
 * @return el email del cliente 
 */
    public String getEmail(){
        return email;
    }
/** 
 * Coje el teléfono del cliente
 * @return teléfono del cliente
 */
    public String getTelefono(){
        return telefono;
    }
/** 
 * Provee una representación de string del cliente
 * 
 * @return Un string en formato "Nombre: <nombre> Email: <email> Teléfono: <teléfono> 
 */
    @Override
    public String toString(){
        return "Nombre: " + nombre + ", Email: " + email + ", Teléfono: " + telefono; 
    }
    /**
     * Hay que vereificar que no se tenga dos clientes iguales basado en su email
     * @param obj lo usamos de objeto para compararlo
     * @return True usamos un booleano para devolver si hay dos clientes con el mismo email sino pues sería False
     */
    @Override 
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Cliente cliente=(Cliente) obj;

        return email.equals(cliente.email);
    }
/** 
 * Creamos un HashCode para el cliente con su email para guardar
 * @return Hash Code
 */
    @Override
    public int hashCode(){
        return email.hashCode();
    }
}
