// ListaClientes.java
public class ListaClientes {
    private NodoCliente raiz;

    public ListaClientes() {
        raiz = null;
    }

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

    /** Permite iterar desde fuera obteniendo la raíz de la lista */
    public NodoCliente getRaiz() {
        return raiz;
    }
}
