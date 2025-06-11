

// Lista.java  // lista de vuelos en hangar
public class Lista {
    private NodoHangar raiz;

    public Lista() {
        raiz = null;
    }

    public void insertar(Avion a) {
        NodoHangar nuevo = new NodoHangar(a);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoHangar aux = raiz;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    public Avion obtenerPrimerVuelo() {
        return (raiz != null) ? raiz.avion : null;
    }

    public void removerPrimero() {
        if (raiz != null) {
            raiz = raiz.siguiente;
        }
    }

        public NodoHangar getRaiz() {
        return raiz;
    }
}



