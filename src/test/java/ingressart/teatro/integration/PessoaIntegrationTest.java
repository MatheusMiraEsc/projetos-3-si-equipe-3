package ingressart.teatro.integration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import ingressart.teatro.model.Pessoa;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PessoaIntegrationTest {

    private Connection connection;

    @BeforeAll
    public void setup() throws SQLException {

        connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/ingressart",
            "postgres",   
            "postgres"   
        );

        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DELETE FROM pessoa");
        }
    }

    @AfterAll
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void deveInserirEPesquisarPessoa() throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setId_pessoa(1);
        pessoa.setNome("Joca Mede");
        pessoa.setEmail("calazans@email.com");
        pessoa.setCpf("12345678901");
        pessoa.setTelefone("11999999999");
        pessoa.setData_nascimento(LocalDate.of(1985, 10, 15));
        pessoa.setEndereco("Rua Altinho, 123");
        pessoa.setSenha("senha123");
        pessoa.setTipo_usuario("admin");

        
        String insertSQL = "INSERT INTO pessoa (id_pessoa, nome, email, cpf, telefone, data_nascimento, endereco, senha, tipo_usuario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(insertSQL)) {
            ps.setInt(1, pessoa.getId_pessoa());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getEmail());
            ps.setString(4, pessoa.getCpf());
            ps.setString(5, pessoa.getTelefone());
            ps.setDate(6, Date.valueOf(pessoa.getData_nascimento()));
            ps.setString(7, pessoa.getEndereco());
            ps.setString(8, pessoa.getSenha());
            ps.setString(9, pessoa.getTipo_usuario());
            int rowsInserted = ps.executeUpdate();
            assertEquals(1, rowsInserted, "Deve inserir uma linha");
        }

        
        String selectSQL = "SELECT nome, email, cpf FROM pessoa WHERE id_pessoa = ?";
        try (PreparedStatement ps = connection.prepareStatement(selectSQL)) {
            ps.setInt(1, pessoa.getId_pessoa());
            try (ResultSet rs = ps.executeQuery()) {
                assertTrue(rs.next(), "Pessoa deve existir no banco");
                assertEquals(pessoa.getNome(), rs.getString("nome"));
                assertEquals(pessoa.getEmail(), rs.getString("email"));
                assertEquals(pessoa.getCpf(), rs.getString("cpf"));
            }
        }
    }
}
