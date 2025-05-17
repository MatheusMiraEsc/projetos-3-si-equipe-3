package ingressart.teatro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ingressart.teatro.model.Review;

public class ReviewDAO {
    public void insert(Review r) throws SQLException {
        String sql = "INSERT INTO review (id_cliente, id_peca, rating, comentario, data_review) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, r.getId_cliente());
            stmt.setInt(2, r.getId_peca());
            stmt.setInt(3, r.getRating());
            stmt.setString(4, r.getComentario());
            stmt.setTimestamp(5, Timestamp.valueOf(r.getData_review()));
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    r.setId_review(rs.getInt(1));
                }
            }
        }
    }



    public List<Review> findByPecaId(int idPeca) throws SQLException {
        String sql = "SELECT * FROM review WHERE id_peca = ? ORDER BY data_review DESC";
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPeca);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Review r = new Review();
                    r.setId_review(rs.getInt("id_review"));
                    r.setId_cliente(rs.getInt("id_cliente"));
                    r.setId_peca(rs.getInt("id_peca"));
                    r.setRating(rs.getInt("rating"));
                    r.setComentario(rs.getString("comentario"));
                    r.setData_review(rs.getTimestamp("data_review").toLocalDateTime());
                    reviews.add(r);
                }
            }
        }
        return reviews;
    }

    public List<Review> findByClienteId(int idCliente) throws SQLException {
        String sql = "SELECT * FROM review WHERE id_cliente = ? ORDER BY data_review DESC";
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Review r = new Review();
                    r.setId_review(rs.getInt("id_review"));
                    r.setId_cliente(rs.getInt("id_cliente"));
                    r.setId_peca(rs.getInt("id_peca"));
                    r.setRating(rs.getInt("rating"));
                    r.setComentario(rs.getString("comentario"));
                    r.setData_review(rs.getTimestamp("data_review").toLocalDateTime());
                    reviews.add(r);
                }
            }
        }
        return reviews;
    }
    public void delete(int idReview) throws SQLException {
        String sql = "DELETE FROM review WHERE id_review = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idReview);
            stmt.executeUpdate();
        }
    }
}
