// Agencia.java
public class Agencia {
    private Lista hangar = new Lista();
    private ListaClientes clientes = new ListaClientes();
    private Lista avionesTotales = new Lista();
    private int vuelosEfectuados = 0;
    private float recaudacionTotal = 0;

    // 1) Alta de vuelo
    public void altaVuelo(Avion a) {
        hangar.insertar(a);
        avionesTotales.insertar(a);
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
    int totalVendidos = 0;
    int totalLibres = 0;
    int ocupadosEco = 0;
    int libresEco = 0;
    int ocupadosEje = 0;
    int libresEje = 0;

    Avion mayorIngreso = null;
    float maxIngreso = 0;

    NodoHangar aux = avionesTotales.getRaiz();
    while (aux != null) {
        Avion av = aux.avion;
        totalVendidos += av.totalAsientosVendidos();
        totalLibres += av.totalAsientosLibres();
        ocupadosEco += av.getVendidosEconomica();
        libresEco += av.asientosLibresEconomica();
        ocupadosEje += av.getVendidosEjecutiva();
        libresEje += av.asientosLibresEjecutiva();

        if (av.getTotalRecaudado() > maxIngreso) {
            maxIngreso = av.getTotalRecaudado();
            mayorIngreso = av;
        }

        aux = aux.siguiente;
    }

    String txt = "";
    txt += "=== REPORTE FIN DE DÍA ===\n";
    txt += "Vuelos efectuados: " + vuelosEfectuados + "\n";
    txt += "Total de asientos vendidos: " + totalVendidos + "\n";
    txt += "Total de asientos desocupados: " + totalLibres + "\n";
    txt += "Clase Económica - Ocupados: " + ocupadosEco + ", Libres: " + libresEco + "\n";
    txt += "Clase Ejecutiva - Ocupados: " + ocupadosEje + ", Libres: " + libresEje + "\n";
    txt += "Monto total recaudado: " + recaudacionTotal + "\n";

    if (mayorIngreso != null) {
        txt += "--- Vuelo con mayor ingreso ---\n";
        txt += mayorIngreso.toString(); // Asegúrate que toString() esté bien definido en Avion
    }

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
