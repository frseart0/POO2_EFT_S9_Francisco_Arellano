package dao;

import conexion.ConectorDB;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;

public class VentaDAO {

    private final Connection conn;

    public VentaDAO() {
        conn = ConectorDB.getInstance().getConnection();
    }

    // 🔹 Crear venta
    public boolean registrarVenta(Venta venta) {
        String sql = "INSERT INTO venta (rut_cliente, id_equipo, fecha_hora, descuento, total) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, venta.getRutCliente());
            stmt.setInt(2, venta.getIdEquipo());
            stmt.setTimestamp(3, Timestamp.valueOf(venta.getFechaHora()));
            stmt.setDouble(4, venta.getDescuento());
            stmt.setDouble(5, venta.getTotal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar venta");
            return false;
        }
    }

    // 🔹 Listar todas las ventas
    public List<Venta> listarVentas() {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM venta ORDER BY fecha_hora DESC";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Venta v = new Venta(
                        rs.getInt("id_venta"),
                        rs.getString("rut_cliente"),
                        rs.getInt("id_equipo"),
                        rs.getTimestamp("fecha_hora").toLocalDateTime(),
                        rs.getDouble("descuento"),
                        rs.getDouble("total")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ventas");
        }
        return lista;
    }

    // 🔹 Reporte: total de ventas y monto total
    public void mostrarResumenVentas() {
        String sql = "SELECT COUNT(*) AS cantidad, SUM(total) AS monto FROM venta";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.println("Ventas realizadas: " + rs.getInt("cantidad"));
                System.out.println("Monto total recaudado: $" + rs.getDouble("monto"));
            }
        } catch (SQLException e) {
            System.out.println("Error al generar resumen");
        }
    }

    // 🔹 Reporte: equipos vendidos con datos de cliente
    public void listarEquiposVendidos() {
        String sql = """
            SELECT e.modelo, e.tipo, c.nombre, c.telefono, c.correo, e.precio
            FROM venta v
            JOIN cliente c ON v.rut_cliente = c.rut
            JOIN equipo e ON v.id_equipo = e.id_equipo
        """;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Reporte de Equipos Vendidos");
            while (rs.next()) {
                System.out.println(
                        "Modelo: " + rs.getString("modelo") +
                                " | Tipo: " + rs.getString("tipo") +
                                " | Cliente: " + rs.getString("nombre") +
                                " | Tel: " + rs.getString("telefono") +
                                " | Correo: " + rs.getString("correo") +
                                " | Precio: $" + rs.getDouble("precio")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al listar equipos vendidos");
        }
    }
}
