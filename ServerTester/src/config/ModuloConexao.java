package config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author Nyguel
 */    

public class ModuloConexao {

    public static Connection conector() {
        java.sql.Connection conexao = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/cesta_basica?useTimezone=true&serverTimezone=UTC&useSSL=false";
        String user = "root";
        String password = "";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}


