package ingressart.teatro.dao;

import ingressart.teatro.model.Evento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    // Insere um novo evento no banco
    public void insert(Evento e) throws SQLException {
        String sql = "INSERT INTO evento (status, nome_evento, descricao, data_inicio, data_fim, categoria, class_indicativa, id_sala) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setBoolean(1, e.getStatus());
            stmt.setString(2, e.getNomeEvento());
            stmt.setString(3, e.getDescricao());
            // data_inicio
            if (e.getData_inicio() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(e.getData_inicio()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }
            // data_fim
            if (e.getData_fim() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(e.getData_fim()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }
            stmt.setString(6, e.getCategoria());
            stmt.setInt(7, e.getClass_indicativa());
            stmt.setInt(8, e.getId_sala());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setId_evento(rs.getInt(1));
                }
            }
        }
    }

    // Busca todos os eventos no banco
    public List<Evento> findAll() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM evento";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                eventos.add(mapRowToEvento(rs));
            }
        }
        return eventos;
    }

    // Busca um evento pelo seu ID
    public Evento findById(int id) throws SQLException {
        String sql = "SELECT * FROM evento WHERE id_evento = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToEvento(rs);
                }
            }
        }
        return null;
    }

    // Atualiza um evento existente
    public void update(Evento e) throws SQLException {
        String sql = "UPDATE evento SET status=?, nome_evento=?, descricao=?, data_inicio=?, data_fim=?, categoria=?, class_indicativa=?, id_sala=? WHERE id_evento=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, e.getStatus());
            stmt.setString(2, e.getNomeEvento());
            stmt.setString(3, e.getDescricao());
            if (e.getData_inicio() != null) {
                stmt.setTimestamp(4, Timestamp.valueOf(e.getData_inicio()));
            } else {
                stmt.setNull(4, Types.TIMESTAMP);
            }
            if (e.getData_fim() != null) {
                stmt.setTimestamp(5, Timestamp.valueOf(e.getData_fim()));
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }
            stmt.setString(6, e.getCategoria());
            stmt.setInt(7, e.getClass_indicativa());
            stmt.setInt(8, e.getId_sala());
            stmt.setInt(9, e.getId_evento());
            stmt.executeUpdate();
        }
    }

    // Remove um evento pelo ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM evento WHERE id_evento = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Mapeia uma linha do ResultSet para um objeto Evento, tratando possíveis nulls
    private Evento mapRowToEvento(ResultSet rs) throws SQLException {
        Evento e = new Evento();
        e.setId_evento(rs.getInt("id_evento"));
        e.setStatus(rs.getBoolean("status"));
        e.setNomeEvento(rs.getString("nome_evento"));
        e.setDescricao(rs.getString("descricao"));

        // data_inicio
        Timestamp tsInicio = rs.getTimestamp("data_inicio");
        if (tsInicio != null) {
            e.setData_inicio(tsInicio.toLocalDateTime());
        }

        // data_fim
        Timestamp tsFim = rs.getTimestamp("data_fim");
        if (tsFim != null) {
            e.setData_fim(tsFim.toLocalDateTime());
        }

        e.setCategoria(rs.getString("categoria"));
        e.setClass_indicativa(rs.getInt("class_indicativa"));
        e.setId_sala(rs.getInt("id_sala"));
        return e;
    }
}
