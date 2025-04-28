package grupo3.java_backend.dao;

import grupo3.java_backend.model.Sala;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO {
    public void insert(Sala s) throws SQLException {
        String sql = "INSERT INTO sala (capacidade, tipo) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, s.getCapacidade());
            stmt.setString(2, s.getTipo());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) s.setId_sala(rs.getInt(1));
            }
        }
    }

    public Sala findById(int id) throws SQLException {
        String sql = "SELECT * FROM sala WHERE id_sala = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Sala s = new Sala();
                    s.setId_sala(rs.getInt("id_sala"));
                    s.setCapacidade(rs.getInt("capacidade"));
                    s.setTipo(rs.getString("tipo"));
                    return s;
                }
            }
        }
        return null;
    }

    public List<Sala> findAll() throws SQLException {
        String sql = "SELECT * FROM sala";
        List<Sala> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sala s = new Sala();
                s.setId_sala(rs.getInt("id_sala"));
                s.setCapacidade(rs.getInt("capacidade"));
                s.setTipo(rs.getString("tipo"));
                list.add(s);
            }
        }
        return list;
    }

    public void update(Sala s) throws SQLException {
        String sql = "UPDATE sala SET capacidade=?, tipo=? WHERE id_sala=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getCapacidade());
            stmt.setString(2, s.getTipo());
            stmt.setInt(3, s.getId_sala());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM sala WHERE id_sala = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

