package conexion;

import conexion.ConectorDB;
import java.sql.Connection;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = ConectorDB.getInstance().getConnection();
    }
}