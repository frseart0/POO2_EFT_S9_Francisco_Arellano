package dao;

import conexion.ConectorDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDAO {

    private final Connection conn;

    public ClienteDAO() {
        conn = ConectorDB.getInstance().getConnection();
    }

    // 🔹 Crear (INSERT)
    public boolean agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (rut, nombre, direccion, comuna, correo, telefono) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getRut());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getComuna());
            stmt.setString(5, cliente.getCorreo());
            stmt.setString(6, cliente.getTelefono());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        }
    }

    // 🔹 Leer (SELECT)
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente(
                        rs.getString("rut"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("comuna"),
                        rs.getString("correo"),
                        rs.getString("telefono")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Actualizar (UPDATE)
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre=?, direccion=?, comuna=?, correo=?, telefono=? WHERE rut=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getComuna());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getTelefono());
            stmt.setString(6, cliente.getRut());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // 🔹 Eliminar (DELETE)
    public boolean eliminarCliente(String rut) {
        String sql = "DELETE FROM cliente WHERE rut=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rut);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
            return false;
        }
    }
}