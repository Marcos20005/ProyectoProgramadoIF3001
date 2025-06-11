// Interfaz.java

import java.awt.*;
import java.time.LocalTime;
import javax.swing.*;

public class Interfaz extends JFrame {

    private JTabbedPane pestañas;
    private JPanel panelAlta, panelReserva, panelDespegue, panelReportes;
    private Agencia agencia = new Agencia();

    // Componentes Alta Vuelo
    private JTextField tfCapitan, tfOrigen, tfDestino, tfCapacidad, tfEjecutiva, tfEconomica, tfEscala, tfSalida, tfLlegada, tfCostoEco, tfCostoEj;
    private JButton btnCrearVuelo;

    // Componentes Reserva
    private JComboBox<Avion> cbVuelos;
    private JTextField tfNombre, tfPasaporte, tfCantidad;
    private JComboBox<String> cbSeccion, cbPago;
    private JButton btnReservar;
    private JTextArea taTicket;

    // Despegue
    private JButton btnDespegar;
    private JTextArea taDespegue;

    // Reportes
    private JButton btnReporte;
    private JTextArea taReporte;

    public Interfaz() {
        setTitle("Agencia de Vuelos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        pestañas = new JTabbedPane();
        initAltaPanel();
        initReservaPanel();
        initDespeguePanel();
        initReportesPanel();

        add(pestañas);
    }

    private void initAltaPanel() {
        panelAlta = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        tfCapitan = new JTextField(15);
        tfOrigen = new JTextField(10);
        tfDestino = new JTextField(10);
        tfCapacidad = new JTextField(5);
        tfEjecutiva = new JTextField(5);
        tfEconomica = new JTextField(5);
        tfEscala = new JTextField(10);
        tfSalida = new JTextField(5);
        tfLlegada = new JTextField(5);
        tfCostoEco = new JTextField(7);
        tfCostoEj = new JTextField(7);
        btnCrearVuelo = new JButton("Crear Vuelo");

        String[] labels = {"Capitán:", "Origen:", "Destino:", "Capacidad:", "Clase Ejecutiva:", "Clase Económica:", "Escala:", "Hora Salida (HH:MM):", "Hora Llegada (HH:MM):", "Costo Económico:", "Costo Ejecutivo:"};
        JTextField[] fields = {tfCapitan, tfOrigen, tfDestino, tfCapacidad, tfEjecutiva, tfEconomica, tfEscala, tfSalida, tfLlegada, tfCostoEco, tfCostoEj};
        for (int i = 0; i < labels.length; i++) {
            c.gridx = 0;
            c.gridy = i;
            panelAlta.add(new JLabel(labels[i]), c);
            c.gridx = 1;
            panelAlta.add(fields[i], c);
        }
        c.gridx = 1;
        c.gridy = labels.length;
        panelAlta.add(btnCrearVuelo, c);

        btnCrearVuelo.addActionListener(e -> crearVuelo());
        pestañas.addTab("Alta de vuelo", panelAlta);
    }

    private void initReservaPanel() {
        panelReserva = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.anchor = GridBagConstraints.WEST;

        cbVuelos = new JComboBox<>();
        tfNombre = new JTextField(15);
        tfPasaporte = new JTextField(8);
        tfCantidad = new JTextField(3);
        cbSeccion = new JComboBox<>(new String[]{"EJECUTIVA", "ECONOMICA"});
        cbPago = new JComboBox<>(new String[]{"EFECTIVO", "TARJETA"});
        btnReservar = new JButton("Reservar");
        taTicket = new JTextArea(10, 40);
        taTicket.setEditable(false);

        String[] labels = {"Seleccionar Vuelo:", "Nombre Cliente:", "Pasaporte:", "Cantidad Asientos:", "Sección:", "Tipo Pago:"};
        JComponent[] comps = {cbVuelos, tfNombre, tfPasaporte, tfCantidad, cbSeccion, cbPago};
        for (int i = 0; i < labels.length; i++) {
            c.gridx = 0;
            c.gridy = i;
            panelReserva.add(new JLabel(labels[i]), c);
            c.gridx = 1;
            panelReserva.add(comps[i], c);
        }
        c.gridx = 1;
        c.gridy = labels.length;
        panelReserva.add(btnReservar, c);
        c.gridx = 0;
        c.gridy = labels.length + 1;
        c.gridwidth = 2;
        panelReserva.add(new JScrollPane(taTicket), c);

        btnReservar.addActionListener(e -> reservarAsientos());
        pestañas.addTab("Reserva", panelReserva);
    }

    private void initDespeguePanel() {
        panelDespegue = new JPanel(new BorderLayout());
        btnDespegar = new JButton("Despegar siguiente vuelo");
        taDespegue = new JTextArea();
        taDespegue.setEditable(false);
        panelDespegue.add(btnDespegar, BorderLayout.NORTH);
        panelDespegue.add(new JScrollPane(taDespegue), BorderLayout.CENTER);
        btnDespegar.addActionListener(e -> procesarDespegue());
        pestañas.addTab("Despegue", panelDespegue);
    }

    private void initReportesPanel() {
        panelReportes = new JPanel(new BorderLayout());
        btnReporte = new JButton("Generar reporte fin de día");
        taReporte = new JTextArea();
        taReporte.setEditable(false);
        panelReportes.add(btnReporte, BorderLayout.NORTH);
        panelReportes.add(new JScrollPane(taReporte), BorderLayout.CENTER);
        btnReporte.addActionListener(e -> generarReporte());
        pestañas.addTab("Reportes", panelReportes);
    }

    private void crearVuelo() {

        try {
            String cap = tfCapitan.getText();
            String ori = tfOrigen.getText();
            String des = tfDestino.getText();
            int capTotal = Integer.parseInt(tfCapacidad.getText());
            int ej = Integer.parseInt(tfEjecutiva.getText());
            int ec = Integer.parseInt(tfEconomica.getText());
            String esc = tfEscala.getText();
            LocalTime sal = LocalTime.parse(tfSalida.getText());
            LocalTime lle = LocalTime.parse(tfLlegada.getText());
            float costE = Float.parseFloat(tfCostoEco.getText());
            float costJ = Float.parseFloat(tfCostoEj.getText());
            Avion v = new Avion(cap, ori, des, capTotal, ej, ec, esc, sal, lle, costE, costJ);
            agencia.altaVuelo(v);
            cbVuelos.addItem(v);
            JOptionPane.showMessageDialog(this, "Vuelo creado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear vuelo: " + ex.getMessage());
        }
        tfCapitan.setText("");
        tfOrigen.setText("");
        tfDestino.setText("");
        tfCapacidad.setText("");
        tfEjecutiva.setText("");
        tfEconomica.setText("");
        tfEscala.setText("");
        tfSalida.setText("");
        tfLlegada.setText("");
        tfCostoEco.setText("");
        tfCostoEj.setText("");

    }

    private void reservarAsientos() {
        try {
            Avion v = (Avion) cbVuelos.getSelectedItem();
            String nom = tfNombre.getText();
            int pas = Integer.parseInt(tfPasaporte.getText());
            int cant = Integer.parseInt(tfCantidad.getText());
            String sec = (String) cbSeccion.getSelectedItem();
            String pago = (String) cbPago.getSelectedItem();
            Ticket t = agencia.reservar(nom, pas, v, cant, sec, pago);
            taTicket.setText(t.generarTexto());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en reserva: " + ex.getMessage());
        }
        tfNombre.setText("");
        tfPasaporte.setText("");
        tfCantidad.setText("");
    }

    private void procesarDespegue() {
       // Elimina el vuelo despegado de la lista desplegable
        Avion v = agencia.despegue();
        StringBuilder sb = new StringBuilder();
        if (v != null) {
            sb.append("----- INFO VUELO DESPEGADO -----\n");
            sb.append("Capitán: ").append(v.getNombreCapitan()).append("\n");
            sb.append("Ruta: ").append(v.getOrigen()).append(" → ").append(v.getDestino()).append("\n");
            sb.append("Escala: ").append(v.getEscala()).append("\n");
            sb.append("Salida: ").append(v.getHoraSalida()).append("  Llegada: ").append(v.getHoraLlegada()).append("\n");
            sb.append("Asientos Vendidos Ejecutiva: ").append(v.getVendidosEjecutiva()).append("\n");
            sb.append("Asientos Vendidos Económica: ").append(v.getVendidosEconomica()).append("\n");
            sb.append("Recaudación Vuelo: ").append(v.getRecaudado()).append("\n\n");
            sb.append("Pasajeros asignados:\n");

            // Recorremos la lista de pasajeros dentro de Avion
            ListaClientes listaPas = v.getPasajeros();
            NodoCliente nodo = listaPas.getRaiz();
            while (nodo != null) {
                Cliente c = nodo.cliente;
                sb.append("  • ").append(c.getNombre())
                        .append(" (Pasaporte: ").append(c.getPasaporte()).append(")")
                        .append(" → Asientos: ").append(c.getListaAsiento().toString())
                        .append("\n");
                nodo = nodo.siguiente;
            }
        } else {
            sb.append("No hay vuelos en hangar\n");
        }
        taDespegue.setText(sb.toString());
        if(cbVuelos.getItemCount() > 0) {
            // Elimina el primer vuelo de la lista desplegable
        cbVuelos.removeItem(v);
        }
    }

    private void generarReporte() {
        taReporte.setText(agencia.reporteFinDia());
    }
 
}
