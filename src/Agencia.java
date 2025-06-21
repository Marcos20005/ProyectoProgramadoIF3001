public class Agencia {

    //Declaracion de atributos de la clase Agencia
    private Lista hangar = new Lista();
    private ListaClientes clientes = new ListaClientes();
    private Lista avionesTotales = new Lista();
    private int vuelosEfectuados = 0;
    private float recaudacionTotal = 0;

    //Metodo declarado para insertar los aviones en los hangares
    public void altaVuelo(Avion a) {
        hangar.insertar(a);
        avionesTotales.insertar(a);
    }

    //Metodo declarado para reservar y agregar un cliente al vuelo
    public Ticket reservar(String nombre, int pasaporte, Avion vuelo, int[] asientosSeleccionados, String tipoPago) {
        if (clientes.buscarPorPasaporte(pasaporte) != null) {
            throw new IllegalArgumentException("Ya realizó una reserva.");
        }
        for (int asiento : asientosSeleccionados) {
            if (vuelo.isAsientoOcupado(asiento)) {
                throw new IllegalStateException("El asiento " + asiento + " ya está ocupado. Intente de nuevo.");
            }
        }
        float montoTotal = 0;
        for (int asiento : asientosSeleccionados) {
            vuelo.ocuparAsiento(asiento);
            montoTotal += vuelo.getCostoPorAsiento(asiento);
        }
        Cliente c = new Cliente(nombre, pasaporte, asientosSeleccionados.length, montoTotal, tipoPago);
        for (int num : asientosSeleccionados) {
            c.getListaAsiento().insertar(num);
        }
        vuelo.agregarPasajero(c);
        clientes.insertar(c);
        recaudacionTotal += montoTotal;
        return new Ticket(c, vuelo);
    }

    //Metodo declarado para simular el despegue de un vuelo
    public Avion despegue() {
        Avion primero = hangar.obtenerPrimerVuelo();
        if (primero != null) {
            hangar.removerPrimero();
            vuelosEfectuados++;
        }
        return primero;
    }

    //Reporte final de día (CORREGIDO)
    public String reporteFinDia() {
        int totalVendidos = 0;
        int totalLibres = 0;
        int ocupadosEco = 0;
        int libresEco = 0;
        int ocupadosEje = 0;
        int libresEje = 0;

        Avion mayorIngreso = null;
        float maxIngreso = -1; 

        NodoHangar aux = avionesTotales.getRaiz();
        while (aux != null) {
            Avion av = aux.avion;
            
            int vendidosEje = av.getVendidosEjecutiva();
            int vendidosEco = av.getVendidosEconomica();
            
            ocupadosEje += vendidosEje;
            ocupadosEco += vendidosEco;
            totalVendidos += vendidosEje + vendidosEco;
            
            libresEje += av.getnClaseEjecutiva() - vendidosEje;
            libresEco += av.getnClaseEconomica() - vendidosEco;
            totalLibres += (av.getnClaseEjecutiva() - vendidosEje) + (av.getnClaseEconomica() - vendidosEco);

            if (av.getRecaudado() > maxIngreso) {
                maxIngreso = av.getRecaudado();
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
        txt += "Monto total recaudado: " + recaudacionTotal + "\n\n";

        if (mayorIngreso != null) {
            txt += "--- Vuelo con Mayor Ingreso Aportado ---\n";
            txt += mayorIngreso.getDetallesReporte();
        } else {
            txt += "No se registraron vuelos para generar un reporte de mayor ingreso.\n";
        }

        return txt;
    }

    // Devuelve un arreglo de todos los vuelos aún en hangar
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