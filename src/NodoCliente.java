public class NodoCliente {
// Atributos de la clase NodoCliente
    Cliente cliente;
    NodoCliente siguiente;

    public NodoCliente(Cliente cliente) {
        this.cliente = cliente;
        this.siguiente = null;
    }
}

