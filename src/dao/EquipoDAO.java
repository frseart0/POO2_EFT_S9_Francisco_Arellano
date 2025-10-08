package dao;

import conexion.ConectorDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Equipo;

public class EquipoDAO {

    private final Connection conn;

    public EquipoDAO() {
        conn = ConectorDB.getInstance().getConnection();
    }

    public boolean agregarEquipo(Equipo e) {
        String sql = "INSERT INTO equipo (modelo, cpu, disco_mb, ram_gb, precio, tipo, potencia_fuente, factor_forma, pantalla_pulgadas, touch, puertos_usb) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getModelo());
            stmt.setString(2, e.getCpu());
            stmt.setInt(3, e.getDiscoMB());
            stmt.setInt(4, e.getRamGB());
            stmt.setDouble(5, e.getPrecio());
            stmt.setString(6, e.getTipo());
            stmt.setString(7, e.getPotenciaFuente());
            stmt.setString(8, e.getFactorForma());
            stmt.setDouble(9, e.getPantallaPulgadas());
            stmt.setBoolean(10, e.isTouch());
            stmt.setInt(11, e.getPuertosUSB());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al agregar equipo");
            return false;
        }
    }

    public List<Equipo> listarEquipos() {
        List<Equipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM equipo";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Equipo e = new Equipo(
                        rs.getInt("id_equipo"),
                        rs.getString("modelo"),
                        rs.getString("cpu"),
                        rs.getInt("disco_mb"),
                        rs.getInt("ram_gb"),
                        rs.getDouble("precio"),
                        rs.getString("tipo"),
                        rs.getString("potencia_fuente"),
                        rs.getString("factor_forma"),
                        rs.getDouble("pantalla_pulgadas"),
                        rs.getBoolean("touch"),
                        rs.getInt("puertos_usb")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar equipos:");
        }
        return lista;
    }

    public boolean actualizarEquipo(Equipo e) {
        String sql = "UPDATE equipo SET modelo=?, cpu=?, disco_mb=?, ram_gb=?, precio=?, tipo=?, potencia_fuente=?, factor_forma=?, pantalla_pulgadas=?, touch=?, puertos_usb=? WHERE id_equipo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, e.getModelo());
            stmt.setString(2, e.getCpu());
            stmt.setInt(3, e.getDiscoMB());
            stmt.setInt(4, e.getRamGB());
            stmt.setDouble(5, e.getPrecio());
            stmt.setString(6, e.getTipo());
            stmt.setString(7, e.getPotenciaFuente());
            stmt.setString(8, e.getFactorForma());
            stmt.setDouble(9, e.getPantallaPulgadas());
            stmt.setBoolean(10, e.isTouch());
            stmt.setInt(11, e.getPuertosUSB());
            stmt.setInt(12, e.getIdEquipo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al actualizar equipo");
            return false;
        }
    }

    public boolean eliminarEquipo(int idEquipo) {
        String sql = "DELETE FROM equipo WHERE id_equipo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEquipo);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error al eliminar equipo");
            return false;
        }
    }
}