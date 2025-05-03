package ingressart.teatro.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ingressart.teatro.model.Pessoa;

public class PessoaDAO {
    public void insert(Pessoa p) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, email, cpf, telefone, data_nascimento, senha, tipo_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getCpf());
            stmt.setString(4, p.getTelefone());
            stmt.setDate(5, Date.valueOf(p.getData_nascimento()));
            stmt.setString(6, p.getSenha());
            stmt.setString(7, p.getTipo_usuario());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) p.setId_pessoa(rs.getInt(1));
            }
        }
    }

    public Pessoa findById(int id) throws SQLException {
        String sql = "SELECT * FROM pessoa WHERE id_pessoa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Pessoa p = new Pessoa();
                    p.setId_pessoa(rs.getInt("id_pessoa"));
                    p.setNome(rs.getString("nome"));
                    p.setEmail(rs.getString("email"));
                    p.setCpf(rs.getString("cpf"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
                    p.setSenha(rs.getString("senha"));
                    p.setTipo_usuario(rs.getString("tipo_usuario"));
                    return p;
                }
            }
        }
        return null;
    }

    public List<Pessoa> findAll() throws SQLException {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId_pessoa(rs.getInt("id_pessoa"));
                p.setNome(rs.getString("nome"));
                p.setEmail(rs.getString("email"));
                p.setCpf(rs.getString("cpf"));
                p.setTelefone(rs.getString("telefone"));
                p.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
                p.setSenha(rs.getString("senha"));
                p.setTipo_usuario(rs.getString("tipo_usuario"));
                list.add(p);
            }
        }
        return list;
    }

    public void update(Pessoa p) throws SQLException {
        String sql = "UPDATE pessoa SET nome=?, email=?, telefone=?,  senha=? WHERE id_pessoa=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getTelefone());
            stmt.setString(5, p.getSenha());
            stmt.setInt(6, p.getId_pessoa());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pessoa WHERE id_pessoa = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

