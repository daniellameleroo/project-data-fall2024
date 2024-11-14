public class cliente {
    private String nombre;
    private String email;
    private String telefono;

    public cliente(String nombre, String email, String telefono){
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    public String geTelefono(){
        return telefono;
    }

    @Override
    public String toString(){
        return "Nombre: " + nombre + ", Email: " + email + ", Telefono: " + telefono; 
    }
    
}
