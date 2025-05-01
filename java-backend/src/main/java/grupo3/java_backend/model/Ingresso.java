package grupo3.java_backend.model;
import java.time.LocalDateTime;


// Ingresso.java
public class Ingresso {
    private int id_ingresso;
    private int id_assento;
    private String tipo_ingresso;
    private double preco_ingresso;
    private boolean status;
    private int id_cliente;
    private int id_peca;

    public int getId_ingresso() {
        return id_ingresso;
    }

    public void setId_ingresso(int id_ingresso) {
        this.id_ingresso = id_ingresso;
    }

    public int getId_assento() {
        return id_assento;
    }

    public void setId_assento(int id_assento) {
        this.id_assento = id_assento;
    }

    public String getTipo_ingresso() {
        return tipo_ingresso;
    }

    public void setTipo_ingresso(String tipo_ingresso) {
        this.tipo_ingresso = tipo_ingresso;
    }

    public double getPreco_ingresso() {
        return preco_ingresso;
    }

    public void setPreco_ingresso(double preco_ingresso) {
        this.preco_ingresso = preco_ingresso;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public void reservarAssento() {
        // Lógica para reservar assento
    }

    public boolean validarIngresso() {
        // Lógica para validar ingresso
        return status;
    }

    public void liberarIngresso() {
        // Lógica para liberar ingresso
    }
}
