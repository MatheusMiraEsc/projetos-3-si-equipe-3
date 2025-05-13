package ingressart.teatro.model;

import java.time.LocalDateTime;

public class Review {
    private int id_review;
    private int id_cliente;
    private int id_peca;
    private int rating;
    private String comentario;
    private LocalDateTime data_review;

    public int getId_review() {
        return id_review;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_peca() {
        return id_peca;
    }

    public void setId_peca(int id_peca) {
        this.id_peca = id_peca;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getData_review() {
        return data_review;
    }

    public void setData_review(LocalDateTime data_review) {
        this.data_review = data_review;
    }
}