package src;

public class Asiento{
    private String section;
    private int row;
    private int seatNumber;
    private boolean reservado;


public Asiento(String section, int row, int seatNumber, boolean reservado){
    this.section = section;
    this.row = row;
    this.seatNumber = seatNumber;
    this.reservado = reservado;
}


public String getSection(){
    return section;

}
public int getRow(){
    return row;

}

public int getSeatNumber(){
    return seatNumber;
}

public boolean getReservado(){
    return reservado;
}

@Override
public String toString(){
    return "Section: " + section + ", Row: " + row + ", Seat: " + seatNumber;
    }
}