package ingressart.teatro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/ingressart"; 
    private static final String USER = "postgres";  //eu usuário do banco
    private static final String PASS = "postgres";  // sua senha do banco

    // Bloco estático que carrega o driver do PostgreSQL
    static {
        try {
            // Carregando o driver PostgreSQL
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            // Se o driver não for encontrado, uma exceção é lançada
            throw new RuntimeException("PostgreSQL JDBC driver not found", e);
        }
    }

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        // Retorna a conexão usando a URL, usuário e senha configurados
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
