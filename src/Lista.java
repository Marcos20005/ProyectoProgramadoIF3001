
public class Lista {
    // Clase que representa una lista enlazada de aviones en un hangar
    private NodoHangar raiz;
//Constructor de la clase Lista
    public Lista() {
        raiz = null;
    }
//Metodo para insertar un avión en la lista
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

    // Método para buscar obtener el primer avion
    public Avion obtenerPrimerVuelo() {
        return (raiz != null) ? raiz.avion : null;
    }

    //Metodo para remover el primer avion
    public void removerPrimero() {
        if (raiz != null) {
            raiz = raiz.siguiente;
        }
    }

    //Metodo para obtener la el elemento raiz de la lista
        public NodoHangar getRaiz() {
        return raiz;
    }
}



