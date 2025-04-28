package grupo3.java_backend.dao;

import grupo3.java_backend.model.Ingresso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO {
    public void insert(Ingresso i) throws SQLException {
        String sql = "INSERT INTO ingresso (id_assento, tipo_ingresso, preco_ingresso, status, id_cliente, id_sessao) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, i.getId_assento());
            stmt.setString(2, i.getTipo_ingresso());
            stmt.setFloat(3, i.getPreco_ingresso());
            stmt.setBoolean(4, i.getStatus());
            stmt.setInt(5, i.getId_cliente());
            stmt.setInt(6, i.getId_sessao());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) i.setId_ingresso(rs.getInt(1));
            }
        }
    }
    // Métodos findById, findAll, update, delete seguem padrão semelhante.
}

