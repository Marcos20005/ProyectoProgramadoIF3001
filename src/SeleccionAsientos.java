import java.awt.*;
import javax.swing.*;

public class SeleccionAsientos extends JDialog {
    private int[] asientosSeleccionados;
    private int numAsientosSeleccionados = 0;
    
    private int cantidadASeleccionar;
    private Avion avion;
    private boolean aceptado = false;
    private JLabel lblContador;

    public SeleccionAsientos(JFrame owner, Avion avion, int cantidad) {
        super(owner, "Seleccione sus Asientos", true);
        this.avion = avion;
        this.cantidadASeleccionar = cantidad;
        this.asientosSeleccionados = new int[cantidad];
        
        setLayout(new BorderLayout(10, 10));

        JPanel panelAsientos = new JPanel(new GridLayout(0, 10, 5, 5));
        for (int i = 1; i <= avion.getCapacidad(); i++) {
            JButton btnAsiento = new JButton(String.valueOf(i));
            final int numeroAsiento = i;

            if (avion.isAsientoOcupado(numeroAsiento)) {
                btnAsiento.setBackground(Color.RED);
                btnAsiento.setEnabled(false);
            } else {
                btnAsiento.setBackground(avion.esAsientoEjecutivo(numeroAsiento) ? Color.CYAN : Color.GREEN);
                btnAsiento.addActionListener(e -> seleccionarAsiento(btnAsiento, numeroAsiento));
            }
            panelAsientos.add(btnAsiento);
        }

        JPanel panelControl = new JPanel();
        lblContador = new JLabel("Asientos seleccionados: 0 de " + cantidadASeleccionar);
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(e -> {
            if (numAsientosSeleccionados == cantidadASeleccionar) {
                aceptado = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Debe seleccionar " + cantidadASeleccionar + " asientos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        panelControl.add(lblContador);
        panelControl.add(btnAceptar);

        add(new JScrollPane(panelAsientos), BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);
        
        setSize(800, 600);
        setLocationRelativeTo(owner);
    }
    
    private void seleccionarAsiento(JButton boton, int numeroAsiento) {
        boolean yaSeleccionado = false;
        int indiceEncontrado = -1;
        for (int i = 0; i < numAsientosSeleccionados; i++) {
            if (asientosSeleccionados[i] == numeroAsiento) {
                yaSeleccionado = true;
                indiceEncontrado = i;
                break;
            }
        }

        if (yaSeleccionado) {
            asientosSeleccionados[indiceEncontrado] = asientosSeleccionados[numAsientosSeleccionados - 1];
            numAsientosSeleccionados--;
            boton.setBackground(avion.esAsientoEjecutivo(numeroAsiento) ? Color.CYAN : Color.GREEN);
        } else {
            if (numAsientosSeleccionados < cantidadASeleccionar) {
                asientosSeleccionados[numAsientosSeleccionados] = numeroAsiento;
                numAsientosSeleccionados++;
                boton.setBackground(Color.ORANGE);
            }
        }
        lblContador.setText("Asientos seleccionados: " + numAsientosSeleccionados + " de " + cantidadASeleccionar);
    }

    public int[] getAsientosSeleccionados() {
        if (!aceptado) {
            return new int[0];
        }
        int[] resultado = new int[numAsientosSeleccionados];
        for(int i=0; i < numAsientosSeleccionados; i++) {
            resultado[i] = asientosSeleccionados[i];
        }
        return resultado;
    }
}
