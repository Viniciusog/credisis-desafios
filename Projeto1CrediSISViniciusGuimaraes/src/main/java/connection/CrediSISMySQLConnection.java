package connection;

import exceptions.MySQLConnectionException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class CrediSISMySQLConnection {

    private static final String url = "jdbc:mysql://localhost/credisis2db?autoReconnect=true&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    //Conecta para o nosso banco de dados MySQL
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("MySQL Connection Error. Message: " + e.getMessage());
        }
        return con;
    }
}