public class ListaAsientos {
    //Elemento raiz de la lista enlazada de asientos
    private NodoAsiento raiz;

    //Constructor de la clase ListaAsientos
    public ListaAsientos() {
        raiz = null;
    }

//Metodo para insertar un nuevo asiento al final de la lista
    public void insertar(int numeroAsiento) {
        NodoAsiento nuevo = new NodoAsiento(numeroAsiento);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoAsiento aux = raiz;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    //Metodo declarado para obtener todos los asientos comprados por cliente
    @Override
    public String toString() {
        String txt = "[";
        NodoAsiento aux = raiz;
        while (aux != null) {
            txt += aux.numeroAsiento;
            if (aux.siguiente != null) txt += ", ";
            aux = aux.siguiente;
        }
        txt += "]";
        return txt;
    }
}
