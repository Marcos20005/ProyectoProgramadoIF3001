
import java.time.LocalTime;

public class Avion {

    // ... (atributos como antes)
    private String nombreCapitan, origen, destino;
    private int capacidad, nClaseEjecutiva, nClaseEconomica;
    private String escala;
    private LocalTime horaSalida, horaLlegada;
    private float costoXAsientoEconomico, costoXAsientoEjecutivo;
    private ListaClientes pasajeros = new ListaClientes();
    private boolean[] estadoAsientos;

    public Avion(String nombreCapitan, String origen, String destino, int capacidad,
            int nClaseEjecutiva, int nClaseEconomica, String escala,
            LocalTime horaSalida, LocalTime horaLlegada,
            float costoXAsientoEconomico, float costoXAsientoEjecutivo) {
        //... (constructor igual)
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
        this.estadoAsientos = new boolean[capacidad];
    }

    // MODIFICADO: Ahora llama directamente al método de la lista para obtener el total.
    public float getRecaudado() {
        return pasajeros.calcularMontoTotal();
    }

    // --- El resto de la clase Avion.java (getters, setters, etc.) permanece igual ---
    // ... (isAsientoOcupado, ocuparAsiento, esAsientoEjecutivo, etc.)
    public boolean isAsientoOcupado(int numeroAsiento) {
        return estadoAsientos[numeroAsiento - 1];
    }

    public void ocuparAsiento(int numeroAsiento) {
        if (!isAsientoOcupado(numeroAsiento)) {
            estadoAsientos[numeroAsiento - 1] = true;
        }
    }

    public boolean esAsientoEjecutivo(int numeroAsiento) {
        return numeroAsiento <= nClaseEjecutiva;
    }

    public float getCostoPorAsiento(int numeroAsiento) {
        if (esAsientoEjecutivo(numeroAsiento)) {
            return costoXAsientoEjecutivo;
        } else {
            return costoXAsientoEconomico;
        }
    }

    public int getVendidosEjecutiva() {
        int contador = 0;
        for (int i = 0; i < nClaseEjecutiva; i++) {
            if (estadoAsientos[i]) {
                contador++;
            }
        }
        return contador;
    }

    public int getVendidosEconomica() {
        int contador = 0;
        for (int i = nClaseEjecutiva; i < capacidad; i++) {
            if (estadoAsientos[i]) {
                contador++;
            }
        }
        return contador;
    }

    public void agregarPasajero(Cliente c) {
        pasajeros.insertar(c);
    }

    @Override
    public String toString() {
        return origen + " -> " + destino + " (" + horaSalida + ")";
    }

    public String getDetallesReporte() {
        String detalles = "";
        detalles += "  Capitán: " + nombreCapitan + "\n";
        detalles += "  Ruta: " + origen + " -> " + destino + "\n";
        detalles += "  Capacidad: " + capacidad + " asientos\n";
        detalles += "  Ingreso Total del Vuelo: " + getRecaudado() + "\n";
        return detalles;
    }

    public ListaClientes getPasajeros() {
        return pasajeros;
    }

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

    public int getnClaseEjecutiva() {
        return nClaseEjecutiva;
    }

    public int getnClaseEconomica() {
        return nClaseEconomica;
    }

    public int getCapacidad() {
        return capacidad;
    }
}
