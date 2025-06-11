// Agencia.java
public class Agencia {
    private Lista hangar = new Lista();
    private ListaClientes clientes = new ListaClientes();
    private int vuelosEfectuados = 0;
    private float recaudacionTotal = 0;

    // 1) Alta de vuelo
    public void altaVuelo(Avion a) {
        hangar.insertar(a);
    }

    // 2) Reserva
    public Ticket reservar(String nombre, int pasaporte,
                          Avion vuelo, int cantidad, String seccion, String tipoPago) {
        if (clientes.buscarPorPasaporte(pasaporte) != null) {
            throw new IllegalArgumentException("Ya realizó una reserva.");
        }
        int[] asig = vuelo.asignarAsientos(cantidad, seccion);
        float total = vuelo.getCostoPorSeccion(seccion) * cantidad;
        Cliente c = new Cliente(nombre, pasaporte, cantidad, total, tipoPago);
        vuelo.agregarPasajero(c);

        for (int num : asig) {
            c.getListaAsiento().insertar(num);
        }
        // registrar pasajero en el vuelo
        vuelo.agregarPasajero(c);

        clientes.insertar(c);
        vuelo.acumularVenta(cantidad, total);
        recaudacionTotal += total;
        return new Ticket(c, vuelo);
    }

    // 3) Despegue (simula y quita el primer vuelo en hangar)
    public Avion despegue() {
        Avion primero = hangar.obtenerPrimerVuelo();
        if (primero != null) {
            hangar.removerPrimero();
            vuelosEfectuados++;
        }
        return primero;
    }

    // 4) Reporte fin de día
    public String reporteFinDia() {
        String txt = "";
        txt += "=== REPORTE FIN DE DÍA ===\n";
        txt += "Vuelos efectuados: " + vuelosEfectuados + "\n";
        txt += "Recaudación total: " + recaudacionTotal + "\n";
        return txt;
    }

    /** Devuelve un arreglo de todos los vuelos aún en hangar */
    public Avion[] obtenerVuelosEnHangar() {
        int count = 0;
        NodoHangar aux = hangar.getRaiz();
        while (aux != null) {
            count++;
            aux = aux.siguiente;
        }
        Avion[] arr = new Avion[count];
        aux = hangar.getRaiz();
        for (int i = 0; i < count; i++) {
            arr[i] = aux.avion;
            aux = aux.siguiente;
        }
        return arr;
    }
}
