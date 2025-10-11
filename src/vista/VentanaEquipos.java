package vista;

import dao.EquipoDAO;
import modelo.Equipo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaEquipos extends JFrame {
    private EquipoDAO equipoDAO;
    private JTable tablaEquipos;
    private DefaultTableModel modeloTabla;

    private JTextField txtModelo, txtCPU, txtDisco, txtRAM, txtPrecio;
    private JComboBox<String> cmbTipo;
    private JTextField txtPotencia, txtFactor, txtPantalla, txtPuertos;
    private JCheckBox chkTouch;

    public VentanaEquipos() {
        equipoDAO = new EquipoDAO();

        setTitle("Gestión de Equipos - Computec");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(10, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Equipo"));

        txtModelo = new JTextField();
        txtCPU = new JTextField();
        txtDisco = new JTextField();
        txtRAM = new JTextField();
        txtPrecio = new JTextField();

        cmbTipo = new JComboBox<>(new String[]{"Desktop", "Laptop"});
        txtPotencia = new JTextField();
        txtFactor = new JTextField();
        txtPantalla = new JTextField();
        chkTouch = new JCheckBox("Pantalla táctil");
        txtPuertos = new JTextField();

        panelForm.add(new JLabel("Modelo:"));
        panelForm.add(txtModelo);
        panelForm.add(new JLabel("CPU:"));
        panelForm.add(txtCPU);
        panelForm.add(new JLabel("Disco (MB):"));
        panelForm.add(txtDisco);
        panelForm.add(new JLabel("RAM (GB):"));
        panelForm.add(txtRAM);
        panelForm.add(new JLabel("Precio:"));
        panelForm.add(txtPrecio);
        panelForm.add(new JLabel("Tipo:"));
        panelForm.add(cmbTipo);
        panelForm.add(new JLabel("Potencia Fuente (Desktop):"));
        panelForm.add(txtPotencia);
        panelForm.add(new JLabel("Factor de Forma (Desktop):"));
        panelForm.add(txtFactor);
        panelForm.add(new JLabel("Pantalla (Laptop):"));
        panelForm.add(txtPantalla);
        panelForm.add(new JLabel("Puertos USB (Laptop):"));
        panelForm.add(txtPuertos);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);

        modeloTabla = new DefaultTableModel(new Object[]{
                "ID", "Modelo", "CPU", "Disco", "RAM", "Precio", "Tipo",
                "Potencia", "Factor", "Pantalla", "Touch", "Puertos"
        }, 0);
        tablaEquipos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaEquipos);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelForm, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarEquipo());
        btnActualizar.addActionListener(e -> actualizarEquipo());
        btnEliminar.addActionListener(e -> eliminarEquipo());
        btnRefrescar.addActionListener(e -> cargarEquipos());

        cargarEquipos();
    }

    private void agregarEquipo() {
        Equipo e = construirEquipoDesdeFormulario(0);
        if (equipoDAO.agregarEquipo(e)) {
            JOptionPane.showMessageDialog(this, "Equipo agregado.");
            cargarEquipos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar equipo.");
        }
    }

    private void actualizarEquipo() {
        String idStr = JOptionPane.showInputDialog("Ingrese ID del equipo a actualizar:");
        if (idStr == null || idStr.isEmpty()) return;
        int id = Integer.parseInt(idStr);
        Equipo e = construirEquipoDesdeFormulario(id);
        if (equipoDAO.actualizarEquipo(e)) {
            JOptionPane.showMessageDialog(this, "✅ Equipo actualizado.");
            cargarEquipos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar equipo.");
        }
    }

    private void eliminarEquipo() {
        String idStr = JOptionPane.showInputDialog("Ingrese ID del equipo a eliminar:");
        if (idStr == null || idStr.isEmpty()) return;
        int id = Integer.parseInt(idStr);
        if (equipoDAO.eliminarEquipo(id)) {
            JOptionPane.showMessageDialog(this, "Equipo eliminado.");
            cargarEquipos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar equipo.");
        }
    }

    private Equipo construirEquipoDesdeFormulario(int id) {
        String tipo = (String) cmbTipo.getSelectedItem();
        return new Equipo(
                id,
                txtModelo.getText(),
                txtCPU.getText(),
                Integer.parseInt(txtDisco.getText()),
                Integer.parseInt(txtRAM.getText()),
                Double.parseDouble(txtPrecio.getText()),
                tipo,
                tipo.equals("Desktop") ? txtPotencia.getText() : null,
                tipo.equals("Desktop") ? txtFactor.getText() : null,
                tipo.equals("Laptop") ? Double.parseDouble(txtPantalla.getText()) : 0.0,
                tipo.equals("Laptop") && chkTouch.isSelected(),
                tipo.equals("Laptop") ? Integer.parseInt(txtPuertos.getText()) : 0
        );
    }

    private void cargarEquipos() {
        modeloTabla.setRowCount(0);
        List<Equipo> lista = equipoDAO.listarEquipos();
        for (Equipo e : lista) {
            modeloTabla.addRow(new Object[]{
                    e.getIdEquipo(), e.getModelo(), e.getCpu(), e.getDiscoMB(),
                    e.getRamGB(), e.getPrecio(), e.getTipo(),
                    e.getPotenciaFuente(), e.getFactorForma(),
                    e.getPantallaPulgadas(), e.isTouch(), e.getPuertosUSB()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaEquipos().setVisible(true));
    }
}
