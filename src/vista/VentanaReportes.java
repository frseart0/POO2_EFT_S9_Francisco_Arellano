package vista;

import dao.VentaDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaReportes extends JFrame {
    private VentaDAO ventaDAO;
    private JTable tablaReportes;
    private DefaultTableModel modeloTabla;
    private JTextField txtCantidadVentas, txtMontoTotal;

    public VentanaReportes() {
        ventaDAO = new VentaDAO();

        setTitle("Reportes de Ventas - Computec");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelResumen = new JPanel(new GridLayout(2, 2, 10, 10));
        panelResumen.setBorder(BorderFactory.createTitledBorder("Resumen General"));

        txtCantidadVentas = new JTextField();
        txtCantidadVentas.setEditable(false);
        txtMontoTotal = new JTextField();
        txtMontoTotal.setEditable(false);

        panelResumen.add(new JLabel("Cantidad de Ventas:"));
        panelResumen.add(txtCantidadVentas);
        panelResumen.add(new JLabel("Monto Total Recaudado:"));
        panelResumen.add(txtMontoTotal);

        modeloTabla = new DefaultTableModel(new Object[]{
                "Modelo", "Tipo", "Cliente", "Teléfono", "Correo", "Precio"
        }, 0);
        tablaReportes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaReportes);
        JButton btnRefrescar = new JButton("Actualizar Reporte");
        btnRefrescar.addActionListener(e -> cargarReportes());

        add(panelResumen, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(btnRefrescar, BorderLayout.SOUTH);

        cargarReportes();
    }

    private void cargarReportes() {
        modeloTabla.setRowCount(0);
        ventaDAO.listarEquiposVendidos(); // imprime en consola

        try {
            var conn = ventaDAO.getClass()
                    .getDeclaredField("conn")
                    .get(ventaDAO);
            var stmt = ((java.sql.Connection) conn).createStatement();
            var rs = stmt.executeQuery("SELECT COUNT(*) AS cantidad, SUM(total) AS monto FROM venta");
            if (rs.next()) {
                txtCantidadVentas.setText(String.valueOf(rs.getInt("cantidad")));
                txtMontoTotal.setText("$" + String.format("%.2f", rs.getDouble("monto")));
            }
        } catch (Exception e) {
            txtCantidadVentas.setText("Error");
            txtMontoTotal.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaReportes().setVisible(true));
    }
}
