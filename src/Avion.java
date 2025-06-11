
import java.time.LocalTime;


public class Avion {

    String nombreCapitan, origen, destino;
    int capacidad, nClaseEjecutiva,nClaseEconomica;
    String escala;
    LocalTime horaSalida,horaLlegada;
    float costoXAsientoEconomico,costoXAsientoEjecutivo;
    public Avion(String nombreCapitan, String origen, String destino, int capacidad, int nClaseEjecutiva,
            int nClaseEconomica, String escala, LocalTime horaSalida, LocalTime horaLlegada,
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

    
    


}
