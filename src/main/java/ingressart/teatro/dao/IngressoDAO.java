package ingressart.teatro.dao;
import java.util.Map;
import java.util.HashMap;
import ingressart.teatro.model.Ingresso;
import ingressart.teatro.model.Sessao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ingressart.teatro.util.Conexao;


public class IngressoDAO {
    public void insert(Ingresso i) throws SQLException {
        String sql = "INSERT INTO ingresso (id_assento, tipo_ingresso, preco_ingresso, status, id_cliente, id_peca) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, i.getId_assento());
            stmt.setString(2, i.getTipo_ingresso());
            stmt.setDouble(3, i.getPreco_ingresso());
            stmt.setBoolean(4, i.isStatus());
            stmt.setInt(5, i.getId_cliente());
            stmt.setInt(6, i.getId_peca());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) i.setId_ingresso(rs.getInt(1));
            }
        }
    }

    public Ingresso findById(int id) throws SQLException {
        String sql = "SELECT * FROM ingresso WHERE id_ingresso = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Ingresso i = new Ingresso();
                    i.setId_ingresso(rs.getInt("id_ingresso"));
                    i.setId_assento(rs.getInt("id_assento"));
                    i.setTipo_ingresso(rs.getString("tipo_ingresso"));
                    i.setPreco_ingresso(rs.getDouble("preco_ingresso"));
                    i.setStatus(rs.getBoolean("status"));
                    i.setId_cliente(rs.getInt("id_cliente"));
                    i.setId_peca(rs.getInt("id_peca"));
                    return i;
                }
            }
        }
        return null;
    }

    public List<Ingresso> findByClienteId(int clienteId) throws SQLException {
        List<Ingresso> ingressos = new ArrayList<>();
        String sql = "SELECT * FROM ingresso WHERE id_cliente = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
    
            while (rs.next()) {
                Ingresso ingresso = new Ingresso();
                ingresso.setId_ingresso(rs.getInt("id_ingresso"));
                ingresso.setId_cliente(rs.getInt("id_cliente"));
                ingresso.setId_peca(rs.getInt("id_peca"));
                ingresso.setTipo_ingresso(rs.getString("tipo_ingresso"));
                ingresso.setPreco_ingresso(rs.getDouble("preco_ingresso"));
                ingresso.setStatus(rs.getBoolean("status"));
                ingressos.add(ingresso);
            }
        }
    
        return ingressos;
    }
    public List<Ingresso> findAll() throws SQLException {
        String sql = "SELECT * FROM ingresso";
        List<Ingresso> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ingresso i = new Ingresso();
                i.setId_ingresso(rs.getInt("id_ingresso"));
                i.setId_assento(rs.getInt("id_assento"));
                i.setTipo_ingresso(rs.getString("tipo_ingresso"));
                i.setPreco_ingresso(rs.getDouble("preco_ingresso"));
                i.setStatus(rs.getBoolean("status"));
                i.setId_cliente(rs.getInt("id_cliente"));
                i.setId_peca(rs.getInt("id_peca"));
                list.add(i);
            }
        }
        return list;
    }
    

    public void update(Ingresso i) throws SQLException {
        String sql = "UPDATE ingresso SET id_assento=?, tipo_ingresso=?, preco_ingresso=?, status=?, id_cliente=?, id_peca=? WHERE id_ingresso=?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, i.getId_assento());
            stmt.setString(2, i.getTipo_ingresso());
            stmt.setDouble(3, i.getPreco_ingresso());
            stmt.setBoolean(4, i.isStatus());
            stmt.setInt(5, i.getId_cliente());
            stmt.setInt(6, i.getId_peca());
            stmt.setInt(7, i.getId_ingresso());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM ingresso WHERE id_ingresso = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public Map<Integer, Integer> contarIngressosPorCliente(int idPeca) throws SQLException{
        String sql = "SELECT id_cliente, COUNT(*) AS total FROM ingresso WHERE id_peca = ? GROUP BY id_cliente";
        Map<Integer, Integer> mapa = new HashMap<>();
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, idPeca);
                ResultSet rs = stmt .executeQuery();
                while(rs.next()){
                    mapa.put(rs.getInt("id_cliente"), rs.getInt("total"));
                }
            }
            return mapa;
    }
}