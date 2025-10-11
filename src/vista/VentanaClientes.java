package vista;

import dao.ClienteDAO;
import modelo.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaClientes extends JFrame {

    private ClienteDAO clienteDAO;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private JTextField txtRut, txtNombre, txtDireccion, txtComuna, txtCorreo, txtTelefono;

    public VentanaClientes() {
        clienteDAO = new ClienteDAO();

        setTitle("Gestión de Clientes - Computec");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(7, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtRut = new JTextField();
        txtNombre = new JTextField();
        txtDireccion = new JTextField();
        txtComuna = new JTextField();
        txtCorreo = new JTextField();
        txtTelefono = new JTextField();

        panelForm.add(new JLabel("RUT:"));
        panelForm.add(txtRut);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Dirección:"));
        panelForm.add(txtDireccion);
        panelForm.add(new JLabel("Comuna:"));
        panelForm.add(txtComuna);
        panelForm.add(new JLabel("Correo:"));
        panelForm.add(txtCorreo);
        panelForm.add(new JLabel("Teléfono:"));
        panelForm.add(txtTelefono);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);

        modeloTabla = new DefaultTableModel(new Object[]{"RUT", "Nombre", "Dirección", "Comuna", "Correo", "Teléfono"}, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaClientes);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelForm, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarCliente());
        btnActualizar.addActionListener(e -> actualizarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnRefrescar.addActionListener(e -> cargarClientes());
        cargarClientes();
    }

    private void agregarCliente() {
        Cliente c = new Cliente(
                txtRut.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtComuna.getText(),
                txtCorreo.getText(),
                txtTelefono.getText()
        );

        if (clienteDAO.agregarCliente(c)) {
            JOptionPane.showMessageDialog(this, "Cliente agregado.");
            cargarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar cliente.");
        }
    }

    private void actualizarCliente() {
        Cliente c = new Cliente(
                txtRut.getText(),
                txtNombre.getText(),
                txtDireccion.getText(),
                txtComuna.getText(),
                txtCorreo.getText(),
                txtTelefono.getText()
        );

        if (clienteDAO.actualizarCliente(c)) {
            JOptionPane.showMessageDialog(this, "Cliente actualizado.");
            cargarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar cliente.");
        }
    }
    private void eliminarCliente() {
        String rut = txtRut.getText();
        if (rut.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el RUT del cliente a eliminar.");
            return;
        }
        if (clienteDAO.eliminarCliente(rut)) {
            JOptionPane.showMessageDialog(this, "Cliente eliminado");
            cargarClientes();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar cliente");
        }
    }

    private void cargarClientes() {
        modeloTabla.setRowCount(0);
        List<Cliente> lista = clienteDAO.listarClientes();
        for (Cliente c : lista) {
            modeloTabla.addRow(new Object[]{
                    c.getRut(), c.getNombre(), c.getDireccion(), c.getComuna(), c.getCorreo(), c.getTelefono()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaClientes().setVisible(true));
    }
}
