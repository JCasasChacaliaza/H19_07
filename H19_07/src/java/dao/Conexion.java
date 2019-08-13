package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conectar;

    public void Conexion() {
        try {
            if (conectar == null) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conectar = DriverManager.getConnection("jdbc:sqlserver://db-server-ventas.database.windows.net; database=H19_07_BD", "ServerUser", "dbPassF!nd");
//                conectar = DriverManager.getConnection("jdbc:sqlserver://192.168.8.122; database=demoII", "admin", "admin");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error " + e);
        }
    }

    public void cerrrar() throws SQLException {
        if (conectar != null) {
            if (conectar.isClosed() == false) {
                conectar.close();
                conectar = null;
            }
        }
    }

    public static void main(String[] args) {
        Conexion dao = new Conexion();
        dao.Conexion();
        if (dao.getConectar() != null) {
            System.out.println("conectado");
        } else {
            System.out.println("no conecta");
        }
    }

    public Connection getConectar() {
        return conectar;
    }

    public void setConectar(Connection conectar) {
        this.conectar = conectar;
    }

}
