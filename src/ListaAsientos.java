// ListaAsientos.java
public class ListaAsientos {
    private NodoAsiento raiz;

    public ListaAsientos() {
        raiz = null;
    }

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
