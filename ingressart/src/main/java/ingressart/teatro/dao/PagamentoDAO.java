package ingressart.teatro.dao;


import ingressart.teatro.model.Pagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    public void insert(Pagamento p) throws SQLException {
        String sql = "INSERT INTO pagamento (id_venda, valor_pago, data_pagamento, status, metodo_pagamento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, p.getId_venda());
            stmt.setFloat(2, p.getValor_pago());
            stmt.setTimestamp(3, Timestamp.valueOf(p.getData_pagamento()));
            stmt.setString(4, p.getStatus());
            stmt.setString(5, p.getMetodo_pagamento());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) p.setId_pagamento(rs.getInt(1));
            }
        }
    }
    // Outros métodos padrão (findById, findAll, update, delete)
}
