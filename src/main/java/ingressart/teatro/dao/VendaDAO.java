package ingressart.teatro.dao;

import ingressart.teatro.model.Venda;
import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

public class VendaDAO {
    public void insert(Venda v) throws SQLException {
        String sql = "INSERT INTO venda (data_venda, forma_pagamento, id_cliente) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(v.getData_venda()));
            stmt.setString(2, v.getForma_pagamento());
            stmt.setInt(3, v.getId_cliente());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) v.setId_venda(rs.getInt(1));
            }
        }
    }
    // Outros métodos padrão (findById, findAll, update, delete)
}
