package grupo3.java_backend.dao;

import grupo3.java_backend.model.Sessao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {

    // Insere uma nova sessão no banco
    public void insert(Sessao s) throws SQLException {
        String sql = "INSERT INTO sessao (data_inicio, data_fim, preco_sessao, num_ingressos_disp, id_evento, id_sala) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(s.getData_inicio()));
            // data_fim pode ser null
            if (s.getData_fim() != null) {
                stmt.setTimestamp(2, Timestamp.valueOf(s.getData_fim()));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }
            stmt.setFloat(3, s.getPreco_sessao());
            stmt.setInt(4, s.getNum_ingressos_disp());
            stmt.setInt(5, s.getId_evento());
            stmt.setInt(6, s.getId_sala());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setId_sessao(rs.getInt(1));
                }
            }
        }
    }

    // Busca uma sessão pelo seu próprio ID
    public Sessao findById(int id) throws SQLException {
        String sql = "SELECT * FROM sessao WHERE id_sessao = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToSessao(rs);
                }
            }
        }
        return null;
    }

    // Busca a primeira sessão vinculada a um evento
    public Sessao findByEventoId(int eventoId) throws SQLException {
        String sql = "SELECT * FROM sessao WHERE id_evento = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToSessao(rs);
                }
            }
        }
        return null;
    }
    public List<Sessao> findAllByEventoId(int eventoId)throws SQLException{
        String sql = "SELECT * FROM sessao WHERE id_evento = ?";
        List<Sessao> sessoes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sessoes.add(mapRowToSessao(rs));
                }
            }
        }
        return sessoes;
    }

    // Lista todas as sessões vinculadas a uma sala
    public List<Sessao> findBySalaId(int idSala) throws SQLException {
        String sql = "SELECT * FROM sessao WHERE id_sala = ?";
        List<Sessao> sessoes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idSala);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    sessoes.add(mapRowToSessao(rs));
                }
            }
        }
        return sessoes;
    }

    // Atualiza campos de uma sessão (inclusive num_ingressos_disp)
    public void update(Sessao s) throws SQLException {
        String sql = "UPDATE sessao SET data_inicio=?, data_fim=?, preco_sessao=?, num_ingressos_disp=?, id_evento=?, id_sala=? WHERE id_sessao=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(s.getData_inicio()));
            if (s.getData_fim() != null) {
                stmt.setTimestamp(2, Timestamp.valueOf(s.getData_fim()));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }
            stmt.setFloat(3, s.getPreco_sessao());
            stmt.setInt(4, s.getNum_ingressos_disp());
            stmt.setInt(5, s.getId_evento());
            stmt.setInt(6, s.getId_sala());
            stmt.setInt(7, s.getId_sessao());
            stmt.executeUpdate();
        }
    }

    // Remove uma sessão pelo ID
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM sessao WHERE id_sessao = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Converte uma linha do ResultSet em um objeto Sessao, tratando nulls
    private Sessao mapRowToSessao(ResultSet rs) throws SQLException {
        Sessao sessao = new Sessao();
        sessao.setId_sessao(rs.getInt("id_sessao"));

        // data_inicio — deve sempre existir (postgre retorna não null)
        Timestamp tsInicio = rs.getTimestamp("data_inicio");
        if (tsInicio != null) {
            sessao.setData_inicio(tsInicio.toLocalDateTime());
        }

        // data_fim — pode ser NULL
        Timestamp tsFim = rs.getTimestamp("data_fim");
        if (tsFim != null) {
            sessao.setData_fim(tsFim.toLocalDateTime());
        } else {
            sessao.setData_fim(sessao.getData_inicio());
        }

        sessao.setPreco_sessao(rs.getFloat("preco_sessao"));
        sessao.setNum_ingressos_disp(rs.getInt("num_ingressos_disp"));
        sessao.setId_evento(rs.getInt("id_evento"));
        sessao.setId_sala(rs.getInt("id_sala"));
        return sessao;
    }
}
