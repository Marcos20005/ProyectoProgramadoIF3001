public class ListaAsientos {

        NodoAsiento raiz;

    public ListaAsientos() {
    }

    public void insertar(int numeroAsiento) {
        NodoAsiento nuevo = new NodoAsiento(numeroAsiento);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }
        NodoAsiento aux;
        aux = raiz;

        while (aux.siguiente != null) {
            aux = aux.siguiente;
        }

        aux.siguiente = nuevo;

    }

}
