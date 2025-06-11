public class Cliente {


    String nombre;
    int pasaporte;
    int numeroAsientos;
    ListaAsientos listaAsiento=new ListaAsientos();
    
    float montoAPagar;
    String tipoPago;
    public Cliente(String nombre, int pasaporte, int numeroAsientos, float montoAPagar, String tipoPago) {
        this.nombre = nombre;
        this.pasaporte = pasaporte;
        this.numeroAsientos = numeroAsientos;
        this.montoAPagar = montoAPagar;
        this.tipoPago = tipoPago;
    }

    



    

}
