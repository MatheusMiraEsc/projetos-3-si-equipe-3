package grupo3.java_backend.model;
import java.time.LocalDateTime;

public class Sessao {
    private int id_sessao;
    private int id_peca;
    private LocalDateTime data_inicio;
    private double preco_sessao;
    private int num_ingressos_disp;
    private int id_sala;

    // Getters and Setters
    public int getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(int id_sessao) {
        this.id_sessao = id_sessao;
    }

    public int getId_peca() {
        return id_peca;
    }

    public void setId_peca(int id_peca) {
        this.id_peca = id_peca;
    }

    public LocalDateTime getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    public double getPreco_sessao() {
        return preco_sessao;
    }

    public void setPreco_sessao(double preco_sessao) {
        this.preco_sessao = preco_sessao;
    }

    public int getNum_ingressos_disp() {
        return num_ingressos_disp;
    }

    public void setNum_ingressos_disp(int num_ingressos_disp) {
        this.num_ingressos_disp = num_ingressos_disp;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }
}
