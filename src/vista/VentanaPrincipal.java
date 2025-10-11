package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuClientes, menuEquipos, menuVentas, menuReportes;
    private JMenuItem itemClientes, itemEquipos, itemVentas, itemReportes;

    public VentanaPrincipal() {
        setTitle("Computce");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        inicializarMenu();

        JLabel lblTitulo = new JLabel("Sistema de gestion Computec", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.CENTER);
    }

    private void inicializarMenu() {
        menuBar = new JMenuBar();

        menuClientes = new JMenu("Clientes");
        itemClientes = new JMenuItem("Gestión de Clientes");
        itemClientes.addActionListener(e -> new VentanaClientes().setVisible(true));
        menuClientes.add(itemClientes);

        menuEquipos = new JMenu("Equipos");
        itemEquipos = new JMenuItem("Gestión de Equipos");
        itemEquipos.addActionListener(e -> new VentanaEquipos().setVisible(true));
        menuEquipos.add(itemEquipos);

        menuVentas = new JMenu("Ventas");
        itemVentas = new JMenuItem("Registrar Venta");
        itemVentas.addActionListener(e -> new VentanaVentas().setVisible(true));
        menuVentas.add(itemVentas);

        menuReportes = new JMenu("Reportes");
        itemReportes = new JMenuItem("Ver Reportes");
        itemReportes.addActionListener(e -> new VentanaReportes().setVisible(true));
        menuReportes.add(itemReportes);

        menuBar.add(menuClientes);
        menuBar.add(menuEquipos);
        menuBar.add(menuVentas);
        menuBar.add(menuReportes);

        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
