public class ListaClientes {
    // Elemento raíz de la lista enlazada de clientes
    private NodoCliente raiz;

    // Constructor de la clase ListaClientes
    public ListaClientes() {
        raiz = null;
    }

    // Método para insertar un nuevo cliente al final de la lista
    public void insertar(Cliente c) {
        NodoCliente nuevo = new NodoCliente(c);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoCliente aux = raiz;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    // Método para buscar un cliente por su pasaporte
    public Cliente buscarPorPasaporte(int pasaporte) {
        NodoCliente aux = raiz;
        while (aux != null) {
            if (aux.cliente.getPasaporte() == pasaporte) {
                return aux.cliente;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    // NUEVO: Método que calcula el monto total de todos los clientes en la lista.
    // Esto evita la necesidad de devolver un ArrayList o un array.
    public float calcularMontoTotal() {
        float total = 0;
        NodoCliente aux = raiz;
        while (aux != null) {
            total += aux.cliente.getMontoAPagar();
            aux = aux.siguiente;
        }
        return total;
    }

    //Metodo declarado para obtener todos los clientes
    public NodoCliente getRaiz() {
        return raiz;
    }
}