package vista;

import dao.ClienteDAO;
import dao.EquipoDAO;
import dao.VentaDAO;
import descuentos.*;
import modelo.Cliente;
import modelo.Equipo;
import modelo.Venta;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class VentanaVentas extends JFrame {

    private ClienteDAO clienteDAO;
    private EquipoDAO equipoDAO;
    private VentaDAO ventaDAO;

    private JComboBox<String> cmbClientes;
    private JComboBox<String> cmbEquipos;
    private JComboBox<String> cmbDescuento;
    private JTextField txtPrecio, txtTotal;
    private JButton btnRegistrar;

    public VentanaVentas() {
        clienteDAO = new ClienteDAO();
        equipoDAO = new EquipoDAO();
        ventaDAO = new VentaDAO();

        setTitle("Registro de Ventas - Computec");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 8, 8));

        // Componentes
        add(new JLabel("Cliente (RUT):"));
        cmbClientes = new JComboBox<>();
        add(cmbClientes);

        add(new JLabel("Equipo (modelo):"));
        cmbEquipos = new JComboBox<>();
        add(cmbEquipos);

        add(new JLabel("Precio base:"));
        txtPrecio = new JTextField();
        txtPrecio.setEditable(false);
        add(txtPrecio);

        add(new JLabel("Descuento:"));
        cmbDescuento = new JComboBox<>(new String[]{"Sin descuento", "10%", "20%"});
        add(cmbDescuento);

        add(new JLabel("Total final:"));
        txtTotal = new JTextField();
        txtTotal.setEditable(false);
        add(txtTotal);

        btnRegistrar = new JButton("Registrar Venta");
        add(new JLabel()); // Espaciador
        add(btnRegistrar);

        // Eventos
        btnRegistrar.addActionListener(e -> registrarVenta());
        cmbEquipos.addActionListener(e -> mostrarPrecioEquipo());
        cmbDescuento.addActionListener(e -> calcularTotal());

        cargarClientes();
        cargarEquipos();
    }

    private void cargarClientes() {
        List<Cliente> lista = clienteDAO.listarClientes();
        for (Cliente c : lista) {
            cmbClientes.addItem(c.getRut());
        }
    }

    private void cargarEquipos() {
        List<Equipo> lista = equipoDAO.listarEquipos();
        for (Equipo e : lista) {
            cmbEquipos.addItem(e.getModelo() + " (ID:" + e.getIdEquipo() + ")");
        }
    }

    private void mostrarPrecioEquipo() {
        int index = cmbEquipos.getSelectedIndex();
        if (index < 0) return;
        Equipo eq = equipoDAO.listarEquipos().get(index);
        txtPrecio.setText(String.valueOf(eq.getPrecio()));
        calcularTotal();
    }

    private void calcularTotal() {
        if (txtPrecio.getText().isEmpty()) return;
        double precio = Double.parseDouble(txtPrecio.getText());

        Descuento descuento;
        switch ((String) cmbDescuento.getSelectedItem()) {
            case "10%" -> descuento = new Descuento10(new SinDescuento());
            case "20%" -> descuento = new Descuento20(new SinDescuento());
            default -> descuento = new SinDescuento();
        }

        double totalFinal = descuento.aplicarDescuento(precio);
        txtTotal.setText(String.format("%.2f", totalFinal));
    }

    private void registrarVenta() {
        try {
            String rutCliente = (String) cmbClientes.getSelectedItem();
            int indexEquipo = cmbEquipos.getSelectedIndex();
            Equipo eq = equipoDAO.listarEquipos().get(indexEquipo);

            double descuento = switch ((String) cmbDescuento.getSelectedItem()) {
                case "10%" -> 10;
                case "20%" -> 20;
                default -> 0;
            };

            double total = Double.parseDouble(txtTotal.getText());

            Venta v = new Venta(0, rutCliente, eq.getIdEquipo(), LocalDateTime.now(), descuento, total);

            if (ventaDAO.registrarVenta(v)) {
                JOptionPane.showMessageDialog(this, "Venta registrada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar venta.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Complete todos los datos correctamente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaVentas().setVisible(true));
    }
}