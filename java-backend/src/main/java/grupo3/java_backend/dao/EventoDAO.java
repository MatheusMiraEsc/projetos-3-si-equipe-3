package grupo3.java_backend.dao;

import grupo3.java_backend.model.Evento;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    public void insert(Evento e) throws SQLException {
        String sql = "INSERT INTO evento (status, nome_evento, descricao, data_inicio, data_fim, categoria, class_indicativa, id_sala) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, e.getStatus());
            stmt.setString(2, e.getNomeEvento());
            stmt.setString(3, e.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(e.getData_inicio()));
            stmt.setTimestamp(5, Timestamp.valueOf(e.getData_fim()));
            stmt.setString(6, e.getCategoria());
            stmt.setInt(7, e.getClass_indicativa());
            stmt.setInt(8, e.getId_sala());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) e.setId_evento(rs.getInt(1));
            }
        }
    }

    public Evento findById(int id) throws SQLException {
        String sql = "SELECT * FROM evento WHERE id_evento = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Evento e = new Evento();
                    e.setId_evento(rs.getInt("id_evento"));
                    e.setStatus(rs.getBoolean("status"));
                    e.setNomeEvento(rs.getString("nome_evento"));
                    e.setDescricao(rs.getString("descricao"));
                    e.setData_inicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                    e.setData_fim(rs.getTimestamp("data_fim").toLocalDateTime());
                    e.setCategoria(rs.getString("categoria"));
                    e.setClass_indicativa(rs.getInt("class_indicativa"));
                    e.setId_sala(rs.getInt("id_sala"));
                    return e;
                }
            }
        }
        return null;
    }

    public List<Evento> findAll() throws SQLException {
        String sql = "SELECT * FROM evento";
        List<Evento> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Evento e = new Evento();
                e.setId_evento(rs.getInt("id_evento"));
                e.setStatus(rs.getBoolean("status"));
                e.setNomeEvento(rs.getString("nome_evento"));
                e.setDescricao(rs.getString("descricao"));
                e.setData_inicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                e.setData_fim(rs.getTimestamp("data_fim").toLocalDateTime());
                e.setCategoria(rs.getString("categoria"));
                e.setClass_indicativa(rs.getInt("class_indicativa"));
                e.setId_sala(rs.getInt("id_sala"));
                list.add(e);
            }
        }
        return list;
    }

    public void update(Evento e) throws SQLException {
        String sql = "UPDATE evento SET status=?, nome_evento=?, descricao=?, data_inicio=?, data_fim=?, categoria=?, class_indicativa=?, id_sala=? WHERE id_evento=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, e.getStatus());
            stmt.setString(2, e.getNomeEvento());
            stmt.setString(3, e.getDescricao());
            stmt.setTimestamp(4, Timestamp.valueOf(e.getData_inicio()));
            stmt.setTimestamp(5, Timestamp.valueOf(e.getData_fim()));
            stmt.setString(6, e.getCategoria());
            stmt.setInt(7, e.getClass_indicativa());
            stmt.setInt(8, e.getId_sala());
            stmt.setInt(9, e.getId_evento());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM evento WHERE id_evento = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

