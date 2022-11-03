package persistence;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
    private static String USERNAME = "root";
    private static String PASSWORD = "root";
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/treinamento?serverTimezone=America/Sao_Paulo";

    public static Connection createConnectionToMySQL() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");// Driver de conexão
        Connection connection = (Connection) DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;

    }

    public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySQL();
        if (con != null) { // Verifica se a conexão não é vazia
            System.out.println("Conexão criada");
            con.close();
        }
    }
}