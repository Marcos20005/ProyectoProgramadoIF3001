

import java.time.LocalTime;

public class Avion {

    private String nombreCapitan, origen, destino;
    private int capacidad, nClaseEjecutiva, nClaseEconomica;
    private String escala;
    private LocalTime horaSalida, horaLlegada;
    private float costoXAsientoEconomico, costoXAsientoEjecutivo;
    private boolean enElhangar ;
    private int vendidosEjecutiva = 0, vendidosEconomica = 0;
    private float recaudado = 0;
   // private int ocupadosEconomica;
    //private int ocupadosEjecutiva;
    private float totalRecaudado;
    private String capitan;

    // lista de pasajeros
    private ListaClientes pasajeros = new ListaClientes();

    public Avion(String nombreCapitan, String origen, String destino, int capacidad,
            int nClaseEjecutiva, int nClaseEconomica, String escala,
            LocalTime horaSalida, LocalTime horaLlegada,
            float costoXAsientoEconomico, float costoXAsientoEjecutivo) {
        this.nombreCapitan = nombreCapitan;
        this.origen = origen;
        this.destino = destino;
        this.capacidad = capacidad;
        this.nClaseEjecutiva = nClaseEjecutiva;
        this.nClaseEconomica = nClaseEconomica;
        this.escala = escala;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.costoXAsientoEconomico = costoXAsientoEconomico;
        this.costoXAsientoEjecutivo = costoXAsientoEjecutivo;
    }

    /**
     * Asigna ‘cantidad’ asientos en la sección indicada ("EJECUTIVA" o
     * "ECONOMICA") Devuelve un array con los números de asiento asignados.
     */
    public int[] asignarAsientos(int cantidad, String seccion) {
        int inicio, limite, vendidos;
        if (seccion.equalsIgnoreCase("EJECUTIVA")) {
            inicio = 1;
            limite = nClaseEjecutiva;
            vendidos = vendidosEjecutiva;
        } else {
            inicio = nClaseEjecutiva + 1;
            limite = nClaseEconomica;
            vendidos = vendidosEconomica;
        }

        if (vendidos + cantidad > limite) {
            throw new IllegalStateException("No hay suficientes asientos en " + seccion);
        }

        int[] asignados = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            asignados[i] = inicio + vendidos + i;
        }

        if (seccion.equalsIgnoreCase("EJECUTIVA")) {
            vendidosEjecutiva += cantidad;
        } else {
            vendidosEconomica += cantidad;
        }

        return asignados;
    }
    public int totalAsientosLibres() {
        return (capacidad - vendidosEjecutiva - vendidosEconomica);
    }

    public float getCostoPorSeccion(String seccion) {
        if (seccion.equalsIgnoreCase("EJECUTIVA")) {
            return costoXAsientoEjecutivo;
        } else {
            return costoXAsientoEconomico;
        }
    }
     public int totalAsientosVendidos() {
    return vendidosEconomica + vendidosEjecutiva;
}
    public void acumularVenta(int cantidad, float monto) {
        recaudado += monto;
    }
    public int asientosLibresEjecutiva() {
        return nClaseEjecutiva - vendidosEjecutiva;
    }
    public int asientosLibresEconomica() {
        return nClaseEconomica - vendidosEconomica;
    }

    /**
     * Registra el cliente en la lista de pasajeros de este vuelo
     */
    public void agregarPasajero(Cliente c) {
        pasajeros.insertar(c);
    }

    /**
     * Devuelve la lista enlazada de pasajeros
     */
    public ListaClientes getPasajeros() {
        return pasajeros;
    }

    // Getters para reporte e interfaz
    public String getNombreCapitan() {
        return nombreCapitan;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getEscala() {
        return escala;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    public int getVendidosEjecutiva() {
        return vendidosEjecutiva;
    }

    public int getVendidosEconomica() {
        return vendidosEconomica;
    }

    public float getRecaudado() {
        return recaudado;
    }
    public void setEnElHangar(boolean enElhangar) {
        this.enElhangar = enElhangar;
    }
    public boolean isEnElHangar() {
        return enElhangar;
    }

    

    public float getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(float totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    public String getCapitan() {
        return capitan;
    }

    public void setCapitan(String capitan) {
        this.capitan = capitan;
    }

}
