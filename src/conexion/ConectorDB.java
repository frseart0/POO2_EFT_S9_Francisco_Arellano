package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorDB {
    private static ConectorDB instance;
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/computec";
    private final String USER = "root";
    private final String PASSWORD = "1234";

    private ConectorDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conectado");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("error");
        }
    }

    public static ConectorDB getInstance() {
        if (instance == null) {
            instance = new ConectorDB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}