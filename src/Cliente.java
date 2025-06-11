// Cliente.java

public class Cliente {

    private String nombre;
    private int pasaporte;
    private int numeroAsientos;
    private ListaAsientos listaAsiento = new ListaAsientos();
    private float montoAPagar;
    private String tipoPago;

    public Cliente(String nombre, int pasaporte, int numeroAsientos, float montoAPagar, String tipoPago) {
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.numeroAsientos = numeroAsientos;
        this.montoAPagar = montoAPagar;
        this.tipoPago = tipoPago;
    }

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
