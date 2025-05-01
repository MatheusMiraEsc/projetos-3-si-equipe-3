package grupo3.java_backend.dao;

import grupo3.java_backend.model.Sessao;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessaoDAO {

    // Insere uma nova sessão no banco
    public void insert(Sessao s) throws SQLException {
        String sql = "INSERT INTO sessao (id_peca, data_inicio, preco_sessao, num_ingressos_disp, id_sala) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, s.getId_peca());
            stmt.setTimestamp(2, Timestamp.valueOf(s.getData_inicio()));
            stmt.setDouble(3, s.getPreco_sessao());
            stmt.setInt(4, s.getNum_ingressos_disp());
            stmt.setInt(5, s.getId_sala());
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
                    Sessao s = new Sessao();
                    s.setId_sessao(rs.getInt("id_sessao"));
                    s.setId_peca(rs.getInt("id_peca"));
                    s.setData_inicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                    s.setPreco_sessao(rs.getDouble("preco_sessao"));
                    s.setNum_ingressos_disp(rs.getInt("num_ingressos_disp"));
                    s.setId_sala(rs.getInt("id_sala"));
                    return s;
                }
            }
        }
        return null;
    }

    // Busca a primeira sessão vinculada a um evento
    public Sessao findByEventoId(int idPeca) throws SQLException {
        String sql = "SELECT * FROM sessao WHERE id_peca = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPeca);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Sessao s = new Sessao();
                    s.setId_sessao(rs.getInt("id_sessao"));
                    s.setId_peca(rs.getInt("id_peca"));
                    s.setData_inicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                    s.setPreco_sessao(rs.getDouble("preco_sessao"));
                    s.setNum_ingressos_disp(rs.getInt("num_ingressos_disp"));
                    s.setId_sala(rs.getInt("id_sala"));
                    return s;
                }
            }
        }
        return null;
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
        String sql = "UPDATE sessao SET id_peca=?, data_inicio=?, preco_sessao=?, num_ingressos_disp=?, id_sala=? WHERE id_sessao=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, s.getId_peca());
            stmt.setTimestamp(2, Timestamp.valueOf(s.getData_inicio()));
            stmt.setDouble(3, s.getPreco_sessao());
            stmt.setInt(4, s.getNum_ingressos_disp());
            stmt.setInt(5, s.getId_sala());
            stmt.setInt(6, s.getId_sessao());
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
        sessao.setId_peca(rs.getInt("id_peca"));
        sessao.setData_inicio(rs.getTimestamp("data_inicio").toLocalDateTime());
        sessao.setPreco_sessao(rs.getDouble("preco_sessao"));
        sessao.setNum_ingressos_disp(rs.getInt("num_ingressos_disp"));
        sessao.setId_sala(rs.getInt("id_sala"));
        return sessao;
    }

    public List<Sessao> findAll() throws SQLException {
        String sql = "SELECT * FROM sessao";
        List<Sessao> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sessao s = new Sessao();
                s.setId_sessao(rs.getInt("id_sessao"));
                s.setId_peca(rs.getInt("id_peca"));
                s.setData_inicio(rs.getTimestamp("data_inicio").toLocalDateTime());
                s.setPreco_sessao(rs.getDouble("preco_sessao"));
                s.setNum_ingressos_disp(rs.getInt("num_ingressos_disp"));
                s.setId_sala(rs.getInt("id_sala"));
                list.add(s);
            }
        }
        return list;
    }
}
