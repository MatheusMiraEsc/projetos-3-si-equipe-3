package grupo3.java_backend.model;
import java.time.LocalDateTime;

public class Sessao {
    private int id_sessao;
    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;
    private float preco_sessao;
    private int num_ingressos_disp;
    private int id_evento;
    private int id_sala;

    // Getters e Setters
    public int getId_sessao() {
        return id_sessao;
    }

    public void setId_sessao(int id_sessao) {
        this.id_sessao = id_sessao;
    }

    public LocalDateTime getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDateTime data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDateTime getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDateTime data_fim) {
        this.data_fim = data_fim;
    }

    public float getPreco_sessao() {
        return preco_sessao;
    }

    public void setPreco_sessao(float preco_sessao) {
        this.preco_sessao = preco_sessao;
    }

    public int getNum_ingressos_disp() {
        return num_ingressos_disp;
    }

    public void setNum_ingressos_disp(int num_ingressos_disp) {
        this.num_ingressos_disp = num_ingressos_disp;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }
}
