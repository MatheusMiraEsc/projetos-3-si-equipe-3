package grupo3.java_backend.model;
import java.time.LocalDateTime;

public class Evento {
    private int id_evento;
    private boolean status; // ativo ou arquivado
    private String nomeEvento;
    private String descricao;
    private LocalDateTime data_inicio;
    private LocalDateTime data_fim;
    private String categoria;
    private int class_indicativa;
    private int id_sala;

    // Getters e Setters
    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getClass_indicativa() {
        return class_indicativa;
    }

    public void setClass_indicativa(int class_indicativa) {
        this.class_indicativa = class_indicativa;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }
}
