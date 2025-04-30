package grupo3.java_backend.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Peca {
    private int id_peca;
    private String nome;
    private String descricao;
    private LocalDate data;
    private LocalTime hora;
    private double valor_ingresso;

    // Getters and Setters
    public int getId_peca() {
        return id_peca;
    }

    public void setId_peca(int id_peca) {
        this.id_peca = id_peca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getValor_ingresso() {
        return valor_ingresso;
    }

    public void setValor_ingresso(double valor_ingresso) {
        this.valor_ingresso = valor_ingresso;
    }
} 