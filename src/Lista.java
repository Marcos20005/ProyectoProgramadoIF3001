
public class Lista {

    NodoHangar raiz;

    public Lista() {
    }

    public void insertar(Avion avion) {
        NodoHangar nuevo = new NodoHangar(avion);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }
        NodoHangar aux;
        aux = raiz;

        while (aux.siguiente != null) {
            aux = aux.siguiente;
        }

        aux.siguiente = nuevo;

    }

}
