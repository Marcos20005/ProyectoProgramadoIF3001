// Avion.java

import java.time.LocalTime;

public class Avion {

    private String nombreCapitan, origen, destino;
    private int capacidad, nClaseEjecutiva, nClaseEconomica;
    private String escala;
    private LocalTime horaSalida, horaLlegada;
    private float costoXAsientoEconomico, costoXAsientoEjecutivo;
    // seguimiento de ventas
    private int vendidosEjecutiva = 0, vendidosEconomica = 0;
    private float recaudado = 0;
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

    public float getCostoPorSeccion(String seccion) {
        if (seccion.equalsIgnoreCase("EJECUTIVA")) {
            return costoXAsientoEjecutivo;
        } else {
            return costoXAsientoEconomico;
        }
    }

    public void acumularVenta(int cantidad, float monto) {
        recaudado += monto;
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
}
