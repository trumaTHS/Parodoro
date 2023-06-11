package Estrutura;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    /*private static String DB_URL = "jdbc:mysql://192.168.2.38:3306/empresa";
    private static String USERNAME="p00266";
    private static String PASSWORD="s00266";*/

    private static String DB_URL = "jdbc:mysql://localhost:3306/empresa";
    private static String USERNAME="root";
    private static String PASSWORD="";

    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
            } catch (SQLException e) {
                System.out.println("Falha na conexão com o banco de dados");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
