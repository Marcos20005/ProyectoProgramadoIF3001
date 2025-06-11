public class Cliente {
 //Declaracion de atributos de la clase Cliente
    private String nombre;
    private int pasaporte;
    private int numeroAsientos;
    private ListaAsientos listaAsiento = new ListaAsientos();
    private float montoAPagar;
    private String tipoPago;

    //Constructor de la clase Cliente
    public Cliente(String nombre, int pasaporte, int numeroAsientos, float montoAPagar, String tipoPago) {
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.numeroAsientos = numeroAsientos;
        this.montoAPagar = montoAPagar;
        this.tipoPago = tipoPago;
    }


    //Getter y setter de la clase
    public String getNombre() {
        return nombre;
    }

    public int getPasaporte() {
        return pasaporte;
    }

    public int getNumeroAsientos() {
        return numeroAsientos;
    }

    public ListaAsientos getListaAsiento() {
        return listaAsiento;
    }

    public float getMontoAPagar() {
        return montoAPagar;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setMontoAPagar(float monto) {
        this.montoAPagar = monto;
    }
}
