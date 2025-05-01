package grupo3.java_backend.dao;

import grupo3.java_backend.model.Peca;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PecaDAO {
    public void insert(Peca p) throws SQLException {
        String sql = "INSERT INTO peca (nome, descricao, data, hora, valor_ingresso) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDate(3, Date.valueOf(p.getData()));
            stmt.setTime(4, Time.valueOf(p.getHora()));
            stmt.setDouble(5, p.getValor_ingresso());
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setId_peca(rs.getInt(1));
                }
            }
        }
    }

    public Peca findById(int id) throws SQLException {
        String sql = "SELECT * FROM peca WHERE id_peca = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Peca p = new Peca();
                    p.setId_peca(rs.getInt("id_peca"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setData(rs.getDate("data").toLocalDate());
                    p.setHora(rs.getTime("hora").toLocalTime());
                    p.setValor_ingresso(rs.getDouble("valor_ingresso"));
                    return p;
                }
            }
        }
        return null;
    }

    public List<Peca> findAll() throws SQLException {
        String sql = "SELECT * FROM peca";
        List<Peca> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Peca p = new Peca();
                p.setId_peca(rs.getInt("id_peca"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setData(rs.getDate("data").toLocalDate());
                p.setHora(rs.getTime("hora").toLocalTime());
                p.setValor_ingresso(rs.getDouble("valor_ingresso"));
                list.add(p);
            }
        }
        return list;
    }

    public void update(Peca p) throws SQLException {
        String sql = "UPDATE peca SET nome=?, descricao=?, data=?, hora=?, valor_ingresso=? WHERE id_peca=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setDate(3, Date.valueOf(p.getData()));
            stmt.setTime(4, Time.valueOf(p.getHora()));
            stmt.setDouble(5, p.getValor_ingresso());
            stmt.setInt(6, p.getId_peca());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM peca WHERE id_peca = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
} 