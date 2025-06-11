public class Ticket {
// Atributos de la clase Ticket
    private Cliente cliente;
    private Avion vuelo;
// Constructor de la clase Ticket
    public Ticket(Cliente cliente, Avion vuelo) {
        this.cliente = cliente;
        this.vuelo = vuelo;
    }
//Metodo declarado para ontener toda la informacion de un ticket generado
    public String generarTexto() {
        String txt = "";
        txt += "----- TICKET DE RESERVA -----\n";
        txt += "Cliente: " + cliente.getNombre() + "\n";
        txt += "Pasaporte: " + cliente.getPasaporte() + "\n";
        txt += "Vuelo: " + vuelo.getOrigen() + " → " + vuelo.getDestino() + "\n";
        txt += "Asientos: " + cliente.getListaAsiento().toString() + "\n";
        txt += "Precio x asiento: " + (cliente.getMontoAPagar() / cliente.getNumeroAsientos()) + "\n";
        txt += "Total a pagar: " + cliente.getMontoAPagar() + "\n";
        txt += "Forma de pago: " + cliente.getTipoPago() + "\n";
        return txt;
    }
}
